package Server;

import BackandForth.CarColor;
import BackandForth.Message;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static final int SUM_OF_CLIENTS = Message.SUM_OF_PLAYERS;
    private List<Client> clientList;

    private Server() throws IOException {
        int clientCount;
        ServerSocket serverSocket;
        Socket clientSocket;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        clientCount = 0;
        clientList = new ArrayList<>();
        serverSocket = new ServerSocket(Message.PORT);

        do {
            clientSocket = serverSocket.accept();
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            clientList.add(new Client(this, objectInputStream, objectOutputStream, clientCount));
            clientCount++;
        } while (clientList.size() < SUM_OF_CLIENTS);
        serverSocket.close();
        runClients();
    }

    private void runClients() {
        for (Client client: clientList) {
            new Thread(client).start();
        }
    }

    CarColor getCarColor(int clientID) {
        return getCarColors()[clientID];
    }

    // if there are more than 3 clients this function will need to change
    private CarColor[] getCarColors() {
        return new CarColor[] {CarColor.RED, CarColor.BLUE, CarColor.YELLOW};
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }

    synchronized void sendMessage(Object message, int clientID) {
        for (int i = 0; i < clientList.size(); i++) {
            if(i != clientID) {
                clientList.get(i).sendMessage(message);
            }
        }
    }

}
