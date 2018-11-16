package tallerclient;

public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
        while (true) {
            client.sendData();
        }
    }

}
