/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dibujo;

import Client.Cliente;
import Test.Cuadrado;
import Dibujo.Turno1;
import Test.Turnos;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author anonymous
 */
public class Tablero extends JFrame implements WindowListener {

    
//    ArrayList<Square> square = new ArrayList();
    private Dibujo contenido;
//    private Turnos turnos;
    private Turno1 turno;
    private Thread hilo;
    private final Cliente cliente;

    public Tablero(String name, Cliente cliente) {
        this.setTitle(name);
        this.cliente = cliente;
        this.setSize(618, 637 + 90);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        contenido = new Dibujo(this.cliente, this.getWidth(), this.getHeight());
//        turnos = new Turnos();
//        hilo = new Thread(contenido, "Dibujo");
//        hilo.start();
        turno = new Turno1(cliente, contenido);
//        hilo = new Thread(turno, "Tuno");
//        hilo.start();

        this.getContentPane().add(contenido, BorderLayout.CENTER);
//        this.getContentPane().add(panel,BorderLayout.SOUTH);
        this.getContentPane().add(turno, BorderLayout.SOUTH);

        this.setVisible(true);

    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            cliente.write("cerrar");
            System.out.println("envio el mensaje para cerrar");
            cliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
