# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
| :--- | :--- |
| **Nama** | Vika Elda Lestari |
| **NPM** | 4525210088 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | 09 - Komposisi dan Agregasi |
| **Tanggal** | Rabu 29 oktober 2026 |

---

## 1. Implementasi Java

### 1.1. File: `Main.java`

**Penjelasan Kode:**
> Main.java adalah kelas utama untuk menjalankan program. Di sini saya mencoba tiga hal: komposisi antara Buku dan Bab, agregasi antara Jurusan dan Mahasiswa, lalu simulasi peminjaman dan pengembalian buku di Perpustakaan Teknik. Hasilnya ditampilkan di console supaya perbedaan komposisi dan agregasi bisa langsung terlihat.

**Bukti Eksekusi (Screenshot):**
- **Program** ![codingan](image.png)

### 1.2. File: `Buku.java`

**Penjelasan Kode:**
> Buku.java menerapkan Komposisi. Objek Bab dibuat langsung di dalam kelas Buku, jadi Bab hanya ada selama Bukunya ada. Daftar bab juga tidak diberikan langsung ke luar, tapi berupa salinan (read-only). Karena itu, waktu saya coba mengubah koleksi bab dari luar, hasilnya ditolak.

**Bukti Eksekusi (Screenshot):**
- **Program** (image-1.png)

### 1.3. File: `Jurusan.java`

**Penjelasan Kode:**
> Jurusan.java menerapkan Agregasi. Objek Mahasiswa dibuat di luar kelas Jurusan, lalu baru dimasukkan ke dalamnya. Jadi waktu Budi pindah dari jurusan TI, jumlah mahasiswa berkurang dari 2 menjadi 1, tapi objek Budi masih ada dan datanya masih bisa diakses.

**Bukti Eksekusi (Screenshot):**
- **Program** (image-2.png)

### 1.4. File: `PerpustakaanService.java`

**Penjelasan Kode:**
> PerpustakaanService.java adalah service layer yang mengatur proses di perpustakaan, seperti mencari buku, meminjam, dan mengembalikan buku. Kelas ini hanya menyimpan referensi ke objek Buku dan Anggota, jadi hubungannya termasuk agregasi.

**Bukti Eksekusi (Screenshot):**
- **program** (image-3.png)

### Output

**Output Program:**

![Output](<WhatsApp Image 2026-11-25 at 15.37.01.jpeg>)

**Penjelasan Output:**
- Buku "Pemrograman Berorientasi Objek" punya 3 bab, yaitu Paradigma Objek, Enkapsulasi, serta Pewarisan dan Polimorfisme.
- Percobaan mengubah koleksi bab dari luar ditolak, artinya enkapsulasinya sudah benar.
- Setelah Budi pindah, Jurusan TI tinggal 1 mahasiswa, tapi Budi tetap ada sebagai objek.
- Di Perpustakaan Teknik, pencarian buku berhasil dan proses meminjam serta mengembalikan buku PBO-001 oleh anggota IT-4554 berjalan.

---

## 2. Implementasi PHP
2.1. File: main.php

**Penjelasan Kode:**
>main.php adalah titik masuk program PHP. File ini memanggil vendor/autoload.php dari Composer, jadi tidak perlu require_once satu per satu. Alurnya sama seperti versi Java (komposisi, agregasi, perpustakaan), ditambah satu bagian baru yaitu keanggotaan perpustakaan yang memakai library Carbon untuk mengambil tanggal sekarang.
**Bukti Eksekusi (Screenshot):**
- **program** 
![program](image-5.png)

**2.2. File: Buku.php**
Penjelasan Kode:
>Di file ini ada dua class, yaitu Bab dan Buku. Bab memakai constructor readonly supaya nilainya tidak bisa diubah setelah dibuat. Buku membuat Bab sendiri di dalam tambahBab(nomor, judulBab). PHP tidak punya nested class seperti Java, jadi Bab tidak bisa dibuat private sepenuhnya dan hanya dijaga lewat aturan penulisan. Method getBab() mengembalikan array dengan cara by-value (salinan), tapi objek Bab di dalamnya tetap berbagi referensi. Karena itu Bab dibuat readonly supaya aman.

**Bukti Eksekusi (Screenshot):**
- **program** 
![program](image-6.png)
**2.3. File: Jurusan.php dan Mahasiswa.php**

Penjelasan Kode:
>Jurusan.php menerapkan Agregasi. Objek Mahasiswa dibuat di luar lalu diberikan ke daftarkan(). Pada keluarkan(), Mahasiswa dicari dengan array_search (mode strict), dihapus dari array, lalu index array diurutkan ulang dengan array_values. Mahasiswa.php sendiri berupa class sederhana dengan properti nim dan nama yang readonly.

**Bukti Eksekusi (Screenshot):**
- **program** 
![program](image-3.png)

**2.4. File: Anggota.php**

Penjelasan Kode:
>Anggota.php adalah turunan Jurusan (extends Jurusan) dan memakai Carbon untuk urusan tanggal. Method daftarkanAnggota() menyimpan Mahasiswa dan tanggal mulai keanggotaan, checkMasaKeanggotaan() mengecek apakah sudah lebih dari 3 tahun, dan getKeanggotaan() menampilkan nama anggota, jurusan, kode anggota, serta tanggal mulai dengan format d-m-Y.

**Bukti Eksekusi (Screenshot):**
- **program** 
![program](image-7.png)
**2.5. File: PerpustakaanService.php**

Penjelasan Kode:
>PerpustakaanService.php menyimpan koleksi Buku dalam array. Method tambahKoleksi() menambah buku, cariKoleksi() mencari berdasarkan nomor induk dan mengembalikan null kalau tidak ketemu (tipe ?Buku), sedangkan pinjam() dan kembalikan() untuk sekarang baru menampilkan pesan.

**Bukti Eksekusi (Screenshot):**
- **program** ![program](image-8.png)

### Output Program
![output](image-4.png)

## 3. Kesimpulan
Komposisi dan Agregasi sama-sama hubungan antarobjek dalam PBO. Bedanya ada pada seberapa bergantung objek bagiannya. Pada Komposisi, Bab dibuat dan dikelola oleh Buku, jadi kalau Buku hilang maka Bab ikut hilang. Pada Agregasi, Mahasiswa dibuat sendiri di luar Jurusan, jadi tetap ada walaupun sudah dikeluarkan dari Jurusan. Dari dua bahasa ini juga terlihat perbedaannya. Di Java, Bab bisa dibuat benar-benar tertutup memakai nested class dengan constructor private. Di PHP hal itu tidak bisa, jadi pengamanannya memakai readonly dan aturan penulisan.