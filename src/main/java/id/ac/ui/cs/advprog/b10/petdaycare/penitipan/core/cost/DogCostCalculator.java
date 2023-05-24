package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.cost;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;

import java.time.Duration;
import java.time.LocalDateTime;

public class DogCostCalculator implements PenitipanCostCalculator{

    @Override
    public Double getInitialCost(PenitipanRequest penitipanRequest) {
        LocalDateTime tanggalAmbil = penitipanRequest.getTanggalPengambilan();
        LocalDateTime tanggalTitip = penitipanRequest.getTanggalPenitipan();
        Duration lamaTitip = Duration.between(tanggalAmbil, tanggalTitip);
        double lamaJamTitip = lamaTitip.toHours();

        Integer beratHewan = penitipanRequest.getBeratHewan();

        Double costTitip = lamaJamTitip * 0.2;
        Double costBerat = (double) (beratHewan);
        return costBerat + costTitip;
    }

    @Override
    public Double getAmbilCost(PenitipanRequest penitipanRequest) {
        return null;
    }
}
