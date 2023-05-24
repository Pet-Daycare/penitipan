package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order;

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
    private String tipeHewan;
    private Integer beratHewan;
    private String pesanPenitipan;
    private LocalDateTime tanggalPenitipan;
    private LocalDateTime tanggalPengambilan;
    private LocalDateTime tanggalDiambil;
    private String statusPenitipan;
    private Integer userId;
    private String userToken;
}
