# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
| :--- | :--- |
| **Nama** | Vika Elda Lestari |
| **NPM** | 4525210088 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | 10 - Exception Handling dan Pemrograman Defensif |
| **Tanggal** | Kamis 5 November 2026 |

---

## 1. Implementasi Java

### 1.1. File: `Main.java`

**Penjelasan Kode:**
> Main.java menjalankan program lewat sebuah menu yang terus tampil selama pengguna belum memilih keluar. Pilihannya ada empat: tambah buku, tambah anggota, pinjam buku, dan keluar. Angka yang diketik dicocokkan memakai switch, lalu pekerjaannya diserahkan ke PerpustakaanService. Kalau angkanya di luar 0-3, program menampilkan peringatan. Setelah pengguna memilih keluar, method eksporLaporan() menulis daftar buku (ISBN, judul, stok, dan status Tersedia atau Tidak Tersedia) ke sebuah file teks.

**Kode Program:**

```java
package id.ac.univpancasila.perpustakaan;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import  id.ac.univpancasila.perpustakaan.service.*;


public class Main {
    public static void main(String[] args) {
        LibraryService libService = new LibraryService();
        Scanner scanner = new Scanner(System.in);
        boolean berjalan = true;

        while (berjalan) {
            System.out.println("\n=== SISTEM PERPUSTAKAAN ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tambah Anggota");
            System.out.println("3. Pinjam Buku");
            System.out.println("0. Keluar");
            System.out.println("Pilih menu (0-3): ");

            String pilihan = scanner.nextLine().trim();

            switch (pilihan) {
                case "1":
                    PerpustakaanService.tambahBuku(libService, scanner);
                    break;
                case "2":
                    PerpustakaanService.tambahAnggota(libService, scanner);
                    break;
                case "3":
                    PerpustakaanService.pinjamBuku(libService, scanner);
                    break;
                case "0":
                    System.out.println("Terima kasih telah menggunakan sistem perpustakaan.");
                    berjalan = false;
                    break;
                default:
                    System.out.println("[Peringatan] Pilihan menu tidak valid! Silakan masukkan angka 0 - 3.");
            }
        }
        
        System.out.println("\nMengekspor daftar transaksi ke berkas...");
        eksporLaporan(libService);

        scanner.close();
    }

    private static void eksporLaporan(LibraryService service) {
        System.out.println("\n--- Ekspor Laporan Buku ---");
        String namaFile = "laporan_buku.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(namaFile))) {
            writer.println("==========================================================================");
            writer.println("                    LAPORAN DAFTAR BUKU PERPUSTAKAAN                      ");
            writer.println("==========================================================================");
            writer.printf("%-12s | %-35s | %-8s | %-15s%n", "ISBN", "JUDUL BUKU", "STOK", "STATUS");
            writer.println("--------------------------------------------------------------------------");

            Map<String, String> daftarJudul = service.getSemuaJudul();

            if (daftarJudul.isEmpty()) {
                writer.println("Belum ada data buku dalam sistem.");
            } else {
                for (Map.Entry<String, String> entry : daftarJudul.entrySet()) {
                    String isbn = entry.getKey();
                    String judul = entry.getValue();
                    int stokSisa = service.sisaEksemplar(isbn);

                    String status = (stokSisa > 0) ? "Tersedia" : "Tidak Tersedia";

                    writer.printf("%-12s | %-35s | %-8d | %-15s%n", isbn, judul, stokSisa, status);
                }
            }

            writer.println("==========================================================================");
            System.out.println(">> BERHASIL: Laporan buku beserta statusnya disimpan ke berkas '" + namaFile + "'.");

        } catch (IOException e) {
            System.out.println("[ERROR FILE] Gagal menulis laporan ke berkas: " + e.getMessage());
        }
    }
}
```


### 1.2. File: `AnggotaTidakDitemukanException.java`

**Penjelasan Kode:**
> Exception buatan sendiri yang dilempar kalau ID anggota yang dipakai belum pernah didaftarkan. Kelas ini turunan dari PerpustakaanException. ID yang bermasalah disimpan di atribut private final dan bisa diambil lewat getIdAnggota(), jadi bagian kode yang menangkap exception ini tahu persis ID mana yang salah.

**Kode Program:**

```java
package id.ac.univpancasila.perpustakaan.exception;

/**
 * TODO: lengkapi mengikuti pola BukuTidakDitemukanException.
 *       Exception ini membawa idAnggota.
 */
public class AnggotaTidakDitemukanException extends PerpustakaanException {
    private final String idAnggota;
    public AnggotaTidakDitemukanException(String idAnggota) {
        super("Anggota dengan ID " + idAnggota + " tidak ditemukan");
        this.idAnggota = idAnggota;
    }

    public String getIdAnggota(){
        return this.idAnggota;
    }
}
```

