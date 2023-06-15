package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Core.cost;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.cost.CatCostCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CatCostCalculatorTest {

    private CatCostCalculator catCostCalculator;

    @BeforeEach
    void setUp() {
        catCostCalculator = new CatCostCalculator();
    }

    @Test
    void testGetInitialCost() {
        Double lamaJamTitip = 1.0;
        Integer beratHewan = 1;
        Double expectedCost = 10000.00;

        Double actualCost = catCostCalculator.getInitialCost(lamaJamTitip, beratHewan);

        Assertions.assertEquals(expectedCost, actualCost);
    }
}
