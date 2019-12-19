package Server;

import BackandForth.Message;

public class thread implements Runnable {
    Message message;
    double x;
    double y;
    double direction;
    int round;

    public thread(Message message){
        this.message = message;
    }
    @Override
    public void run() {
        x = message.getX();
        y = message.getY();
        direction = message.getDirection();
        round = message.getRound();
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public int getRound(){
        return round;
    }
    public double getDirection(){
        return direction;
    }
}
