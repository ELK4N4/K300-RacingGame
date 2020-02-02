package Server;

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
        sendCarColor();
    }

    @Override
    public void run() {
        while (run) {
            Object message = getMessage();
            server.sendMessage(message, clientID);
            sleep(10);
        }
    }

    private void sendCarColor() {
        try {
            outputStream.writeObject(String.valueOf(server.getCarColor(clientID)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object getMessage() {
        try {
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new Error(e.getMessage());
        }
    }

    void sendMessage(Object message) {
        try {
            outputStream.writeObject(message);
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
