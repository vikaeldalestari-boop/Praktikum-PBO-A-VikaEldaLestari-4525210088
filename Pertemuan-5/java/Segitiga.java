public class Segitiga extends BangunDatar {
    private final double alas;
    private final double tinggi;

    public Segitiga(double alas, double tinggi) {
        super("Segitiga");
        if (alas <= 0 || tinggi <= 0) {
            throw new IllegalArgumentException("Alas dan tinggi harus lebih dari 0");
        }
        this.alas = alas;
        this.tinggi = tinggi;
    }

    @Override
    public double luas() {
        return 0.5 * alas * tinggi;
    }

    @Override
    public double keliling() {
        double sisiMiring = Math.hypot(alas / 2, tinggi);
        return alas + 2 * sisiMiring;
    }
}
