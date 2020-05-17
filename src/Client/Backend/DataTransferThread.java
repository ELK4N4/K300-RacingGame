package Client.Backend;

import BackandForth.Message;
import Client.Client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataTransferThread implements Runnable {

    private PlayersDataBase playersDataBase;
    private KeyTranslator keyTranslator;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private boolean run;

    public DataTransferThread(PlayersDataBase playersDataBase, KeyTranslator keyTranslator, ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        this.playersDataBase = playersDataBase;
        this.keyTranslator = keyTranslator;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        run = true;
    }

    @Override
    public void run() {
        new Thread(() -> {
            while (run) {
                playersDataBase.setPlayersInfo(keyTranslator.getCurrentX(), keyTranslator.getCurrentY(), keyTranslator.getDirection());
                sleep(10);
            }
        }).start();
        while (run){
            sendMessage(new Message(keyTranslator.getCurrentX(), keyTranslator.getCurrentY(), keyTranslator.getDirection(), Client.playersRound, playersDataBase.getPlayersCarColor()));
            Message[] messages = getMessages();
            for (Message message : messages) {
                playersDataBase.setCarInfo(message.carColor, message.x, message.y, message.direction);
            }
            sleep(10);
        }
    }

    private Message[] getMessages() {
        try {
            Object messages = inputStream.readObject();
            if(messages instanceof Message[]) {
                return (Message[]) messages;
            } else {
                throw new Error("wrong message type ");
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
