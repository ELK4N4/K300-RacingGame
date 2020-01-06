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
            System.out.println("waiting for connection num " + clientCount);
            clientSocket = serverSocket.accept();
            System.out.println("connected");
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println(carColors[clientCount].toString());
            objectOutputStream.writeObject(carColors[clientCount].toString());
            clientList.add(new Client(objectInputStream, objectOutputStream, clientCount));
            clientCount++;
        } while (clientList.size() < SUM_OF_CLIENTS);

        // will need to change to two different outputs and two inputs(if one players is added)
        new Thread(new InfoTransferThread(clientList.get(1).getOutputStream(), clientList.get(0).getInputStream())).start();
        new Thread(new InfoTransferThread(clientList.get(0).getOutputStream(), clientList.get(1).getInputStream())).start();
    }

}
