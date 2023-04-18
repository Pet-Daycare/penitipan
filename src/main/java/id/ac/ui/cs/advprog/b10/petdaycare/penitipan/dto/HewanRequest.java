package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HewanRequest {
    private Integer id;
    private String nama;
    private String tipeHewan;
    private String beratHewan;
    private String pesanPenitipan;
    private Date tanggalPenitipan;
    private Date tanggalPengambilan;
}
