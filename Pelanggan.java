class Pelanggan {
    private String nomorPelanggan;
    private String nama;
    private double saldo;
    private String pin;
    private int kesalahanAutentikasi;
    private boolean diblokir;
    
    public Pelanggan(String nomorPelanggan, String nama, double saldo, String pin) {
        this.nomorPelanggan = nomorPelanggan;
        this.nama = nama;
        this.saldo = saldo;
        this.pin = pin;
        this.kesalahanAutentikasi = 0;
        this.diblokir = false;
    }
    
    public String getNomorPelanggan() {
        return nomorPelanggan;
    }
    
    public String getNama() {
        return nama;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public boolean isDiblokir() {
        return diblokir;
    }
    
    public boolean autentikasi(String inputPin) {
        if (diblokir) {
            System.out.println("Akun Anda telah diblokir!");
            return false;
        }
        
        if (this.pin.equals(inputPin)) {
            kesalahanAutentikasi = 0;
            return true;
        } else {
            kesalahanAutentikasi++;
            System.out.println("PIN salah! Percobaan ke-" + kesalahanAutentikasi);
            if (kesalahanAutentikasi >= 3) {
                diblokir = true;
                System.out.println("Akun Anda telah diblokir akibat 3x kesalahan PIN.");
            }
            return false;
        }
    }
    
    public void topUp(double jumlah) {
        if (jumlah > 0) {
            saldo += jumlah;
            System.out.println("Top up berhasil! Saldo Anda: " + saldo);
        } else {
            System.out.println("Jumlah top up tidak valid.");
        }
    }
    
    public boolean beli(double jumlah) {
        double cashback = hitungCashback(jumlah);
        double totalSetelahCashback = jumlah - cashback;
        
        if (saldo - totalSetelahCashback < 10000) {
            System.out.println("Saldo tidak mencukupi untuk transaksi ini!");
            return false;
        }
        
        saldo -= totalSetelahCashback;
        saldo += cashback; // Cashback dikembalikan ke saldo
        System.out.println("Pembelian berhasil! Saldo Anda: " + saldo);
        return true;
    }
    
    private double hitungCashback(double jumlah) {
        if (jumlah <= 1000000) {
            return 0;
        }
        
        String prefix = nomorPelanggan.substring(0, 2);
        switch (prefix) {
            case "38": return jumlah * 0.05;
            case "56": return jumlah * (jumlah > 1000000 ? 0.07 : 0.02);
            case "74": return jumlah * (jumlah > 1000000 ? 0.10 : 0.05);
            default: return 0;
        }
    }
}