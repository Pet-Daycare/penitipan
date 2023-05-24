package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.cost;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;

public interface PenitipanCostCalculator {
    Double getInitialCost(PenitipanRequest penitipanRequest);
    Double getAmbilCost(PenitipanRequest penitipanRequest);
}
