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
        // if there are more than 3 clients this function will need to change
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

    private CarColor[] getCarColors() {
        // todo if more cars are added this will need to change
        return new CarColor[] {CarColor.RED, CarColor.BLUE, CarColor.YELLOW};
    }

    private void setClientsIO() {
        for (int i = 0; i < clientList.size(); i++) {
            setClientInfoTransfers(i);
        }
        for (Client client: clientList) {
            client.startInfoTransfers();
        }
    }

    private void setClientInfoTransfers(int clientIndex) {
        ObjectOutputStream [] outputStreams = new ObjectOutputStream[SUM_OF_CLIENTS - 1];
        ObjectInputStream [] inputStreams = new ObjectInputStream[SUM_OF_CLIENTS - 1];
        int count = 0;
        for (int i = 0; i < SUM_OF_CLIENTS; i++) {
            if(i != clientIndex) {
                outputStreams[count] = clientList.get(i).getOutputStream();
                inputStreams[count] = clientList.get(i).getInputStream();
                count++;
            }
        }
        clientList.get(clientIndex).setOtherPlayersInPutStreams(inputStreams);
        clientList.get(clientIndex).setOtherPlayersOutPutStreams(outputStreams);
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }

}
