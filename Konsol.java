/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum7;


import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Konsol {

    
    SistemParkir sistemParkir;
    private final Scanner input = new Scanner(System.in);
    
    public Konsol(SistemParkir sistemParkir) {


        this.sistemParkir = sistemParkir;

        mainloop: while (true) {
            String menu = """
                Menu:
                1. Kendaraan masuk
                2. Kendaraan keluar
                3. Daftar kendaraan
                4. Keluar""";
            System.out.println(menu);
            int pilihan = Integer.parseInt(input.nextLine());

            switch (pilihan) {
                case 1 -> kendaraanMasuk();
                case 2 -> kendaraanKeluar();
                case 3 -> daftarKendaraan();
                case 4 -> {
                    break mainloop;
                }
                default -> System.out.println("Pilihan tidak tersedia!");
            }
        }
    }

    private void kendaraanMasuk() {
        String menuTipe = """
                   Tipe kendaraan:
                   1. Motor
                   2. Mobil""";
        System.out.println(menuTipe);
        Kendaraan.Tipe tipe = Kendaraan.Tipe.MOTOR;
        int pilihan = Integer.parseInt(input.nextLine());
        if (pilihan == 2)
            tipe = Kendaraan.Tipe.MOBIL;
        System.out.println("Plat nomor:");
        String platNomor = input.nextLine();
        this.sistemParkir.cekIn(tipe, platNomor);
    }

    private void kendaraanKeluar() {
        System.out.println("Kode karcis:");
        String kode = input.nextLine();
        System.out.println("Biaya parkir: " + sistemParkir.biayaParkir(kode));
        this.sistemParkir.cekOut(kode);
    }

    private void daftarKendaraan() {
        String garis = "+--------+-------------------------------+-------+-------------+%n";
        String format = "| %-6s | %-29s | %-5s | %-11s |%n";
        System.out.format(garis);
        System.out.format(format, "Kode", "Masuk", "Tipe", "Plat");
        System.out.format(garis);
        for (Karcis karcis : sistemParkir.listKarcis) {
            System.out.format(format,
                    karcis.kode,
                    karcis.masuk.toString(),
                    karcis.kendaraan.tipe.toString(),
                    karcis.kendaraan.platNomor);
        }
        System.out.format(garis);
    }
}