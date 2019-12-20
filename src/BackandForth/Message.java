package BackandForth;

import java.io.Serializable;

public class Message implements Serializable {

    public static final int PORT = 9090;
    public static final String IP = "192.168.1.63";
    public double x;
    public double y;
    public double direction;
    public int round;

    public Message(double x, double y, double direction, int round) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.round = round;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDirection() {
        return direction;
    }

    public int getRound() {
        return round;
    }

    @Override
    public String toString() {
        return "Message{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                ", round=" + round +
                '}';
    }
}
