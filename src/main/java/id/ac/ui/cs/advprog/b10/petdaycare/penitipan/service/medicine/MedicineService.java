package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.medicine;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.MedicineRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.medicine.Medicine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicineService {
    List<Medicine> findAll();
    Medicine findById(Integer id);
    Medicine create(MedicineRequest request);
    Medicine update(Integer id, MedicineRequest request);
    void delete(Integer id);
}