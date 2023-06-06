package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanAdminResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanUserResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;

import java.util.List;

public interface PenitipanFindService {
    Integer getUserId(PenitipanRequest penitipanRequest);
    List<PenitipanAdminResponse> findAll();
    List<PenitipanUserResponse> findAllByUserId(PenitipanRequest penitipanRequest);
    Penitipan findPenitipanById(Integer id);
    List<PenitipanAdminResponse> findAllByStatus(StatusPenitipan statusPenitipan);
}
