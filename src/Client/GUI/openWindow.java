package Client.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class openWindow extends JPanel {

    private static JFrame frame;
    private BufferedImage backgroundImg;
    private JButton button;
    private Icon iconPlay;

    public static void main(String [] args){

    new openWindow();
        new Button();
    }

    public openWindow(){

        backgroundImg = getImage("Images/FirstWindowBackground.png");
        frame.setSize(1920,1080);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.toFront();
        frame.setVisible(true);
        button =new JButton(new ImageIcon("D:\\playB.jpg"));
        button.setBounds(100,100,100, 40);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


    }

    private BufferedImage getImage(String imageName) {
        try {
            return ImageIO.read(getClass().getResource(imageName));
        } catch (IOException e) {
            throw new Error("no path found");
        }
    }


}
