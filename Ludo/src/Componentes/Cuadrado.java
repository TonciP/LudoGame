/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author anonymous
 */
public class Cuadrado extends JPanel{
    
    JPanel centro;



    public Cuadrado() {
        this.setSize(200,200);
        this.setLayout(null);
       
        
        inicializar_obj();
        this.add(centro);
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); 
    }

    private void inicializar_obj() {

        centro=new JPanel();
        centro.setBounds(23, 23, 150, 150);

    }

    
}
