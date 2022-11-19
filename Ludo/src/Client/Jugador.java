/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Dibujo.Dibujo;
import Dibujo.Square;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author anonymous
 */
public class Jugador extends Observable {

    private String name;
    private Icon img;
    private ImageIcon ico;
    private JLabel[] jugador = new JLabel[4];

    public Jugador(String name) {
//        this.setSize(40, 40);
        this.name = name;
        crear();
        Insertar_peon();

    }

    private void crear() {
//        jugador =;
        for (int i = 0; i < 4; i++) {
            jugador[i] = new JLabel();
            jugador[i].setSize(40, 40);
        }
    }

    private void Insertar_peon() {
        switch (name) {
            case "Amarillo":
                ico = new ImageIcon(getClass().getResource("/Imagen_Players/" + name + ".jpg"));
                img = new ImageIcon(ico.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
                break;
            case "Rojo":
                ico = new ImageIcon(getClass().getResource("/Imagen_Players/" + name + ".jpg"));
                img = new ImageIcon(ico.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
                break;
            case "Azul":
                ico = new ImageIcon(getClass().getResource("/Imagen_Players/" + name + ".jpg"));
                img = new ImageIcon(ico.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
                break;
            case "Verde":
                ico = new ImageIcon(getClass().getResource("/Imagen_Players/" + name + ".jpg"));
                img = new ImageIcon(ico.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
                break;
        }
        for (JLabel jLabel : jugador) {
            jLabel.setIcon(img);
        }

    }

    public boolean Comprobar_inicio(int select_peon, List<Square> star) {
        boolean comprobar = false;
        for (Square square1 : star) {
            if (getpeones(select_peon).getX() == square1.getX1() + getpeones(select_peon).getIcon().getIconWidth() / 2 && getpeones(select_peon).getY() == square1.getY1()) {
                comprobar = true;
                System.out.println("esta iniciando");
            }
        }
        return comprobar;
    }

    public boolean Comprobar_final(int select_peon, List<Square> wing) {
        boolean comprobar = false;
        for (Square square1 : wing) {
            if (getpeones(select_peon).getX() == square1.getX1() + getpeones(select_peon).getIcon().getIconWidth() / 2 && getpeones(select_peon).getY() == square1.getY1()) {
                comprobar = true;
                System.out.println("esta iniciando");
            }
        }
        return comprobar;
    }

    public int getIdObjetoEn(int posX, int posY) {
        for (int i = 0; i < jugador.length; i++) {

            int xx = posX - (jugador[i].getX() + 40 / 2);
            xx = xx * xx;

            int yy = posY - (jugador[i].getY() + 40 / 2);
            yy = yy * yy;

            if ((xx + yy) <= ((40 * 40) / 4)) {
                return i;
            }
//            
//            if(posX <= (coordenada.getX() + ANCHO) &&
//                    posX >= coordenada.getX() &&
//                    posY <= (coordenada.getY() + ANCHO) &&
//                    posY >= coordenada.getY()) {
//                return coordenada.getId();
//            }
        }

        return 10;
    }

    public JLabel getpeones(int number_peon) {
        return jugador[number_peon];
    }

    public void posicionar_peon(int number_peon, int posX, int posY) {
        jugador[number_peon].setLocation(posX, posY);
    }

    public String getName() {
        return name;
    }

    public int volver_inicio(JLabel contricante, int x, int y) {
        for (int i = 0; i < jugador.length; i++) {
            if (jugador[i].getX() == contricante.getX() && jugador[i].getY() == contricante.getY()) {
//                jugador[i].setLocation(x, y);
                return i;
            }

        }
        return 10;
    }

    // falta fijar bien el salto
    public void mover_jugador(int inicio, int salto, int number_peon, ArrayList<ArrayList<Integer>> recorrido) {
//        int total_salto = inicio + salto;

        if (salto == 0) {
            getpeones(number_peon).setLocation(recorrido.get(0).get(0), recorrido.get(1).get(0));
        } else if ((inicio + salto) <= 56) {
            int total_salto = inicio + salto;

            Thread hilo = new Thread(() -> {

                for (int i = inicio; i <= total_salto; i++) {
                    if (i < 56) {
                        if (i == 5 || i == 18 || i == 31 || i == 44) {

                            getpeones(number_peon).setLocation(recorrido.get(0).get(i), recorrido.get(1).get(i));
                        } else {
                            int compararX = getpeones(number_peon).getX() < recorrido.get(0).get(i) ? 1 : -1;
                            int compararY = getpeones(number_peon).getY() < recorrido.get(1).get(i) ? 1 : -1;

                            if (compararX > 0) {
                                for (int j = getpeones(number_peon).getX(); j <= recorrido.get(0).get(i); j++) {

                                    getpeones(number_peon).setLocation(j, getpeones(number_peon).getY());
                                    try {

                                        Thread.sleep(10);
//                                System.out.println("avanzando");
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Dibujo.class
                                                .getName()).log(Level.SEVERE, null, ex);
                                    }

                                }
                            }
                            if (compararY < 0) {

                                for (int j = getpeones(number_peon).getY(); j >= recorrido.get(1).get(i); j--) {

                                    getpeones(number_peon).setLocation(getpeones(number_peon).getX(), j);
                                    try {
                                        Thread.sleep(10);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Dibujo.class
                                                .getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                            }
                            if (compararX < 0) {
                                for (int j = getpeones(number_peon).getX(); j >= recorrido.get(0).get(i); j--) {

                                    try {

                                        getpeones(number_peon).setLocation(j, getpeones(number_peon).getY());
                                        Thread.sleep(10);
//                                System.out.println("avanzando");
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Dibujo.class
                                                .getName()).log(Level.SEVERE, null, ex);
                                    }

                                }

                            }
                            if (compararY > 0) {
                                for (int j = getpeones(number_peon).getY(); j <= recorrido.get(1).get(i); j++) {

                                    getpeones(number_peon).setLocation(getpeones(number_peon).getX(), j);
                                    try {
                                        Thread.sleep(10);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Dibujo.class
                                                .getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }

                        }
                    } else if (i == 56) {

                        getpeones(number_peon).setLocation(getpeones(number_peon).getX() + 40, getpeones(number_peon).getY());

                    }

                }
            }, "hilo red");

            hilo.start();
            //falta el paso final si el hilo termino

        }

    }

}
