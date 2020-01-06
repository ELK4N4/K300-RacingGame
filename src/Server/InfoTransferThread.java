package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InfoTransferThread implements Runnable{

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private boolean run;

    InfoTransferThread(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        run = true;
    }

    @Override
    public void run() {
        while (run) {
            try {
                outputStream.writeObject(inputStream.readObject());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    void stop() {
        run = false;
        try {
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
