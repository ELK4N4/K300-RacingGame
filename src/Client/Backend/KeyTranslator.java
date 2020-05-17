package Client.Backend;

import Client.Client;

public class KeyTranslator implements Runnable {

    private boolean run;
    private double currentX;
    private double currentY;
    private double direction;
    private double angle = 0;
    private double carTurnAngle = 4;
    private double speed = 10;
    private boolean up, down, right, left;
    private Client client;

    private Track track = new Track(984, 517);

    public KeyTranslator(Client client) {
        run = true;
        up = false;
        down = false;
        right = false;
        left = false;
        this.client = client;
    }

    public void setCurrentX(double currentX){
        this.currentX = currentX;
    }

    public void setCurrentY(double currentY) {
        this.currentY = currentY;
    }

    @Override
    public void run() {
        while (run) {
            myMove();
            if(!track.onTheTrack(currentX, currentY)) {
                currentX = 900;
                currentY = 900;
            }
            client.refreshWindow();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void myMove() {
        if(up) {
            changeDirection(currentX, currentY);
            driveForward();
        }
        if(down) {
            changeDirection(currentX, currentY);
            driveBackward();
        }
    }

    public  double getDirection() {
        return direction;
    }

    public double getCurrentX() {
        return currentX;
    }

    public double getCurrentY() {
        return currentY;
    }

    /*
    private double getY(double x) {
        double y;
        y = Math.tan(Math.toRadians(angle)) * (x); // bug while angle = 0
        return y;
    }
    */

    private void driveForward()   {
        double newX;
        double newY;
        if(angle >= 0 && angle <= 90) {
            newX = currentX + (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
            newY = currentY - Math.tan(Math.toRadians(angle)) * (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
        } else if(angle >= 90 && angle <= 180) {
            newX = currentX - (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
            newY = currentY + Math.tan(Math.toRadians(angle)) * (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
        } else if(angle >= 180 && angle <= 270) {
            newX = currentX - (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
            newY = currentY + Math.tan(Math.toRadians(angle)) * (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
        } else /*if(angle >= 270 && angle <= 360)*/ {
            newX = currentX + (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
            newY = currentY - Math.tan(Math.toRadians(angle)) * (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
        }
        currentX = newX;
        currentX = Math.round(currentX*10000)/10000.0; //5 numbers after the decimal point
        currentY = newY;
        currentY = Math.round(currentY*10000)/10000.0; //5 numbers after the decimal point
    }

    private void driveBackward() {
        double newX;
        double newY;
        if(angle >= 0 && angle <= 90) {
            newX = currentX - (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
            newY = currentY + Math.tan(Math.toRadians(angle)) * (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
        } else if(angle >= 90 && angle <= 180) {
            newX = currentX + (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
            newY = currentY - Math.tan(Math.toRadians(angle)) * (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
        } else if(angle >= 180 && angle <= 270) {
            newX = currentX + (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
            newY = currentY - Math.tan(Math.toRadians(angle)) * (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
        } else /*if(angle >= 270 && angle <= 360)*/ {
            newX = currentX - (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
            newY = currentY + Math.tan(Math.toRadians(angle)) * (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
        }
        currentX = newX;
        currentX = Math.round(currentX*10000)/10000.0; //5 numbers after the decimal point
        currentY = newY;
        currentY = Math.round(currentY*10000)/10000.0; //5 numbers after the decimal point
    }

    private void changeDirection(double x, double y) {
        if (left) {
            angle += carTurnAngle;
            if (angle == 90) {
                angle += carTurnAngle;
            }
        } else if (right) {
            angle -= carTurnAngle;
            if (angle == 90) {
                angle -= carTurnAngle;
            }
        }
        if (angle > 360) {
            angle = 0;
        } else if (angle < 0) {
            angle = 360;
        }
        direction = angle;
        ////////////////////////////
        /*
        if(direction >= 0.0822) {
            direction = -0.0276;
        }
        else if(direction <= -0.0276) {
            direction = 0.0822;
        }
        if(left) {
            direction -= 0.0004;
        } else if (right) {
            direction += 0.0004;
        }
        */
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void stop() {
        run = false;
    }

}