### 1.3. File: `BatasPinjamTerlampauiException.java`

**Penjelasan Kode:**
> Exception ini dilempar kalau seorang anggota sudah meminjam sebanyak batas maksimal. Ada dua data yang dibawa, yaitu idAnggota dan batas, dan keduanya punya getter. Karena angka batasnya ikut tersimpan di dalam exception, bagian yang menangkapnya bisa langsung memakai angka itu untuk membuat pesan, tanpa harus tahu aturan peminjaman.

**Kode Program:**

```java
package id.ac.univpancasila.perpustakaan.exception;

/**
 * TODO: exception ini membawa idAnggota dan batas maksimum.
 *       Menyertakan batas di dalam exception memudahkan pemanggil
 *       menyusun pesan tanpa harus mengetahui aturannya sendiri.
 */
public class BatasPinjamTerlampauiException extends PerpustakaanException {
    private  final String idAnggota;
    private final int batas;

    public BatasPinjamTerlampauiException(String idAnggota, int batas) {
        super("Anggota dengan ID " + idAnggota + " telah mencapai batas maksimum peminjaman");
        this.idAnggota = idAnggota;
        this.batas = batas;
    }

    public String getIdAnggota(){
        return this.idAnggota;
    }

    public int getBatas(){
        return this.batas;
    }
}
```

### 1.4. File: `BukuSedangDipinjamException.java`

**Penjelasan Kode:**
> Exception ini dilempar kalau buku yang diminta sudah tidak punya eksemplar tersisa karena semuanya sedang dipinjam. Isbn disimpan supaya bisa dipakai oleh sistem, sedangkan judul disimpan supaya pesan untuk pengguna lebih enak dibaca. Masing-masing punya getter sendiri.

**Kode Program:**

```java
package id.ac.univpancasila.perpustakaan.exception;

/**
 * TODO: exception ini membawa DUA data — isbn dan judul.
 *       Judul disertakan agar pesan untuk pengguna lebih berarti;
 *       isbn disertakan agar sistem dapat mengolahnya.
 */
public class BukuSedangDipinjamException extends PerpustakaanException {
    private final String isbn;
    private  final String judul;

    public BukuSedangDipinjamException(String isbn, String judul) {
        super("Buku " + judul + " dengan ISBN  " + isbn + " sedang dipinjam");
        this.isbn = isbn;
        this.judul = judul;
    }

    public String getIsbn(){
        return this.isbn;
    }

    public String getJudul(){
        return this.judul;
    }
}
```

### 1.5. File: `BukuTidakDitemukanException.java`

**Penjelasan Kode:**
> Exception ini dilempar ketika ISBN yang dicari tidak ada di katalog. ISBN-nya disimpan sebagai atribut sendiri, bukan hanya terselip di dalam kalimat pesan, dan bisa diambil lewat getIsbn(). Jadi penyebab error tetap bisa dibaca sebagai data yang rapi, bukan sekadar teks.

**Kode Program:**

```java
package id.ac.univpancasila.perpustakaan.exception;

/**
 * EXCEPTION YANG MEMBAWA DATA — bukan sekadar teks.
 *
 * Perhatikan atribut isbn di bawah. Itulah yang membedakan exception ini
 * dari exception biasa: pemanggil bisa MENGAMBIL data penyebabnya kembali,
 * bukan harus mengurai kalimat pesan.
 *
 * Pada sesi 13 dan 14, data inilah yang menjadi isi badan respons HTTP 404.
 */
public class BukuTidakDitemukanException extends PerpustakaanException {

    // TODO 1: deklarasikan atribut privat final untuk isbn.
    private final String isbn;

    public BukuTidakDitemukanException(String isbn) {
        // TODO 2: panggil super(...) dengan pesan yang menyebut ISBN-nya,
        //         misalnya: "Buku dengan ISBN 978-1 tidak ditemukan"
        super("Buku dengan ISBN " + isbn + " tidak ditemukan");

        // TODO 3: simpan isbn ke atribut.
        this.isbn = isbn;
    }

    // TODO 4: sediakan getter getIsbn().
    public String getIsbn(){
        return this.isbn;
    }
}
```

### 1.6. File: `PerpustakaanException.java`

**Penjelasan Kode:**
> Ini kelas induk (abstract) untuk semua exception di sistem perpustakaan. Kelas ini turunan RuntimeException (unchecked), karena masalah yang ditangani muncul dari permintaan yang tidak sah, bukan dari gangguan luar yang memang wajar terjadi. Karena semua exception domain punya induk yang sama, cukup satu blok catch untuk menangkap semuanya, dan kesalahan pemrograman lain tidak ikut tertangkap.

