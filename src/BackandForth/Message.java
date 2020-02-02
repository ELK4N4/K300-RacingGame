package BackandForth;

import java.io.Serializable;

public class Message implements Serializable {

    public static final int SUM_OF_PLAYERS = 3;
    public static final int PORT = 9090;
    public static final String IP = "127.0.0.1";
    public double x;
    public double y;
    public double direction;
    public int round;
    public CarColor carColor;

    public Message(double x, double y, double direction, int round, CarColor carColor) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.round = round;
        this.carColor = carColor;
    }

    @Override
    public String toString() {
        return "Message{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                ", round=" + round +
                ", carColor=" + carColor +
                '}';
    }
}
