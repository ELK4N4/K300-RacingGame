package Server.Testing2;

import BackandForth.CarColor;
import BackandForth.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(Message.IP, Message.PORT);
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        inputStream = new ObjectInputStream(socket.getInputStream());
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("connected");
        outputStream.writeObject(new Message(3,3,3,3, CarColor.BLUE));
        for (int i = 0; i < 25; i++) {
            System.out.println("receiving");
            Message m = (Message) inputStream.readObject();
            System.out.println(m);
            System.out.println("sending data ");
            outputStream.writeObject(new Message(m.x, m.y, m.direction, m.round, m.carColor));
        }

    }

}
