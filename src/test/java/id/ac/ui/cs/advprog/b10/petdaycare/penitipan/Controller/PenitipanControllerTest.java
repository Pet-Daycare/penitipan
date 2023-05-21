package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Controller;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.controller.PenitipanController;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanAdminResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanUserResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.hewan.HewanService;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        penitipanController = new PenitipanController(penitipanService);
    }

    @Test
    void testGetAllOrder() {
        List<PenitipanAdminResponse> expectedResponse = new ArrayList<>();
        when(penitipanService.findAll()).thenReturn(expectedResponse);

        ResponseEntity<List<PenitipanAdminResponse>> responseEntity = penitipanController.getAllOrder();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(penitipanService, times(1)).findAll();
    }

    @Test
    void testCreatePenitipan() {
        PenitipanRequest request = new PenitipanRequest();
        Penitipan expectedResponse = new Penitipan();
        when(penitipanService.create(anyInt(), eq(request))).thenReturn(expectedResponse);

        ResponseEntity<Penitipan> responseEntity = penitipanController.createPenitipan(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(penitipanService, times(1)).create(anyInt(), eq(request));
    }

    @Test
    void testUpdateOrder() {
        int id = 1;
        PenitipanRequest request = new PenitipanRequest();
        Penitipan expectedResponse = new Penitipan();
        when(penitipanService.update(anyInt(), eq(id), eq(request))).thenReturn(expectedResponse);

        ResponseEntity<Penitipan> responseEntity = penitipanController.updateOrder(id, request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(penitipanService, times(1)).update(anyInt(), eq(id), eq(request));
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
        when(penitipanService.verify(anyInt(), eq(id))).thenReturn(expectedResponse);

        ResponseEntity<Penitipan> responseEntity = penitipanController.verifyPenitipan(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(penitipanService, times(1)).verify(anyInt(), eq(id));
    }

    @Test
    void testPengambilanHewan() {
        int id = 1;
        Penitipan expectedResponse = new Penitipan();
        when(penitipanService.ambilHewan(anyInt(), eq(id))).thenReturn(expectedResponse);

        ResponseEntity<Penitipan> responseEntity = penitipanController.pengambilanHewan(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(penitipanService, times(1)).ambilHewan(anyInt(), eq(id));
    }
}
