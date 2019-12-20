package Client;

import BackandForth.Message;
import Client.Backend.BackAndForth;
import Client.Backend.KeyInput;
import Client.Backend.keyLogic;
import Client.GUI.Window;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;

public class Main {

    private keyLogic keyLogic;
    private Window window;
    private int round;

    private Main() throws IOException {
        Socket socket;
        socket = new Socket(Message.IP, Message.PORT);
        BackAndForth backAndForth;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        keyLogic = new keyLogic(this);
        KeyListener listener = new KeyInput(keyLogic);
        window = new Window(this, listener);
        keyLogic.setImageHeight(window.getCarHeight());
        keyLogic.setImageWidth(window.getCarWidth());
        new Thread(keyLogic).start();
        inputStream = new ObjectInputStream(socket.getInputStream());
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        backAndForth = new BackAndForth(this, keyLogic, outputStream, inputStream);
        new Thread(backAndForth).start();
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }

    public void setBackendX(double x) {
        keyLogic.setX1(x);
    }

    public void setBackendY(double y) {
        keyLogic.setY1(y);
    }

    public void repaint() {
        window.repaint();
    }

    public void setPlayersX(double x) {
        window.setPlayerX(x);
    }
    public void setEnemyX(double x){
        window.setEnemyX(x);
    }

    public void setPlayerY(double y) {
        window.setPlayerY(y);
    }
    
    public void setEnemyY(double y){
        window.setEnemyY(y);
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
