package Server;

import BackandForth.CarColor;
import BackandForth.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int SUM_OF_CLIENTS = 2;
    private static List<Client> clientList;

    public static void main(String[] args) throws IOException {
        int clientCount;
        CarColor [] carColors;
        ServerSocket serverSocket;
        Socket clientSocket;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        clientCount = 0;
        carColors = new CarColor[] {CarColor.RED, CarColor.BLUE, CarColor.YELLOW};
        clientList = new ArrayList<>();
        serverSocket = new ServerSocket(Message.PORT);

        do {
            clientSocket = serverSocket.accept();
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            objectOutputStream.writeObject(carColors[clientCount].toString());
            clientList.add(new Client(objectInputStream, objectOutputStream, clientCount));
            clientCount++;
        } while (clientList.size() < SUM_OF_CLIENTS);

        new Thread(new InfoTransferThread(clientList.get(1).getOutputStream(), clientList.get(0).getInputStream())).start();
        new Thread(new InfoTransferThread(clientList.get(0).getOutputStream(), clientList.get(1).getInputStream())).start();
    }

}
