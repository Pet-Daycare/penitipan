package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanAdminResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanUserResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PenitipanService {
    List<PenitipanAdminResponse> findAll();
    List<PenitipanUserResponse> findAllByUserId(Integer userId);

    Penitipan findById(Integer id);
    Penitipan create(Integer userId, PenitipanRequest request);
    Penitipan update(Integer userId, Integer id, PenitipanRequest request);
    void delete(Integer id);
}
