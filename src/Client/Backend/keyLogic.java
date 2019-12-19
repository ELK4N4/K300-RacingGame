package Client.Backend;

import Client.Main;

public class keyLogic implements Runnable {

    private double x;
    private double y;
    private double Cx;
    private double Cy;
    private double direction;
    private boolean up, down, right, left;
    private Main main;

    public keyLogic(Main main) {
        up = false;
        down = false;
        right = false;
        left = false;
        this.main = main;
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
        System.out.println("hello");
        while (true) {
            myMove();
            main.repaint();
                try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("error");
                break;
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
                changeDirection(x, y, false);
            }
            if(left) {
                changeDirection(x, y, true);
            }

            if(direction >= -0.0276 && direction <= 0.0276) {
                calcXYDown(1);
            } else if(direction >= 0.0276 && direction <= 0.0822) {
                calcXYDown(-1);
            }
        }


        /*
       if(up) {
            if(Test2.direction >= -0.0276 && Test2.direction <= 0.0276) {
                Test2.x += 3;
            } else if(Test2.direction >= 0.0276 && Test2.direction <= 0.0822) {
                Test2.x -= 3;
            }
            Test2.y = getY(Test2.x);
            up = false;
        }
        if(down) {
            if(Test2.direction >= -0.0276 && Test2.direction <= 0.0276) {
                Test2.x -= 3;
            } else if(Test2.direction >= 0.0276 && Test2.direction <= 0.0822) {
                Test2.x += 3;
            }
            Test2.y = getY(Test2.x);
            down = false;
        }
        if(right) {
            changeDirection(Test2.x, Test2.y, false);
            right = false;
        }
        if(left) {
            changeDirection(Test2.x, Test2.y, true);
            left = false;
        }
        */

    }

    public  double getDirection()
    {
        return direction;
    }
    public  double getX()
    {
        return x;
    }
    public  double getY()
    {
        return y;
    }
    private double getY(double x) {
        double y;
        y = Math.tan(Math.toDegrees(direction)) * (x - Cx) ;
        y = y + Cy;
        return y;
    }

    private void calcXY(int n) {
        double tx = n;
        double ty = getY(tx + x);
        double td = Math.sqrt(Math.pow(-1, 2) + Math.pow(y - ty, 2));
        x =  (x + (4 / td) * n);
        y = y - (4 / td) *  (y - ty);
    }

    private void calcXYDown(int n) {
        double tx = n;
        double ty = getY(tx + x);

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

