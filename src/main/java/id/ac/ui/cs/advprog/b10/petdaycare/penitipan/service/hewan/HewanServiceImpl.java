package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.hewan;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.HewanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.HewanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.HewanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Hewan> hewan = hewanRepository.findById(id);
        if (hewan.isPresent()){
            return hewan.get();
        }
        throw new HewanDoesNotExistException(id);
    }

    @Override
    public Hewan create(HewanRequest request) {
        Hewan hewan = Hewan.builder()
                .nama(request.getNama())
                .tipeHewan(request.getTipeHewan())
                .beratHewan(request.getBeratHewan())
                .build();
        hewanRepository.save(hewan);
        return hewan;
    }

    @Override
    public Hewan update(Integer id, HewanRequest request) {
        if (isHewanDoesNotExist(id)) {
            throw new HewanDoesNotExistException(id);
        }
        Hewan hewan = Hewan.builder()
                .id(id)
                .nama(request.getNama())
                .tipeHewan(request.getTipeHewan())
                .beratHewan(request.getBeratHewan())
                .build();
        hewanRepository.save(hewan);
        return hewan;
    }

    @Override
    public void delete(Integer id) {
        if (isHewanDoesNotExist(id)){
            throw new HewanDoesNotExistException(id);
        }
        else{
            hewanRepository.deleteById(id);
        }
    }

    private boolean isHewanDoesNotExist(Integer id) {
        return hewanRepository.findById(id).isEmpty();
    }
}
