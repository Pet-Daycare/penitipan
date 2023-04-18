package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.medicine.MedicineCategory;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.OrderDetails;
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
public class Hewan {
    @Id
    @GeneratedValue
    private Integer id;
    private String nama;
    private String tipeHewan;
    private String beratHewan;
    private String pesanPenitipan;
    private Date tanggalPenitipan;
    private Date tanggalPengambilan;
}
