/**
 * Sesi 5 — polimorfisme.
 * Kelas induk menetapkan KONTRAK; turunan mengisi caranya.
 */
public abstract class BangunDatar {

    private final String nama;

    protected BangunDatar(String nama) { this.nama = nama; }

    /** Kontrak: setiap bangun datar wajib bisa menghitung luas dan kelilingnya. */
    public abstract double luas();
    public abstract double keliling();

    public String getNama() { return nama; }

    /**
     * Polimorfisme bekerja di sini.
     * Method toString() didefinisikan di kelas induk, tetapi memanggil luas()
     * dan keliling() yang diimplementasikan di kelas turunan.
     *
     * Saat objek BangunDatar dipanggil melalui referensi ke subclass,
     * Java melakukan dynamic dispatch: method yang dijalankan adalah
     * implementasi override dari objek nyata, bukan versi dari kelas induk.
     *
     * Inilah inti dari polimorfisme: objek "tahu" cara kerjanya sendiri.
     */
    @Override
    public String toString() {
        return String.format("%-12s luas=%10.2f  keliling=%10.2f", nama, luas(), keliling());
    }
}
