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
            carMove();
            frame.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("error");
                break;
            }
        }
    }

    private void carMove() {
        if(up) {
            Window.Ycar += 1;
            if(right) {
                Window.Xcar += 1;
            }
            if(left) {
                Window.Xcar -= 1;
            }
        }
        if(down) {
            Window.Ycar -= 1;
            if(right) {
                Window.Xcar += 1;
            }
            if(left) {
                Window.Xcar -= 1;
            }
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
