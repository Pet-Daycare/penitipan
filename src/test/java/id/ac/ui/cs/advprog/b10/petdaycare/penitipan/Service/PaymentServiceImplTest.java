package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Service;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.cost.DogCostCalculator;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.cost.PenitipanCostCalculator;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.TipeHewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.payment.PaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

class PaymentServiceImplTest {
    private PenitipanCostCalculator penitipanCostCalculator;
    private PaymentService paymentService;

    @BeforeEach
    public void setup() {
    }

    @Test
    void testCalculatePrice_UnverifiedPenitipan() {
        // Arrange
        penitipanCostCalculator = new DogCostCalculator();
        Hewan hewan = new Hewan();
        hewan.setTipeHewan(TipeHewan.DOG);
        hewan.setBeratHewan(1);

        Penitipan penitipan = new Penitipan();
        penitipan.setHewan(hewan);
        penitipan.setStatusPenitipan(StatusPenitipan.UNVERIFIED_PENITIPAN);
        penitipan.setTanggalPenitipan(LocalDateTime.of(2023, 5, 1, 10, 0));
        penitipan.setTanggalPengambilan(LocalDateTime.of(2023, 5, 1, 11, 0));
        Double expectedPrice = 12000.0;

        // Act
        Double actualPrice = paymentService.calculatePrice(penitipan);

        // Assert
        Assertions.assertEquals(expectedPrice, actualPrice);
    }

}
