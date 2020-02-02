package Server.Testing2;

import BackandForth.CarColor;
import BackandForth.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<SClient> clients;
    private final int SUM_OF_CLIENTS = 3;

    private Server() throws IOException {
        int clientCount = 0;
        ServerSocket serverSocket;
        Socket clientSocket;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        clients = new ArrayList<>();
        serverSocket = new ServerSocket(Message.PORT);
        System.out.println("listening");
        do {
            clientSocket = serverSocket.accept();
            System.out.println("connected to " + clientCount);
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            clients.add(new SClient(objectInputStream, objectOutputStream, clientCount, this));
            clientCount++;
        } while (clientCount < SUM_OF_CLIENTS);

        for (int i = 0; i < SUM_OF_CLIENTS; i++) {
            clients.get(i).setColor(getCarColors()[i]);
        }

        for (SClient client: clients) {
            new Thread(client).start();
        }

    }


    private CarColor[] getCarColors() {
        return new CarColor[] {CarColor.RED, CarColor.BLUE, CarColor.YELLOW};
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }

    public synchronized void sendMessage(Object message, int clientID) {
        for (int i = 0; i < SUM_OF_CLIENTS; i++) {
            if(i != clientID) {
                clients.get(i).sendMessage(message);
            }
        }
    }

}
