package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.pembayaran;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private Integer idPenitipan;

    private String username;
    private String token;
    private double total;
}