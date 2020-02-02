package Client;

import BackandForth.CarColor;
import BackandForth.Message;
import Client.Backend.*;
import Client.GUI.Window;

import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;

public class Client {

    private KeyTranslator keyTranslator;
    private Window window;
    public static int playersRound;

    private Client() throws IOException, ClassNotFoundException {
        Socket socket;
        DataBase dataBase;
        // todo if connection fails give single player option
        socket = new Socket(Message.IP, Message.PORT);
        DataTransferThread dataTransferThread;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        CarColor playersCarColor;
        keyTranslator = new KeyTranslator(this);
        KeyListener listener = new KeyInput(keyTranslator);
        inputStream = new ObjectInputStream(socket.getInputStream());
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        playersCarColor = CarColor.valueOf(((String) inputStream.readObject()));
        System.out.println(playersCarColor);
        dataBase = new DataBase(this);
        window = new Window(dataBase, listener);
        dataBase.setStartingXY(playersCarColor);
        dataTransferThread = new DataTransferThread(dataBase, keyTranslator, outputStream, inputStream);
        new Thread(dataTransferThread).start();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Client();
    }

    public void refreshWindow() {
        window.repaint();
    }

    // todo move to key logic
//    public void setPlayersPosition(double x, double y) {
//        if((x-900) * (x-900) + 3 * ((y-600) * (y-600)) <= 10000000 && (x-900) * (x-900) + 5 * ((y-600) * (y-600)) >= 10000) {
//            window.setPlayerX(x);
//            window.setPlayerY(y);
//        } else {
//            window.setPlayerX(700);
//            window.setPlayerY(700);
//            setPlayersX(700);

//            setPlayersY(700);
//        }
//    }
    // todo implement this
//    public int getPlayersRounds() {
//        return playersRound;
//    }
//
//    public void addRound() {
//        playersRound++;
//    }

    public void setBackendXY(double startingX, double startingY) {
        keyTranslator.setX(startingX);
        keyTranslator.setY(startingY);
    }

    public double getWindowWidth() {
        return window.getWidth();
    }

    public double getWindowHeight() {
        return window.getHeight();
    }

    public double getCarWidth(CarColor carColor) {
        return window.getCarWidth(carColor);
    }

    public double getCarHeight(CarColor carColor) {
        return window.getCarHeight(carColor);
    }

}
