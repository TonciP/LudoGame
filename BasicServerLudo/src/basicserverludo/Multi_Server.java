package basicserverludo;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author anonymous
 */
public class Multi_Server {

    private static ListaCliente lista = new ListaCliente();

    public static void main(String[] args) {

        Thread hilo = new Thread(() -> {

            int port = 8080;
            try {
                ServerSocket serverSocket = new ServerSocket(port);

                while (true) {
                    Socket param = serverSocket.accept();

                    Hilo_Cliente Server_hilo = new Hilo_Cliente(param, 0);

                    Server_hilo.addObserver(lista);

                }
            } catch (IOException ex) {
                System.out.println("se tiro" + ex);
            }
        });
        hilo.start();

    }
}
