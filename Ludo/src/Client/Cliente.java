/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anonymous
 */
public class Cliente extends Observable implements Runnable {

    private String name;
    private String name_sala;
    private String host;
    private int port;
    private DataOutputStream out;
    private DataInputStream input;
//    protected static Cliente instance;
    private Socket cliente;

    public Cliente(String host, int puerto) throws IOException {

        this.host = host;
        this.port = puerto;
        cliente = new Socket(host, puerto);

    }

//    public Cliente getInstance(){
//        if (instance==null) {
//            instance=new Cliente();
//        }
//        return instance;
//        
//    }
    public void write(String messagge) {
        try {
            out = new DataOutputStream(cliente.getOutputStream());
            out.writeUTF(messagge);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String read() throws IOException {
        String texto = "";

        input = new DataInputStream(cliente.getInputStream());

        return input.readUTF();

    }

    public void close() throws IOException {
        out.close();
        input.close();
        cliente.close();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String cadena;
                if ((cadena = this.read()) != null) {
                    this.setChanged();
                    this.notifyObservers(cadena);
                }

            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_sala() {
        return name_sala;
    }

    public void setName_sala(String name_sala) {
        this.name_sala = name_sala;
    }
    

}
