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
class Kendaraan {
    public enum Tipe {
        MOBIL, MOTOR
    }

    public Tipe tipe;
    public String platNomor;

    public Kendaraan(Tipe tipe, String platNomor) {
        this.tipe = tipe;
        this.platNomor = platNomor;
    }
}