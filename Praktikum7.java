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
public class Praktikum7 {

       public static void main(String[] args) {
        SistemParkir sistemParkir = new SistemParkir();
        // Menentukan mode program (konsol/gui) berdasarkan argumen program
        if (args.length > 0) {
            for (String arg : args) {
                if (arg.toUpperCase().equals("CONSOLE"))
                    new Konsol(sistemParkir);
                if (arg.toUpperCase().equals("GUI"))
                    new GUI(sistemParkir);
            }
        }
        else {
            new GUI(sistemParkir);
        }
    }
}