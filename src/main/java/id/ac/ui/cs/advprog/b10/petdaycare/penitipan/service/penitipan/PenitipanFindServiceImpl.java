package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.auth.AuthTransactionDto;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanAdminResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanUserResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanWithHewanIdDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.HewanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.PenitipanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class PenitipanFindServiceImpl implements PenitipanFindService{
    private final PenitipanRepository penitipanRepository;
    private final HewanRepository hewanRepository;
    private final RestTemplate restTemplate;
    private final PaymentService paymentService;

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
    public List<PenitipanAdminResponse> findAll() {
        return penitipanRepository.findAll()
                .stream()
                .map(PenitipanAdminResponse::fromPenitipan)
                .toList();
    }

    @Override
    public List<PenitipanUserResponse> findAllByUserId(PenitipanRequest request) {
        return penitipanRepository.findAllByUserId(request.getUserId())
                .stream()
                .map(PenitipanUserResponse::fromPenitipan)
                .toList();
    }

    @Override
    public Penitipan findPenitipanById(Integer id) throws PenitipanDoesNotExistException{
        Optional<Penitipan> optionalPenitipan = penitipanRepository.findById(id);
        if (optionalPenitipan.isEmpty()) {
            throw new PenitipanDoesNotExistException(id);
        }
        return optionalPenitipan.get();
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
        return penitipanRepository.findAllByUserIdAndStatusPenitipan(userId, statusPenitipan)
                .stream()
                .map(PenitipanUserResponse::fromPenitipan)
                .toList();
    }

    @Override
    public List<PenitipanAdminResponse> findAllByHewanIdAndStatus(Integer hewanId, StatusPenitipan statusPenitipan) {
        return penitipanRepository.findAllByHewanIdAndStatusPenitipan(hewanId, statusPenitipan)
                .stream()
                .map(PenitipanAdminResponse::fromPenitipan)
                .toList();
    }

}
