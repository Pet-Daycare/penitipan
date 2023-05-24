package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.auth.CustomerRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanAdminResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanUserResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PenitipanService {
    List<PenitipanAdminResponse> findAll();
    List<PenitipanUserResponse> findAllByUserId(PenitipanRequest penitipanRequest);

    Penitipan findById(Integer id);
    Penitipan create(PenitipanRequest penitipanRequest);
    Penitipan update(Integer id, PenitipanRequest request);
    Penitipan verify(Integer id);
    Penitipan ambilHewan(Integer id);
    void delete(Integer id);

    Penitipan cancel(PenitipanRequest penitipanRequest);


    List<PenitipanAdminResponse> findAllByHewanId(Integer hewanId);

    List<Penitipan> findAllByStatus(StatusPenitipan statusPenitipan);

    List<PenitipanUserResponse> findAllByUserIdAndStatus(Integer userId, StatusPenitipan statusPenitipan);

    List<PenitipanAdminResponse> findAllByHewanIdAndStatus(Integer hewanId, StatusPenitipan statusPenitipan);

    Penitipan complete(Integer userId, Integer penitipanId);
}
