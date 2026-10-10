# **Mengapa Penolakan Saat Kompilasi Menguntungkan?**

- Mencegah Kegagalan Saat Runtime
Program yang menolak dikompilasi jauh lebih baik daripada program yang lolos kompilasi tetapi mendadak crash (runtime error) saat digunakan oleh pengguna akhir (user).

- Bahasa seperti Java memanfaatkan penolakan kompilasi untuk memastikan operasi data sudah valid. Kompiler akan melarang kamu memasukkan tipe data yang tidak sesuai (misalnya memasukkan String ke variabel int), sehingga potensi bug akibat manipulasi data yang salah bisa dicegah sejak awal.

## **Output Exception**

## JAVA OUTPUT

```output
$ javac Main.java
Main.java:36: error: incompatible types: Sepeda cannot be converted to Fuelable
        isiPenuh(sepeda);
                 ^
Note: Some messages have been simplified; recompile with -Xdiags:verbose to get full output
1 error
```

---

## PHP OUTPUT

```output
PHP Fatal error:  Uncaught TypeError: isiPenuh(): Argument #1 ($kendaraan) must be of type Fuelable, Sepeda given, called in C:\Users\Vika elda lestari\OneDrive\Dokumen\tugas 2\tugas se 3\Praktikum-PBO-A-VikaEldaLestari-4525210088\Pertemuan-6\php\main.php on line 31 and defined in C:\Users\Vika elda lestari\OneDrive\Dokumen\tugas 2\tugas se 3\Praktikum-PBO-A-VikaEldaLestari-4525210088\Pertemuan-6\php\main.php:8
Stack trace:
#0 C:\Users\Vika elda lestari\OneDrive\Dokumen\tugas 2\tugas se 3\Praktikum-PBO-A-VikaEldaLestari-4525210088\Pertemuan-6\php\main.php(31): isiPenuh(Object(Sepeda))
#1 {main}
  thrown in C:\Users\Vika elda lestari\OneDrive\Dokumen\tugas 2\tugas se 3\Praktikum-PBO-A-VikaEldaLestari-4525210088\Pertemuan-6\php\main.php on line 8

Fatal error: Uncaught TypeError: isiPenuh(): Argument #1 ($kendaraan) must be of type Fuelable, Sepeda given, called in C:\Users\Vika elda lestari\OneDrive\Dokumen\tugas 2\tugas se 3\Praktikum-PBO-A-VikaEldaLestari-4525210088\Pertemuan-6\php\main.php on line 31 and defined in C:\Users\Vika elda lestari\OneDrive\Dokumen\tugas 2\tugas se 3\Praktikum-PBO-A-VikaEldaLestari-4525210088\Pertemuan-6\php\main.php:8
Stack trace:
#0 C:\Users\Vika elda lestari\OneDrive\Dokumen\tugas 2\tugas se 3\Praktikum-PBO-A-VikaEldaLestari-4525210088\Pertemuan-6\php\main.php(31): isiPenuh(Object(Sepeda))
#1 {main}
  thrown in C:\Users\Vika elda lestari\OneDrive\Dokumen\tugas 2\tugas se 3\Praktikum-PBO-A-VikaEldaLestari-4525210088\Pertemuan-6\php\main.php on line 8```