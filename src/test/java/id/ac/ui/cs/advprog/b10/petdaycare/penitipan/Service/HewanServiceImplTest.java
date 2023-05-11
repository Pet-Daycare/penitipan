package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Service;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.HewanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.HewanDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.HewanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.hewan.HewanServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HewanServiceImplTest {

    @InjectMocks
    private HewanServiceImpl service;

    @Mock
    private HewanRepository repository;

    Hewan hewan;
    Hewan newHewan;
    HewanRequest createRequest;
    HewanRequest updateRequest;

    @BeforeEach
    void setUp() {
        createRequest = HewanRequest.builder()
                .nama("Jimmy")
                .beratHewan("30Kg")
                .tipeHewan("Kucing")
                .build();

        updateRequest = HewanRequest.builder()
                .nama("Bob")
                .beratHewan("30Kg")
                .tipeHewan("Kucing")
                .build();


        hewan = Hewan.builder()
                .id(0)
                .nama("Jimmy")
                .beratHewan("30Kg")
                .tipeHewan("Kucing")
                .build();

        newHewan = Hewan.builder()
                .id(0)
                .nama("Bob")
                .beratHewan("30Kg")
                .tipeHewan("Kucing")
                .build();
    }
    @Test
    void whenFindAllHewanShouldReturnListOfHewan() {
        List<Hewan> allHewan = List.of(hewan);

        when(repository.findAll()).thenReturn(allHewan);

        List<Hewan> result = service.findAll();
        verify(repository, atLeastOnce()).findAll();
        Assertions.assertEquals(allHewan, result);
    }

    @Test
    void whenFindByIdAndFoundShouldReturnHewan() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(hewan));

        Hewan result = service.findById(0);
        verify(repository, atLeastOnce()).findById(any(Integer.class));
        Assertions.assertEquals(hewan, result);
    }

    @Test
    void whenFindByIdAndNotFoundShouldThrowException() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(HewanDoesNotExistException.class, () -> {
            service.findById(0);
        });
    }

    @Test
    void whenCreateHewanShouldReturnTheCreatedHewan() {
        when(repository.save(any(Hewan.class))).thenAnswer(invocation -> {
            var hewan = invocation.getArgument(0, Hewan.class);
            hewan.setId(0);
            return hewan;
        });

        Hewan result = service.create(createRequest);
        verify(repository, atLeastOnce()).save(any(Hewan.class));
        Assertions.assertEquals(hewan, result);
    }

    @Test
    void whenUpdateHewanAndFoundShouldReturnTheUpdatedHewan() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(hewan));
        when(repository.save(any(Hewan.class))).thenAnswer(invocation ->
                invocation.getArgument(0, Hewan.class));

        Hewan result = service.update(0, updateRequest);
        verify(repository, atLeastOnce()).save(any(Hewan.class));
        Assertions.assertEquals(newHewan, result);
    }

    @Test
    void whenUpdateHewanAndNotFoundShouldThrowException() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.empty());
        Assertions.assertThrows(HewanDoesNotExistException.class, () -> {
            service.update(0, createRequest);
        });
    }

    @Test
    void whenDeleteHewanAndFoundShouldCallDeleteByIdOnRepo() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(hewan));

        service.delete(0);
        verify(repository, atLeastOnce()).deleteById(any(Integer.class));
    }

    @Test
    void whenDeleteHewanAndNotFoundShouldThrowException() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.empty());
        Assertions.assertThrows(HewanDoesNotExistException.class, () -> {
            service.delete(0);
        });
    }
}
