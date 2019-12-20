package Client;

import BackandForth.Message;
import Client.Backend.BackAndForth;
import Client.Backend.KeyInput;
import Client.Backend.keyLogic;
import Client.GUI.Window;
import Client.GUI.openWindow;

import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;

public class Main {

    private keyLogic keyLogic;
    private Window window;
    private int round;

    private Main() throws IOException {
        Socket socket;
        //socket = new Socket(Message.IP, Message.PORT);
        BackAndForth backAndForth;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        keyLogic = new keyLogic(this);
        KeyListener listener = new KeyInput(keyLogic);
        window = new Window(this, listener);
        keyLogic.setImageHeight(window.getCarHeight());
        keyLogic.setImageWidth(window.getCarWidth());
        new Thread(keyLogic).start();
//        inputStream = new ObjectInputStream(socket.getInputStream());
//        outputStream = new ObjectOutputStream(socket.getOutputStream());
        backAndForth = new BackAndForth(this, keyLogic);
        new Thread(backAndForth).start();
    }

    public static void main(String[] args) throws IOException {

        //openWindow startWindow = new openWindow();
        new Main();

    }

    public void setBackendX1(double x) {
            keyLogic.setX1(x);
    }

    public void setBackendY1(double y) {
        keyLogic.setY1(y);
    }

    public void setBackendX2(double x) {
            keyLogic.setX2(x);
    }

    public void setBackendY2(double y) {
        keyLogic.setY2(y);
    }

    public void repaint() {
        window.repaint();
    }

    public void setPlayersX(double x, double y) {
        if((x-1000) * (x-1000) + 3 * ((y-600) * (y-600)) <= 990000) {
            window.setPlayerX(x);
        }
    }
    public void setEnemyX(double x, double y){
        if((x-1000) * (x-1000) + 3 * ((y-600) * (y-600)) <= 990000) {
            window.setEnemyX(x);
        }
    }

    public void setPlayerY(double y, double x) {
        if((x-1000) * (x-1000) + 3 * ((y-600) * (y-600)) <= 990000) {
            window.setPlayerY(y);
        }
    }
    
    public void setEnemyY(double y, double x){
        if((x-1000) * (x-1000) + 3 * ((y-600) * (y-600)) <= 990000) {
            window.setEnemyY(y);
        }
    }

    public void setPlayerDirection(double direction) {
        window.setPlayerAngle(direction);
    }
    public void setEnemyDirection(double direction){
        window.setEnemyAngle(direction);
    }

    public int getRounds() {
        return round;
    }

    public void setRound(int round) {
    }
}
