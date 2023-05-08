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
    private Hewan hewan;
    private StatusPenitipan statusPenitipan;
    private String pesanPenitipan;
    private Date tanggalPenitipan;
    private Date tanggalPengambilan;
    private Date tanggalDiambil;
}
