package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.cost.*;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.TipeHewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    PenitipanCostCalculator penitipanCost;

    @Override
    public Double calculatePrice(Penitipan penitipan) {
        TipeHewan tipeHewan = penitipan.getHewan().getTipeHewan();
        if (tipeHewan.equals(TipeHewan.DOG) ){
            penitipanCost = new DogCostCalculator();
        } else if (tipeHewan.equals(TipeHewan.CAT)) {
            penitipanCost = new CatCostCalculator();
        } else if (tipeHewan.equals(TipeHewan.RABBIT)) {
            penitipanCost = new RabbitCostCalculator();
        } else if (tipeHewan.equals(TipeHewan.OTHER)) {
            penitipanCost = new OtherCostCalculator();
        }

        StatusPenitipan statusPenitipan = penitipan.getStatusPenitipan();
        LocalDateTime tanggalTitip = penitipan.getTanggalPenitipan();
        Integer beratHewan = penitipan.getHewan().getBeratHewan();

        if (statusPenitipan == StatusPenitipan.UNVERIFIED_PENITIPAN) {
            LocalDateTime tanggalPengambilan = penitipan.getTanggalPengambilan();
            Duration lamaTitip = Duration.between(tanggalTitip,tanggalPengambilan);
            double lamaJamTitip = lamaTitip.toHours();
            return penitipanCost.getInitialCost(lamaJamTitip, beratHewan);
        } else {
            LocalDateTime tanggalDiambil = penitipan.getTanggalDiambil();
            Duration lamaTitip = Duration.between(tanggalTitip,tanggalDiambil);
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
