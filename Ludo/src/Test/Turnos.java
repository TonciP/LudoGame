/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author anonymous
 */
public class Turnos extends JPanel {
    
    private JPanel jugador;
    
    public Turnos() {
        
        this.setSize(new Dimension(400,637));
       
        instaciar_objetos();
        añadir();
    }
    
    private void instaciar_objetos() {
        jugador = new JPanel();
        jugador.setSize(300, 100);
        jugador.setOpaque(true);
        jugador.setBackground(Color.red);
        
    }
    
    private void añadir() {
        
        this.add(jugador);
    }
    
}
