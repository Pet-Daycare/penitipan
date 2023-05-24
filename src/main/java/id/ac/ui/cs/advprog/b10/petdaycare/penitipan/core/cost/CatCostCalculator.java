package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.cost;

public class CatCostCalculator implements PenitipanCostCalculator{

    @Override
    public Double getInitialCost(Double lamaJamTitip, Integer beratHewan) {
        Double costTitip = lamaJamTitip * 0.3;
        Double costBerat = (beratHewan*0.2);
        return (costBerat + costTitip)*20000;
    }
}
