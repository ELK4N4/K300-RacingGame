package Client.GUI;

public class keyThread implements Runnable {

    private boolean up, down, right, left;
    Window frame;

    public keyThread(Window frame) {
        this.frame = frame;
        up = false;
        down = false;
        right = false;
        left = false;
    }

    @Override
    public void run() {
        while (true) {
            myMove();
            frame.repaint();
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
            Window.Ycar += 1;
        }
        if(down) {
            Window.Ycar -= 1;
        }
        if(left) {
            Window.Xcar -= 1;
        }
        if(right) {
            Window.Xcar += 1;
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
