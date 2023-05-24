package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "penitipan_hewan")
public class Penitipan {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;

    @OneToOne
    private Hewan hewan;
    @Enumerated(EnumType.STRING)
    private StatusPenitipan statusPenitipan;
    private String pesanPenitipan;
    private LocalDateTime tanggalPenitipan;
    private LocalDateTime tanggalPengambilan;
    private LocalDateTime tanggalDiambil;
    private Double initialCost;
    private Double completionCost;
}
