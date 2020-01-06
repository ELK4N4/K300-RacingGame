package Server;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Client {

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private ObjectInputStream [] otherPlayersInPutStreams;
    private ObjectOutputStream [] otherPlayersOutPutStreams;
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

    void setOtherPlayersInPutStreams(ObjectInputStream [] inPutStreams) {
        otherPlayersInPutStreams = inPutStreams;
    }

    void setOtherPlayersOutPutStreams(ObjectOutputStream [] outPutStreams) {
        otherPlayersOutPutStreams = outPutStreams;
    }

    void startInfoTransfers() {
        for (int i = 0; i < Main.SUM_OF_CLIENTS - 1; i++) {
            new Thread(new InfoTransferThread(outputStream, otherPlayersInPutStreams[i])).start();
            new Thread(new InfoTransferThread(otherPlayersOutPutStreams[i], inputStream)).start();
        }
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
