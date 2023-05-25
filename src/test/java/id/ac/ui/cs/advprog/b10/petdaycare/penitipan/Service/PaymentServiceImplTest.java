package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Service;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.cost.DogCostCalculator;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.cost.PenitipanCostCalculator;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.TipeHewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.HewanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.repository.PenitipanRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.payment.PaymentService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.payment.PaymentServiceImpl;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

class PaymentServiceImplTest {
    private PaymentService paymentService;
    @Mock
    private PenitipanRepository penitipanRepository;
    @Mock
    private HewanRepository hewanRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        paymentService = new PaymentServiceImpl();
    }

    @Test
    void testCalculatePrice_UnverifiedPenitipan() {
        // Arrange
        Hewan hewan = new Hewan();
        hewan.setTipeHewan(TipeHewan.DOG);
        hewan.setBeratHewan(1);

        Penitipan penitipan = new Penitipan();
        penitipan.setHewan(hewan);
        penitipan.setStatusPenitipan(StatusPenitipan.UNVERIFIED_PENITIPAN);
        penitipan.setTanggalPenitipan(LocalDateTime.of(2023, 5, 1, 10, 0));
        penitipan.setTanggalPengambilan(LocalDateTime.of(2023, 5, 1, 11, 0));
        Double expectedPrice = 24000.00;

        // Act
        Double actualPrice = paymentService.calculatePrice(penitipan);

        // Assert
        Assertions.assertEquals(expectedPrice, actualPrice);
    }

}
