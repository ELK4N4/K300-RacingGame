package Server;

import BackandForth.CarColor;
import BackandForth.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final int SUM_OF_CLIENTS = Message.SUM_OF_PLAYERS;
    private static List<Client> clientList;

    private Main() throws IOException {
        int clientCount;
        CarColor [] carColors;
        ServerSocket serverSocket;
        Socket clientSocket;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        clientCount = 0;
        carColors = getCarColors();
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

        setClientsIO();
    }

    //if there are more than 3 clients this function will need to change
    private CarColor[] getCarColors() {
        return new CarColor[] {CarColor.RED, CarColor.BLUE, CarColor.YELLOW};
    }

    private void setClientsIO() {
        for (int clientIndex = 0; clientIndex < clientList.size(); clientIndex++) {
            setClientsInfoTransfers(clientIndex);
        }
        for (Client client: clientList) {
            client.startInfoTransfers();
        }
    }

    private void setClientsInfoTransfers(int clientIndex) {
        ObjectOutputStream [] outputStreams = new ObjectOutputStream[SUM_OF_CLIENTS - 1];
        ObjectInputStream [] inputStreams = new ObjectInputStream[SUM_OF_CLIENTS - 1];
        int arrayIndex = 0;
        for (int i = 0; i < SUM_OF_CLIENTS; i++) {
            if(i != clientIndex) {
                outputStreams[arrayIndex] = clientList.get(i).getOutputStream();
                inputStreams[arrayIndex] = clientList.get(i).getInputStream();
                arrayIndex++;
            }
        }
        clientList.get(clientIndex).setOtherPlayersInPutStreams(inputStreams);
        clientList.get(clientIndex).setOtherPlayersOutPutStreams(outputStreams);
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }

}
