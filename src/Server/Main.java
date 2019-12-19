package Server;

import BackandForth.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Client> clientList;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;
        Socket clientSocket;
        OutputStream outputStream;
        InputStream inputStream;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        clientList = new ArrayList<>();
        serverSocket = new ServerSocket(Message.PORT);
        int count = 1;
        do {
            clientSocket = serverSocket.accept();
            outputStream = clientSocket.getOutputStream();
            inputStream = clientSocket.getInputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectInputStream = new ObjectInputStream(inputStream);
            clientList.add(new Client(objectInputStream, objectOutputStream, count));
        } while (clientList.size() < 2);


        new Thread(new InfoTransferThread(clientList.get(1).outputStream, clientList.get(0).inputStream)).start();
        new Thread(new InfoTransferThread(clientList.get(0).outputStream, clientList.get(1).inputStream)).start();
        while (true){}
    }

}
