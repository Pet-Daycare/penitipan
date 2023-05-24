package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Service;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanAdminResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanUserResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.HewanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.HewanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.PenitipanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PenitipanServiceImplTest {
    private PenitipanService penitipanService;

    @Mock
    private PenitipanRepository penitipanRepository;

    @Mock
    private HewanRepository hewanRepository;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        penitipanService = new PenitipanServiceImpl(penitipanRepository, hewanRepository,restTemplate);
    }

    @Test
    void testFindAll() {
        List<PenitipanAdminResponse> expectedResponse = new ArrayList<>();
        when(penitipanRepository.findAll()).thenReturn(new ArrayList<>());

        List<PenitipanAdminResponse> result = penitipanService.findAll();

        assertEquals(expectedResponse, result);
        verify(penitipanRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdWithExistingId() {
        Integer id = 1;
        Penitipan expectedPenitipan = new Penitipan();
        when(penitipanRepository.findById(id)).thenReturn(Optional.of(expectedPenitipan));

        Penitipan result = penitipanService.findById(id);

        assertEquals(expectedPenitipan, result);
        verify(penitipanRepository, times(1)).findById(id);
    }

    @Test
    void testFindByIdWithNonExistingId() {
        Integer id = 1;
        when(penitipanRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PenitipanDoesNotExistException.class, () -> penitipanService.findById(id));
        verify(penitipanRepository, times(1)).findById(id);
    }

    @Test
    void testCreateWithExistingHewan() {
        Integer userId = 1;
        Integer hewanId = 1;
        PenitipanRequest request = new PenitipanRequest();
        Hewan hewan = new Hewan(); // Create a new Hewan object
        hewan.setId(hewanId); // Set the id attribute
        request.setUserId(userId);
        request.setHewan(hewan);
        request.setTanggalPenitipan(new Date());
        request.setTanggalPengambilan(new Date());
        request.setPesanPenitipan("Test");

        when(hewanRepository.findById(hewanId)).thenReturn(Optional.of(hewan)); // Return the same Hewan object from the repository
        when(penitipanRepository.save(any(Penitipan.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Penitipan result = penitipanService.create(request);

        assertNotNull(result);
        assertEquals(hewanId, result.getHewan().getId());
        verify(hewanRepository, times(1)).findById(hewanId);
        verify(penitipanRepository, times(1)).save(any(Penitipan.class));
    }

//    TODO: Complete this test
//    @Test
//    void testCreateWithNonExistingHewan() {
//        Integer userId = 1;
//        Integer hewanId = 1;
//        PenitipanRequest request = new PenitipanRequest();
//        request.setHewan(new Hewan());
//        request.setTanggalPenitipan(new Date());
//        request.setTanggalPengambilan(new Date());
//        request.setPesanPenitipan("Test");
//
//        when(hewanRepository.findById(hewanId)).thenReturn(Optional.empty());
//
//        assertThrows(HewanDoesNotExistException.class, () -> penitipanService.create(userId, request));
//        verify(hewanRepository, times(1)).findById(hewanId);
//        verify(penitipanRepository, never()).save(any(Penitipan.class));
//    }

    @Test
    void testUpdateWithExistingPenitipanAndExistingHewan() {
        Integer userId = 1;
        Integer penitipanId = 1;
        Integer hewanId = 1;
        PenitipanRequest request = new PenitipanRequest();
        Hewan hewan = new Hewan(); // Create a new Hewan object
        hewan.setId(hewanId); // Set the id attribute
        request.setHewan(hewan);
        request.setTanggalPenitipan(new Date());
        request.setTanggalPengambilan(new Date());
        request.setPesanPenitipan("Test");

        when(penitipanRepository.findById(penitipanId)).thenReturn(Optional.of(new Penitipan()));
        when(hewanRepository.findById(hewanId)).thenReturn(Optional.of(hewan)); // Return the same Hewan object from the repository
        when(penitipanRepository.save(any(Penitipan.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Penitipan result = penitipanService.update(penitipanId, request);

        assertNotNull(result);
        assertEquals(penitipanId, result.getId());
        assertEquals(hewanId, result.getHewan().getId());
        verify(penitipanRepository, times(1)).findById(penitipanId);
        verify(hewanRepository, times(1)).findById(hewanId);
        verify(penitipanRepository, times(1)).save(any(Penitipan.class));
    }

    @Test
    void testUpdateWithNonExistingPenitipan() {
        Integer userId = 1;
        Integer penitipanId = 1;
        Integer hewanId = 1;
        PenitipanRequest request = new PenitipanRequest();
        request.setHewan(new Hewan());
        request.setTanggalPenitipan(new Date());
        request.setTanggalPengambilan(new Date());
        request.setPesanPenitipan("Test");

        when(penitipanRepository.findById(penitipanId)).thenReturn(Optional.empty());

        assertThrows(PenitipanDoesNotExistException.class, () -> penitipanService.update(penitipanId, request));
        verify(penitipanRepository, times(1)).findById(penitipanId);
        verify(hewanRepository, never()).findById(anyInt());
        verify(penitipanRepository, never()).save(any(Penitipan.class));
    }

//    TODO: Complete this test
//    @Test
//    void testUpdateWithExistingPenitipanAndNonExistingHewan() {
//        Integer userId = 1;
//        Integer penitipanId = 1;
//        Integer hewanId = 1;
//        PenitipanRequest request = new PenitipanRequest();
//        request.setHewan(new Hewan());
//        request.setTanggalPenitipan(new Date());
//        request.setTanggalPengambilan(new Date());
//        request.setPesanPenitipan("Test");
//
//        when(penitipanRepository.findById(penitipanId)).thenReturn(Optional.of(new Penitipan()));
//        when(hewanRepository.findById(hewanId)).thenReturn(Optional.empty());
//
//        assertThrows(HewanDoesNotExistException.class, () -> penitipanService.update(userId, penitipanId, request));
//        verify(penitipanRepository, times(1)).findById(penitipanId);
//        verify(hewanRepository, times(1)).findById(hewanId);
//        verify(penitipanRepository, never()).save(any(Penitipan.class));
//    }

    @Test
    void testDeleteWithExistingPenitipan() {
        Integer penitipanId = 1;
        when(penitipanRepository.findById(penitipanId)).thenReturn(Optional.of(new Penitipan()));

        penitipanService.delete(penitipanId);

        verify(penitipanRepository, times(1)).findById(penitipanId);
        verify(penitipanRepository, times(1)).deleteById(penitipanId);
    }

    @Test
    void testDeleteWithNonExistingPenitipan() {
        Integer penitipanId = 1;
        when(penitipanRepository.findById(penitipanId)).thenReturn(Optional.empty());

        assertThrows(PenitipanDoesNotExistException.class, () -> penitipanService.delete(penitipanId));
        verify(penitipanRepository, times(1)).findById(penitipanId);
        verify(penitipanRepository, never()).deleteById(anyInt());
    }

    @Test
    void testVerifyWithExistingPenitipan() {
        Integer userId = 1;
        Integer penitipanId = 1;
        Penitipan penitipan = new Penitipan();
        when(penitipanRepository.findById(penitipanId)).thenReturn(Optional.of(penitipan));
        when(penitipanRepository.save(penitipan)).thenReturn(penitipan);

        Penitipan result = penitipanService.verify(penitipanId);

        assertNotNull(result);
        assertEquals(StatusPenitipan.VERIFIED_PENITIPAN, result.getStatusPenitipan());
        verify(penitipanRepository, times(1)).findById(penitipanId);
        verify(penitipanRepository, times(1)).save(penitipan);
    }

    @Test
    void testVerifyWithNonExistingPenitipan() {
        Integer userId = 1;
        Integer penitipanId = 1;
        when(penitipanRepository.findById(penitipanId)).thenReturn(Optional.empty());

        assertThrows(PenitipanDoesNotExistException.class, () -> penitipanService.verify(penitipanId));
        verify(penitipanRepository, times(1)).findById(penitipanId);
        verify(penitipanRepository, never()).save(any(Penitipan.class));
    }

//    TODO: Complete this test
//    @Test
//    void testCompleteWithExistingPenitipan() {
//        Integer userId = 1;
//        Integer penitipanId = 1;
//        Penitipan penitipan = new Penitipan();
//        when(penitipanRepository.findById(penitipanId)).thenReturn(Optional.of(penitipan));
//        when(penitipanRepository.save(penitipan)).thenReturn(penitipan);
//
//        Penitipan result = penitipanService.complete(userId, penitipanId);
//
//        assertNotNull(result);
//        assertEquals(StatusPenitipan.COMPLETED_PENITIPAN, result.getStatusPenitipan());
//        verify(penitipanRepository, times(1)).findById(penitipanId);
//        verify(penitipanRepository, times(1)).save(penitipan);
//    }

//    TODO: Complete this test
//    @Test
//    void testCompleteWithNonExistingPenitipan() {
//        Integer userId = 1;
//        Integer penitipanId = 1;
//        when(penitipanRepository.findById(penitipanId)).thenReturn(Optional.empty());
//
//        assertThrows(PenitipanDoesNotExistException.class, () -> penitipanService.complete(userId, penitipanId));
//        verify(penitipanRepository, times(1)).findById(penitipanId);
//        verify(penitipanRepository, never()).save(any(Penitipan.class));
//    }

//    TODO: Complete this test
//    @Test
//    void testCancelWithExistingPenitipan() {
//        Integer userId = 1;
//        Integer penitipanId = 1;
//        Penitipan penitipan = new Penitipan();
//        when(penitipanRepository.findById(penitipanId)).thenReturn(Optional.of(penitipan));
//        when(penitipanRepository.save(penitipan)).thenReturn(penitipan);
//
//        Penitipan result = penitipanService.cancel(userId, penitipanId);
//
//        assertNotNull(result);
//        assertEquals(StatusPenitipan.CANCELED_PENITIPAN, result.getStatusPenitipan());
//        verify(penitipanRepository, times(1)).findById(penitipanId);
//        verify(penitipanRepository, times(1)).save(penitipan);
//    }

//    TODO: Complete this test
//    @Test
//    void testCancelWithNonExistingPenitipan() {
//        Integer userId = 1;
//        Integer penitipanId = 1;
//        when(penitipanRepository.findById(penitipanId)).thenReturn(Optional.empty());
//
//        assertThrows(PenitipanDoesNotExistException.class, () -> penitipanService.cancel(userId, penitipanId));
//        verify(penitipanRepository, times(1)).findById(penitipanId);
//        verify(penitipanRepository, never()).save(any(Penitipan.class));
//    }

//    TODO: Complete this test
//    @Test
//    void testFindAllByUserIdWithExistingUserId() {
//        Integer userId = 1;
//        List<PenitipanUserResponse> expectedResponse = new ArrayList<>();
//        when(penitipanRepository.findAllByUserId(userId)).thenReturn(new ArrayList<>());
//
//        List<PenitipanUserResponse> result = penitipanService.findAllByUserId(userId);
//
//        assertEquals(expectedResponse, result);
//        verify(penitipanRepository, times(1)).findAllByUserId(userId);
//    }

//    TODO: Complete this test
//    @Test
//    void testFindAllByUserIdWithNonExistingUserId() {
//        Integer userId = 1;
//        when(penitipanRepository.findAllByUserId(userId)).thenReturn(null);
//
//        List<PenitipanUserResponse> result = penitipanService.findAllByUserId(userId);
//
//        assertNull(result);
//        verify(penitipanRepository, times(1)).findAllByUserId(userId);
//    }

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
