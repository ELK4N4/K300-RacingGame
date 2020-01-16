package Client.GUI;

import BackandForth.CarColor;
import BackandForth.Message;
import Client.Backend.PlayersDataBase;

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
            carAngle = AffineTransform.getTranslateInstance(playersDataBase.getPlayersXPosition(), playersDataBase.getPlayersYPosition());
            carAngle.rotate(Math.toDegrees(angle), width, height);
            graphics2D.drawImage(getImage(playersDataBase.getPlayersCarColor()), carAngle, null);
        } catch (NullPointerException ignore){}
        // todo run on different thread
        // enemies
        for (int i = 0; i < playersDataBase.getEnemiesCarColors().length; i++) {
            angle = playersDataBase.getEnemyAngels()[i];
            width = getImage(playersDataBase.getEnemiesCarColors()[i]).getWidth() / 2.0;
            height = getImage(playersDataBase.getEnemiesCarColors()[i]).getHeight() / 2.0;
            carAngle = AffineTransform.getTranslateInstance(playersDataBase.getEnemyXPositions()[i], playersDataBase.getEnemyYPositions()[i]);
            carAngle.rotate(Math.toDegrees(angle), width, height);
            graphics2D.drawImage(getImage(playersDataBase.getEnemiesCarColors()[i]), carAngle, null);
        }

    }

}
