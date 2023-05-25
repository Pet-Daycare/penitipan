package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.payment;


import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    Double calculatePrice(Penitipan penitipan);
}
