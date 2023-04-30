package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PenitipanService {
    List<Penitipan> findAll();
    Penitipan findById(Integer id);
    Penitipan create(PenitipanRequest request);
    Penitipan update(Integer id, PenitipanRequest request);
    void delete(Integer id);
}
