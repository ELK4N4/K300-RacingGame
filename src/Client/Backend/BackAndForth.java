package Client.Backend;

import Client.Main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BackAndForth implements  Runnable{
    private Main main;
    private KeyLogic keyLogic;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public BackAndForth(Main main, KeyLogic keyLogic, ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        this.main = main;
        this.keyLogic = keyLogic;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }

    public BackAndForth(Main main, KeyLogic keyLogic) {
        this.main = main;
        this.keyLogic = keyLogic;
    }

    @Override
    public void run() {
        while (true){
            main.setPlayersX(keyLogic.getX(), keyLogic.getY());
            main.setPlayerY(keyLogic.getY(), keyLogic.getX());
            main.setPlayerDirection(keyLogic.getDirection());
            //todo get this from server
//            main.setEnemyX(keyLogic.getX2(), keyLogic.getY2());
//            main.setEnemyY(keyLogic.getY2(), keyLogic.getY2());
//            main.setEnemyDirection(keyLogic.getDirection2());
//            try {
//                outputStream.writeObject(new Message(keyLogic.getX1(), keyLogic.getY1(), keyLogic.getDirection1(), main.getRounds()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                Message inputMessage = (Message) inputStream.readObject();
//                main.setEnemyX(inputMessage.getX());
//                main.setEnemyY(inputMessage.getY());
//                main.setEnemyDirection(inputMessage.getDirection());
//                main.setRound(inputMessage.getRound());
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
}
