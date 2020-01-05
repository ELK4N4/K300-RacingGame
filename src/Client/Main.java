package Client;

import BackandForth.CarColor;
import BackandForth.Message;
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
    private int PlayersRound;
    private int enemyRound;

    private Main() throws IOException, ClassNotFoundException {
        Socket socket;
        socket = new Socket(Message.IP, Message.PORT);
        BackAndForth backAndForth;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        CarColor playersCarColor;
        keyLogic = new KeyLogic(this);
        KeyListener listener = new KeyInput(keyLogic);
        inputStream = new ObjectInputStream(socket.getInputStream());
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        playersCarColor = CarColor.valueOf(((String) inputStream.readObject()));
        window = new Window(playersCarColor, listener);
        new Thread(keyLogic).start();
        backAndForth = new BackAndForth(this, keyLogic, outputStream, inputStream);
        setPlayersX(window.getPlayersX());
        setPlayersY(window.getPlayersY());
        new Thread(backAndForth).start();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Main();
    }

    public void refreshWindow() {
        window.repaint();
    }

    public void setPlayersPosition(double x, double y) {
        if((x-900) * (x-900) + 3 * ((y-600) * (y-600)) <= 10000000 && (x-900) * (x-900) + 5 * ((y-600) * (y-600)) >= 10000) {
            window.setPlayerX(x);
            window.setPlayerY(y);
        } else {
            window.setPlayerX(700);
            window.setPlayerY(700);
            setPlayersX(700);
            setPlayersY(700);
        }
    }

    // not sure if necessary (x won't change without y and vice versa)
//    public void setPlayersX(double x, double y) {
//        if((x-900) * (x-900) + 3 * ((y-600) * (y-600)) <= 10000000 && (x-900) * (x-900) + 5 * ((y-600) * (y-600)) >= 10000) {
//            window.setPlayerX(x);
//        } else {
//            window.setPlayerX(700);
//            setPlayersX(700);
//        }
//    }
//
//    public void setPlayerY(double y, double x) {
//        if((x-900) * (x-900) + 3 * ((y-600) * (y-600)) <= 10000000 && (x-900) * (x-900) + 5 * ((y-600) * (y-600)) >= 10000) {
//            window.setPlayerY(y);
//        } else {
//            window.setPlayerY(700);
//            setPlayersY(700);
//        }
//    }
//
//    public void setEnemyX(double x, double y) {
//        if((x-900) * (x-900) + 3 * ((y-600) * (y-600)) <= 10000000 && (x-900) * (x-900) + 5 * ((y-600) * (y-600)) >= 10000) {
//            window.setEnemyX(x);
//        } else {
//            window.setEnemyX(700);
//        }
//    }
//
//    public void setEnemyY(double y, double x) {
//        if((x-900) * (x-900) + 3 * ((y-600) * (y-600)) <= 10000000 && (x-900) * (x-900) + 5 * ((y-600) * (y-600)) >= 10000) {
//            window.setEnemyX(y);
//        } else {
//            window.setEnemyX(700);
//            setPlayersY(700);
//        }
//    }

    public void setPlayerDirection(double direction) {
        window.setPlayerAngle(direction);
    }

    public void setEnemyPosition(double x, double y) {
        if((x-900) * (x-900) + 3 * ((y-600) * (y-600)) <= 10000000 && (x-900) * (x-900) + 5 * ((y-600) * (y-600)) >= 10000) {
            window.setEnemyX(x);
            window.setEnemyY(y);
        } else {
            window.setEnemyX(700);
            window.setEnemyY(700);
        }
    }

    public void setEnemyDirection(double direction) {
        window.setEnemyAngle(direction);
    }

    public int getPlayersRounds() {
        return PlayersRound;
    }

    public void setEnemyRound(int enemyRound) {
        this.enemyRound = enemyRound;
    }

    public void addRound() {
        PlayersRound++;
    }

    private void setPlayersX(double x) {
        keyLogic.setX(x);
    }

    private void setPlayersY(double y) {
        keyLogic.setY(y);
    }

}
