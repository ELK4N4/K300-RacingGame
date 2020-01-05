package Client.Backend;

import Client.Main;

public class KeyLogic implements Runnable {

    private double x;
    private double y;
    private double Cx;
    private double Cy;
    private double direction;
    private boolean up, down, right, left;
    private Main main;

    public KeyLogic(Main main) {
        up = false;
        down = false;
        right = false;
        left = false;
        this.main = main;
    }

    public void setX(double x1){
        this.x = x1;
        Cx = x1;
    }

    public void setY(double y) {
        this.y = y;
        Cy = y;
    }

    @Override
    public void run() {
        while (true) {
            myMove();
            main.refreshWindow();
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
        double td = Math.sqrt(Math.pow(-1, 2) + Math.pow(y - ty, 2));
        x =  (x + (4 / td) * n);
        y = y - (4 / td) *  (y - ty);
    }

    private void calcXYDown(int n) {
        double ty = getY(n + x);
        double td = Math.sqrt(Math.pow(-1, 2) + Math.pow(y - ty, 2));
        x =  (x - (4 / td) * n);
        y = y - (4 / td) *  (y - ty);
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

}
