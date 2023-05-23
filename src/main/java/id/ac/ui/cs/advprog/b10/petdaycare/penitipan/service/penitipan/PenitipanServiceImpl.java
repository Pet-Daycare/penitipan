package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan;


import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.auth.AuthTransactionDto;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanAdminResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanUserResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.HewanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.auth.CustomerRequest;
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
        String otherInstanceURL = "http://localhost:8080/api/v1/auth/verify-token/"+token; // TODO : Change to main url
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

    public AuthTransactionDto getAuthTransactionDto(CustomerRequest request){
        CompletableFuture<AuthTransactionDto> futureDto = CompletableFuture.supplyAsync(
                getAuthTransactionDtoSupplier(request.getToken())
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
    public List<PenitipanUserResponse> findAllByUserId(Integer userId) {
        return penitipanRepository.findAllByUserId(userId)
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
    public Penitipan create(Integer userId, PenitipanRequest penitipanRequest) {
        Integer idHewan = penitipanRequest.getHewan().getId();
        if (isHewanDoesNotExist(idHewan)){
            throw new HewanDoesNotExistException(idHewan);
        }
        var penitipan = Penitipan.builder()
                .userId(userId)
                .hewan(penitipanRequest.getHewan())
                .tanggalPenitipan(penitipanRequest.getTanggalPenitipan())
                .tanggalPengambilan(penitipanRequest.getTanggalPengambilan())
                .statusPenitipan(StatusPenitipan.UNVERIFIED_PENITIPAN)
                .pesanPenitipan(penitipanRequest.getPesanPenitipan())
                .build();
        penitipanRepository.save(penitipan);
        return penitipan;
    }

    @Override
    public Penitipan update(Integer userId, Integer id, PenitipanRequest penitipanRequest) {
        if (isPenitipanDoesNotExist(id)) {
            throw new PenitipanDoesNotExistException(id);
        }
        Integer idHewan = penitipanRequest.getHewan().getId();
        if (isHewanDoesNotExist(idHewan)){
            throw new HewanDoesNotExistException(idHewan);
        }
        var penitipan = Penitipan.builder()
                .id(id)
                .userId(userId)
                .hewan(penitipanRequest.getHewan())
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
    public Penitipan verify(Integer userId, Integer id){
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
    public Penitipan ambilHewan(Integer userId, Integer id){
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

    private boolean isHewanDoesNotExist(Integer id){
        return hewanRepository.findById(id).isEmpty();
    }
}
