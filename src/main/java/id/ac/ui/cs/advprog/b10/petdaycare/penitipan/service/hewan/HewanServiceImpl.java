package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.hewan;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.HewanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.HewanDoesnotException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.HewanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HewanServiceImpl implements HewanService {
    private final HewanRepository hewanRepository;
    @Override
    public List<Hewan> findAll() {
        return hewanRepository.findAll();
    }

    @Override
    public Hewan findById(Integer id) {
        if (isHewanDoesNotExist(id)){
            throw new HewanDoesnotException(id);
        }
        return hewanRepository.findById(id).get();
    }

    @Override
    public Hewan create(HewanRequest request) {
        Hewan hewan = Hewan.builder()
                .nama(request.getNama())
                .tipeHewan(request.getTipeHewan())
                .beratHewan(request.getBeratHewan())
                .build();
        return hewanRepository.save(hewan);
    }

    @Override
    public Hewan update(Integer id, HewanRequest request) {
        if (isHewanDoesNotExist(id)) {
            throw new HewanDoesnotException(id);
        }
        Hewan hewan = Hewan.builder()
                .nama(request.getNama())
                .tipeHewan(request.getTipeHewan())
                .beratHewan(request.getBeratHewan())
                .build();
        return this.hewanRepository.save(hewan);
    }

    @Override
    public void delete(Integer id) {
        if (isHewanDoesNotExist(id)){
            throw new HewanDoesnotException(id);
        }
        else{
            hewanRepository.deleteById(id);
        }
    }

    private boolean isHewanDoesNotExist(Integer id) {
        return hewanRepository.findById(id).isEmpty();
    }
}
