package Client.Backend;

import BackandForth.CarColor;
import BackandForth.Message;
import Client.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BackAndForth implements  Runnable {

    private Main main;
    private KeyLogic keyLogic;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private boolean run;

    public BackAndForth(Main main, KeyLogic keyLogic, ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        this.main = main;
        this.keyLogic = keyLogic;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        run = true;
    }

    @Override
    public void run() {
        while (run){
            main.setPlayersPosition(keyLogic.getX(), keyLogic.getY());
            main.setPlayerDirection(keyLogic.getDirection());
            try {
                outputStream.writeObject(new Message(keyLogic.getX(), keyLogic.getY(), keyLogic.getDirection(), main.getPlayersRounds()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Message inputMessage = (Message) inputStream.readObject();
                main.setEnemyPosition(inputMessage.x, inputMessage.y);
                main.setEnemyDirection(inputMessage.direction);
                main.setEnemyRound(inputMessage.round);
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

    public void stop(){
        run = false;
    }

}
