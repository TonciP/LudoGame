/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dibujo;

import Client.Cliente;
import Client.Jugador;
import Componentes.Triangulo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 *
 * @author anonymous
 */
public class Dibujo extends JPanel implements Observer, MouseListener {

//    Cuadrado[] cuadrao;
    ArrayList<Square> square = new ArrayList();
    ArrayList<Square> winR = new ArrayList();
    ArrayList<Square> winG = new ArrayList();
    ArrayList<Square> winY = new ArrayList();
    ArrayList<Square> winB = new ArrayList();

    ArrayList<Square> starR = new ArrayList();
    ArrayList<Square> starG = new ArrayList();
    ArrayList<Square> starY = new ArrayList();
    ArrayList<Square> starB = new ArrayList();

    ArrayList<ArrayList<Integer>> travel_red = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> travel_green = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> travel_yellow = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> travel_blue = new ArrayList<ArrayList<Integer>>();

    ArrayList<Triangulo> finish = new ArrayList();

    Jugador player_yellow = new Jugador("Amarillo");
    Jugador player_red = new Jugador("Rojo");
    Jugador player_green = new Jugador("Verde");
    Jugador player_blue = new Jugador("Azul");

    private Cliente cliente;

    public Dibujo(Cliente cliente, int ancho, int alto) {
        this.cliente = cliente;

        this.setSize(ancho, alto);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.añadir_recorrido();
        añadir_star();
        finish();

        agregar_jugadores();
        Obtener_travel();

//        this.cliente.addObserver(this);
        this.addMouseListener(this);

    }

    @Override
    public void paintComponent(Graphics gs) {
        super.paintComponent(gs);
        Graphics2D g2 = (Graphics2D) gs;
        int width = 223;
        int height = 222;

        g2.setStroke(new BasicStroke(18));
        g2.setColor(new Color(255, 26, 26));
        g2.drawRect(8, 8, width, height);
        g2.setColor(new Color(74, 255, 74));
//        g2.drawRect(this.getWidth() - (width + 8), 8, width, height);
        g2.drawRect(width + 147, 8, width, height);
        g2.setColor(new Color(255, 255, 60));
//        g2.drawRect(8, this.getHeight() - (height + 8), width, height);
        g2.drawRect(8, height + 147, width, height);
        g2.setColor(new Color(64, 64, 255));
//        g2.drawRect(this.getWidth() - (width + 8), this.getHeight() - (height + 8), width, height);
        g2.drawRect(width + 147, height + 147, width, height);
        g2.setStroke(new BasicStroke(2));

//        square.forEach((square1) -> {
//
//            square1.Dibujar(g2);
//        });
        for (int i = 0; i < square.size(); i++) {
            square.get(i).Dibujar(g2);
            g2.drawString("" + i, square.get(i).getX1() + (square.get(i).getX2() / 2), square.get(i).getY1() + (square.get(i).getY2() / 2));
        }

        winG.stream().map((squareG) -> {
            squareG.DibujarG(g2);
            return squareG;
        }).forEachOrdered((squareG) -> {
            squareG.Dibujar(g2);
        });

        winB.stream().map((squareB) -> {
            squareB.DibujarB(g2);
            return squareB;
        }).forEachOrdered((squareB) -> {
            squareB.Dibujar(g2);
        });
        winY.stream().map((squareY) -> {
            squareY.DibujarY(g2);
            return squareY;
        }).forEachOrdered((squareY) -> {
            squareY.Dibujar(g2);
        });
        winR.stream().map((squareR) -> {
            squareR.DibujarR(g2);
            return squareR;
        }).forEachOrdered((squareR) -> {
            squareR.Dibujar(g2);
        });
        starR.forEach((squareR) -> {
            squareR.DibujarR_star(g2);
        });
        starG.forEach((squareG) -> {
            squareG.DibujarG_star(g2);
        });
        starY.forEach((squareY) -> {
            squareY.DibujarY_star(g2);
        });
        starB.forEach((squareB) -> {
            squareB.DibujarB_star(g2);
        });
        starB.forEach((squareB) -> {
            squareB.DibujarB_star(g2);
        });
//        finish.forEach((triangulo) -> {
//            triangulo.paintBlue(g2);
//            triangulo.paint(g2);
//        });

        finish.get(0).paintBlue(g2);
        finish.get(0).paint(g2);
        finish.get(1).paintYellow(g2);
        finish.get(1).paint(g2);
        finish.get(2).paintRed(g2);
        finish.get(2).paint(g2);
        finish.get(3).paintGreen(gs);
        finish.get(3).paint(g2);
    }

