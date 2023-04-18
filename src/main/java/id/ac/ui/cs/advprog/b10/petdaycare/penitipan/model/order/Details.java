package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
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
public class Details {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne
    private Order order;
    @OneToOne
    private Hewan hewan;
    private Integer quantity;
    private Integer totalPrice;
}
