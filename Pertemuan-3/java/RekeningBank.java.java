public class RekeningBank {

    // TODO 1: ganti tiga angka ajaib berikut menjadi konstanta bernama
    //         (public static final). Setelah itu, tidak boleh ada lagi
    //         angka literal di dalam badan method.
    //   - bunga tahunan          : 0.025
    //   - biaya administrasi     : 5000
    //   - batas penarikan sekali : 5000000
    public static final double BUNGA_TAHUNAN = 0.025;
    public static final double BIAYA_ADMINISTRASI = 5000;
    public static final double BATAS_PENARIKAN_SEKALI = 5_000_000;

    // TODO 2: deklarasikan field statis penghitung jumlah rekening.
    //         Perhatikan: static, privat, dan bernilai awal 0.
    private static int jumlahRekening = 0;

    private final String nomor;
    private final String pemilik;
    private double saldo;

    /**
     * Constructor ringkas.
     * TODO 3: delegasi ke constructor lengkap.
     */
    public RekeningBank(String nomor, String pemilik) {
        this(nomor, pemilik, 0);
    }

    /** Constructor lengkap — SATU-SATUNYA tempat validasi berada. */
    public RekeningBank(String nomor, String pemilik, double saldoAwal) {
        // TODO 4: tolak nomor kosong dan saldo awal negatif.
        if (nomor == null || nomor.isBlank()) {
            throw new IllegalArgumentException("Nomor rekening tidak boleh kosong");
        }
        if (saldoAwal < 0) {
            throw new IllegalArgumentException("Saldo awal tidak boleh negatif");
        }

        this.nomor = nomor;
        this.pemilik = pemilik;
        this.saldo = saldoAwal;

        // TODO 5: naikkan penghitung jumlah rekening DI SINI SAJA.
        //         Pikirkan mengapa bukan di kedua constructor.
        jumlahRekening++;
    }

    public void setor(double jumlah) {
        // TODO 6: tolak jumlah <= 0, lalu tambahkan ke saldo.
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah setoran harus lebih besar dari 0");
        }
        saldo += jumlah;
    }

    public void tarik(double jumlah) {
        // TODO 7: tolak jumlah <= 0, tolak jika melebihi saldo,
        //         dan tolak jika melebihi batas penarikan sekali transaksi.
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah penarikan harus lebih besar dari 0");
        }
        if (jumlah > BATAS_PENARIKAN_SEKALI) {
            throw new RuntimeException("Penarikan melebihi batas sekali transaksi (Rp"
                    + String.format("%,.2f", BATAS_PENARIKAN_SEKALI) + ")");
        }
        if (jumlah > saldo) {
            throw new RuntimeException("Saldo tidak mencukupi. Saldo saat ini: Rp"
                    + String.format("%,.2f", saldo));
        }
        saldo -= jumlah;
    }

    /** TODO 8 */
    public void potongBiayaAdmin() {
        saldo -= BIAYA_ADMINISTRASI;
        if (saldo < 0) {
            saldo = 0;
        }
    }

    /** TODO 9 */
    public static int getJumlahRekening() {
        return jumlahRekening;
    }

    /** TODO 10 */
    public static double bungaSetahun(double pokok) {
        return pokok * BUNGA_TAHUNAN;
    }

    public double getSaldo()  { return saldo; }
    public String getNomor()  { return nomor; }

    public String toString() {
        return String.format("Rekening[%s] %-14s Rp%,.2f", nomor, pemilik, saldo);
    }
}