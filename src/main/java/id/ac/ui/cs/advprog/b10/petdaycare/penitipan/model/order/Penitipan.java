package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    private Date tanggalPenitipan;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "_user_id", nullable = false)
    private User user;
}
