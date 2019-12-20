package Client.Backend;

import Client.Main;
import java.awt.geom.Area;

public class keyLogic implements Runnable {

    private double x1;
    private double y1;
    private double Cx1;
    private double Cy1;
    private double direction1;
    private double x2;
    private double y2;
    private double Cx2;
    private double Cy2;
    private double direction2;
    private double imageHeight;
    private double imageWidth;
    private boolean up, down, right, left;
    private boolean w, s, d, a;
    private Main main;
    private Area playerCar;
    private Area enemyCar;

    public keyLogic(Main main) {
        up = false;
        down = false;
        right = false;
        left = false;
        this.main = main;
    }

    public void setX1(double x1){
        this.x1 = x1;
        Cx1 = x1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
        Cy1 = y1;
    }

    public void setX2(double x2) {
        this.x2 = x2;
        Cx2 = x2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
        Cy2 = y2;
    }

    public void setImageHeight(double imageHeight) {
        this.imageHeight = imageHeight;
    }

    public void setImageWidth(double imageWidth) {
        this.imageWidth = imageWidth;
    }

    @Override
    public void run() {
        while (true) {
            myMove1();
            myMove2();
            main.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void isIntersects() {

    }

    private void myMove1() {

        if(up) {
            if(right) {
                changeDirection1(x1, y1, false);
            }
            if(left) {
                changeDirection1(x1, y1, true);
            }

            if(direction1 >= -0.0276 && direction1 <= 0.0276) {
                calcXY1(1);
            } else if(direction1 >= 0.0276 && direction1 <= 0.0822) {
                calcXY1(-1);
            }


        }
        if(down) {
            if(right) {
                changeDirection1(x1, y1, false);
            }
            if(left) {
                changeDirection1(x1, y1, true);
            }

            if(direction1 >= -0.0276 && direction1 <= 0.0276) {
                calcXYDown1(1);
            } else if(direction1 >= 0.0276 && direction1 <= 0.0822) {
                calcXYDown1(-1);
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

    private void myMove2() {

        if(w) {
            if(d) {
                changeDirection1(x2, y2, false);
            }
            if(a) {
                changeDirection1(x2, y2, true);
            }

            if(direction2 >= -0.0276 && direction2 <= 0.0276) {
                calcXY1(1);
            } else if(direction2 >= 0.0276 && direction2 <= 0.0822) {
                calcXY1(-1);
            }


        }
        if(s) {
            if(d) {
                changeDirection1(x2, y2, false);
            }
            if(a) {
                changeDirection1(x2, y2, true);
            }

            if(direction2 >= -0.0276 && direction2 <= 0.0276) {
                calcXYDown1(1);
            } else if(direction2 >= 0.0276 && direction2 <= 0.0822) {
                calcXYDown1(-1);
            }
        }
    }

    public  double getDirection1()
    {
        return direction1;
    }
    public  double getX1()
    {
        return x1;
    }
    public  double getY1()
    {
        return y1;
    }
    private double getY1(double x) {
        double y;
        y = Math.tan(Math.toDegrees(direction1)) * (x - Cx1) ;
        y = y + Cy1;
        return y;
    }

    public double getDirection2() {
        return direction2;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    private double getY2(double x) {
        double y;
        y = Math.tan(Math.toDegrees(direction2)) * (x - Cx2) ;
        y = y + Cy2;
        return y;
    }

    private void calcXY1(int n)   {
        double tx = n;
        double ty = getY1(tx + x1);
        double td = Math.sqrt(Math.pow(-1, 2) + Math.pow(y1 - ty, 2));
        x1 =  (x1 + (4 / td) * n);
        y1 = y1 - (4 / td) *  (y1 - ty);
    }

    private void calcXYDown1(int n) {
        double tx = n;
        double ty = getY1(tx + x1);

        double td = Math.sqrt(Math.pow(-1, 2) + Math.pow(y1 - ty, 2));



        x1 =  (x1 - (4 / td) * n);
        y1 = y1 - (4 / td) *  (y1 - ty);
    }

    private void changeDirection1(double x, double y, boolean left) {
        Cx1 = x;
        Cy1 = y;
        if(direction1 >= 0.0822) {
            direction1 = -0.0276;
        }
        else if(direction1 <= -0.0276) {
            direction1 = 0.0822;
        }
        if(left) {
            direction1 -= 0.0006;

        } else {
            direction1 += 0.0006;
        }
    }

    private void calcXY2(int n)   {
        double tx = n;
        double ty = getY1(tx + x2);
        double td = Math.sqrt(Math.pow(-1, 2) + Math.pow(y2 - ty, 2));
        x2 =  (x2 + (4 / td) * n);
        y2 = y2 - (4 / td) *  (y2 - ty);
    }

    private void calcXYDown2(int n) {
        double tx = n;
        double ty = getY1(tx + x2);

        double td = Math.sqrt(Math.pow(-1, 2) + Math.pow(y2 - ty, 2));



        x2 =  (x2 - (4 / td) * n);
        y2 = y2 - (4 / td) *  (y2 - ty);
    }

    private void changeDirection2(double x, double y, boolean left) {
        Cx2 = x;
        Cy2 = y;
        if(direction2 >= 0.0822) {
            direction2 = -0.0276;
        }
        else if(direction2 <= -0.0276) {
            direction2 = 0.0822;
        }
        if(left) {
            direction2 -= 0.0006;

        } else {
            direction2 += 0.0006;
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

    public void setW(boolean w) {
        this.w = w;
    }

    public void setS(boolean s) {
        this.s = s;
    }

    public void setD(boolean d) {
        this.d = d;
    }

    public void setA(boolean a) {
        this.a = a;
    }
}

