package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Service;


import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.HewanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.HewanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.HewanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.PenitipanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PenitipanServiceImplTest {

    @InjectMocks
    private PenitipanServiceImpl service;

    @Mock
    private PenitipanRepository penitipanRepository;

    @Mock
    private  HewanRepository hewanRepository;

    Penitipan penitipan;
    PenitipanRequest penitipanRequest;
    Hewan hewan;

    @BeforeEach
    void setUp() {
        hewan = new Hewan();

        penitipan = new Penitipan();

        penitipan.setStatusPenitipan(StatusPenitipan.UNVERIFIED_PENITIPAN);
        penitipan.setHewan(hewan);
        penitipan.setTanggalPenitipan(new Date());
        penitipan.setTanggalPengambilan(new Date());
        penitipan.setPesanPenitipan("Dia galak");

        penitipanRequest = new PenitipanRequest();
        penitipanRequest.setHewan(penitipan.getHewan());
        penitipanRequest.setPesanPenitipan(penitipan.getPesanPenitipan());
        penitipanRequest.setTanggalPenitipan(penitipan.getTanggalPenitipan());
        penitipanRequest.setTanggalPengambilan(penitipan.getTanggalPengambilan());
    }

    @Test
    void whenCreatePenitipanButHewanNotFoundShouldThrowException() {
        //when(userRepository.findById(any(Integer.class))).thenReturn(Optional.of(user));
        lenient().when(hewanRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        assertThrows(HewanDoesNotExistException.class, () -> {
            service.create(0, penitipanRequest);
        });
    }

    @Test
    void whenUpdatePenitipanButNotFoundShouldThrowException() {
        lenient().when(penitipanRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        lenient().when(hewanRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        assertThrows(PenitipanDoesNotExistException.class, () -> {
            service.update(0, 0, penitipanRequest);
        });
    }

    @Test
    void whenUpdatePenitipanButHewanNotFoundShouldThrowException() {
        lenient().when(penitipanRepository.findById(any(Integer.class))).thenReturn(Optional.of(penitipan));
        //when(userRepository.findById(any(Integer.class))).thenReturn(Optional.of(user));
        lenient().when(hewanRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        assertThrows(HewanDoesNotExistException.class, () -> {
            service.update(0, 0, penitipanRequest);
        });
    }

    @Test
    void whenDeletePenitipanAndFoundShouldCallDeleteByIdOnRepo() {
        lenient().when(penitipanRepository.findById(any(Integer.class))).thenReturn(Optional.of(penitipan));
        service.delete(0);
        verify(penitipanRepository, atLeastOnce()).deleteById(any(Integer.class));
    }

    @Test
    void whenDeletePenitipanButNotFoundShouldThrowException() {
        lenient().when(penitipanRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        assertThrows(PenitipanDoesNotExistException.class, () -> {
            service.delete(0);
        });
    }
}