**Kode Program:**

```java
package id.ac.univpancasila.perpustakaan.exception;

/**
 * Sesi 10 — induk seluruh exception domain perpustakaan.
 *
 * Manfaat punya induk bersama: pemanggil bisa menangkap SELURUH kegagalan
 * domain sekaligus dengan satu blok catch, tanpa ikut menelan kesalahan
 * pemrograman yang lain.
 *
 * TODO 1: putuskan — turunkan dari RuntimeException (unchecked) atau dari
 *         Exception (checked)? Tuliskan alasannya di skenario-uji.md.
 *         Petunjuk: kalau kegagalannya menandakan permintaan yang tidak sah
 *         dan bukan keadaan luar yang wajar, unchecked biasanya lebih tepat.
 */
public abstract class PerpustakaanException extends RuntimeException {

    protected PerpustakaanException(String pesan) {
        super(pesan);
    }

    protected PerpustakaanException(String pesan, Throwable penyebab) {
        super(pesan, penyebab);
    }
}
```

### 1.7. File: `LibraryService.java`

**Penjelasan Kode:**
> Data perpustakaan disimpan di tiga HashMap: stok, judul, dan pinjaman. Method pinjam() memakai guard clause. Empat syarat dicek satu per satu (anggota terdaftar, buku ada, stok masih ada, dan batas pinjam belum penuh), dan begitu ada yang tidak terpenuhi, exception yang cocok langsung dilempar. Proses utamanya, yaitu mengurangi stok dan menambah jumlah pinjaman anggota, ada di bagian paling bawah tanpa if bersarang, dan pemanggil bisa tahu alasan kenapa peminjaman gagal. Method lain di kelas ini adalah tambahBuku, daftarkanAnggota, sisaEksemplar, judulDari, dan getSemuaJudul.

**Kode Program:**

```java
package id.ac.univpancasila.perpustakaan.service;
/**
 * Sesi 10 Langkah 2 — BAHAN REFACTORING.
 *
 * Method pinjam() di bawah bersarang EMPAT tingkat. Bacalah, lalu rasakan
 * sendiri betapa sulitnya mengikuti alurnya.
 *
 * Tugas Anda: refaktor menjadi guard clause, simpan sebagai LibraryService.java.
 * JANGAN hapus berkas ini — keduanya diperlukan saat demo.
 *
 * Pertanyaan pemandu:
 *   1. Di mana letak logika utamanya? Berapa tingkat menjorok ke dalam?
 *   2. Apa yang terjadi kalau salah satu kondisi tidak terpenuhi?
 *      Apakah pemanggil bisa tahu ALASANNYA?
 *   3. Bagaimana Anda menambahkan satu aturan baru di tengah-tengah ini?
 */
import java.util.HashMap;
import java.util.Map;

import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BatasPinjamTerlampauiException;
import id.ac.univpancasila.perpustakaan.exception.BukuSedangDipinjamException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;

public class LibraryService {

    public static final int BATAS_PINJAM = 3;

    private final Map<String, Integer> stok = new HashMap<>();
    private final Map<String, String> judul = new HashMap<>();
    private final Map<String, Integer> pinjaman = new HashMap<>();

    public void tambahBuku(String isbn, String judulBuku, int eksemplar) {
        stok.merge(isbn, eksemplar, Integer::sum);
        judul.put(isbn, judulBuku);
    }

    public void daftarkanAnggota(String idAnggota) {
        pinjaman.putIfAbsent(idAnggota, 0);
    }

    /** INILAH yang harus Anda refaktor. */
    public boolean pinjam(String idAnggota, String isbn) {
        if (!pinjaman.containsKey(idAnggota)) {
            throw new AnggotaTidakDitemukanException(idAnggota);
        }

        if (!stok.containsKey(isbn)){
            throw new BukuTidakDitemukanException(isbn);
        }

        if (stok.get(isbn) <= 0){
            throw new BukuSedangDipinjamException(isbn, judul.get(isbn));
        }

        if (pinjaman.get(idAnggota) >= BATAS_PINJAM){
            throw new BatasPinjamTerlampauiException(idAnggota, BATAS_PINJAM);
        }

        stok.merge(isbn, -1, Integer::sum);
        pinjaman.merge(idAnggota, 1, Integer::sum);
        return true;
    }

    public int sisaEksemplar(String isbn) {
        return stok.getOrDefault(isbn, 0);
    }

    public String judulDari(String isbn) {
        return judul.get(isbn);
    }

    public Map<String, String> getSemuaJudul() {
        return this.judul;
    }
}
```

### 1.8. File: `LibraryServiceKotor.java`

