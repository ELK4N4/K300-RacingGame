package Client.Backend;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.IOException;

public class keyLogic implements Runnable {


    private static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static JFrame frame;
    private BufferedImage banana;
    private ClickThread clickThread;
    private boolean isUp = true;
    static double direction = 0;
    static double x = SCREEN_WIDTH / 2.0;
    static double y = SCREEN_HEIGHT / 2.0;
    static double Cx = x;
    static double Cy = y;

    private Test2(){
        clickThread = new ClickThread(this);
        banana = getImage("Images/arrow.png");
        this.setBackground(Color.blue);
        frame.addKeyListener(this);
        new Thread(clickThread).start();
//        x -= banana.getWidth() / 2.0;
//        y -= banana.getHeight() / 2.0;
    }

    private BufferedImage getImage(String imageName) {
        try {
            return ImageIO.read(getClass().getResource(imageName));
        } catch (IOException e) {
            throw new Error("no path found");
        }
    }

    public static void main(String[] args) {
        frame = new JFrame("Test");
        JPanel panel = new Test2();
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        g.setFont(new Font("arial", 1, 50));
        g.setColor(Color.red);

        g.drawString(Test2.direction + "", 70, 50);

        if (isUp) {
            at.rotate(Math.toDegrees(direction), banana.getWidth() / 2.0, banana.getHeight() / 2.0);
        } else {
            at.rotate(Math.toDegrees(direction), banana.getWidth() / 2.0, banana.getHeight() / 2.0);
        }


        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(banana, at, null);
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
            clickThread.setRight(true);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            clickThread.setLeft(true);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            clickThread.setUp(true);
            isUp = true;
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            clickThread.setDown(true);
            isUp = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            clickThread.setRight(false);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            clickThread.setLeft(false);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            clickThread.setUp(false);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            clickThread.setDown(false);
        }
    }
}