    private void añadir_recorrido() {
        int Mitadwidth = (this.getWidth() / 2) + 2;
        int Mitadheight = (this.getWidth() / 2) + 2;

        int puntoMX = Mitadwidth;
        int puntoMY = Mitadheight - 25;
        int positiveX = puntoMX + 10;
        int negativoX = puntoMX - 78;
        int positiveY = puntoMY + 72;
        int negativoY = puntoMY - 72;

        int squareZize = 40;

        int distancia = 2;

        square.add(new Square(positiveX - squareZize, 0, squareZize, squareZize));
        //primer bloque 
        for (int i = 0; i < 6; i++) {
            square.add(new Square(positiveX, squareZize * i, squareZize, squareZize));
        }
        //win Green   
        for (int i = 1; i < 6; i++) {
            winG.add(new Square(positiveX - squareZize, squareZize * i, squareZize, squareZize));
        }
        //segundo bloque 
        int derecha = (squareZize * 2) - 8;
        for (int i = 1; i < 7; i++) {
            square.add(new Square(positiveX + (squareZize * i), Mitadheight - derecha, squareZize, squareZize));
        }
        //tercer bloque 
        for (int i = 1; i < 3; i++) {
            square.add(new Square(positiveX + (squareZize * 6), (Mitadheight - derecha) + squareZize * i, squareZize, squareZize));
        }
        //cuarto bloque 
        for (int i = 5; i > 0; i--) {
            square.add(new Square(positiveX + (squareZize * i), (Mitadheight - derecha) + squareZize * 2, squareZize, squareZize));
        }
        //win Blue   
        for (int i = 5; i > 0; i--) {
            winB.add(new Square(positiveX + (squareZize * i), (Mitadheight - derecha) + squareZize * 1, squareZize, squareZize));
        }
        //quinto bloque 
        for (int i = 0; i < 6; i++) {
            square.add(new Square(positiveX, positiveY + (squareZize * i), squareZize, squareZize));
        }
        //sexto bloque 
        for (int i = 1; i < 3; i++) {
            square.add(new Square(positiveX - (squareZize * i), positiveY + (squareZize * 5), squareZize, squareZize));
        }
        //septimo bloque 
        for (int i = 4; i >= 0; i--) {
            square.add(new Square(positiveX - (squareZize * 2), positiveY + (squareZize * i), squareZize, squareZize));
        }
        // win Yellow
        for (int i = 4; i >= 0; i--) {
            winY.add(new Square(positiveX - (squareZize), positiveY + (squareZize * i), squareZize, squareZize));
        }
        //octavo bloque 
        for (int i = 0; i < 6; i++) {
            square.add(new Square(positiveX - (squareZize * i) - 1 - (squareZize * 3), (Mitadheight - derecha) + squareZize * 2, squareZize, squareZize));
        }
        //noveno bloque 
        for (int i = 1; i >= 0; i--) {
            square.add(new Square(0, (Mitadheight - derecha) + squareZize * i, squareZize, squareZize));
        }
        //decimo bloque 
        for (int i = 1; i < 6; i++) {
            square.add(new Square(squareZize * i, (Mitadheight - derecha), squareZize, squareZize));
        }
        //win Red
        for (int i = 1; i < 6; i++) {
            winR.add(new Square(squareZize * i, (Mitadheight - derecha) + squareZize, squareZize, squareZize));
        }
        //udecimo bloque 
        for (int i = 5; i >= 0; i--) {
            square.add(new Square(positiveX - (squareZize * 2), squareZize * i, squareZize, squareZize));
        }
    }

