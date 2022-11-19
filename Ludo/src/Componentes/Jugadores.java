/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import Client.Jugador;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author anonymous
 */
public class Jugadores extends HashMap<Integer, ArrayList<Jugador>> {

    Jugador yellow;
    Jugador blue;
    Jugador red;
    Jugador green;

    public Jugadores() {
        amarillos();
        rojos();
        azul();
        verdes();
        rojos();

    }

    private void amarillos() {
        ArrayList<Jugador> amarillo = new ArrayList<Jugador>();
        amarillo.add(new Jugador("Amarillo"));
        amarillo.add(new Jugador("Amarillo"));
        amarillo.add(new Jugador("Amarillo"));
        amarillo.add(new Jugador("Amarillo"));
        this.put(3, amarillo);

    }

    private void rojos() {
        ArrayList<Jugador> rojo = new ArrayList<Jugador>();
        rojo.add(new Jugador("Rojo"));
        rojo.add(new Jugador("Rojo"));
        rojo.add(new Jugador("Rojo"));
        rojo.add(new Jugador("Rojo"));
        this.put(1, rojo);
    }

    private void azul() {
        ArrayList<Jugador> azul = new ArrayList<Jugador>();
        azul.add(new Jugador("Azul"));
        azul.add(new Jugador("Azul"));
        azul.add(new Jugador("Azul"));
        azul.add(new Jugador("Azul"));
        this.put(4, azul);
    }

    private void verdes() {
        ArrayList<Jugador> verde = new ArrayList<Jugador>();
        verde.add(new Jugador("Verde"));
        verde.add(new Jugador("Verde"));
        verde.add(new Jugador("Verde"));
        verde.add(new Jugador("Verde"));
        this.put(2, verde);
    }

}
