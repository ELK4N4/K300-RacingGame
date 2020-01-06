package Client.Backend;

import BackandForth.Message;
import Client.Main;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BackAndForth implements  Runnable {

    private PlayersDataBase playersDataBase;
    private KeyLogic keyLogic;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private boolean run;

    public BackAndForth(PlayersDataBase playersDataBase, KeyLogic keyLogic, ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        this.playersDataBase = playersDataBase;
        this.keyLogic = keyLogic;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        run = true;
    }

    @Override
    public void run() {
        while (run){
            playersDataBase.setPlayersInfo(keyLogic.getX(), keyLogic.getY(), keyLogic.getDirection());
            try {
                System.out.println("sending " + new Message(keyLogic.getX(), keyLogic.getY(), keyLogic.getDirection(), Main.playersRound, playersDataBase.getPlayersCarColor()));
                outputStream.writeObject(new Message(keyLogic.getX(), keyLogic.getY(), keyLogic.getDirection(), Main.playersRound, playersDataBase.getPlayersCarColor()));
                System.out.println("sent");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("receiving");
                Message inputMessage = (Message) inputStream.readObject();
                System.out.println("reading " + inputMessage);
                playersDataBase.setCarInfo(inputMessage.carColor, inputMessage.x, inputMessage.y, inputMessage.direction);
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
