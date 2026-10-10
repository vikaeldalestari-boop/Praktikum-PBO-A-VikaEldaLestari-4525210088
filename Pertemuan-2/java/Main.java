class Mahasiswa {
    private final String nim;
    private final String nama;
    private final double tugas;
    private final double uts;
    private final double uas;

    public Mahasiswa(String nim, String nama, double tugas, double uts, double uas) {
        if (nim == null || nim.trim().isEmpty()) {
            throw new IllegalArgumentException("NIM tidak boleh kosong");
        }
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        validasiNilai("Tugas", tugas);
        validasiNilai("UTS", uts);
        validasiNilai("UAS", uas);

        this.nim = nim.trim();
        this.nama = nama.trim();
        this.tugas = tugas;
        this.uts = uts;
        this.uas = uas;
    }

    private void validasiNilai(String label, double nilai) {
        if (nilai < 0 || nilai > 100) {
            throw new IllegalArgumentException(label + " harus berada di antara 0 dan 100");
        }
    }

    public double hitungNilaiAkhir() {
        return (0.2 * tugas) + (0.3 * uts) + (0.5 * uas);
    }

    @Override
    public String toString() {
        return nim + " | " + nama + " | " + String.format("%.2f", hitungNilaiAkhir());
    }
}

/**
 * Program uji — JANGAN DIUBAH pada Langkah 1 sampai 4.
 * Kalau kode Anda benar, seluruh keluaran di bawah akan masuk akal.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("=== Rekap Nilai ===");
        Mahasiswa[] kelas = {
            new Mahasiswa("2024001", "Ani Lestari",  85, 78, 90),
            new Mahasiswa("2024002", "Budi Santoso", 60, 55, 62),
            new Mahasiswa("2024003", "Citra Wijaya", 92, 88, 95)
        };
        for (Mahasiswa m : kelas) {
            System.out.println("  " + m);
        }

        System.out.println();
        System.out.println("=== Objek menolak data yang melanggar aturan ===");

        try {
            new Mahasiswa("2024004", "Salah Nilai", 150, 80, 80);
            System.out.println("  MASALAH: nilai 150 seharusnya ditolak!");
        } catch (IllegalArgumentException e) {
            System.out.println("  Ditolak: " + e.getMessage());
        }

        try {
            new Mahasiswa("", "NIM Kosong", 80, 80, 80);
            System.out.println("  MASALAH: NIM kosong seharusnya ditolak!");
        } catch (IllegalArgumentException e) {
            System.out.println("  Ditolak: " + e.getMessage());
        }
    }
}
