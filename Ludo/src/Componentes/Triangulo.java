/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 *
 * @author anonymous
 */
public class Triangulo {

    private int[] vx2;
    private int[] vy2;

    public Triangulo() {
    }

    public Triangulo(int[] vx2, int[] vy2) {
        this.vx2 = vx2;
        this.vy2 = vy2;
    }

    public void paint(Graphics gs) {
        gs.setColor(Color.BLACK);
        gs.drawPolygon(vx2, vy2, 3);

    }

    public void paintBlue(Graphics gs) {
        gs.setColor(Color.BLUE);

        gs.fillPolygon(vx2, vy2, 3);

    }
    public void paintYellow(Graphics gs) {
        gs.setColor(Color.YELLOW);

        gs.fillPolygon(vx2, vy2, 3);

    }
    public void paintRed(Graphics gs) {
        gs.setColor(Color.RED);

        gs.fillPolygon(vx2, vy2, 3);

    }
    public void paintGreen(Graphics gs) {
        gs.setColor(Color.GREEN);

        gs.fillPolygon(vx2, vy2, 3);

    }

    public int[] getVx2() {
        return vx2;
    }

    public void setVx2(int[] vx2) {
        this.vx2 = vx2;
    }

    public int[] getVy2() {
        return vy2;
    }

    public void setVy2(int[] vy2) {
        this.vy2 = vy2;
    }

}
