package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanAdminResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanUserResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanWithHewanIdDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.HewanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.PenitipanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.auth.AuthService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PenitipanFindServiceImpl implements PenitipanFindService{
    private final PenitipanRepository penitipanRepository;
    private final AuthService authService;

    @Override
    public Integer getUserId(PenitipanRequest penitipanRequest) {
        return authService.getUserId(penitipanRequest);
    }

    @Override
    public List<PenitipanAdminResponse> findAll() {
        return penitipanRepository.findAll()
                .stream()
                .map(PenitipanAdminResponse::fromPenitipan)
                .toList();
    }

    @Override
    public List<PenitipanUserResponse> findAllByUserId(PenitipanRequest request) {
        List<Penitipan> allByUserId = penitipanRepository.findAllByUserId(request.getUserId());
        if (allByUserId == null) {
            return new ArrayList<>();
        }
        return allByUserId
                .stream()
                .map(PenitipanUserResponse::fromPenitipan)
                .toList();
    }

    @Override
    public Penitipan findPenitipanById(Integer id) throws PenitipanDoesNotExistException{
        Optional<Penitipan> optionalPenitipan = penitipanRepository.findById(id);
        if (optionalPenitipan.isEmpty()) {
            throw new PenitipanDoesNotExistException(id);
        }
        return optionalPenitipan.get();
    }

    @Override
    public Penitipan findByHewanId(Integer hewanId) {
        Optional<Penitipan> optionalPenitipan = penitipanRepository.findByHewanId(hewanId);
        if (optionalPenitipan.isEmpty()) {
            throw new PenitipanWithHewanIdDoesNotExistException(hewanId);
        }
        return optionalPenitipan.get();
    }

    @Override
    public List<PenitipanAdminResponse> findAllByStatus(StatusPenitipan statusPenitipan) {
        return penitipanRepository.findAll()
                .stream()
                .map(PenitipanAdminResponse::fromPenitipan)
                .toList();
    }

    @Override
    public List<PenitipanUserResponse> findAllByUserIdAndStatus(Integer userId, StatusPenitipan statusPenitipan) {
        return penitipanRepository.findAllByUserIdAndStatusPenitipan(userId, statusPenitipan)
                .stream()
                .map(PenitipanUserResponse::fromPenitipan)
                .toList();
    }

    @Override
    public List<PenitipanAdminResponse> findAllByHewanIdAndStatus(Integer hewanId, StatusPenitipan statusPenitipan) {
        return penitipanRepository.findAllByHewanIdAndStatusPenitipan(hewanId, statusPenitipan)
                .stream()
                .map(PenitipanAdminResponse::fromPenitipan)
                .toList();
    }

}
