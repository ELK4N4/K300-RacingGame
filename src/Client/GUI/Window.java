package Client.GUI;

import BackandForth.CarColor;
import BackandForth.Message;
import Client.Backend.PlayersDataBase;
import Client.Backend.Track;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Window extends JPanel {

    private static final int SCREEN_WIDTH = 1920;
    private static final int SCREEN_HEIGHT = 1080;


    // _small / ""
    private static final String CAR_SIZE = "_small";
    private PlayersDataBase playersDataBase;
    private BufferedImage background;

    public Window(PlayersDataBase playersDataBase, KeyListener keyListener) {
        this.playersDataBase = playersDataBase;
        JFrame frame = new JFrame();
        background = getImage("Images/Track.jpg");
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

    private BufferedImage getImage(CarColor carColor) {
        return getImage("Images/car_" + carColor.toString().toLowerCase() + CAR_SIZE + ".png");
    }

    private BufferedImage getImage(String imageName) {
        try {
            return ImageIO.read(getClass().getResource(imageName));
        } catch (IOException e) {
            throw new Error("no path found");
        }
    }

    public double getCarWidth(CarColor carColor) {
        return getImage(carColor).getWidth();
    }

    public double getCarHeight(CarColor carColor) {
        return getImage(carColor).getHeight();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D;
        graphics2D = (Graphics2D) g;
        double angle;
        double width;
        double height;
        AffineTransform carAngle;
        g.drawImage(background, 0, 0, this);
        // player (client)
        try {
            angle = playersDataBase.getPlayersAngle();
            // todo might not need to dived by 2
            width = getImage(playersDataBase.getPlayersCarColor()).getWidth() / 2.0;
            height = getImage(playersDataBase.getPlayersCarColor()).getHeight() / 2.0;
            System.out.println(playersDataBase.getPlayersXPosition() + "\n" + playersDataBase.getPlayersYPosition() + "\n" + angle);
            carAngle = AffineTransform.getTranslateInstance(playersDataBase.getPlayersXPosition()-width, playersDataBase.getPlayersYPosition()-height);
            carAngle.rotate(Math.toRadians(-angle), width, height); //need Minus because Java is multiplier minus
            graphics2D.drawImage(getImage(playersDataBase.getPlayersCarColor()), carAngle, null);
        } catch (NullPointerException ignore){}
        // todo run on different thread
        // enemies
        for (int i = 0; i < playersDataBase.getEnemiesCarColors().length; i++) {
            angle = playersDataBase.getEnemyAngels()[i];
            width = getImage(playersDataBase.getEnemiesCarColors()[i]).getWidth() / 2.0;
            height = getImage(playersDataBase.getEnemiesCarColors()[i]).getHeight() / 2.0;
            carAngle = AffineTransform.getTranslateInstance(playersDataBase.getEnemyXPositions()[i], playersDataBase.getEnemyYPositions()[i]);
            carAngle.rotate(Math.toRadians(-angle), width, height);
            graphics2D.drawImage(getImage(playersDataBase.getEnemiesCarColors()[i]), carAngle, null);
        }

        g.setColor(Color.red);

        int xLocation = SCREEN_WIDTH/2;
        int yLocation = SCREEN_HEIGHT/2;
        int a = 984;
        int b = 517;
        int bigA = (int)(a*1.7);
        int bigB = (int)(b*1.7);
        g.drawOval(xLocation-a/2, yLocation-b/2, a, b);
        g.drawOval(xLocation-bigA/2, yLocation-bigB/2, bigA, bigB);

        // Center Location 468, 282
        int carX = (int)playersDataBase.getPlayersXPosition();
        int carY = (int)playersDataBase.getPlayersYPosition();
        /*
        System.out.println("--------------" + carX + "------------------");
        System.out.println("--------------" + carY + "------------------");
        */

        g.fillOval(carX-25/2 , carY-25/2, 25, 25);

        a = a/2;
        b = b/2;

        bigA = bigA/2;
        bigB = bigB/2;

        int positiveC = getPositiveC(a, b);
        int negativeC = getNegativeC(a, b);

        int positiveBigC = getPositiveC(bigA, bigB);
        int negativeBigC = getNegativeC(bigA, bigB);

        int axisX = getAxisXPoint(carX);
        int axisY = getAxisYPoint(carY);

        int distance1 = getDistance(positiveC, 0, axisX, axisY);
        int distance2 = getDistance(negativeC, 0, axisX, axisY);
        /*
        int smallDistance = distance1+distance2;
        System.out.println(smallDistance);
        System.out.println(2*a);


        distance1 = getDistance(positiveBigC, 0, axisX, axisY);
        distance2 = getDistance(negativeBigC, 0, axisX, axisY);
        int bigDistance = distance1+distance2;
        System.out.println(positiveC);
        System.out.println(smallDistance + " > "  + (2 * a) + " && " +  bigDistance + " < "+  (2 * bigB));
        if(smallDistance > 2*a && bigDistance < 2*bigA) {
            System.out.println("OK");
        } else {
            System.out.println("Not OK");
        }
        */

        Track track = new Track(984, 517);
        /*
        System.out.println(track.onTheTrack(carX, carY));
        */

        g.setFont(new Font("TimesRoman", Font.BOLD, 70));
        g.drawString("Angle: " + Double.toString(this.playersDataBase.getPlayersAngle()), 830, 500);
        g.drawString("X: " + Double.toString(this.playersDataBase.getPlayersXPosition()), 830, 600);
        g.drawString("Y: " + Double.toString(this.playersDataBase.getPlayersYPosition()), 830, 700);
    }

    public int getDistance(int x1, int y1, int x2, int y2) {
        return (int) Point2D.distance(x1, y1, x2, y2);
    }

    public int getPositiveC(int a, int b) {
        return (int) Math.sqrt( (Math.pow(a, 2) - Math.pow(b, 2)) );
    }

    public int getNegativeC(int a, int b) {
        return - (int) Math.sqrt( (Math.pow(a, 2) - Math.pow(b, 2)) );
    }

    public int getFrameXPoint(int xPosition) {
        return 1920/2 + xPosition;
    }

    public int getFrameYPoint(int yPosition) {
        return 1080/2 - yPosition;
    }

    public int getAxisXPoint(int xPosition) {
        return xPosition - 1920/2;
    }

    public int getAxisYPoint(int yPosition) {
        return -(yPosition - 1080/2);
    }

}
