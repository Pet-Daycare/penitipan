package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface PenitipanRepository extends JpaRepository<Penitipan, Integer> {
    @NonNull
    List<Penitipan> findAll();

    List<Penitipan> findAllByUserId(Integer userId);
    @NonNull
    Optional<Penitipan> findById(@NonNull Integer id);

    Optional<Penitipan> findByHewanId(Integer hewanId);

    List<Penitipan> findAllByStatusPenitipan(StatusPenitipan statusPenitipan);

    List<Penitipan> findAllByUserIdAndStatusPenitipan(Integer userId, StatusPenitipan statusPenitipan);

    List<Penitipan> findAllByHewanIdAndStatusPenitipan(Integer hewanId, StatusPenitipan statusPenitipan);
}