    private void añadir_star() {
        int squareZize = 40;
        starR.add(new Square((223 / 2) - squareZize, (222 / 2) - squareZize, squareZize, squareZize));
//        players.get(1).get(0).setLocation((223 / 2) - squareZize + players.get(1).get(0).getIcon().getIconWidth() / 2, (222 / 2) - squareZize);
        starR.add(new Square((223 / 2) + 5, (222 / 2) - squareZize, squareZize, squareZize));
//        players.get(1).get(1).setLocation((223 / 2) + 5 + players.get(1).get(1).getIcon().getIconWidth() / 2, (222 / 2) - squareZize);
        starR.add(new Square((223 / 2) - squareZize, (222 / 2) + 5, squareZize, squareZize));
//        players.get(1).get(2).setLocation((223 / 2) - squareZize + players.get(1).get(2).getIcon().getIconWidth() / 2, (222 / 2) + 5);
        starR.add(new Square((223 / 2) + 5, (222 / 2) + 5, squareZize, squareZize));
//        players.get(1).get(3).setLocation((223 / 2) + 5 + players.get(1).get(3).getIcon().getIconWidth() / 2, (222 / 2) + 5);

        starG.add(new Square(((223 + 147) + (223 / 2)) - squareZize, (222 / 2) - squareZize, squareZize, squareZize));
        starG.add(new Square(((223 + 147) + (223 / 2)) + 5, (222 / 2) - squareZize, squareZize, squareZize));
        starG.add(new Square(((223 + 147) + (223 / 2)) - squareZize, (222 / 2) + 5, squareZize, squareZize));
        starG.add(new Square(((223 + 147) + (223 / 2)) + 5, (222 / 2) + 5, squareZize, squareZize));

        starY.add(new Square((223 / 2) - squareZize, ((222 + 147) + (222 / 2)) - squareZize, squareZize, squareZize));

//        player.setBounds((223 / 2) - squareZize+(player.getIcon().getIconWidth()/2), ((222 + 147) + (222 / 2)) - squareZize, 40, 40);
        starY.add(new Square((223 / 2) + 5, ((222 + 147) + (222 / 2)) - squareZize, squareZize, squareZize));
        starY.add(new Square((223 / 2) - squareZize, ((222 + 147) + (222 / 2)) + 5, squareZize, squareZize));
        starY.add(new Square((223 / 2) + 5, ((222 + 147) + (222 / 2)) + 5, squareZize, squareZize));
//        
        starB.add(new Square(((223 + 147) + (223 / 2)) - squareZize, ((222 + 147) + (222 / 2)) - squareZize, squareZize, squareZize));
        starB.add(new Square(((223 + 147) + (223 / 2)) + 5, ((222 + 147) + (222 / 2)) - squareZize, squareZize, squareZize));
        starB.add(new Square(((223 + 147) + (223 / 2)) - squareZize, ((222 + 147) + (222 / 2)) + 5, squareZize, squareZize));
        starB.add(new Square(((223 + 147) + (223 / 2)) + 5, ((222 + 147) + (222 / 2)) + 5, squareZize, squareZize));
    }
// coordena final

    private void finish() {
        int wiqdth = (this.getWidth() / 2) - 8;
        int heigth = (this.getHeight() / 2) - 66;
        int[] vx1 = {wiqdth, wiqdth + 58, wiqdth + 58};
        int[] vy1 = {heigth, heigth - 57, heigth + 58};
        finish.add(new Triangulo(vx1, vy1));
        int[] vx2 = {wiqdth, wiqdth - 58, wiqdth + 58};
        int[] vy2 = {heigth, heigth + 59, heigth + 59};
        finish.add(new Triangulo(vx2, vy2));
        int[] vx3 = {wiqdth, wiqdth - 59, wiqdth - 59};
        int[] vy3 = {heigth, heigth - 56, heigth + 59};
        finish.add(new Triangulo(vx3, vy3));
        int[] vx4 = {wiqdth, wiqdth - 58, wiqdth + 59};
        int[] vy4 = {heigth, heigth - 56, heigth - 58};
        finish.add(new Triangulo(vx4, vy4));
    }
// agregamos jugadores

