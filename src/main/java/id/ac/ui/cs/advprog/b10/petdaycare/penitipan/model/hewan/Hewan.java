package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hewan {
    @Id
    @GeneratedValue
    private Integer id;

    private String nama;
    private String tipeHewan;
    private String beratHewan;
}