**Penjelasan Kode:**
> Di versi ini method pinjam() ditulis dengan empat if yang bertumpuk dan hanya mengembalikan true atau false, jadi pemanggil tidak bisa tahu kenapa peminjaman gagal. File ini sengaja disimpan sebagai pembanding dengan LibraryService yang sudah dirapikan.

**Kode Program:**

```java
package id.ac.univpancasila.perpustakaan.service;
/**
 * Sesi 10 Langkah 2 — BAHAN REFACTORING.
 *
 * Method pinjam() di bawah bersarang EMPAT tingkat. Bacalah, lalu rasakan
 * sendiri betapa sulitnya mengikuti alurnya.
 *
 * Tugas Anda: refaktor menjadi guard clause, simpan sebagai LibraryService.java.
 * JANGAN hapus berkas ini — keduanya diperlukan saat demo.
 *
 * Pertanyaan pemandu:
 *   1. Di mana letak logika utamanya? Berapa tingkat menjorok ke dalam?
 *   2. Apa yang terjadi kalau salah satu kondisi tidak terpenuhi?
 *      Apakah pemanggil bisa tahu ALASANNYA?
 *   3. Bagaimana Anda menambahkan satu aturan baru di tengah-tengah ini?
 */
import java.util.HashMap;
import java.util.Map;

public class LibraryServiceKotor {

    public static final int BATAS_PINJAM = 3;

    private final Map<String, Integer> stok = new HashMap<>();
    private final Map<String, String> judul = new HashMap<>();
    private final Map<String, Integer> pinjaman = new HashMap<>();

    public void tambahBuku(String isbn, String judulBuku, int eksemplar) {
        stok.merge(isbn, eksemplar, Integer::sum);
        judul.put(isbn, judulBuku);
    }

    public void daftarkanAnggota(String idAnggota) {
        pinjaman.putIfAbsent(idAnggota, 0);
    }

    /** INILAH yang harus Anda refaktor. */
    public boolean pinjam(String idAnggota, String isbn) {
        if (pinjaman.containsKey(idAnggota)) {
            if (stok.containsKey(isbn)) {
                if (stok.get(isbn) > 0) {
                    if (pinjaman.get(idAnggota) < BATAS_PINJAM) {
                        stok.merge(isbn, -1, Integer::sum);
                        pinjaman.merge(idAnggota, 1, Integer::sum);
                        return true;
                    } else {
                        return false;   // kenapa gagal? pemanggil tidak tahu
                    }
                } else {
                    return false;       // kenapa gagal? pemanggil tidak tahu
                }
            } else {
                return false;           // kenapa gagal? pemanggil tidak tahu
            }
        } else {
            return false;               // kenapa gagal? pemanggil tidak tahu
        }
    }

    public int sisaEksemplar(String isbn) {
        return stok.getOrDefault(isbn, 0);
    }

    public String judulDari(String isbn) {
        return judul.get(isbn);
    }
}
```

### 1.9. File: `PerpustakaanService.java`

**Penjelasan Kode:**
> Kelas ini menjadi penghubung antara input dari pengguna dan LibraryService, isinya tiga method static. tambahAnggota mengecek supaya ID tidak kosong. tambahBuku mengecek ISBN dan jumlah eksemplar, yang harus angka lebih dari 0 (kalau input bukan angka, NumberFormatException ditangkap). pinjamBuku berjalan berulang sampai pengguna mengetik exit. Exception domain ditangkap lewat PerpustakaanException, lalu instanceof dipakai untuk memilih keterangan tambahan yang ditampilkan (ISBN, ID anggota, judul, atau batas pinjam). Exception di luar itu dianggap sebagai error sistem.

**Kode Program:**

