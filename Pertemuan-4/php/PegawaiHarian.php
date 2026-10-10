<?php

/** Pegawai harian: gajiPokok diperlakukan sebagai upah per hari kerja. */
class PegawaiHarian extends Pegawai
{

    private int $hariKerja;

    public function __construct(string $nip, string $nama, float $upahPerHari, int $hariKerja)
    {
        parent::__construct($nip, $nama, $upahPerHari);
        if ($hariKerja < 0) {
            throw new InvalidArgumentException("Hari kerja tidak boleh negatif: " . $hariKerja);
        }
        $this->hariKerja = $hariKerja;
    }

    public function hitungGaji(): float
    {
        return parent::hitungGaji() * $this->hariKerja;
    }

    public function jenis(): string
    {
        return "HARIAN";
    }

    public function getHariKerja(): int
    {
        return $this->hariKerja;
    }
}
