package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PenitipanUserResponse {
    private Integer penitipanId;
    private Hewan hewan;
    private StatusPenitipan statusPenitipan;
    private String pesanPenitipan;
    private LocalDateTime tanggalPenitipan;
    private LocalDateTime tanggalPengambilan;
    private LocalDateTime tanggalDiambil;
    private Double initialCost;
    private Double completionCost;

    public static PenitipanUserResponse fromPenitipan(Penitipan penitipan) {
        return PenitipanUserResponse.builder()
                .penitipanId(penitipan.getId())
                .hewan(penitipan.getHewan())
                .tanggalPenitipan(penitipan.getTanggalPenitipan())
                .tanggalPengambilan(penitipan.getTanggalPengambilan())
                .tanggalDiambil(penitipan.getTanggalDiambil())
                .pesanPenitipan(penitipan.getPesanPenitipan())
                .statusPenitipan(penitipan.getStatusPenitipan())
                .build();
    }
}
