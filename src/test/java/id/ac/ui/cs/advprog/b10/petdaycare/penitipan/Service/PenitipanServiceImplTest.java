package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Service;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.HewanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.PenitipanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.auth.AuthService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.hewan.HewanService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.payment.PaymentService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanFindService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PenitipanServiceImplTest {
    private PenitipanService penitipanService;

    @Mock
    private PenitipanFindService penitipanFindService;

    @Mock
    private PenitipanRepository penitipanRepository;

    @Mock
    private HewanRepository hewanRepository;

    @Mock
    private PaymentService paymentService;

    @Mock
    private HewanService hewanService;

    @Mock
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        penitipanService = new PenitipanServiceImpl(
                penitipanRepository,
                hewanRepository,
                paymentService,
                penitipanFindService,
                hewanService,
                authService);
    }


    //@Test
    //void testCreateWithExistingHewan() {
    //    Integer userId = 1;
    //    Integer hewanId = 1;
    //    PenitipanRequest request = new PenitipanRequest();
    //    Hewan hewan = new Hewan(); // Create a new Hewan object
    //    hewan.setId(hewanId); // Set the id attribute
    //    request.setUserId(userId);
    //    request.setHewanId(hewanId);
    //    request.setTanggalPenitipan(LocalDateTime.now());
    //    request.setTanggalPengambilan(LocalDateTime.now());
    //    request.setPesanPenitipan("Test");
//
    //    when(hewanRepository.findById(hewanId)).thenReturn(Optional.of(hewan)); // Return the same Hewan object from the repository
    //    when(penitipanRepository.save(any(Penitipan.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
    //    Penitipan result = penitipanService.create(request);
//
    //    assertNotNull(result);
    //    assertEquals(hewanId, result.getHewan().getId());
    //    verify(hewanRepository, times(1)).findById(hewanId);
    //    verify(penitipanRepository, times(1)).save(any(Penitipan.class));
    //}

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

    //@Test
    //void testUpdateWithExistingPenitipanAndExistingHewan() {
    //    Integer penitipanId = 1;
    //    Integer hewanId = 1;
//
    //    PenitipanRequest request = new PenitipanRequest();
    //    request.setNamaHewan("john");
    //    request.setBeratHewan(30);
    //    request.setTipeHewan("DOG");
    //    request.setTanggalPenitipan(LocalDateTime.now());
    //    request.setTanggalPengambilan(LocalDateTime.now());
    //    request.setPesanPenitipan("Test");
//
    //    Hewan hewan = new Hewan(); // Create a new Hewan object
    //    hewan.setId(hewanId); // Set the id attribute
    //    request.setHewanId(hewanId);
//
    //    Penitipan penitipan = new Penitipan();
    //    penitipan.setHewan(hewan);
//
    //    when(penitipanFindService.findPenitipanById(penitipanId)).thenReturn(penitipan);
    //    when(hewanService.findById(hewanId)).thenReturn(hewan); // Return the same Hewan object from the repository
    //    when(penitipanRepository.save(any(Penitipan.class))).thenAnswer(invocation -> invocation.getArgument(0));
    //    when(hewanRepository.save(any(Hewan.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//
    //    Penitipan result = penitipanService.update(penitipanId, request);
//
    //    assertNotNull(result);
    //    assertEquals(penitipanId, result.getId());
    //    assertEquals(hewanId, result.getHewan().getId());
    //    verify(penitipanRepository, times(1)).findById(penitipanId);
    //    verify(hewanRepository, times(1)).findById(hewanId);
    //    verify(penitipanRepository, times(1)).save(any(Penitipan.class));
    //}

    //@Test
    //void testUpdateWithNonExistingPenitipan() {
    //    Integer penitipanId = 1;
    //    Integer hewanId = 1;
    //    PenitipanRequest request = new PenitipanRequest();
    //    request.setHewanId(hewanId);
    //    request.setTanggalPenitipan(LocalDateTime.now());
    //    request.setTanggalPengambilan(LocalDateTime.now());
    //    request.setPesanPenitipan("Test");
//
    //    when(penitipanRepository.findById(penitipanId)).thenReturn(Optional.empty());
//
    //    assertThrows(PenitipanDoesNotExistException.class, () -> penitipanFindService.findPenitipanById(penitipanId));
    //    verify(penitipanRepository, times(1)).findById(penitipanId);
    //    verify(penitipanFindService, times(1)).findPenitipanById(penitipanId);
    //    verify(hewanRepository, never()).findById(anyInt());
    //    verify(penitipanRepository, never()).save(any(Penitipan.class));
    //}
//
    //@Test
    //void testUpdateWithExistingPenitipanAndNonExistingHewan() {
    //    Integer penitipanId = 1;
    //    Integer hewanId = 1;
    //    Penitipan penitipan = new Penitipan();
    //    PenitipanRequest request = new PenitipanRequest();
    //    request.setHewanId(hewanId);
    //    request.setTanggalPenitipan(LocalDateTime.now());
    //    request.setTanggalPengambilan(LocalDateTime.now());
    //    request.setPesanPenitipan("Test");
//
    //    when(penitipanRepository.findById(penitipanId)).thenReturn(Optional.of(penitipan));
//
    //    assertThrows(PenitipanDoesNotHaveHewanException.class, () -> penitipanService.update(penitipanId, request));
    //    verify(penitipanRepository, times(1)).findById(penitipanId);
    //    verify(hewanRepository, never()).findById(hewanId);
    //    verify(penitipanRepository, never()).save(any(Penitipan.class));
    //}

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

    //@Test
    //void testVerifyWithExistingPenitipan() {
    //    Integer penitipanId = 1;
    //    Penitipan penitipan = new Penitipan();
    //    when(penitipanRepository.findById(penitipanId)).thenReturn(Optional.of(penitipan));
    //    when(penitipanFindService.findPenitipanById(penitipanId)).thenReturn(penitipan);
    //    when(penitipanRepository.save(penitipan)).thenReturn(penitipan);
//
    //    Penitipan result = penitipanService.verifyPayment(penitipanId);
//
    //    assertNotNull(result);
    //    assertEquals(StatusPenitipan.VERIFIED_PENITIPAN, result.getStatusPenitipan());
    //    verify(penitipanRepository, times(1)).findById(penitipanId);
    //    verify(penitipanRepository, times(1)).save(penitipan);
    //}

    //@Test
    //void testVerifyWithNonExistingPenitipan() {
    //    Integer penitipanId = 1;
    //    when(penitipanRepository.findById(penitipanId)).thenReturn(Optional.empty());
//
    //    assertThrows(PenitipanDoesNotExistException.class, () -> penitipanService.verifyPayment(penitipanId));
    //    verify(penitipanRepository, times(1)).findById(penitipanId);
    //    verify(penitipanRepository, never()).save(any(Penitipan.class));
    //}

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

//
}
