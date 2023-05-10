package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HewanRequest {
    private String nama;
    private String tipeHewan;
    private String beratHewan;
}
