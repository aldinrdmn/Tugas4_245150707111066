import java.util.HashMap;
import java.util.Scanner;

// Kelas utama untuk menjalankan sistem
public class SwalayanTiny {
    private static HashMap<String, Pelanggan> databasePelanggan = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Menambahkan beberapa pelanggan ke dalam database
        databasePelanggan.put("3812345678", new Pelanggan("3812345678", "Andi", 2000000, "1234"));
        databasePelanggan.put("5612345678", new Pelanggan("5612345678", "Budi", 1500000, "5678"));
        databasePelanggan.put("7412345678", new Pelanggan("7412345678", "Citra", 5000000, "4321"));
        
        while (true) {
            System.out.println("\nSelamat datang di Swalayan Tiny");
            System.out.print("Masukkan nomor pelanggan: ");
            String nomor = scanner.nextLine();
            
            Pelanggan pelanggan = databasePelanggan.get(nomor);
            if (pelanggan == null) {
                System.out.println("Nomor pelanggan tidak ditemukan!");
                continue;
            }
            
            if (pelanggan.isDiblokir()) {
                System.out.println("Akun Anda telah diblokir dan tidak bisa digunakan lagi.");
                continue;
            }
            
            System.out.print("Masukkan PIN: ");
            String pin = scanner.nextLine();
            
            if (!pelanggan.autentikasi(pin)) {
                continue;
            }
            
            System.out.println("Login berhasil! Selamat datang, " + pelanggan.getNama());
            
            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Top Up");
                System.out.println("2. Pembelian");
                System.out.println("3. Keluar");
                System.out.print("Pilih menu: ");
                
                int pilihan = scanner.nextInt();
                scanner.nextLine(); // Membersihkan newline
                
                if (pilihan == 1) {
                    System.out.print("Masukkan jumlah top up: ");
                    double jumlah = scanner.nextDouble();
                    pelanggan.topUp(jumlah);
                } else if (pilihan == 2) {
                    System.out.print("Masukkan jumlah pembelian: ");
                    double jumlah = scanner.nextDouble();
                    pelanggan.beli(jumlah);
                } else if (pilihan == 3) {
                    System.out.println("Terima kasih telah berbelanja di Swalayan Tiny!");
                    break;
                } else {
                    System.out.println("Pilihan tidak valid!");
                }
            }
        }
    }
}
