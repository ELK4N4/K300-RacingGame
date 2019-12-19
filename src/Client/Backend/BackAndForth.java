package Client.Backend;

import Client.Main;

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
            main.setX(keyLogic.getX());
            main.setY(keyLogic.getY());
            main.setDirection(keyLogic.getDirection());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
