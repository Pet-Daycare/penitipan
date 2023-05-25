package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.auth.AuthTransactionDto;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanDoesNotHaveHewanException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.TipeHewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.HewanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.PenitipanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.hewan.HewanService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class PenitipanServiceImpl implements PenitipanService{
    private final PenitipanRepository penitipanRepository;
    private final HewanRepository hewanRepository;
    private final RestTemplate restTemplate;
    private final PaymentService paymentService;
    private final PenitipanFindService penitipanFindService;
    private final HewanService hewanService;

    @Override
    public Integer getUserId(PenitipanRequest penitipanRequest) {
        return getAuthTransactionDto(penitipanRequest).getIdCustomer();
    }

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
    public Penitipan create(PenitipanRequest penitipanRequest) {
        var hewan = Hewan.builder()
                .nama(penitipanRequest.getNamaHewan())
                .beratHewan(penitipanRequest.getBeratHewan())
                .tipeHewan(TipeHewan.valueOf(penitipanRequest.getTipeHewan()))
                .build();
        hewanRepository.save(hewan);
        Integer userId = getUserId(penitipanRequest);
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
    public Penitipan update(Integer id, PenitipanRequest penitipanRequest) throws PenitipanDoesNotHaveHewanException{
        Penitipan penitipan = penitipanFindService.findPenitipanById(id);
        if (penitipan.getHewan() == null){
            throw new PenitipanDoesNotHaveHewanException(id);
        }
        Integer hewanId = penitipan.getHewan().getId();
        Hewan hewan = hewanService.findById(hewanId);
        hewan.setNama(penitipanRequest.getNamaHewan());
        hewan.setBeratHewan(penitipanRequest.getBeratHewan());
        hewan.setTipeHewan(TipeHewan.valueOf(penitipanRequest.getTipeHewan()));
        hewanRepository.save(hewan);
        Integer userId = penitipan.getUserId();
        penitipan = Penitipan.builder()
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
        Penitipan penitipan = penitipanFindService.findPenitipanById(penitipanId);
        penitipan.setStatusPenitipan(StatusPenitipan.CANCELED_PENITIPAN);
        penitipanRepository.save(penitipan);

        return penitipan;
    }
    @Override
    public Penitipan complete(Integer penitipanId) {
        Penitipan penitipan = penitipanFindService.findPenitipanById(penitipanId);
        penitipan.setStatusPenitipan(StatusPenitipan.COMPLETED_PENITIPAN);
        penitipanRepository.save(penitipan);
        Integer hewanId = penitipan.getHewan().getId();
        hewanRepository.deleteById(hewanId);
        return penitipan;
    }

    @Override
    public Penitipan verifyPayment(Integer id){
        Penitipan penitipan = penitipanFindService.findPenitipanById(id);
        penitipan.setStatusPenitipan(StatusPenitipan.VERIFIED_PENITIPAN);
        penitipanRepository.save(penitipan);

        return penitipan;
    }

    @Override
    public Penitipan ambilHewan(Integer id){
        Penitipan penitipan = penitipanFindService.findPenitipanById(id);

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
        Penitipan penitipan = penitipanFindService.findPenitipanById(penitipanId);
        penitipan.setCompletionCost(paymentService.calculatePrice(penitipan));
        penitipanRepository.save(penitipan);
        return penitipan;
    }

    private boolean isPenitipanDoesNotExist(Integer id) {
        return penitipanRepository.findById(id).isEmpty();
    }
}
