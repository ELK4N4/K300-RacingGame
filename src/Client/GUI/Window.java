package Client.GUI;

import BackandForth.CarColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Window extends JPanel {
    private static final int SCREEN_HEIGHT = 1080;
    private static final int SCREEN_WIDTH = 1920;
    private BufferedImage blueCar;
    private BufferedImage redCar;
    private BufferedImage yellowCar;
    private BufferedImage background;
    private CarColor playersCarColor;
    static double blueCarAngle = 0;
    static double redCarAngle = 0;
    static double yellowCarAngle = 0;
    static double blueCarX = SCREEN_WIDTH / 2.0;
    static double blueCarY = SCREEN_HEIGHT / 2.0 + 300;
    static double redCarX = SCREEN_WIDTH / 2.0;
    static double redCarY = SCREEN_HEIGHT / 2.0 + 300;
    static double yellowCarX = SCREEN_WIDTH / 2.0;
    static double yellowCarY = SCREEN_HEIGHT / 2.0 + 300;

    public Window(CarColor playersCarColor, KeyListener keyListener) {
        this.playersCarColor = playersCarColor;
        JFrame frame = new JFrame();
        blueCar = getImage("Images/car_blue_small.png");
        redCar = getImage("Images/car_red_small.png");
        yellowCar = getImage("Images/car_yellow_small.png");
        background = getImage("Images/Track.jpg");
        this.setBackground(Color.blue);
        blueCarX = redCar.getWidth() / 2.0;
        blueCarY -= redCar.getHeight() / 2.0;
        redCarX -= redCar.getWidth() / 2.0;
        redCarY -= redCar.getHeight() / 2.0;
        frame.addKeyListener(keyListener);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
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

    public void setPlayerX(double x) {
        if (playerIsRed()) {
            redCarX = x;
        } else if(playerIsBlue()) {
            blueCarX = x;
        } else {
            yellowCarX = x;
        }
    }

    public void setPlayerY(double y) {
        if (playerIsRed()) {
            redCarY = y;
        } else if(playerIsBlue()){
            blueCarY = y;
        } else {
            yellowCarY = y;
        }
    }

    public void setPlayerAngle(double angle) {
        if (playerIsRed()) {
            redCarAngle = angle;
        } else if(playerIsBlue()){
            blueCarAngle = angle;
        } else {
            yellowCarAngle = angle;
        }
    }

    public void setEnemyY(double y) {
        if (playerIsRed()) {
            blueCarY = y;
        } else {
            redCarY = y;
        }
    }

    public void setEnemyX(double x) {
        if (playerIsRed()) {
            blueCarX = x;
        } else {
            redCarX = x;
        }
    }

    public void setEnemyAngle(double angle) {
        if (playerIsRed()) {
            blueCarAngle = angle;
        } else {
            redCarAngle = angle;
        }
    }

    public double getPlayersX() {
        if(playerIsRed()) {
            return redCarX;
        } else if(playerIsBlue()) {
            return blueCarX;
        } else {
            return yellowCarX;
        }
    }

    public double getPlayersY() {
        if(playerIsRed()) {
            return redCarY;
        } else if(playerIsBlue()){
            return blueCarY;
        } else {
            return yellowCarY;
        }
    }

    // for collision detection
    public double getEnemyX() {
        if(playerIsRed()) {
            return blueCarX;
        } else {
            return redCarX;
        }
    }

    public double getEnemyY() {
        if(playerIsRed()) {
            return blueCarY;
        } else {
            return redCarY;
        }
    }

    private boolean playerIsRed() {
        return playersCarColor == CarColor.RED;
    }

    private boolean playerIsBlue() {
        return playersCarColor == CarColor.BLUE;
    }

    private boolean playerIsYellow() {
        return playersCarColor == CarColor.YELLOW;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        AffineTransform atBlueCar = AffineTransform.getTranslateInstance(blueCarX, blueCarY);
        AffineTransform atRedCar = AffineTransform.getTranslateInstance(redCarX, redCarY);
        //AffineTransform atYellowCar = AffineTransform.getTranslateInstance(yellowCarX, yellowCarY);
        g.setFont(new Font("arial", 1, 50));
        g.setColor(Color.red);

        atBlueCar.rotate(Math.toDegrees(blueCarAngle), blueCar.getWidth() / 2.0, blueCar.getHeight() / 2.0);
        atRedCar.rotate(Math.toDegrees(redCarAngle), redCar.getWidth() / 2.0, redCar.getHeight() / 2.0);
        //atRedCar.rotate(Math.toDegrees(yellowCarAngle), yellowCar.getWidth() / 2.0, yellowCar.getHeight() / 2.0);

        Graphics2D graphics2D = (Graphics2D) g;
        g.drawImage(background, 0, 0, this);
        graphics2D.drawImage(blueCar, atBlueCar, null);
        graphics2D.drawImage(redCar, atRedCar, null);
        //graphics2D.drawImage(yellowCar, atYellowCar, null);
    }



}
