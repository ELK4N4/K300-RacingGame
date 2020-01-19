package Client.Backend;

import Client.Client;

public class KeyTranslator implements Runnable {

    private boolean run;
    private double x;
    private double y;
    private double Cx;
    private double Cy;
    private double direction;
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

    public void setX(double x){
        this.x = x;
        Cx = x;
    }

    public void setY(double y) {
        this.y = y;
        Cy = y;
    }

    @Override
    public void run() {
        while (run) {
            myMove();
            if(!track.onTheTrack(x, y)) {
                x = 900;
                y = 900;
            }
            client.refreshWindow();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void myMove() {
        if(up) {
            if(right) {
                changeDirection(x, y, false);
            }
            if(left) {
                changeDirection(x, y, true);
            }

            if(direction >= -0.0276 && direction <= 0.0276) {
                calcXY(1);
            } else if(direction >= 0.0276 && direction <= 0.0822) {
                calcXY(-1);
            }


        }
        if(down) {
            if(right) {
                changeDirection(x, y, true);
            }
            if(left) {
                changeDirection(x, y, false);
            }

            if(direction >= -0.0276 && direction <= 0.0276) {
                calcXYDown(1);
            } else if(direction >= 0.0276 && direction <= 0.0822) {
                calcXYDown(-1);
            }
        }

    }

    public  double getDirection() {
        return direction;
    }

    public  double getX() {
        return x;
    }

    public  double getY() {
        return y;
    }

    private double getY(double x) {
        double y;
        y = Math.tan(Math.toDegrees(direction)) * (x - Cx) ;
        y = y + Cy;
        return y;
    }

    private void calcXY(int n)   {
        double ty = getY(n + x);
        double td = Math.sqrt(Math.pow(-1, 2) + ((y - ty) * (y - ty)));
        x =  (x + (4 / td) * n);
        y = y - (4 / td) *  (y - ty);
    }

    private void calcXYDown(int n) {
        double ty = getY(n + x);
        double td = Math.sqrt(Math.pow(-1, 2) + ((y - ty) * (y - ty)));
        x =  (x - (4 / td) * n);
        y = y - (4 / td) * (y - ty);
    }

    private void changeDirection(double x, double y, boolean left) {
        Cx = x;
        Cy = y;
        if(direction >= 0.0822) {
            direction = -0.0276;
        }
        else if(direction <= -0.0276) {
            direction = 0.0822;
        }
        if(left) {
            direction -= 0.0006;
        } else {
            direction += 0.0006;
        }
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

