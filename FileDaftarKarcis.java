/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum7;

/**
 *
 * @author USER
 */
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class FileDaftarKarcis {

    static String namaFile = "daftarKarcis.csv";

    /**
     * Membuat file csv berisi daftar parkir. Apabila file sudah ada, replace.
     * @param daftarKarcis Array berisi karcis
     */
    public static void createFile(Karcis[] daftarKarcis) {
        File file = new File(namaFile);
        // Apabila file sudah ada, coba hapus
        if (file.exists() && !file.delete()) {
            System.out.println("Error: gagal menghapus file lama.");
            return;
        }
        try {
            // Buat file baru
            if (file.createNewFile()) {
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                // Header
                bw.write("Kode Karcis,Waktu Masuk,Tipe Kendaraan,Plat Nomor");
                for (Karcis karcis : daftarKarcis) {
                    bw.newLine();
                    // Tulis data dengan pemisah kolom tanda ","
                    bw.write(karcis.kode
                            + "," + karcis.masuk
                            + "," + karcis.kendaraan.tipe.toString()
                            + "," + karcis.kendaraan.platNomor);
                }
                bw.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Membaca file csv berisi daftar karcis dan mengembalikan datanya.
     * @return Daftar karcis dalam array
     */
    public static Karcis[] loadFile() {
        File file = new File(namaFile);
        if (!file.exists()) {
            System.out.println("Error: file tidak ditemukan.");
            return null;
        }
        ArrayList<Karcis> listKarcis = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            sc.nextLine(); // Skip header
            while (sc.hasNextLine()) {
                // Baris 0: kode
                //       1: waktu masuk
                //       2: tipe
                //       3: plat nomor
                String[] baris = sc.nextLine().split(",");
                Kendaraan kendaraan = new Kendaraan(Kendaraan.Tipe.valueOf(baris[2]), baris[3]);
                Karcis karcis = new Karcis(baris[0], LocalDateTime.parse(baris[1]), kendaraan);
                listKarcis.add(karcis);
            }
            sc.close();
            return listKarcis.toArray(new Karcis[0]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
} 

