package tallerserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private DatagramPacket out;
    private DatagramPacket in;
    private DatagramSocket socket;
    private int intentos = 0;
    private String palabra;
    private boolean trigger = false;
    Game game = new Game();

    public Server() {
        try {
            socket = new DatagramSocket(8484);
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendData(final DatagramPacket client, final String info) {
        try {
            out = new DatagramPacket(info.getBytes(), 0, info.getBytes().length, client.getAddress(), client.getPort());
            socket.send(out);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readData() {
        try {
            byte[] buffer = new byte[400];
            in = new DatagramPacket(buffer, 0, buffer.length);
            socket.receive(in);
            String letra = new String(in.getData());
            System.out.println(letra);
            char let = letra.charAt(0);
            //String info = game(letra);
            String info = Character.toString(game.ame(let)) ;
            sendData(in, info);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
