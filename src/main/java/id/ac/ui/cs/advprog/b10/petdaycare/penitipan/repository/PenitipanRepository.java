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

    //List<Penitipan> findAllByUserId(Integer userId);
    @NonNull
    Optional<Penitipan> findById(@NonNull Integer id);

    Object findAllByUserId(Integer userId);

    Object findAllByHewanId(Integer hewanId);

    Object findAllByStatusPenitipan(StatusPenitipan statusPenitipan);

    Object findAllByUserIdAndStatusPenitipan(Integer userId, StatusPenitipan statusPenitipan);

    Object findAllByHewanIdAndStatusPenitipan(Integer hewanId, StatusPenitipan statusPenitipan);
}
