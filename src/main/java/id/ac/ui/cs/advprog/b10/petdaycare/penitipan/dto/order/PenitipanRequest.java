package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PenitipanRequest {
    private Integer penitipanId;
    private Integer hewanId;
    private String namaHewan;
    private String tipeHewan;
    private String beratHewan;
    private String pesanPenitipan;
    private Date tanggalPenitipan;
    private Date tanggalPengambilan;
    private Integer userId;
    private String userToken;
}
