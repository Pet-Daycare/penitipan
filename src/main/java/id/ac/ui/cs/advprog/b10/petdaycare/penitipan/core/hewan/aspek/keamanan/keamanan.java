package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.core.hewan.aspek.keamanan;

public interface keamanan {
    public Double getSpeed(); // in GHz
    public Double getPrice();
    public default String getName() {
        return this.getClass().getSimpleName();
    }

}
