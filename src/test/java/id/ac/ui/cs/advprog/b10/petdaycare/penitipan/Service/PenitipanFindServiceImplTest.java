package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Service;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanAdminResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanUserResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.HewanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.PenitipanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.auth.AuthService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.payment.PaymentService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanFindService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanFindServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PenitipanFindServiceImplTest {
    private PenitipanFindService penitipanFindService;

    @Mock
    private PenitipanRepository penitipanRepository;

    @Mock
    private HewanRepository hewanRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private PaymentService paymentService;

    @Mock
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        penitipanFindService = new PenitipanFindServiceImpl(
                penitipanRepository,
                authService);
    }

    @Test
    void testFindAll() {
        List<PenitipanAdminResponse> expectedResponse = new ArrayList<>();
        when(penitipanRepository.findAll()).thenReturn(new ArrayList<>());

        List<PenitipanAdminResponse> result = penitipanFindService.findAll();

        assertEquals(expectedResponse, result);
        verify(penitipanRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdWithExistingId() {
        Integer id = 1;
        Penitipan expectedPenitipan = new Penitipan();
        when(penitipanRepository.findById(id)).thenReturn(Optional.of(expectedPenitipan));

        Penitipan result = penitipanFindService.findPenitipanById(id);

        assertEquals(expectedPenitipan, result);
        verify(penitipanRepository, times(1)).findById(id);
    }

    @Test
    void testFindByIdWithNonExistingId() {
        Integer id = 1;
        when(penitipanRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PenitipanDoesNotExistException.class, () -> penitipanFindService.findPenitipanById(id));
        verify(penitipanRepository, times(1)).findById(id);
    }

    @Test
    void testFindAllByUserIdWithExistingUserId() {
        Integer userId = 1;
        PenitipanRequest penitipanRequest = new PenitipanRequest();
        penitipanRequest.setUserId(userId);

        List<PenitipanUserResponse> expectedResponse = new ArrayList<>();
        when(penitipanRepository.findAllByUserId(userId)).thenReturn(new ArrayList<>());

        List<PenitipanUserResponse> result = penitipanFindService.findAllByUserId(penitipanRequest);

        assertEquals(expectedResponse, result);
        verify(penitipanRepository, times(1)).findAllByUserId(userId);
    }

    @Test
    void testFindAllByUserIdWithNonExistingUserId() {
        Integer userId = 1;
        PenitipanRequest penitipanRequest = new PenitipanRequest();
        penitipanRequest.setUserId(userId);

        List<PenitipanUserResponse> expectedResponse = new ArrayList<>();
        when(penitipanRepository.findAllByUserId(userId)).thenReturn(null);

        List<PenitipanUserResponse> result = penitipanFindService.findAllByUserId(penitipanRequest);

        assertEquals(expectedResponse, result);
        verify(penitipanRepository, times(1)).findAllByUserId(userId);
    }

//    @Test
//    void testFindAllByHewanIdWithExistingHewanId() {
//        Integer hewanId = 1;
//        List<PenitipanAdminResponse> expectedResponse = new ArrayList<>();
//        when(penitipanRepository.findAllByHewanId(hewanId)).thenReturn(new ArrayList<>());
//
//        List<PenitipanAdminResponse> result = penitipanService.findAllByHewanId(hewanId);
//
//        assertEquals(expectedResponse, result);
//        verify(penitipanRepository, times(1)).findAllByHewanId(hewanId);
//    }
//
//    @Test
//    void testFindAllByHewanIdWithNonExistingHewanId() {
//        Integer hewanId = 1;
//        when(penitipanRepository.findAllByHewanId(hewanId)).thenReturn(null);
//
//        List<PenitipanAdminResponse> result = penitipanService.findAllByHewanId(hewanId);
//
//        assertNull(result);
//        verify(penitipanRepository, times(1)).findAllByHewanId(hewanId);
//    }
//
//    @Test
//    void testFindAllByStatusWithExistingStatus() {
//        StatusPenitipan statusPenitipan = StatusPenitipan.VERIFIED_PENITIPAN;
//        List<Penitipan> expectedPenitipans = new ArrayList<>();
//        when(penitipanRepository.findAllByStatusPenitipan(statusPenitipan)).thenReturn(new ArrayList<>());
//
//        List<Penitipan> result = penitipanService.findAllByStatus(statusPenitipan);
//
//        assertEquals(expectedPenitipans, result);
//        verify(penitipanRepository, times(1)).findAllByStatusPenitipan(statusPenitipan);
//    }
//
//    @Test
//    void testFindAllByStatusWithNonExistingStatus() {
//        StatusPenitipan statusPenitipan = StatusPenitipan.VERIFIED_PENITIPAN;
//        when(penitipanRepository.findAllByStatusPenitipan(statusPenitipan)).thenReturn(null);
//
//        List<Penitipan> result = penitipanService.findAllByStatus(statusPenitipan);
//
//        assertNull(result);
//        verify(penitipanRepository, times(1)).findAllByStatusPenitipan(statusPenitipan);
//    }
//
//    @Test
//    void testFindAllByUserIdAndStatusWithExistingUserIdAndStatus() {
//        Integer userId = 1;
//        StatusPenitipan statusPenitipan = StatusPenitipan.VERIFIED_PENITIPAN;
//        List<PenitipanUserResponse> expectedResponse = new ArrayList<>();
//        when(penitipanRepository.findAllByUserIdAndStatusPenitipan(userId, statusPenitipan)).thenReturn(new ArrayList<>());
//
//        List<PenitipanUserResponse> result = penitipanService.findAllByUserIdAndStatus(userId, statusPenitipan);
//
//        assertEquals(expectedResponse, result);
//        verify(penitipanRepository, times(1)).findAllByUserIdAndStatusPenitipan(userId, statusPenitipan);
//    }
//
//    @Test
//    void testFindAllByUserIdAndStatusWithNonExistingUserIdAndExistingStatus() {
//        Integer userId = 1;
//        StatusPenitipan statusPenitipan = StatusPenitipan.VERIFIED_PENITIPAN;
//        when(penitipanRepository.findAllByUserIdAndStatusPenitipan(userId, statusPenitipan)).thenReturn(null);
//
//        List<PenitipanUserResponse> result = penitipanService.findAllByUserIdAndStatus(userId, statusPenitipan);
//
//        assertNull(result);
//        verify(penitipanRepository, times(1)).findAllByUserIdAndStatusPenitipan(userId, statusPenitipan);
//    }
//
//    @Test
//    void testFindAllByUserIdAndStatusWithExistingUserIdAndNonExistingStatus() {
//        Integer userId = 1;
//        StatusPenitipan statusPenitipan = StatusPenitipan.VERIFIED_PENITIPAN;
//        when(penitipanRepository.findAllByUserIdAndStatusPenitipan(userId, statusPenitipan)).thenReturn(null);
//
//        List<PenitipanUserResponse> result = penitipanService.findAllByUserIdAndStatus(userId, statusPenitipan);
//
//        assertNull(result);
//        verify(penitipanRepository, times(1)).findAllByUserIdAndStatusPenitipan(userId, statusPenitipan);
//    }
//
//    @Test
//    void testFindAllByHewanIdAndStatusWithExistingHewanIdAndStatus() {
//        Integer hewanId = 1;
//        StatusPenitipan statusPenitipan = StatusPenitipan.VERIFIED_PENITIPAN;
//        List<PenitipanAdminResponse> expectedResponse = new ArrayList<>();
//        when(penitipanRepository.findAllByHewanIdAndStatusPenitipan(hewanId, statusPenitipan)).thenReturn(new ArrayList<>());
//
//        List<PenitipanAdminResponse> result = penitipanService.findAllByHewanIdAndStatus(hewanId, statusPenitipan);
//
//        assertEquals(expectedResponse, result);
//        verify(penitipanRepository, times(1)).findAllByHewanIdAndStatusPenitipan(hewanId, statusPenitipan);
//    }
//
//    @Test
//    void testFindAllByHewanIdAndStatusWithNonExistingHewanIdAndExistingStatus() {
//        Integer hewanId = 1;
//        StatusPenitipan statusPenitipan = StatusPenitipan.VERIFIED_PENITIPAN;
//        when(penitipanRepository.findAllByHewanIdAndStatusPenitipan(hewanId, statusPenitipan)).thenReturn(null);
//
//        List<PenitipanAdminResponse> result = penitipanService.findAllByHewanIdAndStatus(hewanId, statusPenitipan);
//
//        assertNull(result);
//        verify(penitipanRepository, times(1)).findAllByHewanIdAndStatusPenitipan(hewanId, statusPenitipan);
//    }
//
//    @Test
//    void testFindAllByHewanIdAndStatusWithExistingHewanIdAndNonExistingStatus() {
//        Integer hewanId = 1;
//        StatusPenitipan statusPenitipan = StatusPenitipan.VERIFIED_PENITIPAN;
//        when(penitipanRepository.findAllByHewanIdAndStatusPenitipan(hewanId, statusPenitipan)).thenReturn(null);
//
//        List<PenitipanAdminResponse> result = penitipanService.findAllByHewanIdAndStatus(hewanId, statusPenitipan);
//
//        assertNull(result);
//        verify(penitipanRepository, times(1)).findAllByHewanIdAndStatusPenitipan(hewanId, statusPenitipan);
//    }
}
