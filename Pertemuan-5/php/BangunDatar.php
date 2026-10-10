<?php

declare(strict_types=1);

abstract class BangunDatar
{
    public function __construct(private readonly string $nama) {}

    abstract public function luas(): float;
    abstract public function keliling(): float;

    public function getNama(): string
    {
        return $this->nama;
    }

    public function __toString(): string
    {
        return sprintf(
            '%-12s luas=%10.2f  keliling=%10.2f',
            $this->nama,
            $this->luas(),
            $this->keliling()
        );
    }
}

class Lingkaran extends BangunDatar
{
    public function __construct(private readonly float $jariJari)
    {
        parent::__construct('Lingkaran');

        if ($this->jariJari <= 0) {
            throw new InvalidArgumentException('Jari-jari lingkaran harus lebih besar dari 0.');
        }
    }

    public function luas(): float
    {
        return M_PI * $this->jariJari ** 2;
    }

    public function keliling(): float
    {
        return 2 * M_PI * $this->jariJari;
    }

    public function getJariJari(): float
    {
        return $this->jariJari;
    }
}

class Persegi extends BangunDatar
{
    public function __construct(private readonly float $sisi)
    {
        parent::__construct('Persegi');

        if ($this->sisi <= 0) {
            throw new InvalidArgumentException('Sisi persegi harus lebih besar dari 0.');
        }
    }

    public function luas(): float
    {
        return $this->sisi * $this->sisi;
    }

    public function keliling(): float
    {
        return 4 * $this->sisi;
    }

    public function getSisi(): float
    {
        return $this->sisi;
    }
}

class Segitiga extends BangunDatar
{
    public function __construct(
        private readonly float $sisi1,
        private readonly float $sisi2,
        private readonly float $sisi3
    ) {
        parent::__construct('Segitiga');

        if ($this->sisi1 <= 0 || $this->sisi2 <= 0 || $this->sisi3 <= 0) {
            throw new InvalidArgumentException('Semua sisi segitiga harus lebih besar dari 0.');
        }

        if (
            $this->sisi1 + $this->sisi2 <= $this->sisi3 ||
            $this->sisi1 + $this->sisi3 <= $this->sisi2 ||
            $this->sisi2 + $this->sisi3 <= $this->sisi1
        ) {
            throw new InvalidArgumentException('Ketiga sisi tidak membentuk segitiga.');
        }
    }

    public function luas(): float
    {
        $s = ($this->sisi1 + $this->sisi2 + $this->sisi3) / 2;

        return sqrt($s * ($s - $this->sisi1) * ($s - $this->sisi2) * ($s - $this->sisi3));
    }

    public function keliling(): float
    {
        return $this->sisi1 + $this->sisi2 + $this->sisi3;
    }

    public function getSisi1(): float
    {
        return $this->sisi1;
    }

    public function getSisi2(): float
    {
        return $this->sisi2;
    }

    public function getSisi3(): float
    {
        return $this->sisi3;
    }
}

class Trapesium extends BangunDatar
{
    public function __construct(
        private readonly float $alasAtas,
        private readonly float $alasBawah,
        private readonly float $sisiKiri,
        private readonly float $sisiKanan,
        private readonly float $tinggi
    ) {
        parent::__construct('Trapesium');

        if ($this->alasAtas <= 0 || $this->alasBawah <= 0 || $this->sisiKiri <= 0 || $this->sisiKanan <= 0 || $this->tinggi <= 0) {
            throw new InvalidArgumentException('Semua parameter trapesium harus lebih besar dari 0.');
        }
    }

    public function luas(): float
    {
        return (($this->alasAtas + $this->alasBawah) / 2) * $this->tinggi;
    }

    public function keliling(): float
    {
        return $this->alasAtas + $this->alasBawah + $this->sisiKiri + $this->sisiKanan;
    }

    public function getAlasAtas(): float
    {
        return $this->alasAtas;
    }

    public function getAlasBawah(): float
    {
        return $this->alasBawah;
    }

    public function getSisiKiri(): float
    {
        return $this->sisiKiri;
    }

    public function getSisiKanan(): float
    {
        return $this->sisiKanan;
    }

    public function getTinggi(): float
    {
        return $this->tinggi;
    }
}
