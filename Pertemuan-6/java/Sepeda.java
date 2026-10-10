public class Sepeda extends Kendaraan {
    public Sepeda(String merek, int tahun) {
        super(merek, tahun);
    }

    @Override
    public int jumlahRoda() {
        return 2;
    }
} 
    
