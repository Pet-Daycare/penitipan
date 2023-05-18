package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    // TODO : Hubungkan dengan user yang melakukan penitipan melalui auth
    @ManyToOne
    @JoinColumn(name = "_user_id", nullable = false)
    //private User user;

    // TODO : Pastikan asumsi satu penitipan hanya menitipkan satu hewan benar
    private Hewan hewan;
    @Enumerated(EnumType.STRING)
    private StatusPenitipan statusPenitipan;
    private String pesanPenitipan;
    private Date tanggalPenitipan;
    private Date tanggalPengambilan;
    private Date tanggalDiambil;
}
