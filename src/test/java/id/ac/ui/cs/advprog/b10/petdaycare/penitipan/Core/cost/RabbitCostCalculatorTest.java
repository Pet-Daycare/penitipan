package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Core.cost;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.cost.RabbitCostCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RabbitCostCalculatorTest {

    private RabbitCostCalculator rabbitCostCalculator;

    @BeforeEach
    void setUp() {
        rabbitCostCalculator = new RabbitCostCalculator();
    }

    @Test
    void testGetInitialCost() {
        Double lamaJamTitip = 2.5;
        Integer beratHewan = 3;
        Double expectedCost = 150000.0;

        Double actualCost = rabbitCostCalculator.getInitialCost(lamaJamTitip, beratHewan);

        Assertions.assertEquals(expectedCost, actualCost);
    }
}
