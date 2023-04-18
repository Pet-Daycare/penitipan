package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.hewan;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.HewanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HewanService {
    List<Hewan> findAll();
    Hewan findById(Integer id);
    Hewan create(HewanRequest request);
    Hewan update(Integer id, HewanRequest request);
    void delete(Integer id);
}