```java
package id.ac.univpancasila.perpustakaan.service;


import java.util.Scanner;

import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BatasPinjamTerlampauiException;
import id.ac.univpancasila.perpustakaan.exception.BukuSedangDipinjamException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.PerpustakaanException;


public class PerpustakaanService {
    public static void tambahAnggota(LibraryService libService, Scanner scanner) {
        System.out.println("\n--- Form Tambah Anggota ---");
        System.out.println("Masukkan ID Anggota: ");
        String idAnggota = scanner.nextLine().trim();


        if (idAnggota.isEmpty()) {
            System.out.println("[Gagal] ID Anggota tidak boleh kosong!");
            return;
        }


        libService.daftarkanAnggota(idAnggota);
        System.out.println(">> BERHASIL: Anggota dengan ID '" + idAnggota + "' telah terdaftar.");
    }


   public static void tambahBuku(LibraryService libService, Scanner scanner) {
        System.out.println("\n--- Form Tambah Buku ---");
        System.out.print("Masukkan ISBN Buku: ");
        String isbn = scanner.nextLine().trim();


        if (isbn.isEmpty()) {
            System.out.println("[Gagal] ISBN tidak boleh kosong!");
            return;
        }


        System.out.print("Masukkan Judul Buku: ");
        String judul = scanner.nextLine().trim();


        System.out.print("Masukkan Jumlah Eksemplar: ");
        int eksemplar = 0;
        try {
            eksemplar = Integer.parseInt(scanner.nextLine().trim());
            if (eksemplar <= 0) {
                System.out.println("[Gagal] Jumlah eksemplar harus lebih dari 0!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("[Error Input] Jumlah eksemplar harus berupa angka!");
            return;
        }


        libService.tambahBuku(isbn, judul, eksemplar);
        System.out.println(">> BERHASIL: Buku '" + judul + "' (" + eksemplar + " eksemplar) ditambahkan.");
    }


    public  static void pinjamBuku(LibraryService libService, Scanner scanner){
        System.out.println("=== SISTEM PERPUSTAKAAN (PERT 10) ===");


        while (true) {
            try {
                System.out.println("\n --- Form Peminjaman Buku ---");
                System.out.print("Masukan ID Anggota (Ketik 'exit' untuk keluar): ");
                String idAngota = scanner.nextLine().trim();
               
                if (idAngota.equalsIgnoreCase("exit")){
                    break;
                }


                if (idAngota.isEmpty()){
                    System.out.println("[Peringatan] ID Anggota tidak boleh kosong!");
                }


                System.out.print("Masukan ISBN Buku: ");
                String isbn = scanner.nextLine().trim();


                libService.pinjam(idAngota, isbn);
                System.out.println("Peminjaman Berhasil!");
            } catch (PerpustakaanException e) {
                System.out.println("[Error Domain] " + e.getMessage());


                if (e instanceof BukuTidakDitemukanException ex){
                    System.out.println("ISBN bermasalah: " + ex.getIsbn());
                }else if(e instanceof AnggotaTidakDitemukanException ex){
                    System.out.println("Id Anggota bermasalah: " + ex.getIdAnggota());
                } else if(e instanceof BukuSedangDipinjamException ex){
                    System.out.println("  -> Judul Buku: " + ex.getJudul() + " (ISBN: " + ex.getIsbn() + ")");
                }else if(e instanceof BatasPinjamTerlampauiException ex) {
                    System.out.println("  -> Batas Maksimum: " + ex.getBatas() + " buku");
                }
            }catch(Exception e){
                System.out.println("[Error Sistem] Terjadi kesalahan input " + e.getMessage());
            }
        }
    }
}
```

### Output

**Output Program:**

![Output Java](image-1.png)
---

## 2. Implementasi PHP

### 2.1. File: `main.php`

**Penjelasan Kode:**
> main.php adalah program demo untuk versi PHP. Pertama autoloader Composer dimuat, lalu objek LibraryService dibuat dan diisi dua buku serta dua anggota. Setelah itu ada tujuh skenario peminjaman yang dijalankan satu per satu dengan foreach. Tiap skenario dibungkus try-catch supaya PerpustakaanException tertangkap, dan hasilnya dicetak sebagai BERHASIL atau GAGAL lengkap dengan alasannya. Fungsi createReport() menulis laporan buku ke file laporan_buku.txt memakai fopen dan fwrite. Blok try-catch-finally dipakai supaya file selalu ditutup dengan fclose, termasuk kalau di tengah jalan terjadi error.

**Kode Program:**

