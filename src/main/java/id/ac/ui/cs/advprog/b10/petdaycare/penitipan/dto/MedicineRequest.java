package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineRequest {
    private String name;
    private String category;
    private String dose;
    private Integer stock;
    private Integer price;
    private String manufacturer;
}
