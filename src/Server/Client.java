package Server;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Client {

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private int count;

    Client(ObjectInputStream inputStream, ObjectOutputStream outputStream, int count) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.count = count;
    }

    ObjectInputStream getInputStream() {
        return inputStream;
    }

    ObjectOutputStream getOutputStream () {
        return outputStream;
    }

    @Override
    public String toString() {
        return "Client{\n" +
                "inputStream=" + inputStream +
                ", outputStream=" + outputStream +
                ", count=" + count +
                "\n}";
    }
}
