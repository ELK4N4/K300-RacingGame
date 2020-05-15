package Server;

import BackandForth.CarColor;
import BackandForth.Message;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server {

    public static final int SUM_OF_CLIENTS = Message.SUM_OF_PLAYERS;
    private List<Client> clientList;
    private List<Message[]> messages;

    private Server() throws IOException {
        int clientCount;
        CarColor [] carColors;
        ServerSocket serverSocket;
        Socket clientSocket;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        clientCount = 0;
        carColors = getCarColors();
        clientList = new ArrayList<>();
        messages = new ArrayList<>();
        serverSocket = new ServerSocket(Message.PORT);

        do {
            clientSocket = serverSocket.accept();
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            objectOutputStream.writeObject(carColors[clientCount].toString());
            clientList.add(new Client(this , objectInputStream, objectOutputStream, clientCount));
            clientCount++;
        } while (clientList.size() < SUM_OF_CLIENTS);
        runClients();
    }

    private void runClients() {
        setupMessageList();
        for (Client client : clientList) {
            new Thread(client).start();
        }
    }

    private void setupMessageList() {
        Message[] emptyArray = new Message[SUM_OF_CLIENTS - 1];
        for (int i = 0; i < emptyArray.length; i++) {
            messages.add(emptyArray);
        }
    }

    //if there are more than 3 clients this function will need to change
    private CarColor[] getCarColors() {
        return new CarColor[] {CarColor.RED, CarColor.BLUE, CarColor.YELLOW};
    }

    void addMessageFromClient(int clientID, Message message) {
        for (int clientIndex = 0; clientIndex < SUM_OF_CLIENTS; clientIndex++) {
            if(clientIndex == clientID) {
                continue;
            }
            System.out.println("message from " + clientID + " sending to " + clientIndex);
            Message[] messages = this.messages.get(clientIndex);
            messages[getArrayPositionForClient(clientID, clientIndex)] = message;
            this.messages.set(clientIndex, messages);
            System.out.println("adding to " + clientIndex + Arrays.toString(this.messages.get(clientIndex)) + " from " + clientID);
        }
    }

    Message[] getMessageForClient(int clientID) {
        return messages.get(clientID);
    }

    boolean messageIsReadyForClient(int clientID) {
        int readyCount= 0;
        Message [] messages = this.messages.get(clientID);
        for (Message message : messages) {
            if (message != null) {
                readyCount++;
            }
        }
        return messages.length == readyCount;
    }

    void removeOldMessages(int clientID) {
        int sizeOfArray = this.messages.get(clientID).length;
        for (int arrayIndex = 0; arrayIndex < sizeOfArray; arrayIndex++) {
            this.messages.get(clientID)[arrayIndex] = null;
        }
    }

    private int getArrayPositionForClient(int clientWriting, int clientReading) {
        if(clientWriting != clientList.size() - 1) {
            return clientWriting;
        } else {
            return clientReading;
        }
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }

}
