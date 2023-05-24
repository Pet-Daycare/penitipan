package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.TipeHewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PenitipanRequest {
    private Integer penitipanId;
    private Integer hewanId;
    private String namaHewan;
    private TipeHewan tipeHewan;
    private Integer beratHewan;
    private String pesanPenitipan;
    private LocalDateTime tanggalPenitipan;
    private LocalDateTime tanggalPengambilan;
    private LocalDateTime tanggalDiambil;
    private StatusPenitipan statusPenitipan;
    private Integer userId;
    private String userToken;
}
