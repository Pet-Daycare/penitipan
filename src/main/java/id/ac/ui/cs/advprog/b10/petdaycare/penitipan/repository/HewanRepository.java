package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HewanRepository extends JpaRepository<Hewan, Integer> {
    @NonNull
    List<Hewan> findAll();
    @NonNull
    Optional<Hewan> findById(@NonNull Integer id);
    void deleteById(@NonNull Integer id);
}
