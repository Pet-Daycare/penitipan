package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.cost.*;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.TipeHewan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    PenitipanCostCalculator penitipanCost;

    // todo : post payment
    @Override
    public Double calculateInitialPrice(PenitipanRequest penitipanRequest) {
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
        Double initialPayment = penitipanCost.getInitialCost(penitipanRequest);
            return initialPayment;
    }

    @Override
    public Double calculateAmbilPrice(PenitipanRequest penitipanRequest) {
        return null;
    }
}
