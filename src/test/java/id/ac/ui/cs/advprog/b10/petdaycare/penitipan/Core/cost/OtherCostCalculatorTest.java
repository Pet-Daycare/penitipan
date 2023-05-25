package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Core.cost;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.cost.OtherCostCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OtherCostCalculatorTest {

    private OtherCostCalculator otherCostCalculator;

    @BeforeEach
    void setUp() {
        otherCostCalculator = new OtherCostCalculator();
    }

    @Test
    void testGetInitialCost() {
        Double lamaJamTitip = 3.5;
        Integer beratHewan = 5;
        Double expectedCost = 330000.0;

        Double actualCost = otherCostCalculator.getInitialCost(lamaJamTitip, beratHewan);

        Assertions.assertEquals(expectedCost, actualCost);
    }
}
