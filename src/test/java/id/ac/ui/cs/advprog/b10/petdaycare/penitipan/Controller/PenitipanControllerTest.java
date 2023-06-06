package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Controller;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.controller.PenitipanController;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanAdminResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanFindService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    void testComplete_Success() {
        // Arrange
        Integer id = 1;
        Penitipan expectedPenitipan = new Penitipan();
        when(penitipanService.complete(id)).thenReturn(expectedPenitipan);

        // Act
        ResponseEntity<Penitipan> response = penitipanController.complete(id);

        // Assert
        Assertions.assertEquals(expectedPenitipan, response.getBody());
        Assertions.assertEquals(ResponseEntity.ok(expectedPenitipan), response);
        verify(penitipanService, times(1)).complete(id);
    }

    @Test
    void testComplete_PenitipanDoesNotExist() {
        // Arrange
        Integer id = 1;
        when(penitipanService.complete(id)).thenThrow(new PenitipanDoesNotExistException(id));

        // Act
        Assertions.assertThrows(PenitipanDoesNotExistException.class, () -> {
            penitipanController.complete(id);
        });

        // Assert
        verify(penitipanService, times(1)).complete(id);
    }

    @Test
    void testCancel_Success() {
        // Arrange
        Integer id = 1;
        Penitipan expectedPenitipan = new Penitipan();
        when(penitipanService.cancel(id)).thenReturn(expectedPenitipan);

        // Act
        ResponseEntity<Penitipan> response = penitipanController.cancel(id);

        // Assert
        Assertions.assertEquals(expectedPenitipan, response.getBody());
        Assertions.assertEquals(ResponseEntity.ok(expectedPenitipan), response);
        verify(penitipanService, times(1)).cancel(id);
    }

    @Test
    void testCancel_PenitipanDoesNotExist() {
        // Arrange
        Integer id = 1;
        when(penitipanService.cancel(id)).thenThrow(new PenitipanDoesNotExistException(id));

        // Act
        Assertions.assertThrows(PenitipanDoesNotExistException.class, () -> {
            penitipanController.cancel(id);
        });

        // Assert
        verify(penitipanService, times(1)).cancel(id);
    }

    @Test
    void testPayComplete_Success() {
        // Arrange
        Integer id = 1;
        Penitipan expectedPenitipan = new Penitipan();
        when(penitipanService.payComplete(id)).thenReturn(expectedPenitipan);

        // Act
        ResponseEntity<Penitipan> response = penitipanController.payComplete(id);

        // Assert
        Assertions.assertEquals(expectedPenitipan, response.getBody());
        Assertions.assertEquals(ResponseEntity.ok(expectedPenitipan), response);
        verify(penitipanService, times(1)).payComplete(id);
    }

    @Test
    void testPayComplete_PenitipanDoesNotExist() {
        // Arrange
        Integer id = 1;
        when(penitipanService.payComplete(id)).thenThrow(new PenitipanDoesNotExistException(id));

        // Act
        Assertions.assertThrows(PenitipanDoesNotExistException.class, () -> {
            penitipanController.payComplete(id);
        });

        // Assert
        verify(penitipanService, times(1)).payComplete(id);
    }

    @Test
    void testGetPenitipanById_Success() {
        // Arrange
        Integer id = 1;
        Penitipan expectedPenitipan = new Penitipan();
        when(penitipanFindService.findPenitipanById(id)).thenReturn(expectedPenitipan);

        // Act
        ResponseEntity<Penitipan> response = penitipanController.getPenitipanById(id);

        // Assert
        Assertions.assertEquals(expectedPenitipan, response.getBody());
        Assertions.assertEquals(ResponseEntity.ok(expectedPenitipan), response);
        verify(penitipanFindService, times(1)).findPenitipanById(id);
    }

    @Test
    void testGetPenitipanById_PenitipanDoesNotExist() {
        // Arrange
        Integer id = 1;
        when(penitipanFindService.findPenitipanById(id)).thenThrow(new PenitipanDoesNotExistException(id));

        // Act
        Assertions.assertThrows(PenitipanDoesNotExistException.class, () -> {
            penitipanController.getPenitipanById(id);
        });

        // Assert
        verify(penitipanFindService, times(1)).findPenitipanById(id);
    }

    @Test
    void testGetAllOrderByStatus_Success() {
        // Arrange
        StatusPenitipan statusPenitipan = StatusPenitipan.UNVERIFIED_PENITIPAN;
        List<PenitipanAdminResponse> expectedResponse = Arrays.asList(new PenitipanAdminResponse(), new PenitipanAdminResponse());
        when(penitipanFindService.findAllByStatus(StatusPenitipan.UNVERIFIED_PENITIPAN)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<List<PenitipanAdminResponse>> response = penitipanController.getAllOrderByStatus(statusPenitipan);

        // Assert
        Assertions.assertEquals(expectedResponse, response.getBody());
        Assertions.assertEquals(ResponseEntity.ok(expectedResponse), response);
        verify(penitipanFindService, times(1)).findAllByStatus(StatusPenitipan.UNVERIFIED_PENITIPAN);
    }

}
