# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
| :--- | :--- |
| **Nama** | Vika Elda Lestari |
| **NPM** | 4525210088 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | [6] - [Abstract Class, Interface, Enum, dan Trait] |
| **Tanggal** | [08-10-2026] |

---

## 1. Implementasi Java

### 1.1. File: `Fuelable.java`
**Penjelasan Kode:**
> [File Fuelable.java dan Movable.java merupakan antarmuka (interface) yang mendefinisikan kontrak perilaku. Fuelable mengatur kontrak untuk kendaraan yang membutuhkan bahan bakar beserta pengelolaannya, sedangkan Movable mengatur metode pergerakan kendaraan.]

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:
(<img width="1075" height="342" alt="WhatsApp Image 2026-10-10 at 19 16 29" src="https://github.com/user-attachments/assets/75e1549a-13f6-429d-8be9-0b65f04b7663" />
)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
(<img width="551" height="296" alt="image" src="https://github.com/user-attachments/assets/03020d7b-6c10-45c6-9deb-ab18cea75a68" />
)

### 1.2. File: `Kendaraan.java`
**Penjelasan Kode:**
> [File Kendaraan.java mendefinisikan abstract class Kendaraan yang berfungsi sebagai kerangka dasar untuk kelas turunan. Kelas abstrak ini memuat properti umum kendaraan serta abstract method yang wajib diimplementasikan (override) oleh kelas konkret seperti Mobil dan Sepeda.]

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:
(<img width="1063" height="574" alt="WhatsApp Image 2026-10-10 at 19 16 49" src="https://github.com/user-attachments/assets/86bb4f21-c9cc-4a6c-b1bd-2da18e26192d" />
)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
(<img width="662" height="460" alt="image" src="https://github.com/user-attachments/assets/4527cc08-f124-44e7-aca0-2748811ace52" />
)

### 1.3. File: `Main.java`
**Penjelasan Kode:**
> [Main.java bertindak sebagai driver class untuk menginstansiasi objek Mobil dan Sepeda serta menguji interaksi abstract class dan interface.]

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:
(<img width="1233" height="853" alt="WhatsApp Image 2026-10-10 at 19 17 07" src="https://github.com/user-attachments/assets/6f2395eb-5fcf-4257-83e3-b01b1a6e0fc2" />
)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
(<img width="597" height="446" alt="image" src="https://github.com/user-attachments/assets/ed335a2c-948f-4336-8d8e-bc8fdd4f5e1a" />
)

### 1.4. File: `Mobil.java`
**Penjelasan Kode:**
> [Kelas Mobil mengimplementasikan interface Fuelable dan Movable.]

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:
(<img width="972" height="705" alt="WhatsApp Image 2026-10-10 at 19 17 24" src="https://github.com/user-attachments/assets/f78bd5ff-1985-4170-89c6-f056ab22606a" />
)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
(<img width="611" height="434" alt="image" src="https://github.com/user-attachments/assets/6b5da7bf-9d73-428b-9713-2896dc1240dc" />
)

### 1.5. File: `Movable.java`
**Penjelasan Kode:**
> [Movable mengatur metode pergerakan kendaraan.]

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:
(<img width="961" height="547" alt="WhatsApp Image 2026-10-10 at 19 17 43" src="https://github.com/user-attachments/assets/aec9c512-15a8-49e5-a0e7-3b046ffac268" />
)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
(<img width="433" height="250" alt="image" src="https://github.com/user-attachments/assets/49086737-ec4a-4c8f-9abb-933fc38bd3b4" />
)

### 1.6. File: `Sepeda.java`
**Penjelasan Kode:**
> [Sepeda mengimplementasikan Movable. Keduanya memberikan penanganan spesifik sesuai karakteristik kendaraan masing-masing.]

**Bukti Eksekusi (Screenshot):**

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
(<img width="281" height="199" alt="image" src="https://github.com/user-attachments/assets/04ef088c-fc3d-4640-871d-3d09a22699d8" />
)

### 1.7. File: `TipeBahanBakar.java`
**Penjelasan Kode:**
> [TipeBahanBakar.java menyimpan entitas enum untuk jenis bahan bakar.]

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:
(<img width="1285" height="688" alt="WhatsApp Image 2026-10-10 at 19 18 09" src="https://github.com/user-attachments/assets/89cad975-5e1c-4960-a388-6ec8a750719e" />
)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
(<img width="545" height="396" alt="image" src="https://github.com/user-attachments/assets/78922ca1-de47-4a8d-a5df-592d9db0ff53" />
)

### Output
**Output Program:**
(<img width="725" height="216" alt="image" src="https://github.com/user-attachments/assets/e9bd3b26-8fc1-4511-bf92-5d4b57cf33cd" />
)

---

## 2. Implementasi PHP

### 2.1. File: `abstraksi.php`
**Penjelasan Kode:**
> [File abstraksi.php berisi konversi struktur abstract class dan interface ke dalam sintaks PHP. Di dalamnya mencakup deklarasi abstract class Kendaraan, interface Fuelable, interface Movable, serta kelas turunan Mobil dan Sepeda yang mengimplementasikannya.]

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Galat logika)*:
(<img width="792" height="904" alt="WhatsApp Image 2026-10-10 at 19 48 03" src="https://github.com/user-attachments/assets/84b63266-8225-4f06-b8ea-20a24cc94b2c" />
)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
(<img width="176" height="429" alt="image" src="https://github.com/user-attachments/assets/ebd3210d-aecf-4263-bdeb-f49674e975ff" />
)

### 2.2. File: `main.php`
**Penjelasan Kode:**
> [Merupakan konversi kelas Dosen.java ke PHP.]

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Galat logika)*:
(<img width="955" height="795" alt="WhatsApp Image 2026-10-10 at 19 47 17" src="https://github.com/user-attachments/assets/e64fcdec-2a67-4fb0-811b-31a7d0e8822e" />
)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
(<img width="403" height="356" alt="image" src="https://github.com/user-attachments/assets/e4899e9c-f549-425e-b97c-675e1080afb6" />
)


### Output
**Output Program:**
(<img width="628" height="428" alt="image" src="https://github.com/user-attachments/assets/320081fe-a1b2-4119-8877-91310498e1d6" />
)

---

## 3. Kesimpulan
> [Pada Pertemuan 6 ini, penerapan konsep Abstract Class dan Interface berhasil dipelajari. Abstract Class digunakan untuk mendefinisikan kerangka umum kendaraan yang memiliki atribut terbagi, sedangkan Interface memodelkan kemampuan khusus (seperti Fuelable dan Movable) yang dapat diterapkan pada kelas berbeda. Hasil pemetaan dari Java ke PHP membuktikan bahwa kedua bahasa mendukung konsep abstraksi OOP secara penuh.]