```php
<?php
require __DIR__ . '/vendor/autoload.php';


use Carbon\Carbon;
use Perpustakaan\Model\Anggota;
use Perpustakaan\Model\Buku;
use Perpustakaan\Model\Mahasiswa;
use Perpustakaan\Model\Jurusan;
use Perpustakaan\Service\PerpustakaanService;
use Perpustakaan\Service\LibraryService;
use Perpustakaan\Exception\PerpustakaanException;


/*
 * DEMO: hanya jalan kalau file ini dijalankan langsung (php LibraryService.php)
 */
$s = new LibraryService();
$s->tambahBuku('111', 'Laskar Pelangi', 1);
$s->tambahBuku('222', 'Bumi Manusia', 5);
$s->daftarkanAnggota('A-001');
$s->daftarkanAnggota('A-002');


$percobaan = [
    ['A-001', '111'],   // berhasil
    ['A-002', '111'],   // buku habi
    ['A-002', '999'],   // buku tidak ada
    ['X-999', '222'],   // anggota tidak terdaftar
    ['A-001', '222'],   // berhasil
    ['A-001', '222'],   // berhasil
    ['A-001', '222'],   // batas pinjam tercapai
];


foreach ($percobaan as [$id, $isbn]) {
    echo "Anggota $id meminjam ISBN '$isbn' ... " ,PHP_EOL;
    try {
        $s->pinjam($id, $isbn);
        echo "[BERHASIL]\n";
    } catch (PerpustakaanException $e) {
        echo "[GAGAL] " . $e->getMessage() . "\n";
    }
}


createReport($s);


function createReport(LibraryService $s){
    echo PHP_EOL . "--- Ekspor Laporan Buku ---" . PHP_EOL;
    $namaFile = 'laporan_buku.txt';
   
    // Membuka file dalam mode write ('w')
    $fileHandle = fopen(__DIR__ . '/' . $namaFile, 'w');


    if (!$fileHandle) {
        echo "[ERROR] Gagal membuka file untuk menulis.\n";
        exit(1);
    }


    try {
        fwrite($fileHandle, "==========================================================================\n");
        fwrite($fileHandle, "                    LAPORAN DAFTAR BUKU PERPUSTAKAAN                      \n");
        fwrite($fileHandle, "==========================================================================\n");
        fwrite($fileHandle, sprintf("%-12s | %-35s | %-8s | %-15s\n", "ISBN", "JUDUL BUKU", "STOK", "STATUS"));
        fwrite($fileHandle, "--------------------------------------------------------------------------\n");


        $daftarJudul = $s->getSemuaJudul();


        foreach ($daftarJudul as $isbn => $judulBuku) {
            $stokSisa = $s->sisaEksemplar($isbn);
            $status = ($stokSisa > 0) ? "Tersedia" : "Tidak Tersedia";
            fwrite($fileHandle, sprintf("%-12s | %-35s | %-8d | %-15s\n", $isbn, $judulBuku, $stokSisa, $status));
        }


        fwrite($fileHandle, "==========================================================================\n");


        // throw new \Exception("Simulasi error tak terduga di tengah penulisan file!");


        echo ">> BERHASIL: Laporan buku beserta statusnya disimpan ke berkas '{$namaFile}'.\n";


    } catch (\Exception $e) {
        echo "[ERROR SISTEM] Gagal menyelesaikan laporan: " . $e->getMessage() . "\n";
    } finally {
        if (is_resource($fileHandle)) {
            fclose($fileHandle);
            echo "[INFO] File '{$namaFile}' telah ditutup dengan aman melalui blok finally.\n";
        }
    }
}
```

### 2.2. File: `AnggotaTidakDitemukanException.php`

**Penjelasan Kode:**
> Exception ini dilempar kalau ID anggota belum terdaftar. Kelasnya final dan turunan PerpustakaanException. ID anggota disimpan dengan constructor property promotion (private readonly string $idAnggota) dan bisa diambil lewat getIdAnggota().

**Kode Program:**

```php
<?php
namespace Perpustakaan\Exception;
use Perpustakaan\Exception\PerpustakaanException;


final class AnggotaTidakDitemukanException extends PerpustakaanException
{
    // TODO 3: lengkapi mengikuti pola di atas. Membawa $idAnggota.
    public function __construct(private readonly string $idAnggota)
    {
        parent::__construct("Anggota dengan ID {$idAnggota} tidak ditemukan.");
    }


    public function getIdAnggota(): string
    {
        return $this->idAnggota;
    }
}
```

### 2.3. File: `BatasPinjamTerlampauiException.php`

**Penjelasan Kode:**
> Exception ini dilempar kalau anggota sudah meminjam sampai batas maksimal. $idAnggota dan $batas disimpan sebagai properti readonly. Pesannya memuat ID anggota dan jumlah buku yang diizinkan, dan kedua nilai itu bisa diambil lewat getIdAnggota() dan getBatas().

**Kode Program:**

```php
<?php
namespace Perpustakaan\Exception;


use Perpustakaan\Exception\PerpustakaanException;


final class BatasPinjamTerlampauiException extends PerpustakaanException
{
    // TODO 5: membawa $idAnggota dan $batas.
    public function __construct(
        private readonly string $idAnggota,
        private readonly int $batas,
    ) {
        parent::__construct(
            "Anggota {$idAnggota} telah mencapai batas peminjaman ({$batas} buku)."
        );
    }
public function getIdAnggota(): string
    {
        return $this->idAnggota;
    }


    public function getBatas(): int
    {
        return $this->batas;
    }
}
```

### 2.4. File: `BukuSedangDipinjamException.php`

**Penjelasan Kode:**
> Exception ini dilempar kalau semua eksemplar buku sedang dipinjam dan tidak ada yang tersisa. $isbn dan $judul disimpan sebagai properti readonly supaya pesannya jelas, dan keduanya bisa diambil kembali lewat getIsbn() dan getJudul().

