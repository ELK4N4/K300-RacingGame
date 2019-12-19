package Client.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class openWindow extends JPanel {

    private static JFrame frame;
    private BufferedImage backgroundImg;
    private JButton button;
    private Icon iconPlay;

    public static void main(String [] args){

       openWindow startWindow = new openWindow();

    }

    public openWindow(){
        frame = new JFrame();
        backgroundImg = getImage("Images/FirstWindowBackground.jpg");
        frame.setSize(1920,1080);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button = new JButton(new ImageIcon("Images/playButton.png"));
        button.setBounds(0,0,10, 10);

        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.add(button);
        frame.toFront();
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        frame.setVisible(true);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImg, 0, 0, this);
    }

    private BufferedImage getImage(String imageName) {
        try {
            return ImageIO.read(getClass().getResource(imageName));
        } catch (IOException e) {
            throw new Error("no path found");
        }
    }


}
