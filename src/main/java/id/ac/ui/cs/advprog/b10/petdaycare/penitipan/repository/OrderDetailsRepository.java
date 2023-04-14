package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    @NonNull
    List<OrderDetails> findAll();
    List<OrderDetails> findAllByOrderId(Integer orderId);
    Optional<OrderDetails> findByOrderIdAndMedicineId(Integer orderId, Integer medicineId);
}
