package Server;


import BackandForth.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
            server.addMessageFromClient(clientID, message);
            do {
                if (server.messageIsReadyForClient(clientID)) {
                    sendMessage();
                    break;
                } else {
                    sleep(5);
                }
            } while (true);
            sleep(10);
        }
    }

    Message getMessage() {
        try {
            return (Message) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new Error(e.getMessage());
        }
    }

    void sendMessage() {
        try {
            outputStream.writeObject(server.getMessageForClient(clientID));
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
