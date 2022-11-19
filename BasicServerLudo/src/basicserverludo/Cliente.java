package basicserverludo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anonymous
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    private int port;
    private static Socket clientSocket;
//    private static PrintWriter out2;
//    private static BufferedReader in2;
    private static DataOutputStream out;
    private static DataInputStream in;
    private String host;
    Scanner sc = new Scanner(System.in);

    public Cliente(String host, int puerto) {
        this.host = host;
        this.port = puerto;
//        Thread hilo = new Thread(() -> {
//            try {
//                while (true) {
//                    this.write("ok");
//                }
//
//            } catch (IOException ex) {
//                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        });
//        hilo.start();
    }

    private void iniciar() {
        try {
            clientSocket = new Socket(host, 8080);
        } catch (IOException ex) {

            System.out.println("error al comunicar tipo-->" + ex);
        }
    }

    public static void main(String[] args) throws IOException {

        Cliente cliente = new Cliente("localhost", 8080);
        cliente.iniciar();
        Scanner sc = new Scanner(System.in);

        Thread hilo = new Thread(() -> {

            while (true) {

//            while ((cadena = cliente.read()) != null) {
                try {
              
                    cliente.write("ok");
                    String cadena = cliente.read();
                    System.out.println("Receive: " + cadena);
                   

//            }
                } catch (IOException ex) {
                    System.out.println("se tiro el cliente:" + ex);
                }

            }

        });
        hilo.start();
    }

    public String read() throws IOException {

        in = new DataInputStream(clientSocket.getInputStream());
        String cadena = in.readUTF();

//        in.close();
        return cadena;
    }

    public void write(String texto) throws IOException {
        out = new DataOutputStream(clientSocket.getOutputStream());
        out.writeUTF(texto);
        out.flush();
//        out.close();

    }

    private void close() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

}
