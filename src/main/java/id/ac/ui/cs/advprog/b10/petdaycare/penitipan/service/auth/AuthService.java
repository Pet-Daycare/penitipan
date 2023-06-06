package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.auth;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.auth.AuthTransactionDto;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
@Service
@RequiredArgsConstructor
public class AuthService {
    private final RestTemplate restTemplate;

    public Integer getUserId(PenitipanRequest penitipanRequest) {
        return getAuthTransactionDto(penitipanRequest).getIdCustomer();
    }

    public AuthTransactionDto verifyToken(String token) {
        String otherInstanceURL = "http://localhost:8082/api/v1/auth/verify-token/" + token; // TODO : Change to main url
        return restTemplate.getForObject((otherInstanceURL), AuthTransactionDto.class);
    }

    public Supplier<AuthTransactionDto> getAuthTransactionDtoSupplier(String token) {
        return () -> verifyToken(token);

    }

    public AuthTransactionDto getAuthTransactionDto(PenitipanRequest request) {
        CompletableFuture<AuthTransactionDto> futureDto = CompletableFuture.supplyAsync(
                getAuthTransactionDtoSupplier(request.getUserToken())
        );

        return futureDto.join();
    }
}