**Kode Program:**

```php
<?php
namespace Perpustakaan\Exception;


use Perpustakaan\Exception\PerpustakaanException;


final class BukuSedangDipinjamException extends PerpustakaanException
{
    // TODO 4: membawa $isbn DAN $judul.
    public function __construct(
        private readonly string $isbn,
        private readonly string $judul,
    ) {
        parent::__construct("Buku \"{$judul}\" (ISBN {$isbn}) sedang dipinjam.");
    }


    public function getIsbn(): string
    {
        return $this->isbn;
    }


    public function getJudul(): string
    {
        return $this->judul;
    }
}
```

### 2.5. File: `BukuTidakDitemukanException.php`

**Penjelasan Kode:**
> Exception ini dilempar kalau ISBN yang dicari tidak ada di katalog. $isbn disimpan sebagai properti readonly lewat constructor property promotion, dan bisa dibaca dengan getIsbn().

**Kode Program:**

```php
<?php
namespace Perpustakaan\Exception;


use Perpustakaan\Exception\PerpustakaanException;


final class BukuTidakDitemukanException extends PerpustakaanException
{
    /**
     * TODO 1: gunakan constructor property promotion agar $isbn tersimpan
     *         sebagai properti readonly
     */
    public function __construct(private readonly string $isbn)
    {
        parent::__construct("Buku dengan ISBN {$isbn} tidak ditemukan.");
    }


    // TODO 2: sediakan getter getIsbn().
    public function getIsbn(): string
    {
        return $this->isbn;
    }
}
```

### 2.6. File: `PerpustakaanException.php`

**Penjelasan Kode:**
> Kelas abstract ini menjadi induk dari semua exception domain di versi PHP, dan merupakan turunan RuntimeException. Gunanya sebagai satu titik tangkap untuk semua kegagalan domain. PHP tidak punya checked exception, jadi penulisan @throws di PHPDoc jadi penting supaya orang yang membaca kode tahu exception apa saja yang mungkin muncul.

**Kode Program:**

```php
<?php


namespace Perpustakaan\Exception;
use RuntimeException;
/**
 * Sesi 10 — hierarki exception domain (PHP).
 *ewdd
 * PHP tidak mengenal checked exception; seluruh exception setara dengan
 * unchecked di Java. Karena itu anotasi @throws pada PbHPDoc menjadi jauh
 * lebih penting: pembaca kode tidak dibantu kompilator.
 */


abstract class PerpustakaanException extends RuntimeException
{
}
```

### 2.7. File: `LibraryService.php`

**Penjelasan Kode:**
> Data disimpan di tiga array, yaitu $stok, $judul, dan $pinjaman, dan ada konstanta BATAS_PINJAM bernilai 3. Method pinjam() memakai guard clause yang melempar exception sesuai penyebabnya (anggota tidak ditemukan, buku tidak ditemukan, buku sedang dipinjam, atau batas pinjam terlampaui), dan semuanya dicatat lewat @throws. Kalau semua syarat lolos, stok dikurangi dan jumlah pinjaman anggota ditambah. Method lainnya adalah tambahBuku, daftarkanAnggota, sisaEksemplar, dan getSemuaJudul.

**Kode Program:**

```php
<?php
namespace Perpustakaan\Service;


use Perpustakaan\Exception\AnggotaTidakDitemukanException;
use Perpustakaan\Exception\BukuTidakDitemukanException;
use Perpustakaan\Exception\BukuSedangDipinjamException;
use Perpustakaan\Exception\BatasPinjamTerlampauiException;


final class LibraryService
{
    public const int BATAS_PINJAM = 3;


    /** @var array<string,int> */
    private array $stok = [];
    /** @var array<string,string> */
    private array $judul = [];
    /** @var array<string,int> */
    private array $pinjaman = [];


    public function tambahBuku(string $isbn, string $judulBuku, int $eksemplar): void
    {
        $this->stok[$isbn] = ($this->stok[$isbn] ?? 0) + $eksemplar;
        $this->judul[$isbn] = $judulBuku;
    }


    public function daftarkanAnggota(string $idAnggota): void
    {
        $this->pinjaman[$idAnggota] ??= 0;
    }


    /**
     * @throws AnggotaTidakDitemukanException jika anggota belum terdaftar
     * @throws BukuTidakDitemukanException    jika ISBN tidak ada di katalog
     * @throws BukuSedangDipinjamException    jika tidak ada eksemplar tersisa
     * @throws BatasPinjamTerlampauiException jika anggota sudah mencapai batas
     */
    public function pinjam(string $idAnggota, string $isbn): void
    {
        if (!array_key_exists($idAnggota, $this->pinjaman)) {
            throw new AnggotaTidakDitemukanException($idAnggota);
        }


        if (!array_key_exists($isbn, $this->stok)) {
            throw new BukuTidakDitemukanException($isbn);
        }


        if ($this->stok[$isbn] <= 0) {
            throw new BukuSedangDipinjamException($isbn, $this->judul[$isbn]);
        }


        if ($this->pinjaman[$idAnggota] >= self::BATAS_PINJAM) {
            throw new BatasPinjamTerlampauiException($idAnggota, self::BATAS_PINJAM);
        }


        $this->stok[$isbn]--;
        $this->pinjaman[$idAnggota]++;
    }


    public function sisaEksemplar(string $isbn): int
    {
        return $this->stok[$isbn] ?? 0;
    }


    public function getSemuaJudul(): array
    {
        return $this->judul;
    }
}
```

