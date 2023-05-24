package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "petwallet")
public class PetWallet {
    @Id
    @GeneratedValue
    private Integer id;
    private int balance;
}
