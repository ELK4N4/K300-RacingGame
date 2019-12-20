package Client.GUI;

import BackandForth.Message;
import Client.Backend.BackAndForth;
import Client.Backend.KeyInput;
import Client.Backend.keyLogic;
import Client.Main;

import javax.imageio.ImageIO;
import javax.management.MBeanAttributeInfo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class openWindow extends JPanel implements ActionListener{

    public static JFrame frame;
    private BufferedImage backgroundImg;
    private JButton button;
    private Window window;
    private keyLogic keyLogic;
    private Main main;

    public openWindow(Main main){
        frame = new JFrame();
        backgroundImg = getImage("Images/FirstWindowBackground.jpg");
        frame.setSize(1920,1080);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.setBounds(0,0,10, 10);
        frame.add(button);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.toFront();
        frame.pack();//
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

    private void IconButton(){
        frame.getContentPane().setLayout(new FlowLayout());
        button  = new JButton("Play");
        button.addActionListener(new ActionListener() {
                                     @Override
                                     public void actionPerformed(ActionEvent actionEvent) {
                                         main.startGame();
                                     }
                                 });
                add(button);
    }

    public void actionPerformed(ActionEvent e) {

        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // display/center the jdialog when the button is pressed



            }
        });

    }




}
