package Client;

import BackandForth.Message;
import Client.GUI.Window;

import java.io.*;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Window frame = new Window();
        Socket socket;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        socket = new Socket(Message.IP,Message.PORT);
        inputStream = new ObjectInputStream(socket.getInputStream());
        outputStream = new ObjectOutputStream(socket.getOutputStream());
    }

}
