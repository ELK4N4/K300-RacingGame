package Server;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Client {

    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    private int count;

    Client(ObjectInputStream inputStream, ObjectOutputStream outputStream, int count) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.count = count;
        System.out.println(inputStream.toString() + "  " + outputStream.toString() + "   " + count);
    }
}
