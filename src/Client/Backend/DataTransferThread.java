package Client.Backend;

import BackandForth.Message;
import Client.Client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataTransferThread implements  Runnable {

    private DataBase dataBase;
    private KeyTranslator keyTranslator;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private boolean run;

    public DataTransferThread(DataBase dataBase, KeyTranslator keyTranslator, ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        this.dataBase = dataBase;
        this.keyTranslator = keyTranslator;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        run = true;
    }

    @Override
    public void run() {
        new Thread(() -> {
            while (run) {
                dataBase.setCarInfo(dataBase.getPlayersCarColor(), keyTranslator.getX(), keyTranslator.getY(), keyTranslator.getDirection());
                sleep(5);
            }
        }).start();
        while (run){
            sendMessage(new Message(keyTranslator.getX(), keyTranslator.getY(), keyTranslator.getDirection(), Client.playersRound, dataBase.getPlayersCarColor()));
            Message message = getMessage();
            dataBase.setCarInfo(message.carColor, message.x, message.y, message.direction);
            sleep(30);
        }
    }

    private Message getMessage() {
        try {
            //todo data not updated
            Object message = inputStream.readObject();
            if(message instanceof Message) {
                return (Message) message;
            } else {
                throw new Error("wrong message type");
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new Error(e.getMessage());
        }
    }

    private void sendMessage(Message message) {
        try {
            outputStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sleep(long sleepDuration) {
        try {
            Thread.sleep(sleepDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        run = false;
    }

}
