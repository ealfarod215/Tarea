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
            String info = game(letra);
            sendData(in, info);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String game(String letra) {
        String mensaje;
        int maxIntentos = 5;
        if (trigger == false) {
            String[] palabras = {"hamilton", "cigarro", "tokyo", "exotico"};
            int palabraRandom = (int) (Math.random() * palabras.length);
            palabra = palabras[palabraRandom];
            trigger = true;
        }

        if (palabra.contains(letra)) {
            mensaje = "La letra si esta en la palabra";
            System.out.println(mensaje);
        } else {
            intentos++;
            int total = maxIntentos - intentos;
            mensaje = "La letra no esta en la palabra, quedan " + total + " intentos.";
            if (total == 0) {
                mensaje += "\nPerdio todos los intentos, la palabra era: " + palabra + ".\n";
                intentos = 0;
                trigger = false;
            }
        }
        return mensaje;
    }
}
