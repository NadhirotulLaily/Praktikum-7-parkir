/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkir7;


import java.util.*;
import java.time.*;
import java.io.*;


/**
 *
 * @author Asus
 */
public final class SistemKarcis {
   Scanner in = new Scanner(System.in);
    File f = new File("SistemKarcis.txt");

    Random ran = new Random();
    int max = 99999;
    int i = ran.nextInt(max);

    ArrayList<String> Kendaraan;
    ArrayList<Integer> Id;
    ArrayList<LocalDateTime> Tanggal;
    ArrayList<Duration> lamaParkir;

    public SistemKarcis(){

        LocalDateTime sekarang = LocalDateTime.now(); 
        LocalDateTime nanti = sekarang.plusHours(5);
        Duration  durasi = Duration.between(sekarang, nanti);

        this.Tanggal = new ArrayList<>();
        this.Tanggal.add(0,sekarang);
        this.Tanggal.add(1,nanti);

        this.lamaParkir = new ArrayList<>();
        this.lamaParkir.add(0,durasi);

        this.Kendaraan = new ArrayList<>();
        System.out.print("Jenis Kendaraan : ");
        this.Kendaraan.add(0, in.nextLine());

        this.Id = new ArrayList<>();
        this.Id.add(0,i);

        Id();

    }

    void Id(){
        System.out.println("\nId              : " + this.Id.get(0));

        JenisKendaraan();
    }

    String JenisKendaraan(){
        System.out.println("Jenis Kendaraan : " + this.Kendaraan.get(0));
            if (this.Kendaraan.get(0).isBlank()) {
                return(this.Kendaraan.get(0));
            }
            tanggal();
            return null;
    }

    void tanggal(){
        System.out.println("Parkir pada     : " + this.Tanggal.get(0));

            keluar();
    }

    Integer keluar(){
        System.out.print("\nMasukkan Id         : ");
            int kode = in.nextInt();

            if (kode != this.Id.get(0)) {
                System.out.println("Id Tidak Ditemukan!!\n");
                return kode;
            }

            System.out.println("Keluar Pada         : " + this.Tanggal.get(1));

            lamaParkir();

            return null;            
    }

    void lamaParkir(){
            System.out.println("Anda Parkir Selama  : " + this.lamaParkir.get(0).toHours() + " Jam");

            bayar();
    }

    void bayar(){
        if (this.Kendaraan.get(0).equals("Motor")) {
            int totalBayar = (int) this.lamaParkir.get(0).toHours() * 2000;
            System.out.println("Biaya Parkir        : " + totalBayar);
        }
        if (this.Kendaraan.get(0).equals("Mobil")) {
            int totalBayar = (int) this.lamaParkir.get(0).toHours() * 4000;
            System.out.println("Biaya Parkir        : " + totalBayar);
        }                
        if (this.Kendaraan.get(0).equals("Truk")) {
            int totalBayar = (int) this.lamaParkir.get(0).toHours() * 5000;
            System.out.println("Biaya Parkir        : " + totalBayar);                
        }
        cetak();
    }

    void cetak(){
                try{
            boolean berhasil = f.createNewFile();
        }catch(IOException ignore){}

        try{
            FileWriter fw = new FileWriter(f,true);
            fw.write("\n\nHASIL CETAK\nId   : " + Id +
                     "\nJenis Kendaraan     : " + Kendaraan +
                     "\nParkir Pada         : " + Tanggal.get(0) +
                     "\nKeluar Pada         : " + Tanggal.get(1)+
                     "\nAnda Parkir Selama  : " + lamaParkir );

        if (this.Kendaraan.get(0).equals("Motor")) {
            int totalBayar = (int) this.lamaParkir.get(0).toHours() * 2000;
            fw.write("\nBiaya Parkir        : " + totalBayar);
        }
        if (this.Kendaraan.get(0).equals("Mobil")) {
            int totalBayar = (int) this.lamaParkir.get(0).toHours() * 4000;
            fw.write("\nBiaya Parkir        : " + totalBayar);
        }                
        if (this.Kendaraan.get(0).equals("Truk")) {
            int totalBayar = (int) this.lamaParkir.get(0).toHours() * 5000;
            fw.write("\nBiaya Parkir        : " + totalBayar);                
        }
            fw.close();
        }catch(IOException ignore){}
        try{

         Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }
        }catch(IOException ignore){}
    }
}  