public class Trapesium extends BangunDatar {
    private final double sisiAtas;
    private final double sisiBawah;
    private final double tinggi;
    private final double sisiKiri;
    private final double sisiKanan;

    public Trapesium(double sisiAtas, double sisiBawah, double tinggi, double sisiKiri, double sisiKanan) {
        super("Trapesium");
        if (sisiAtas <= 0 || sisiBawah <= 0 || tinggi <= 0 || sisiKiri <= 0 || sisiKanan <= 0) {
            throw new IllegalArgumentException("Semua sisi dan tinggi harus lebih dari 0");
        }
        this.sisiAtas = sisiAtas;
        this.sisiBawah = sisiBawah;
        this.tinggi = tinggi;
        this.sisiKiri = sisiKiri;
        this.sisiKanan = sisiKanan;
    }

    @Override
    public double luas() {
        return ((sisiAtas + sisiBawah) * tinggi) / 2;
    }

    @Override
    public double keliling() {
        return sisiAtas + sisiBawah + sisiKiri + sisiKanan;
    }
}
