package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Controller;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.controller.PenitipanController;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanAdminResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanFindService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PenitipanControllerTest {
    private PenitipanController penitipanController;

    @Mock
    private PenitipanService penitipanService;

    @Mock
    private PenitipanFindService penitipanFindService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        penitipanController = new PenitipanController(
                penitipanService,
                penitipanFindService);
    }

    @Test
    void testGetAllOrder() {
        List<PenitipanAdminResponse> expectedResponse = new ArrayList<>();
        when(penitipanFindService.findAll()).thenReturn(expectedResponse);

        ResponseEntity<List<PenitipanAdminResponse>> responseEntity = penitipanController.getAllOrder();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(penitipanFindService, times(1)).findAll();
    }

    @Test
    void testCreatePenitipan() {
        PenitipanRequest request = new PenitipanRequest();
        Penitipan expectedResponse = new Penitipan();
        when(penitipanService.create(request)).thenReturn(expectedResponse);

        ResponseEntity<Penitipan> responseEntity = penitipanController.createPenitipan(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(penitipanService, times(1)).create(request);
    }

    @Test
    void testUpdateOrder() {
        int id = 1;
        PenitipanRequest request = new PenitipanRequest();
        Penitipan expectedResponse = new Penitipan();
        when(penitipanService.update(id, request)).thenReturn(expectedResponse);

        ResponseEntity<Penitipan> responseEntity = penitipanController.updateOrder(id, request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(penitipanService, times(1)).update(id, request);
    }

    @Test
    void testDeleteOrder() {
        int id = 1;

        ResponseEntity<String> responseEntity = penitipanController.deleteOrder(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Deleted Order with id 1", responseEntity.getBody());
        verify(penitipanService, times(1)).delete(id);
    }

    @Test
    void testVerifyPenitipan() {
        int id = 1;
        Penitipan expectedResponse = new Penitipan();
        when(penitipanService.verifyPayment(id)).thenReturn(expectedResponse);

        ResponseEntity<Penitipan> responseEntity = penitipanController.verifyPenitipan(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(penitipanService, times(1)).verifyPayment(id);
    }

    @Test
    void testPengambilanHewan() {
        int id = 1;
        Penitipan expectedResponse = new Penitipan();
        when(penitipanService.ambilHewan(id)).thenReturn(expectedResponse);

        ResponseEntity<Penitipan> responseEntity = penitipanController.pengambilanHewan(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(penitipanService, times(1)).ambilHewan(id);
    }
}