    private void agregar_jugadores() {
        for (int i = 0; i < starR.size(); i++) {
            player_red.getpeones(i).setLocation(starR.get(i).getX1() + player_red.getpeones(i).getIcon().getIconWidth() / 2, starR.get(i).getY1());
        }
        for (int i = 0; i < starG.size(); i++) {
            player_green.getpeones(i).setLocation(starG.get(i).getX1() + player_green.getpeones(i).getIcon().getIconWidth() / 2, starG.get(i).getY1());
        }
        for (int i = 0; i < starY.size(); i++) {
            player_yellow.getpeones(i).setLocation(starY.get(i).getX1() + player_yellow.getpeones(i).getIcon().getIconWidth() / 2, starY.get(i).getY1());
        }
        for (int i = 0; i < starB.size(); i++) {
            player_blue.getpeones(i).setLocation(starB.get(i).getX1() + player_blue.getpeones(i).getIcon().getIconWidth() / 2, starB.get(i).getY1());
        }

        for (int i = 0; i < 4; i++) {
            this.add(player_red.getpeones(i));
            this.add(player_green.getpeones(i));
            this.add(player_yellow.getpeones(i));
            this.add(player_blue.getpeones(i));
        }
    }
    // obtenemos el numero del dado 

    @Override
    public void update(Observable objeto, Object msg) {
        this.repaint();

    }
// movemos el peon seleccionado
    int dice_number;

    public void mover(int msg) {
        dice_number = msg;

    }

    // obtener recorridos
    private void Obtener_travel() {
//        List<Square> recorrido_rojo = square.subList(41, 51);
//        recorrido_rojo.addAll(square.subList(0, 39));
        //coordena peon rojo
        ArrayList<Integer> cordenaX = new ArrayList();
        ArrayList<Integer> cordenaY = new ArrayList();
        for (int i = 41; i <= 51; i++) {
            cordenaX.add(square.get(i).getX1());
            cordenaY.add(square.get(i).getY1());
        }
        for (int i = 0; i <= 39; i++) {
            cordenaX.add(square.get(i).getX1());
            cordenaY.add(square.get(i).getY1());
        }
        for (Square square1 : winR) {
            cordenaX.add(square1.getX1());
            cordenaY.add(square1.getY1());
        }
        travel_red.add(cordenaX);
        travel_red.add(cordenaY);
        //coordenada peon verde
        ArrayList<Integer> cordenaX1 = new ArrayList();
        ArrayList<Integer> cordenaY1 = new ArrayList();
//        java.util.List<Square> recorrido_Verde = square.subList(2, 51);
//        recorrido_Verde.add(square.get(0));
        for (int i = 2; i <= 51; i++) {
            cordenaX1.add(square.get(i).getX1());
            cordenaY1.add(square.get(i).getY1());
        }
        cordenaX1.add(square.get(0).getX1());
        cordenaY1.add(square.get(0).getY1());
        for (Square square1 : winG) {
            cordenaX1.add(square1.getX1());
            cordenaY1.add(square1.getY1());
        }
        travel_green.add(cordenaX1);
        travel_green.add(cordenaY1);

        //cordena yellow
        ArrayList<Integer> cordenaX2 = new ArrayList();
        ArrayList<Integer> cordenaY2 = new ArrayList();
//        java.util.List<Square> recorrido_Amarrillo = square.subList(28, 51);
//        recorrido_Amarrillo.addAll(square.subList(0, 26));
        for (int i = 28; i <= 51; i++) {
            cordenaX2.add(square.get(i).getX1());
            cordenaY2.add(square.get(i).getY1());
        }
        for (int i = 0; i <= 26; i++) {
            cordenaX2.add(square.get(i).getX1());
            cordenaY2.add(square.get(i).getY1());
        }
        for (Square square1 : winY) {
            cordenaX2.add(square1.getX1());
            cordenaY2.add(square1.getY1());
        }
        travel_yellow.add(cordenaX2);

        travel_yellow.add(cordenaY2);
        //cordena Blue
        ArrayList<Integer> cordenaX3 = new ArrayList();
        ArrayList<Integer> cordenaY3 = new ArrayList();
//        java.util.List<Square> recorrido_Azul = square.subList(15, 51);
//        recorrido_Azul.addAll(square.subList(0, 13));
        for (int i = 15; i <= 51; i++) {

            cordenaX3.add(square.get(i).getX1());
            cordenaY3.add(square.get(i).getY1());

        }
        for (int i = 0; i <= 13; i++) {
            cordenaX3.add(square.get(i).getX1());
            cordenaY3.add(square.get(i).getY1());

        }
        for (Square square1 : winB) {
            cordenaX3.add(square1.getX1());
            cordenaY3.add(square1.getY1());
        }
        travel_blue.add(cordenaX3);
        travel_blue.add(cordenaY3);
    }

