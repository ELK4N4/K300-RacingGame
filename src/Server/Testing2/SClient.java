package Server.Testing2;

import BackandForth.CarColor;
import BackandForth.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SClient implements Runnable {

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private int clientID;
    private Server server;
    private CarColor carColor;
    private boolean run;

    SClient(ObjectInputStream inputStream, ObjectOutputStream outputStream, int clientID, Server server) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.clientID = clientID;
        this.server = server;
        run = true;
    }

    ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public void setColor(CarColor carColor) {
        this.carColor = carColor;
    }

    void sendMessage(Object message) {
        try {
            outputStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (run) {
            try {
                Message message = (Message) inputStream.readObject();
                message.carColor = carColor;
                server.sendMessage(message, clientID);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    void stop() {
        run = false;
    }

}
