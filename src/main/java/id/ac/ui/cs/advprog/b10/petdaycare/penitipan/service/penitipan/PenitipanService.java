package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import org.springframework.stereotype.Service;

@Service
public interface PenitipanService {
    Integer getUserId(PenitipanRequest penitipanRequest);
    Penitipan create(PenitipanRequest penitipanRequest);
    Penitipan update(Integer id, PenitipanRequest request);
    Penitipan verifyPayment(Integer id);
    Penitipan ambilHewan(Integer id);
    void delete(Integer id);
    Penitipan cancel(Integer penitipanId);
    Penitipan complete(Integer penitipanId);
    Penitipan payComplete(Integer penitipanId);
}
