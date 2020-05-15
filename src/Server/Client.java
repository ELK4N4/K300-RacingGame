package Server;


import BackandForth.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

class Client implements Runnable {

    private Server server;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private int clientID;
    private boolean run;

    Client(Server server, ObjectInputStream inputStream, ObjectOutputStream outputStream, int clientID) {
        this.server = server;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.clientID = clientID;
        run = true;
    }

    @Override
    public void run() {
        while (run) {
            Message message = getMessage();
            System.out.println("got " + message);
            server.addMessageFromClient(clientID, message);
            do {
                if (server.messageIsReadyForClient(clientID)) {
                    sendMessage();
                    break;
                } else {
                    sleep(5);
                }
            } while (true);
            System.out.println("sent to " + clientID);
            sleep(10);
        }
    }

    private Message getMessage() {
        try {
            return (Message) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new Error(e.getMessage());
        }
    }

    private void sendMessage() {
        try {
            Message[] message = server.getMessageForClient(clientID);
            System.out.println("sending " + Arrays.toString(message));
            outputStream.writeObject(message);
            server.removeOldMessages(clientID);
        } catch (IOException e) {
            System.out.println("message not sent to client num " + clientID);
        }
    }

    void sleep(long sleepDuration) {
        try {
            Thread.sleep(sleepDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void stop() {
        run = false;
    }

    @Override
    public String toString() {
        return "Client{\n" +
                "inputStream=" + inputStream +
                ", outputStream=" + outputStream +
                ", count=" + clientID +
                "\n}";
    }

}