    //actualizo el tablero despues de recibir el mensaje del cliente
    public void Actualizar_tablero(String msg) {
        String[] splic = msg.split(",");
        String name = splic[1];
        int inicio = Integer.parseInt(splic[2]);
        int dice_number = Integer.parseInt(splic[3]);
        int peon_number = Integer.parseInt(splic[4]);
        switch (name) {
            case "cliente1":

//                moverRojo(inicio, dice_number, peon_number);
                player_red.mover_jugador(inicio, dice_number, peon_number, travel_red);
                break;
            case "cliente2":

//                moverVerde(inicio, dice_number, peon_number);
                player_green.mover_jugador(inicio, dice_number, peon_number, travel_green);
                break;
            case "cliente3":
//                moverAmarillo(inicio, dice_number, peon_number);
                player_yellow.mover_jugador(inicio, dice_number, peon_number, travel_yellow);
                break;
            case "cliente4":
                System.out.println("cliente4");
                player_blue.mover_jugador(inicio, dice_number, peon_number, travel_blue);
                break;
        }
    }

    // evento de seleccion del dado dado para el cliente name
    @Override
    public void mouseClicked(MouseEvent e) {

        int index = 0;
        System.out.println("numero del dado:" + dice_number);

        boolean esta_iniciando;
        switch (cliente.getName()) {
            case "cliente1":
                int select_peon = player_red.getIdObjetoEn(e.getX(), e.getY());
                if (select_peon < 4) {
                    esta_iniciando = player_red.Comprobar_inicio(select_peon, starR);
                    if (esta_iniciando == true && dice_number == 6) {

//                    System.out.println("se recorrera desde::" + index);
//                        moverRojo(index, 0, select_peon);
                        player_red.mover_jugador(index, 0, select_peon, travel_red);
                        comer_peon(player_red.getName(), select_peon);

//                    System.out.println("cliente inicia y envia mensaje");
                        cliente.write(cliente.getName_sala() + "," + cliente.getName() + "," + index + "," + 0 + "," + select_peon);

                    } else {
                        for (int j = 0; j < travel_red.get(0).size(); j++) {
                            int x = travel_red.get(0).get(j);
                            int y = travel_red.get(1).get(j);

                            if (player_red.getpeones(select_peon).getX() == x && player_red.getpeones(select_peon).getY() == y) {
                                index = j;
//                        System.out.println("es igual al recorrido numero:" + index);
                                break;

                            }

                        }
                    }
                    if (esta_iniciando == false && (index + dice_number) <= 56) {

//                        moverRojo(index, dice_number, select_peon);
                        player_red.mover_jugador(index, dice_number, select_peon, travel_red);
                        comer_peon(player_red.getName(), select_peon);
//                    System.out.println("cliente envia el mensaje");
                        cliente.write(cliente.getName_sala() + "," + cliente.getName() + "," + index + "," + dice_number + "," + select_peon);

                    }
                }
                break;
            case "cliente2":
                int peon_verde = player_green.getIdObjetoEn(e.getX(), e.getY());
                if (peon_verde < 4) {
                    System.out.println("peon seleccionado :" + peon_verde);
                    esta_iniciando = player_green.Comprobar_inicio(peon_verde, starG);

                    if (esta_iniciando == true && dice_number == 6) {

//                    System.out.println("desde:" + index);
//                        moverVerde(index, 0, peon_verde);
                        player_green.mover_jugador(index, 0, peon_verde, travel_green);
                        comer_peon(player_green.getName(), peon_verde);
                        cliente.write(cliente.getName_sala() + "," + cliente.getName() + "," + index + "," + 0 + "," + peon_verde);

                    } else {
                        for (int j = 0; j < travel_green.get(0).size(); j++) {
                            int x = travel_green.get(0).get(j);
                            int y = travel_green.get(1).get(j);

                            if (player_green.getpeones(peon_verde).getX() == x && player_green.getpeones(peon_verde).getY() == y) {
                                index = j;
//                        System.out.println("es igual al recorrido numero:" + index);
                                break;

                            }

                        }
                    }

                    if (esta_iniciando == false && (index + dice_number) <= 56) {
//                        moverVerde(index, dice_number, peon_verde);
                        player_green.mover_jugador(index, dice_number, peon_verde, travel_green);
                        comer_peon(player_green.getName(), peon_verde);
                        cliente.write(cliente.getName_sala() + "," + cliente.getName() + "," + index + "," + dice_number + "," + peon_verde);

                    }
                }

                break;
            case "cliente3":
                int numero_peon = player_yellow.getIdObjetoEn(e.getX(), e.getY());
                int peon_amarillo;
                if (numero_peon < 4) {
                    peon_amarillo = numero_peon;
                    esta_iniciando = player_yellow.Comprobar_inicio(peon_amarillo, starY);

                    if (esta_iniciando == true && dice_number == 6) {
//                                    player_red.getpeones(i).setLocation(travel_red.get(0).get(0), travel_red.get(1).get(0));
                        System.out.println("desde:" + index);
//                        moverAmarillo(index, 0, peon_amarillo);
                        player_yellow.mover_jugador(index, 0, peon_amarillo, travel_yellow);
                        comer_peon(player_yellow.getName(), peon_amarillo);
                        cliente.write(cliente.getName_sala() + "," + cliente.getName() + "," + index + "," + 0 + "," + peon_amarillo);

                    } else {
                        for (int j = 0; j < travel_yellow.get(0).size(); j++) {
                            int x = travel_yellow.get(0).get(j);
                            int y = travel_yellow.get(1).get(j);

                            if (player_yellow.getpeones(peon_amarillo).getX() == x && player_yellow.getpeones(peon_amarillo).getY() == y) {
                                index = j;

                                System.out.println("es igual al recorrido numero:" + index);
                                break;

                            }

                        }
                    }

                    if (esta_iniciando == false && (index + dice_number) <= 56) {
//                        moverAmarillo(index, dice_number, peon_amarillo);
                        player_yellow.mover_jugador(index, dice_number, peon_amarillo, travel_yellow);
                        comer_peon(player_yellow.getName(), peon_amarillo);
                        cliente.write(cliente.getName_sala() + "," + cliente.getName() + "," + index + "," + dice_number + "," + peon_amarillo);

                    }

                }

                break;
            case "cliente4":
                int numeros_peon = player_blue.getIdObjetoEn(e.getX(), e.getY());
                int peon_blue;
                if (numeros_peon < 4) {
                    peon_blue = numeros_peon;
                    esta_iniciando = player_blue.Comprobar_inicio(peon_blue, starB);

                    if (esta_iniciando == true && dice_number == 6) {
//                                    player_red.getpeones(i).setLocation(travel_red.get(0).get(0), travel_red.get(1).get(0));
                        System.out.println("desde:" + index);
//                        moverAmarillo(index, 0, peon_amarillo);
                        player_blue.mover_jugador(index, 0, peon_blue, travel_blue);
                        comer_peon(player_blue.getName(), peon_blue);
                        cliente.write(cliente.getName_sala() + "," + cliente.getName() + "," + index + "," + 0 + "," + peon_blue);

                    } else {
                        for (int j = 0; j < travel_blue.get(0).size(); j++) {
                            int x = travel_blue.get(0).get(j);
                            int y = travel_blue.get(1).get(j);

                            if (player_blue.getpeones(peon_blue).getX() == x && player_blue.getpeones(peon_blue).getY() == y) {
                                index = j;

                                System.out.println("es igual al recorrido numero:" + index);
                                break;

                            }

                        }
                    }

                    if (esta_iniciando == false && (index + dice_number) <= 56) {
//                        moverAmarillo(index, dice_number, peon_amarillo);
                        player_blue.mover_jugador(index, dice_number, peon_blue, travel_blue);
                        comer_peon(player_blue.getName(), peon_blue);
                        cliente.write(cliente.getName_sala() + "," + cliente.getName() + "," + index + "," + dice_number + "," + peon_blue);

                    }

                }
                break;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        ;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ;
    }

    public void comer_peon(String name, int selec_peon) {
        int compararX;
        int compararY;
        if (name.equals("Rojo")) {
            compararX = player_red.getpeones(selec_peon).getX();
            compararY = player_red.getpeones(selec_peon).getY();
            for (int i = 0; i < 4; i++) {

                if (player_green.getpeones(i).getX() == compararX && player_green.getpeones(i).getY() == compararY) {
//                    player_green.getpeones(selec_peon).setLocation(starG.get(i).getX1() + player_green.getpeones(selec_peon).getIcon().getIconWidth() / 2, starG.get(i).getY1());
                    player_green.getpeones(i).setLocation(starG.get(i).getX1() + 20 / 2, starG.get(i).getY1());
                    cliente.write(cliente.getName_sala() + "," + "comer" + "," + name + "," + selec_peon);
                } else if (player_yellow.getpeones(i).getX() == compararX && player_yellow.getpeones(i).getY() == compararY) {
//                    player_yellow.getpeones(selec_peon).setLocation(starY.get(i).getX1() + player_yellow.getpeones(selec_peon).getIcon().getIconWidth() / 2, starY.get(i).getY1());
                    player_yellow.getpeones(i).setLocation(starY.get(i).getX1() + 20 / 2, starY.get(i).getY1());
                    cliente.write(cliente.getName_sala() + "," + "comer" + "," + name + "," + selec_peon);
                } else if (player_blue.getpeones(i).getX() == compararX && player_blue.getpeones(i).getY() == compararY) {
//                    player_blue.getpeones(selec_peon).setLocation(starB.get(i).getX1() + player_blue.getpeones(selec_peon).getIcon().getIconWidth() / 2, starB.get(i).getY1());
                    player_blue.getpeones(i).setLocation(starB.get(i).getX1() + 20 / 2, starB.get(i).getY1());
                    cliente.write(cliente.getName_sala() + "," + "comer" + "," + name + "," + selec_peon);
                }

            }
        }
        if (name.equals("Verde")) {
            compararX = player_green.getpeones(selec_peon).getX();
            compararY = player_green.getpeones(selec_peon).getY();
            for (int i = 0; i < 4; i++) {

                if (player_red.getpeones(i).getX() == compararX && player_red.getpeones(i).getY() == compararY) {
//                    player_red.getpeones(selec_peon).setLocation(starR.get(i).getX1() + player_red.getpeones(selec_peon).getIcon().getIconWidth() / 2, starR.get(i).getY1());
                    player_red.getpeones(i).setLocation(starR.get(i).getX1() + 20 / 2, starR.get(i).getY1());
                    cliente.write(cliente.getName_sala() + "," + "comer" + "," + name + "," + selec_peon);
                } else if (player_yellow.getpeones(i).getX() == compararX && player_yellow.getpeones(i).getY() == compararY) {
//                    player_yellow.getpeones(selec_peon).setLocation(starY.get(i).getX1() + player_yellow.getpeones(selec_peon).getIcon().getIconWidth() / 2, starY.get(i).getY1());
                    player_yellow.getpeones(i).setLocation(starY.get(i).getX1() + 20 / 2, starY.get(i).getY1());
                    cliente.write(cliente.getName_sala() + "," + "comer" + "," + name + "," + selec_peon);
                } else if (player_blue.getpeones(i).getX() == compararX && player_blue.getpeones(i).getY() == compararY) {
//                    player_blue.getpeones(selec_peon).setLocation(starB.get(i).getX1() + player_blue.getpeones(selec_peon).getIcon().getIconWidth() / 2, starB.get(i).getY1());
                    player_blue.getpeones(i).setLocation(starB.get(i).getX1() + 20 / 2, starB.get(i).getY1());
                    cliente.write(cliente.getName_sala() + "," + "comer" + "," + name + "," + selec_peon);
                }

            }
        }
        if (name.equals("Amarillo")) {
            compararX = player_yellow.getpeones(selec_peon).getX();
            compararY = player_yellow.getpeones(selec_peon).getY();
            for (int i = 0; i < 4; i++) {

                if (player_red.getpeones(i).getX() == compararX && player_red.getpeones(i).getY() == compararY) {
//                    player_red.getpeones(selec_peon).setLocation(starR.get(i).getX1() + player_red.getpeones(selec_peon).getIcon().getIconWidth() / 2, starR.get(i).getY1());
                    player_red.getpeones(i).setLocation(starR.get(i).getX1() + 20 / 2, starR.get(i).getY1());
                    cliente.write(cliente.getName_sala() + "," + "comer" + "," + name + "," + selec_peon);
                } else if (player_green.getpeones(i).getX() == compararX && player_green.getpeones(i).getY() == compararY) {
//                    player_green.getpeones(selec_peon).setLocation(starG.get(i).getX1() + player_green.getpeones(selec_peon).getIcon().getIconWidth() / 2, starG.get(i).getY1());
                    player_green.getpeones(i).setLocation(starG.get(i).getX1() + 20 / 2, starG.get(i).getY1());
                    cliente.write(cliente.getName_sala() + "," + "comer" + "," + name + "," + selec_peon);
                } else if (player_blue.getpeones(i).getX() == compararX && player_blue.getpeones(i).getY() == compararY) {
//                    player_blue.getpeones(selec_peon).setLocation(starB.get(i).getX1() + player_blue.getpeones(selec_peon).getIcon().getIconWidth() / 2, starB.get(i).getY1());
                    player_blue.getpeones(i).setLocation(starB.get(i).getX1() + 20 / 2, starB.get(i).getY1());
                    cliente.write(cliente.getName_sala() + "," + "comer" + "," + name + "," + selec_peon);
                }

            }

        }
        if (name.equals("Azul")) {
            compararX = player_blue.getpeones(selec_peon).getX();
            compararY = player_blue.getpeones(selec_peon).getY();
            for (int i = 0; i < 4; i++) {

                if (player_red.getpeones(i).getX() == compararX && player_red.getpeones(i).getY() == compararY) {
//                    player_red.getpeones(selec_peon).setLocation(starR.get(i).getX1() + player_red.getpeones(selec_peon).getIcon().getIconWidth() / 2, starR.get(i).getY1());
                    player_red.getpeones(i).setLocation(starR.get(i).getX1() + 20 / 2, starR.get(i).getY1());
                    cliente.write(cliente.getName_sala() + "," + "comer" + "," + name + "," + selec_peon);
                } else if (player_green.getpeones(i).getX() == compararX && player_green.getpeones(i).getY() == compararY) {
//                    player_green.getpeones(selec_peon).setLocation(starG.get(i).getX1() + player_green.getpeones(selec_peon).getIcon().getIconWidth() / 2, starG.get(i).getY1());
                    player_green.getpeones(i).setLocation(starG.get(i).getX1() + 20 / 2, starG.get(i).getY1());
                    cliente.write(cliente.getName_sala() + "," + "comer" + "," + name + "," + selec_peon);
                } else if (player_yellow.getpeones(i).getX() == compararX && player_yellow.getpeones(i).getY() == compararY) {
//                    player_yellow.getpeones(selec_peon).setLocation(starY.get(i).getX1() + player_yellow.getpeones(selec_peon).getIcon().getIconWidth() / 2, starY.get(i).getY1());
                    player_yellow.getpeones(i).setLocation(starY.get(i).getX1() + 20 / 2, starY.get(i).getY1());
                    cliente.write(cliente.getName_sala() + "," + "comer" + "," + name + "," + selec_peon);
                }

            }
        }
    }

}
