/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dibujo;

import Client.Cliente;

/**
 *
 * @author anonymous
 */
public class Ludo implements Runnable {

    Tablero tablero;
    Cliente cliente;
    String title;

    public Ludo(String title, Cliente cliente) {

//        System.out.println(cliente.getName_sala());
        this.cliente = cliente;
        this.title = title;
//        Iniciar(title);

    }

    private void Iniciar(String t) {
        tablero = new Tablero(t, cliente);
    }

    @Override
    public void run() {
        Iniciar(title);
    }

}
