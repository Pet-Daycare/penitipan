package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.cost.*;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.TipeHewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    PenitipanCostCalculator penitipanCost;

    // todo : post payment
    @Override
    public Double calculatePrice(PenitipanRequest penitipanRequest) {
        TipeHewan tipeHewan = penitipanRequest.getTipeHewan();
        if (tipeHewan.equals("DOG") ){
            penitipanCost = new DogCostCalculator();
        } else if (tipeHewan.equals("CAT")) {
            penitipanCost = new CatCostCalculator();
        } else if (tipeHewan.equals("RABBIT")) {
            penitipanCost = new RabbitCostCalculator();
        } else if (tipeHewan.equals("Other")) {
            penitipanCost = new OtherCostCalculator();
        }

        StatusPenitipan statusPenitipan = penitipanRequest.getStatusPenitipan();
        LocalDateTime tanggalTitip = penitipanRequest.getTanggalPenitipan();
        Integer beratHewan = penitipanRequest.getBeratHewan();

        if (statusPenitipan == StatusPenitipan.UNVERIFIED_PENITIPAN) {
            LocalDateTime tanggalPengambilan = penitipanRequest.getTanggalPengambilan();
            Duration lamaTitip = Duration.between(tanggalPengambilan, tanggalTitip);
            double lamaJamTitip = lamaTitip.toHours();
            return penitipanCost.getInitialCost(lamaJamTitip, beratHewan);
        } else {
            LocalDateTime tanggalDiambil = penitipanRequest.getTanggalDiambil();
            Duration lamaTitip = Duration.between(tanggalDiambil, tanggalTitip);
            double lamaJamTitip = lamaTitip.toHours();

            Double totalCost = penitipanCost.getInitialCost(lamaJamTitip, beratHewan);

            if (statusPenitipan == StatusPenitipan.PENGAMBILAN_AWAL){
                return totalCost * -0.1;
            } else if (statusPenitipan == StatusPenitipan.PENGAMBILAN_TERLAMBAT){
                return totalCost * 0.1;
            } else if (statusPenitipan == StatusPenitipan.PENGAMBILAN_TEPAT){
                return 0.0;
            }
            return null;
        }

    }

}
