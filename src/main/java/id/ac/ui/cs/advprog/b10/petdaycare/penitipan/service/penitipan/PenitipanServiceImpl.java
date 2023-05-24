package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.auth.AuthTransactionDto;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanAdminResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanUserResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanWithHewanIdDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.TipeHewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.HewanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.PenitipanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
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
    private final PaymentService paymentService;


    private AuthTransactionDto verifyToken(String token){
        String otherInstanceURL = "http://localhost:8082/api/v1/auth/verify-token/"+token; // TODO : Change to main url
        return restTemplate.getForObject((otherInstanceURL), AuthTransactionDto.class);
    }

    private Supplier<AuthTransactionDto> getAuthTransactionDtoSupplier(String token) {
        return () -> verifyToken(token);

    }

    public AuthTransactionDto getAuthTransactionDto(PenitipanRequest request){
        CompletableFuture<AuthTransactionDto> futureDto = CompletableFuture.supplyAsync(
                getAuthTransactionDtoSupplier(request.getUserToken())
        );

        return futureDto.join();
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
                .tipeHewan(TipeHewan.valueOf(penitipanRequest.getTipeHewan()))
                .build();
        hewanRepository.save(hewan);
        AuthTransactionDto dto = getAuthTransactionDto(penitipanRequest);
        Integer userId = dto.getIdCustomer();
        var penitipan = Penitipan.builder()
                .userId(userId)
                .hewan(hewan)
                .tanggalPenitipan(penitipanRequest.getTanggalPenitipan())
                .tanggalPengambilan(penitipanRequest.getTanggalPengambilan())
                .statusPenitipan(StatusPenitipan.UNVERIFIED_PENITIPAN)
                .pesanPenitipan(penitipanRequest.getPesanPenitipan())
                .build();
        penitipan.setInitialCost(paymentService.calculatePrice(penitipan));
        penitipanRepository.save(penitipan);
        return penitipan;
    }

    @Override
    public Penitipan update(Integer id, PenitipanRequest penitipanRequest) {
        var hewan = Hewan.builder()
                .nama(penitipanRequest.getNamaHewan())
                .beratHewan(penitipanRequest.getBeratHewan())
                .tipeHewan(TipeHewan.valueOf(penitipanRequest.getTipeHewan()))
                .build();
        hewanRepository.save(hewan);
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
        penitipan.setInitialCost(paymentService.calculatePrice(penitipan));
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
    public Penitipan cancel(Integer penitipanId) {
        Penitipan penitipan = findById(penitipanId);
        penitipan.setStatusPenitipan(StatusPenitipan.CANCELED_PENITIPAN);
        penitipanRepository.save(penitipan);

        return penitipan;
    }

    @Override
    public Penitipan findByHewanId(Integer hewanId) {
        Optional<Penitipan> optionalPenitipan = penitipanRepository.findByHewanId(hewanId);
        if (optionalPenitipan.isEmpty()) {
            throw new PenitipanWithHewanIdDoesNotExistException(hewanId);
        }
        return optionalPenitipan.get();
    }

    @Override
    public List<PenitipanAdminResponse> findAllByStatus(StatusPenitipan statusPenitipan) {
        return penitipanRepository.findAll()
                .stream()
                .map(PenitipanAdminResponse::fromPenitipan)
                .toList();
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
    public Penitipan complete(Integer penitipanId) {
        Penitipan penitipan = findById(penitipanId);
        penitipan.setStatusPenitipan(StatusPenitipan.COMPLETED_PENITIPAN);
        penitipanRepository.save(penitipan);
        return penitipan;
    }

    @Override
    public Penitipan verifyPayment(Integer id){
        Penitipan penitipan = findById(id);
        penitipan.setStatusPenitipan(StatusPenitipan.VERIFIED_PENITIPAN);
        penitipanRepository.save(penitipan);

        return penitipan;
    }

    @Override
    public Penitipan ambilHewan(Integer id){
        Penitipan penitipan = findById(id);

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime supposedReturnDate = penitipan.getTanggalPengambilan();
        if (currentDate.isAfter(supposedReturnDate)){
            penitipan.setStatusPenitipan(StatusPenitipan.PENGAMBILAN_TERLAMBAT);
        } else if (currentDate.equals(supposedReturnDate)) {
            penitipan.setStatusPenitipan(StatusPenitipan.PENGAMBILAN_TEPAT);
        } else if (currentDate.isBefore(supposedReturnDate)){
            penitipan.setStatusPenitipan(StatusPenitipan.PENGAMBILAN_AWAL);
        }
        penitipan.setTanggalDiambil(currentDate);
        penitipanRepository.save(penitipan);
        return penitipan;
    }

    @Override
    public Penitipan payComplete(Integer penitipanId) {
        Penitipan penitipan = findById(penitipanId);
        penitipan.setCompletionCost(paymentService.calculatePrice(penitipan));
        penitipanRepository.save(penitipan);
        return penitipan;
    }

    private boolean isPenitipanDoesNotExist(Integer id) {
        return penitipanRepository.findById(id).isEmpty();
    }

}
