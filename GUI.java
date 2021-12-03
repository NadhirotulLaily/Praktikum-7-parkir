/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum7;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    DefaultTableModel tabelModel;
    SistemParkir sistemParkir;

    public GUI(SistemParkir sistemParkir) {
        this.sistemParkir = sistemParkir;

        JFrame frameWindow = new JFrame("Sistem Parkir");
        JPanel panelKendaraanMasuk = new JPanel();
        JPanel panelKendaraanKeluar = new JPanel();
        JScrollPane panelTabel = new JScrollPane();

        formKendaraanMasuk(panelKendaraanMasuk);
        formKendaraanKeluar(panelKendaraanKeluar);
        tabelDaftarKendaraan(panelTabel);

        frameWindow.setLayout(new FlowLayout(FlowLayout.LEFT));

        frameWindow.add(panelKendaraanMasuk);
        frameWindow.add(panelKendaraanKeluar);
        frameWindow.add(panelTabel);

        // Window dengan tema sesuai OS
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException
                | InstantiationException | IllegalAccessException ignored) { }

        frameWindow.setSize(600, 400);
        // Mencegah resize window
        frameWindow.setResizable(false);
        frameWindow.setVisible(true);
    }

    private void formKendaraanMasuk(JPanel panel) {
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Kendaraan masuk"));

        JTextField fieldTipe = new JTextField(5);
        JTextField fieldPlatNomor = new JTextField(12);
        JButton tombolSubmit = new JButton("Submit");

        panel.add(new JLabel("Tipe:"));
        panel.add(fieldTipe);
        panel.add(new JLabel("Plat:"));
        panel.add(fieldPlatNomor);
        panel.add(tombolSubmit);

        tombolSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Memastikan tidak ada isian yang kosong
                if (isFieldEmpty(fieldTipe) || isFieldEmpty(fieldPlatNomor)) {
                    JOptionPane.showMessageDialog(panel, "Ada isian yg belum diisi!");
                    return;
                }
                Kendaraan.Tipe tipe = Kendaraan.Tipe.valueOf(fieldTipe.getText().toUpperCase());
                String platNomor = fieldPlatNomor.getText().toUpperCase();
                sistemParkir.cekIn(tipe, platNomor);
                // Karena isi list berubah, update tabel
                updateTabel();
                // Kosongkan kembali field setelah selesai
                fieldTipe.setText("");
                fieldPlatNomor.setText("");
            }
        });
    }

    private void formKendaraanKeluar(JPanel panel) {
        // Rata kiri
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Kendaraan keluar"));

        JTextField fieldKodeKarcis = new JTextField(6);
        JButton tombolSubmit = new JButton("Submit");

        panel.add(new JLabel("Kode karcis:"));
        panel.add(fieldKodeKarcis);
        panel.add(tombolSubmit);

        tombolSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String kode = fieldKodeKarcis.getText();
                // Memastikan kode karcis ada di list
                if (sistemParkir.cariKarcis(kode) == null) {
                    JOptionPane.showMessageDialog(panel, "Kode karcis tidak ditemukan!");
                    return;
                }
                // Menampilkan dialog biaya parkir
                int biaya = sistemParkir.biayaParkir(kode);
                JOptionPane.showMessageDialog(panel, "Biaya parkir " + biaya);
                sistemParkir.cekOut(kode);
                // Karena isi list berubah, update tabel
                updateTabel();
                // Kosongkan kembali field setelah selesai
                fieldKodeKarcis.setText("");
            }
        });
    }

    /**
     * Menampilkan tabel daftar kendaraan parkir
     * @param panel Panel untuk tabel
     */
    private void tabelDaftarKendaraan(JScrollPane panel) {
        panel.setBorder(BorderFactory.createTitledBorder("Daftar karcis"));
        String[] kolom = {"Kode", "Tipe", "Plat Nomor", "Waktu masuk"};
        String[][] isi = {}; // Awalnya berisi kosong
        tabelModel = new DefaultTableModel(isi, kolom);
        JTable tabel = new JTable(tabelModel);
        // Set ukuran tabel sebelum dapat discroll
        tabel.setPreferredScrollableViewportSize(new Dimension(540, 175));
        // Mencegah interaksi dengan tabel
        tabel.setEnabled(false);
        panel.setViewportView(tabel);
        // Isi tabel
        updateTabel();
    }

    /**
     * Mengupdate isi tabel dengan mengosongkan tabel dan mengisinya dengan data terbaru.
     */
    private void updateTabel() {
        // Mengosongkan tabel
        tabelModel.setRowCount(0);
        // Menambahkan baris-baris pada tabel dengan data dari listKarcis
        for (Karcis karcis : sistemParkir.listKarcis) {
            String[] baris = { karcis.kode, karcis.kendaraan.tipe.toString(), karcis.kendaraan.platNomor, karcis.masuk.toString() };
            tabelModel.addRow(baris);
        }
    }

    /**
     * Mengecek apabila text field masih kosong.
     * @param field Objek text field
     * @return      Bernilai true apabila kosong
     */
    public boolean isFieldEmpty(JTextField field) {
        return field.getText().equals("");
    }
}