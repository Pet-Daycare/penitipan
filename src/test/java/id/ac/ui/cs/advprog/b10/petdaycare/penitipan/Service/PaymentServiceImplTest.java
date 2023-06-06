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
        MockitoAnnotations.openMocks(this);
        paymentService = new PaymentServiceImpl();
    }

    @Test
    void testCalculatePrice_UnverifiedPenitipan() {
        Hewan hewan = new Hewan();
        hewan.setTipeHewan(TipeHewan.DOG);
        hewan.setBeratHewan(1);

        Penitipan penitipan = new Penitipan();
        penitipan.setHewan(hewan);
        penitipan.setStatusPenitipan(StatusPenitipan.UNVERIFIED_PENITIPAN);
        penitipan.setTanggalPenitipan(LocalDateTime.of(2023, 5, 1, 10, 0));
        penitipan.setTanggalPengambilan(LocalDateTime.of(2023, 5, 1, 11, 0));
        Double expectedPrice = 24000.00;

        Double actualPrice = paymentService.calculatePrice(penitipan);

        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void testCalculatePrice_CatCostCalculator() {
        Hewan hewan = new Hewan();
        hewan.setTipeHewan(TipeHewan.CAT);
        hewan.setBeratHewan(1);

        Penitipan penitipan = new Penitipan();
        penitipan.setHewan(hewan);
        penitipan.setStatusPenitipan(StatusPenitipan.UNVERIFIED_PENITIPAN);
        penitipan.setTanggalPenitipan(LocalDateTime.of(2023, 5, 1, 10, 0));
        penitipan.setTanggalPengambilan(LocalDateTime.of(2023, 5, 1, 11, 0));
        Double expectedPrice = 10000.00;

        Double actualPrice = paymentService.calculatePrice(penitipan);

        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void testCalculatePrice_RabbitCostCalculator() {
        Hewan hewan = new Hewan();
        hewan.setTipeHewan(TipeHewan.RABBIT);
        hewan.setBeratHewan(1);

        Penitipan penitipan = new Penitipan();
        penitipan.setHewan(hewan);
        penitipan.setStatusPenitipan(StatusPenitipan.UNVERIFIED_PENITIPAN);
        penitipan.setTanggalPenitipan(LocalDateTime.of(2023, 5, 1, 10, 0));
        penitipan.setTanggalPengambilan(LocalDateTime.of(2023, 5, 1, 11, 0));
        Double expectedPrice = 26000.00;

        Double actualPrice = paymentService.calculatePrice(penitipan);

        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void testCalculatePrice_OtherCostCalculator() {
        Hewan hewan = new Hewan();
        hewan.setTipeHewan(TipeHewan.OTHER);
        hewan.setBeratHewan(1);

        Penitipan penitipan = new Penitipan();
        penitipan.setHewan(hewan);
        penitipan.setStatusPenitipan(StatusPenitipan.UNVERIFIED_PENITIPAN);
        penitipan.setTanggalPenitipan(LocalDateTime.of(2023, 5, 1, 10, 0));
        penitipan.setTanggalPengambilan(LocalDateTime.of(2023, 5, 1, 11, 0));
        Double expectedPrice = 69000.00;

        Double actualPrice = paymentService.calculatePrice(penitipan);

        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void testCalculateCompletionCost_PENGAMBILAN_AWAL(){
        Hewan hewan = new Hewan();
        hewan.setTipeHewan(TipeHewan.CAT);
        hewan.setBeratHewan(1);

        Penitipan penitipan = new Penitipan();
        penitipan.setHewan(hewan);
        penitipan.setStatusPenitipan(StatusPenitipan.PENGAMBILAN_AWAL);
        penitipan.setTanggalPenitipan(LocalDateTime.of(2023, 5, 1, 10, 0));
        penitipan.setTanggalPengambilan(LocalDateTime.of(2023, 5, 10, 10, 0));
        penitipan.setTanggalDiambil(LocalDateTime.of(2023, 5, 9, 10, 0));
        Double expectedPrice = -148000.00;

        Double actualPrice = paymentService.calculatePrice(penitipan);

        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void testCalculateCompletionCost_PENGAMBILAN_TERLAMBAT(){
        Hewan hewan = new Hewan();
        hewan.setTipeHewan(TipeHewan.CAT);
        hewan.setBeratHewan(1);

        Penitipan penitipan = new Penitipan();
        penitipan.setHewan(hewan);
        penitipan.setStatusPenitipan(StatusPenitipan.PENGAMBILAN_TERLAMBAT);
        penitipan.setTanggalPenitipan(LocalDateTime.of(2023, 5, 1, 10, 0));
        penitipan.setTanggalPengambilan(LocalDateTime.of(2023, 5, 10, 10, 0));
        penitipan.setTanggalDiambil(LocalDateTime.of(2023, 5, 11, 10, 0));
        Double expectedPrice = 148000.00;

        Double actualPrice = paymentService.calculatePrice(penitipan);

        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void testCalculateCompletionCost_PENGAMBILAN_TEPAT(){
        Hewan hewan = new Hewan();
        hewan.setTipeHewan(TipeHewan.CAT);
        hewan.setBeratHewan(1);

        Penitipan penitipan = new Penitipan();
        penitipan.setHewan(hewan);
        penitipan.setStatusPenitipan(StatusPenitipan.PENGAMBILAN_TEPAT);
        penitipan.setTanggalPenitipan(LocalDateTime.of(2023, 5, 1, 10, 0));
        penitipan.setTanggalPengambilan(LocalDateTime.of(2023, 5, 10, 10, 0));
        penitipan.setTanggalDiambil(LocalDateTime.of(2023, 5, 9, 10, 0));
        Double expectedPrice = 0.0;

        Double actualPrice = paymentService.calculatePrice(penitipan);

        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void testCalculateCompletionCost_OTHER_STATUS(){
        Hewan hewan = new Hewan();
        hewan.setTipeHewan(TipeHewan.CAT);
        hewan.setBeratHewan(1);

        Penitipan penitipan = new Penitipan();
        penitipan.setHewan(hewan);
        penitipan.setStatusPenitipan(StatusPenitipan.CANCELED_PENITIPAN);
        penitipan.setTanggalPenitipan(LocalDateTime.of(2023, 5, 1, 10, 0));
        penitipan.setTanggalPengambilan(LocalDateTime.of(2023, 5, 10, 10, 0));
        penitipan.setTanggalDiambil(LocalDateTime.of(2023, 5, 9, 10, 0));

        Double actualPrice = paymentService.calculatePrice(penitipan);

        Assertions.assertNull(actualPrice);
    }
}