### 2.8. File: `LibraryServiceKotor.php`

**Penjelasan Kode:**
> Method pinjam() di versi ini ditulis dengan empat if bertingkat dan hanya mengembalikan true atau false, jadi pemanggil tidak tahu apa penyebab peminjaman gagal. File ini disimpan sebagai pembanding dengan LibraryService.php.

**Kode Program:**

```php
<?php
declare(strict_types=1);


/**
 * BAHAN REFACTORING (PHP) — bandingkan dengan versi Java.
 */
final class LibraryServiceKotor
{
public const int BATAS_PINJAM = 3;


    /** @var array<string,int> */
    private array $stok = [];
    /** @var array<string,string> */
    private array $judul = [];
    /** @var array<string,int> */
    private array $pinjaman = [];


    public function tambahBuku(string $isbn, string $judulBuku, int $eksemplar): void
    {
        $this->stok[$isbn] = ($this->stok[$isbn] ?? 0) + $eksemplar;
        $this->judul[$isbn] = $judulBuku;
    }


    public function daftarkanAnggota(string $idAnggota): void
    {
        $this->pinjaman[$idAnggota] ??= 0;
    }


    /** INILAH yang harus Anda refaktor. */
    public function pinjam(string $idAnggota, string $isbn): bool
    {
        if (array_key_exists($idAnggota, $this->pinjaman)) {
            if (array_key_exists($isbn, $this->stok)) {
                if ($this->stok[$isbn] > 0) {
                    if ($this->pinjaman[$idAnggota] < self::BATAS_PINJAM) {
                        $this->stok[$isbn]--;
                        $this->pinjaman[$idAnggota]++;
                        return true;
                    } else {
                        return false;   // kenapa gagal? pemanggil tidak tahu
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public function sisaEksemplar(string $isbn): int
    {
        return $this->stok[$isbn] ?? 0;
    }
}
```

### 2.9. File: `PerpustakaanService.php`

**Penjelasan Kode:**
> tambahKoleksi() memasukkan buku ke dalam koleksi, cariKoleksi() mencari buku berdasarkan nomor induk dan mengembalikan null kalau tidak ketemu, sedangkan pinjam() dan kembalikan() hanya mencetak pesan transaksi milik anggota.

**Kode Program:**

```php
<?php
namespace Perpustakaan\Service;

use Perpustakaan\Model\Buku;

class PerpustakaanService 
{
    /**
     * @var Buku[]
     */
    private array $buku = [];

    public function tambahKoleksi(Buku $buku): void 
    {
        $this->buku[] = $buku;
    }

    public function cariKoleksi(string $nomorInduk): ?Buku 
    {
        foreach ($this->buku as $item) {
            if ($item->getNomorInduk() === $nomorInduk) {
                return $item;
            }
        }
        return null;
    }

    public function pinjam(string $nomorAnggota, string $nomorInduk): void 
    {
        echo "Anggota {$nomorAnggota} meminjam buku {$nomorInduk}\n";
    }

    public function kembalikan(string $nomorAnggota, string $nomorInduk): void 
    {
        echo "Anggota: {$nomorAnggota} mengembalikan buku {$nomorInduk}\n";
    }
}
```


### Output

**Output Program:**

![Ouput]!(image-2.png)

---

## 3. Kesimpulan

Dengan exception, kegagalan program jadi lebih jelas dibandingkan kalau fungsi hanya mengembalikan true atau false. Di LibraryService, setiap kegagalan peminjaman dilempar sebagai exception khusus yang membawa data penyebabnya, dan semuanya berinduk pada PerpustakaanException. Guard clause membuat alur kode lebih rapi daripada if yang bertumpuk. Validasi input, try-with-resources di Java, dan try-catch-finally di PHP juga membantu program tetap aman ketika ada kesalahan. Konsepnya sama di kedua bahasa, yang beda hanya cara penulisannya.