/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum7;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Karcis
 */
public class Karcis {
    public String kode;
    public LocalDateTime masuk;
    public Kendaraan kendaraan;

    public Karcis(Kendaraan kendaraan) {
        this.kendaraan = kendaraan;
        this.masuk = LocalDateTime.now();
        // Kode acak 6-digit
        Random random = new Random();
        this.kode = Integer.toString(random.nextInt(999999));
    }

    public Karcis(String kode, LocalDateTime masuk, Kendaraan kendaraan) {
        this.kode = kode;
        this.masuk = masuk;
        this.kendaraan = kendaraan;
    }

    /**
     * Membuat file text karcis agar dapat dicetak.
     */
    public void buatFile() {
        String format = "%-16s %30s";
        String line = "-----------------------------------------------";
        try {
            File file = new File("karcis_" + kode + ".txt");
            if (file.createNewFile()) {
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(line);
                bw.newLine();
                bw.write(kode);
                bw.newLine();
                bw.write(line);
                bw.newLine();
                bw.write(String.format(format, "Waktu masuk:", masuk.toString()));
                bw.newLine();
                bw.write(String.format(format, "Tipe kendaraan:", kendaraan.tipe.toString()));
                bw.newLine();
                bw.write(String.format(format, "Plat nomor:", kendaraan.platNomor));
                bw.newLine();
                bw.write(line);
                bw.close();
                fw.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}