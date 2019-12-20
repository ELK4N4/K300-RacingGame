package Client.Backend;

import BackandForth.Message;
import Client.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BackAndForth implements  Runnable{
    private Main main;
    private keyLogic keyLogic;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public BackAndForth(Main main, Client.Backend.keyLogic keyLogic, ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        this.main = main;
        this.keyLogic = keyLogic;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        while (true){
            main.setPlayersX(keyLogic.getX());
            main.setPlayerY(keyLogic.getY());
            main.setPlayerDirection(keyLogic.getDirection());
            try {
                outputStream.writeObject(new Message(keyLogic.getX(), keyLogic.getY(), keyLogic.getDirection(), main.getRounds()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Message inputMessage = (Message) inputStream.readObject();
                main.setEnemyX(inputMessage.getX());
                main.setEnemyY(inputMessage.getY());
                main.setEnemyDirection(inputMessage.getDirection());
                main.setRound(inputMessage.getRound());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
