package Client.GUI;

import Client.Backend.KeyTranslator;
import Client.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class openWindow extends JPanel implements ActionListener{

    public static JFrame frame;
    private BufferedImage backgroundImg;
    private JButton button;
    private Window window;
    private KeyTranslator keyTranslator;
    private Main main;

    public openWindow(Main main){
        this.main = main;
        frame = new JFrame();
        button  = new JButton("Play");
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                //main.startGame();
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        backgroundImg = getImage("Images/FirstWindowBackground.jpg");
        frame.setSize(1920,1080);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.toFront();
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

    private void IconButton(){
        frame.getContentPane().setLayout(new FlowLayout());

                add(button);
    }

    public void actionPerformed(ActionEvent e) {


    }




}
