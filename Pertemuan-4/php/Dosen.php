<?php

class Dosen extends Pegawai
{
    private int $jumlahSKS;

    public function __construct(string $nip, string $nama, float $gajiPokok, int $jumlahSKS)
    {
        parent::__construct($nip, $nama, $gajiPokok);
        $this->jumlahSKS = $jumlahSKS;
    }

    public function hitungGaji(): float
    {
        return $this->gajiPokok + ($this->jumlahSKS * 50000);
    }

    public function jenis(): string
    {
        return "DOSEN";
    }

    public function getJumlahSKS(): int
    {
        return $this->jumlahSKS;
    }
}

?>