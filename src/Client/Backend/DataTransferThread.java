package Client.Backend;

import BackandForth.Message;
import Client.Main;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataTransferThread implements  Runnable {

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
        Message outputMessage;
        while (run){
            playersDataBase.setPlayersInfo(keyTranslator.getX(), keyTranslator.getY(), keyTranslator.getDirection());
            System.out.println(keyTranslator.getX() + "," + keyTranslator.getY());
//            try {
//                outputMessage = new Message(keyTranslator.getX(), keyTranslator.getY(), keyTranslator.getDirection(), Main.playersRound, playersDataBase.getPlayersCarColor());
//                System.out.println(outputMessage);
//                outputStream.writeObject(outputMessage);
//                System.out.println("sent");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                System.out.println("receiving");
//                Message inputMessage = (Message) inputStream.readObject();
//                System.out.println("reading " + inputMessage);
//                playersDataBase.setCarInfo(inputMessage.carColor, inputMessage.x, inputMessage.y, inputMessage.direction);
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
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
