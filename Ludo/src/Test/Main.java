package Test;

import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author anonymous
 */
public class Main {
    
    public static void main(String[] args) {
//        Ludo ludo=new Ludo("GAME LUDO");
//        new NewJFrame();
        Thread hilo = new Thread();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                hilo.sleep(1000);
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("otro" + i);
                hilo.sleep(1000);
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        hilo.start();
        
    }
    
}
