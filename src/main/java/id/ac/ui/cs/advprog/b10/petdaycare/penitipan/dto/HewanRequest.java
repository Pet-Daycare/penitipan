package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.TipeHewan;
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
    private TipeHewan tipeHewan;
    private Integer beratHewan;
}
