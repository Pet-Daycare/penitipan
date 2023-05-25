package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.cost;

public class DogCostCalculator implements PenitipanCostCalculator{

    @Override
    public Double getInitialCost(Double lamaJamTitip, Integer beratHewan) {
        Double costTitip = lamaJamTitip * 0.2;
        Double costBerat = (double) (beratHewan);
        return (costBerat + costTitip)*20000;
    }
}
