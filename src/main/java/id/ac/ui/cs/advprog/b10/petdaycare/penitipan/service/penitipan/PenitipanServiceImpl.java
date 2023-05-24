package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan;


import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.auth.AuthTransactionDto;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanAdminResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanUserResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.HewanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.HewanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.PenitipanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class PenitipanServiceImpl implements PenitipanService{
    private final PenitipanRepository penitipanRepository;
    private final HewanRepository hewanRepository;
    private final RestTemplate restTemplate;

    private AuthTransactionDto verifyToken(String token) throws InterruptedException{
        String otherInstanceURL = "http://localhost:8082/api/v1/auth/verify-token/"+token; // TODO : Change to main url
        return restTemplate.getForObject((otherInstanceURL), AuthTransactionDto.class);
    }

    private Supplier<AuthTransactionDto> getAuthTransactionDtoSupplier(String token) {
        return () -> {
            try {
                return verifyToken(token);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        };
    }

    public AuthTransactionDto getAuthTransactionDto(PenitipanRequest request){
        CompletableFuture<AuthTransactionDto> futureDto = CompletableFuture.supplyAsync(
                getAuthTransactionDtoSupplier(request.getUserToken())
        );

        AuthTransactionDto dto = futureDto.join();
        System.out.println(dto.getIdCustomer());

        System.out.println(dto.getUsername());
        return dto;
    }

    @Override
    public List<PenitipanAdminResponse> findAll() {
        return penitipanRepository.findAll()
                .stream()
                .map(PenitipanAdminResponse::fromPenitipan)
                .toList();
    }

    @Override
    public List<PenitipanUserResponse> findAllByUserId(PenitipanRequest request) {
        return penitipanRepository.findAllByUserId(getAuthTransactionDto(request).getIdCustomer())
                .stream()
                .map(PenitipanUserResponse::fromPenitipan)
                .toList();
    }

    @Override
    public Penitipan findById(Integer id) {
        Optional<Penitipan> optionalPenitipan = penitipanRepository.findById(id);
        if (optionalPenitipan.isEmpty()) {
            throw new PenitipanDoesNotExistException(id);
        }
        return optionalPenitipan.get();
    }

    @Override
    public Penitipan create(PenitipanRequest penitipanRequest) {
        var hewan = Hewan.builder()
                .nama(penitipanRequest.getNamaHewan())
                .beratHewan(penitipanRequest.getBeratHewan())
                .tipeHewan(penitipanRequest.getTipeHewan())
                .build();
        hewanRepository.save(hewan);
        Integer idHewan = hewan.getId();
        Integer userId = getAuthTransactionDto(penitipanRequest).getIdCustomer();
        var penitipan = Penitipan.builder()
                .userId(userId)
                .hewan(hewan)
                .tanggalPenitipan(penitipanRequest.getTanggalPenitipan())
                .tanggalPengambilan(penitipanRequest.getTanggalPengambilan())
                .statusPenitipan(StatusPenitipan.UNVERIFIED_PENITIPAN)
                .pesanPenitipan(penitipanRequest.getPesanPenitipan())
                .build();
        penitipanRepository.save(penitipan);
        return penitipan;
    }

    @Override
    public Penitipan update(Integer id, PenitipanRequest penitipanRequest) {
        var hewan = Hewan.builder()
                .nama(penitipanRequest.getNamaHewan())
                .beratHewan(penitipanRequest.getBeratHewan())
                .tipeHewan(penitipanRequest.getTipeHewan())
                .build();
        hewanRepository.save(hewan);
        Integer idHewan = hewan.getId();
        Integer userId = getAuthTransactionDto(penitipanRequest).getIdCustomer();
        var penitipan = Penitipan.builder()
                .id(id)
                .userId(userId)
                .hewan(hewan)
                .tanggalPenitipan(penitipanRequest.getTanggalPenitipan())
                .tanggalPengambilan(penitipanRequest.getTanggalPengambilan())
                .statusPenitipan(StatusPenitipan.UNVERIFIED_PENITIPAN)
                .pesanPenitipan(penitipanRequest.getPesanPenitipan())
                .build();
        penitipanRepository.save(penitipan);

        return penitipan;
    }

    @Override
    public void delete(Integer id) {
        if (isPenitipanDoesNotExist(id)){
            throw new PenitipanDoesNotExistException(id);
        }
        penitipanRepository.deleteById(id);
    }

    @Override
    public Penitipan cancel(PenitipanRequest penitipanRequest) {
        Optional<Penitipan> optionalPenitipan = penitipanRepository.findById(penitipanRequest.getPenitipanId());
        if (optionalPenitipan.isEmpty()) {
            throw new PenitipanDoesNotExistException(penitipanRequest.getPenitipanId());
        }
        Penitipan penitipan = optionalPenitipan.get();
        penitipan.setStatusPenitipan(StatusPenitipan.CANCELED_PENITIPAN);
        penitipanRepository.save(penitipan);

        return penitipan;
    }

    @Override
    public List<PenitipanAdminResponse> findAllByHewanId(Integer hewanId) {
        // TODO: Find all penitipan by hewan id
        return null;
    }

    @Override
    public List<Penitipan> findAllByStatus(StatusPenitipan statusPenitipan) {
        // TODO: Find all penitipan by status
        return null;
    }

    @Override
    public List<PenitipanUserResponse> findAllByUserIdAndStatus(Integer userId, StatusPenitipan statusPenitipan) {
        // TODO: Find all penitipan by user id and status
        return null;
    }

    @Override
    public List<PenitipanAdminResponse> findAllByHewanIdAndStatus(Integer hewanId, StatusPenitipan statusPenitipan) {
        // TODO: Find all penitipan by hewan id and status
        return null;
    }

    @Override
    public Penitipan complete(Integer userId, Integer penitipanId) {
        // TODO: Complete penitipan
        return null;
    }

    @Override
    public Penitipan verify(Integer id){
        Optional<Penitipan> optionalPenitipan = penitipanRepository.findById(id);
        if (optionalPenitipan.isEmpty()) {
            throw new PenitipanDoesNotExistException(id);
        }
        Penitipan penitipan = optionalPenitipan.get();
        penitipan.setStatusPenitipan(StatusPenitipan.VERIFIED_PENITIPAN);
        penitipanRepository.save(penitipan);

        return penitipan;
    }

    @Override
    public Penitipan ambilHewan(Integer id){
        Optional<Penitipan> optionalPenitipan = penitipanRepository.findById(id);
        if (optionalPenitipan.isEmpty()) {
            throw new PenitipanDoesNotExistException(id);
        }
        Penitipan penitipan = optionalPenitipan.get();

        Date currentDate = new Date();
        Date supposedReturnDate = penitipan.getTanggalPengambilan();
        if (currentDate.after(supposedReturnDate)){
            penitipan.setStatusPenitipan(StatusPenitipan.PENGAMBILAN_TERLAMBAT);
        } else if (currentDate.equals(supposedReturnDate)) {
            penitipan.setStatusPenitipan(StatusPenitipan.PENGAMBILAN_TEPAT);
        }
        else{
            penitipan.setStatusPenitipan(StatusPenitipan.PENGAMBILAN_AWAL);
        }
        penitipan.setTanggalDiambil(currentDate);
        penitipanRepository.save(penitipan);
        return penitipan;
    }

    private boolean isPenitipanDoesNotExist(Integer id) {
        return penitipanRepository.findById(id).isEmpty();
    }

}
