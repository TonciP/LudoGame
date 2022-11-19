package basicserverludo;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
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
public class Hilo_Cliente extends Observable {

    private Socket clienSockect;

    private String cadena;

    public DataOutputStream out;
    public DataInputStream in;
    public int contador;
    private String name;

    public Hilo_Cliente(Socket cliente, int contador) throws IOException {
        clienSockect = cliente;
        this.contador = contador;

//        name = "cliente" + contador;
//        write(name);
        run();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void run() {
        Thread hilo = new Thread(() -> {
            try {
//            String cadena ;
                while (true) {

//                    while ((cadena = this.read()) != null) {
//                    this.write(cadena);
                    cadena = this.read();
         
                        System.out.println("Receive:" + cadena);
//                    this.write(cadena);
                        setChanged();

                        notifyObservers(this.cadena);

                    

//                    }
                }
            } catch (UnknownHostException e) {
                System.out.println(e);
            } catch (IOException e) {
                try {
                    this.close();
                } catch (IOException ex) {
                    Logger.getLogger(Hilo_Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("sucedio algo al tratar de leer:" + e);
            }

        });
        hilo.start();
    }

    public boolean isclose() {
        return clienSockect.isClosed();
    }

    public String read() throws IOException {

        in = new DataInputStream(clienSockect.getInputStream());
        cadena = in.readUTF();

//        in.close();
        return cadena;
    }

    public void write(String texto) throws IOException {
        out = new DataOutputStream(clienSockect.getOutputStream());
        out.writeUTF(texto);
        out.flush();
//        out.close();
    }

    public void close() throws IOException {
        System.out.println("cerro");
        in.close();
        out.close();
        clienSockect.close();
    }

}
