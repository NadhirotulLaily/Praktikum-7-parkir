package praktikum7;



import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class SistemParkir {

    ArrayList<Karcis> listKarcis = new ArrayList<>();

    public SistemParkir() {
        // Load file jika ada
        Karcis[] daftarKarcis = FileDaftarKarcis.loadFile();
        if (daftarKarcis != null)
            Collections.addAll(listKarcis, daftarKarcis);
    }

    /**
     * Dipanggil ketika kendaraan masuk parkiran.
     * @param tipe      Tipe kendaraan
     * @param platNomor Plat nomor kendaraan
     */
    public void cekIn(Kendaraan.Tipe tipe, String platNomor) {
        Kendaraan kendaraan = new Kendaraan(tipe, platNomor);
        Karcis karcis = new Karcis(kendaraan);
        // Tambahkan karcis ke list
        listKarcis.add(karcis);
        // Karena list berubah, buat file baru
        FileDaftarKarcis.createFile(listKarcis.toArray(new Karcis[0]));
        // Buat file karcis
        karcis.buatFile();
    }

    /**
     * Dipanggil ketika kendaraan keluar parkiran.
     * @param kode Kode karcis
     */
    public void cekOut(String kode) {
        // Hapus karcis dari list
        listKarcis.remove(cariKarcis(kode));
        // Karena list berubah, buat file baru
        FileDaftarKarcis.createFile(listKarcis.toArray(new Karcis[0]));
    }

    /**
     * Mendapatkan biaya parkir
     * @param kode Kode karcis
     * @return     Jumlah biaya parkir
     */
    public int biayaParkir(String kode) {
        int biaya = 0;
        Karcis karcis = cariKarcis(kode);
        // Hitung durasi dari waktu masuk sampai sekarang
        Duration durasi = Duration.between(karcis.masuk, LocalDateTime.now());
        // Ambil jamnya saja
        long jumlahJam = durasi.toHours();
        // Apabila durasi parkir kurang dr 1 jam, anggap 1 jam.
        if (jumlahJam == 0) {
            jumlahJam = 1;
        }
        // Tentukan harga per jam berdasarkan tipe kendaraan
        int perJam;
        if (karcis.kendaraan.tipe == Kendaraan.Tipe.MOTOR) {
            perJam = 2000;
        } else {
            perJam = 5000;
        }
        return (int)jumlahJam * perJam;
    }

    /**
     * Mencari objek karcis dari list
     * @param kode Kode karcis
     * @return     Objek karcis
     */
    public Karcis cariKarcis(String kode) {
        for (Karcis karcis : listKarcis) {
            if (karcis.kode.equals(kode)) {
                return karcis;
            }
        }
        return null;
    }
}