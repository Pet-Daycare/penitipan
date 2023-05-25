package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.cost;

public class OtherCostCalculator implements PenitipanCostCalculator{

    @Override
    public Double getInitialCost(Double lamaJamTitip, Integer beratHewan) {
        Double costTitip = lamaJamTitip * 0.3;
        Double costBerat = (double) (beratHewan * 2);
        return (costBerat + costTitip)*30000;
    }
}
