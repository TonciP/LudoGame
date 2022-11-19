package basicserverludo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author anonymous
 */
public class ListaCliente implements Observer {

    private HashMap<String, ArrayList<Hilo_Cliente>> lista_sala;

    String numero_sala = "";

    public ListaCliente() {
        lista_sala = new HashMap<String, ArrayList<Hilo_Cliente>>();
    }

    @Override
    public void update(Observable cliente, Object mensage) {
        Hilo_Cliente observador = (Hilo_Cliente) cliente;
        String msg = (String) mensage;
        String[] splic = msg.split(",");
        try {
            
                procedimiento(observador, msg);
           

        } catch (IOException ex) {
            System.out.println("no se pudo notificar al cliente:" + ex);
        }

    }

    private void unirse_sala(Hilo_Cliente observador) throws IOException {
        System.out.println("se esta uniendo a la sala");
        String salas = "";
        String separator = "";
        for (String sala : lista_sala.keySet()) {
            salas += separator + sala;
            separator = ",";
        }

        observador.write(salas);
        String nombre_sala = observador.read();

        observador.write(nombre_sala);

        observador.setName("cliente" + (lista_sala.get(nombre_sala).size() + 1));
        observador.write(observador.getName());

        lista_sala.get(nombre_sala).add(observador);

    }

    private void crearsala(Hilo_Cliente observador) throws IOException {
//        System.out.println("creando sala");
        String salas = "";
        String separator = "";
        for (String sala : lista_sala.keySet()) {
            salas += separator + sala;
            separator = ",";
        }
//        System.out.println("Nombre de salas:" + salas);
        observador.write(salas);
        String nombre_sala = observador.read();
        observador.write(nombre_sala);

        ArrayList<Hilo_Cliente> lista = new ArrayList<Hilo_Cliente>();
        observador.setName("cliente" + (lista.size() + 1));
        lista.add(observador);
        observador.write(observador.getName());

//        System.out.println("se esta creando la sala con el nombre:" + nombre_sala + ":Cliente-->" + observador.getName());
        lista_sala.put(nombre_sala, lista);

    }

    private void procedimiento(Hilo_Cliente observador, String msg) throws IOException {
        switch (msg) {
            case "Unirse a Sala":
                // unirse a sala
                unirse_sala(observador);
                break;
            case "Crear Sala":
                //crear sala

                crearsala(observador);
                break;

            default:
                Comunicacion(observador, msg);
                break;
        }
    }

    private void Comunicacion(Hilo_Cliente observador, String msg) throws IOException {
        System.out.println("comunicacion:" + msg);
        String[] comunicacion = msg.split(",");
        if (comunicacion[1].equals("actualizar perfil")) {
            actualizacion_perfil(msg);
        } else {
            for (Hilo_Cliente hilo_Cliente : lista_sala.get(comunicacion[0])) {
                if (hilo_Cliente.equals(observador)) {
                    continue;
                }
                hilo_Cliente.write(msg);
            }
        }

    }

    private void actualizacion_perfil(String msg) throws IOException {
        System.out.println("actualizando perfil");
        //obtengo el mensaje completo para actualizar el perfil
        String[] comunicacion = mensaje_completo(msg);
        String mensaje_completo = "";
        String separador = "";
        for (String string : comunicacion) {
            mensaje_completo += separador + string;
            separador = ",";
        }
//        System.out.println("mensaje:" + mensaje_completo);
        for (Hilo_Cliente hilo_Cliente : lista_sala.get(comunicacion[0])) {

            hilo_Cliente.write(mensaje_completo);
        }

    }

    private String[] mensaje_completo(String msg) {
        String[] splic = msg.split(",");

        switch (splic[2]) {
            case "cliente1":
                splic[2] = "" + 2;
                break;
            case "cliente2":
                if (lista_sala.get(splic[0]).size() == 2) {
                    splic[2] = "" + 1;
                } else {
                    splic[2] = "" + 3;
                }
                break;
            case "cliente3":
                if (lista_sala.get(splic[0]).size() == 3) {
                    splic[2] = "" + 1;
                } else {
                    splic[2] = "" + 4;
                }

                break;
            case "cliente4":
                splic[2] = "" + 1;
                break;

        }
        return splic;

    }

}
