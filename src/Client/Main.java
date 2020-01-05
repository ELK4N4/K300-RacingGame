package Client;

import Client.Backend.BackAndForth;
import Client.Backend.KeyInput;
import Client.Backend.KeyLogic;
import Client.GUI.Window;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;

public class Main {

    private KeyLogic keyLogic;
    private Window window;
    private int round;

    private Main() throws IOException {
        Socket socket;
        //socket = new Socket(Message.IP, Message.PORT);
        BackAndForth backAndForth;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        keyLogic = new KeyLogic(this);
        KeyListener listener = new KeyInput(keyLogic);
        window = new Window(listener);
        new Thread(keyLogic).start();
//        inputStream = new ObjectInputStream(socket.getInputStream());
//        outputStream = new ObjectOutputStream(socket.getOutputStream());
        backAndForth = new BackAndForth(this, keyLogic);
        setPlayersX(window.getPlayersX());
        setPlayersY(window.getPlayersY());
        new Thread(backAndForth).start();
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }

    public void setPlayersX(double x) {
        keyLogic.setX(x);
    }

    public void setPlayersY(double y) {
        keyLogic.setY(y);
    }

    public void refreshWindow() {
        window.repaint();
    }

    public void setPlayersX(double x, double y) {
        if((x-900) * (x-900) + 3 * ((y-600) * (y-600)) <= 10000000 && (x-900) * (x-900) + 5 * ((y-600) * (y-600)) >= 10000) {
            window.setPlayerX(x);
        } else {
            window.setPlayerX(700);
            setPlayersX(700);
        }
    }

    public void setPlayerY(double y, double x) {
        if((x-900) * (x-900) + 3 * ((y-600) * (y-600)) <= 10000000 && (x-900) * (x-900) + 5 * ((y-600) * (y-600)) >= 10000) {
            window.setPlayerY(y);
        } else {
            window.setPlayerY(700);
            setPlayersY(700);
        }
    }

    public void setPlayerDirection(double direction) {
        window.setPlayerAngle(direction);
    }

    public void setEnemyX(double x, double y) {
        if((x-900) * (x-900) + 3 * ((y-600) * (y-600)) <= 10000000 && (x-900) * (x-900) + 5 * ((y-600) * (y-600)) >= 10000) {
            window.setEnemyX(x);
        } else {
            window.setEnemyX(700);
        }
    }

    public void setEnemyY(double y, double x) {
        if((x-900) * (x-900) + 3 * ((y-600) * (y-600)) <= 10000000 && (x-900) * (x-900) + 5 * ((y-600) * (y-600)) >= 10000) {
            window.setEnemyX(y);
        } else {
            window.setEnemyX(700);
            setPlayersY(700);
        }
    }

    public void setEnemyDirection(double direction) {
        window.setEnemyAngle(direction);
    }

}
