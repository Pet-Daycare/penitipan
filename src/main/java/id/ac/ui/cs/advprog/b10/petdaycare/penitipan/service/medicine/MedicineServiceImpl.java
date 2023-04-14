package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.medicine;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.MedicineRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.MedicineDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.medicine.Medicine;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.medicine.MedicineCategory;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;
    @Override
    public List<Medicine> findAll() {
        // TODO: Lengkapi kode berikut
        return medicineRepository.findAll();
    }

    @Override
    public Medicine findById(Integer id) {
        // TODO: Lengkapi kode berikut (Pastikan Anda memanfaatkan Exceptions yang ada!)
        if (isMedicineDoesNotExist(id)){
            throw new MedicineDoesNotExistException(id);
        }
        return medicineRepository.findById(id).get();
    }

    @Override
    public Medicine create(MedicineRequest request) {
        // TODO: Lengkapi kode berikut
        Medicine medicine = Medicine.builder()
                .name(request.getName())
                .category(MedicineCategory.valueOf(request.getCategory()))
                .dose(request.getDose())
                .stock(request.getStock())
                .price(request.getPrice())
                .manufacturer(request.getManufacturer())
                .build();
        return medicineRepository.save(medicine);
    }

    @Override
    public Medicine update(Integer id, MedicineRequest request) {
        // TODO: Lengkapi kode berikut (Pastikan Anda memanfaatkan Exceptions yang ada!)
        if (isMedicineDoesNotExist(id)) {
            throw new MedicineDoesNotExistException(id);
        }
        Medicine medicine = Medicine.builder()
                .id(id)
                .name(request.getName())
                .category(MedicineCategory.valueOf(request.getCategory()))
                .dose(request.getDose())
                .stock(request.getStock())
                .price(request.getPrice())
                .manufacturer(request.getManufacturer())
                .build();
        return this.medicineRepository.save(medicine);
    }

    @Override
    public void delete(Integer id) {
        // TODO: Lengkapi kode berikut (Pastikan Anda memanfaatkan Exceptions yang ada!)
        if (isMedicineDoesNotExist(id)){
            throw new MedicineDoesNotExistException(id);
        }
        else{
            medicineRepository.deleteById(id);
        }
    }

    private boolean isMedicineDoesNotExist(Integer id) {
        return medicineRepository.findById(id).isEmpty();
    }
}
