package Client.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Window extends JPanel implements KeyListener {
    private static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static JFrame frame;
    private BufferedImage car;
    //private ClickThread clickThread;
    static double direction = 0;
    static double x = SCREEN_WIDTH / 2.0;
    static double y = SCREEN_HEIGHT / 2.0;
    static double Cx = x;
    static double Cy = y;

    private Window(){
        //clickThread = new ClickThread(this);
        //car = getImage("Images/car_blue.png");
        this.setBackground(Color.blue);
        frame.addKeyListener(this);
        System.out.println(SCREEN_HEIGHT);
        System.out.println(SCREEN_WIDTH);
        //new Thread(clickThread).start();
//        x -= car.getWidth() / 2.0;
 //       y -= car.getHeight() / 2.0;
    }

    public static void main(String[] args) {
        frame = new JFrame("K300");
        JPanel panel = new Window();
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }



    private BufferedImage getImage(String imageName) {
        try {
            return ImageIO.read(getClass().getResource(imageName));
        } catch (IOException e) {
            throw new Error("no path found");
        }
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
