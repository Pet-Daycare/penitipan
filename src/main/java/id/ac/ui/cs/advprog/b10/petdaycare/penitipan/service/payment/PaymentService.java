package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    Double calculateInitialPrice(PenitipanRequest penitipanRequest);
    Double calculateAmbilPrice(PenitipanRequest penitipanRequest);
}
