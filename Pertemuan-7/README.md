# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
| :--- | :--- |
| **Nama** | Vika Elda Lestari |
| **NPM** | 4525210088 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | 07 - Komposisi dan Agregasi |
| **Tanggal** | Kamis 15 Oktober 2026 |

---

## 1. Implementasi Java

### 1.1. File: `Main.java`

**Penjelasan Kode:**
> Main.java adalah program pengujian yang dipakai untuk menunjukkan perbedaan antara Komposisi dan Agregasi di Java. Lewat file ini, kelas PerpustakaanService dijalankan untuk mencoba alur sederhana pengelolaan koleksi buku, mulai dari peminjaman sampai pengembalian.

**Bukti Eksekusi (Screenshot):**
- **Progam** ![codingan](image-1.png)

### 1.2. File: `Buku.java`

**Penjelasan Kode:**
> Di Buku.java, objek Bab dibuat dan sepenuhnya dikendalikan oleh kelas Buku. Daftar bab tidak dibuka langsung ke luar, tetapi dikembalikan lewat List.copyOf sehingga pihak luar tidak bisa mengubah isinya. Kelas ini juga memeriksa stok buku sebagai bagian dari contoh sistem perpustakaan.

**Bukti Eksekusi (Screenshot):**
- **Before** ![Sebelum](image-3.png)
- **After** ![Sesudah](image-2.png)

### 1.3. File: `Jurusan.java`

**Penjelasan Kode:**
> Jurusan.java memperlihatkan konsep Agregasi. Objek Mahasiswa dibuat di luar kelas Jurusan, sehingga tetap ada walaupun Jurusan tidak ada. Ini berbeda dengan Komposisi pada Buku, karena Bab hanya bisa ada selama Buku-nya masih ada.

**Bukti Eksekusi (Screenshot):**
- **Before** ![Sebelum](image-4.png)
- **After** ![Sesudah](image-5.png)

### 1.4. File: `PerpustakaanService.java`

**Penjelasan Kode:**
> PerpustakaanService.java berperan sebagai Service Layer yang mengurus persediaan koleksi dan mensimulasikan proses peminjaman buku. Kelas ini juga menjadi contoh Agregasi karena hanya menyimpan referensi ke objek Buku, sehingga bisa dibandingkan dengan Komposisi.

**Bukti Eksekusi (Screenshot):**
- **Before** ![Sebelum](image-7.png)
- **After** ![Sesudah](image-6.png)

### Output

**Output Program:**

![Output](image.png)

---

## 2. Implementasi PHP

### 2.1. File: `main.php`

**Penjelasan Kode:**
> Praktikum ini menerapkan konsep PBO pada dua bahasa, yaitu Java dan PHP, untuk membandingkan Komposisi dengan Agregasi. Pada file ini, enkapsulasi koleksi diuji supaya data tidak dapat diubah secara langsung dari luar kelas. Selain itu dibuat kerangka layanan perpustakaan untuk mengelola persediaan dan peminjaman buku.

**Bukti Eksekusi (Screenshot):**
- **Main** ![Progam](image-8.png)

### 2.2. File: `relasi.php`

**Penjelasan Kode:**
> Fokus praktikum ini adalah membedakan Komposisi dan Agregasi dalam PBO. Enkapsulasi diuji dengan cara mengembalikan salinan koleksi, sehingga data aslinya aman dari perubahan di luar kelas. Service Layer juga dibuat sebagai kerangka untuk mengatur persediaan dan transaksi perpustakaan.

**Bukti Eksekusi (Screenshot):**
- **Before** ![Sebelum](image-11.png)
- **After** ![Sesudah](image-10.png)

### Output

**Output Program:**

![Output](image-12.png)

---

## 3. Kesimpulan

Komposisi dan Agregasi sama-sama relasi antarobjek dalam PBO, dan yang membedakannya adalah seberapa besar satu objek bergantung pada objek lain. Pada Komposisi, objek bagian (Bab) dibuat oleh induknya (Buku) dan sepenuhnya bergantung padanya, jadi ketika Buku hilang, Bab ikut hilang. Pada Agregasi, objek bagian (Mahasiswa) dibuat secara terpisah dan tetap bisa berdiri sendiri meskipun tidak lagi berada di bawah Jurusan.