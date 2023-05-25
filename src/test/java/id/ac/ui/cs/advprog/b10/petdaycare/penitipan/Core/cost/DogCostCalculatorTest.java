package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Core.cost;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.cost.DogCostCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DogCostCalculatorTest {

    private DogCostCalculator dogCostCalculator;

    @BeforeEach
    void setUp() {
        dogCostCalculator = new DogCostCalculator();
    }

    @Test
    void testGetInitialCost() {
        Double lamaJamTitip = 4.0;
        Integer beratHewan = 7;
        Double expectedCost = 176000.0;

        Double actualCost = dogCostCalculator.getInitialCost(lamaJamTitip, beratHewan);

        Assertions.assertEquals(expectedCost, actualCost);
    }
}
