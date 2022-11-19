/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dibujo;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author anonymous
 */
public class Square {
    
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Square() {
        
    }

    public Square(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public void Dibujar(Graphics gs){
       
        gs.setColor(Color.BLACK);
        gs.drawRect(x1, y1, x2, y2);
    }
    public void DibujarR(Graphics gs){
       
        gs.setColor(Color.RED);
        gs.fillRect(x1, y1, x2, y2);
    }
    public void DibujarB(Graphics gs){
       
        gs.setColor(Color.BLUE);
        gs.fillRect(x1, y1, x2, y2);
    }
    public void DibujarG(Graphics gs){
       
        gs.setColor(Color.GREEN);
        gs.fillRect(x1, y1, x2, y2);
    }
    public void DibujarY(Graphics gs){
       
        gs.setColor(Color.YELLOW);
        gs.fillRect(x1, y1, x2, y2);
    }
    public void DibujarR_star(Graphics gs){
       
        gs.setColor(Color.RED);
       gs.drawRect(x1, y1, x2, y2);
    }
    public void DibujarB_star(Graphics gs){
       
        gs.setColor(Color.BLUE);
        gs.drawRect(x1, y1, x2, y2);
    }
    public void DibujarG_star(Graphics gs){
       
        gs.setColor(Color.GREEN);
       gs.drawRect(x1, y1, x2, y2);
    }
    public void DibujarY_star(Graphics gs){
       
        gs.setColor(Color.YELLOW);
        gs.drawRect(x1, y1, x2, y2);
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
    
    
}
