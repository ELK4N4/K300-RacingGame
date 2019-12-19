package Client.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Window extends JPanel implements KeyListener {
    private static final int SCREEN_HEIGHT = 1080;
    private static final int SCREEN_WIDTH = 1920;
    private static JFrame frame;
    private BufferedImage car;
    private keyThread keyThread;
    static double direction = 0;
    static double Xcar = SCREEN_WIDTH / 2.0;
    static double Ycar = SCREEN_HEIGHT / 2.0;

    private Window(){
        keyThread = new keyThread(this);
        car = getImage("Images/car_blue.png");
        this.setBackground(Color.blue);
        frame.addKeyListener(this);
        new Thread(keyThread).start();
        Xcar -= car.getWidth() / 2.0;
        Ycar -= car.getHeight() / 2.0;
    }

    public static void main(String[] args) {
        frame = new JFrame();
        JPanel panel = new Window();
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.toFront();
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        frame.setVisible(true);
    }



    private BufferedImage getImage(String imageName) {
        try {
            return ImageIO.read(getClass().getResource(imageName));
        } catch (IOException e) {
            throw new Error("no path found");
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        AffineTransform at = AffineTransform.getTranslateInstance(Xcar, Ycar);
        g.setFont(new Font("arial", 1, 50));
        g.setColor(Color.red);

        g.drawString(direction + "", 70, 50);

        at.rotate(Math.toDegrees(direction), car.getWidth() / 2.0, car.getHeight() / 2.0);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(car, at, null);
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("right");
            keyThread.setRight(true);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            keyThread.setLeft(true);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            keyThread.setUp(true);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            keyThread.setDown(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyThread.setRight(false);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            keyThread.setLeft(false);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            keyThread.setUp(false);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            keyThread.setDown(false);
        }
    }
}
