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
        Double lamaJamTitip = 1.0;
        Integer beratHewan = 1;
        Double expectedCost = 69000.0;

        Double actualCost = otherCostCalculator.getInitialCost(lamaJamTitip, beratHewan);

        Assertions.assertEquals(expectedCost, actualCost);
    }
}
