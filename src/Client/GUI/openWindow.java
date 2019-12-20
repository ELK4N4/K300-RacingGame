package Client.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class openWindow extends JPanel implements ActionListener{

    public static JFrame frame;
    private BufferedImage backgroundImg;
    private JButton button;
    private Icon iconPlay;


    public openWindow(){
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
        setLayout(new FlowLayout());
        button.addActionListener(button.getAction());
        button = new JButton(new ImageIcon("Images/playButton.png"));
        add(button);
    }

    public void actionPerformed(ActionEvent e) {
        button.addActionListener( button.getAction() );//?!!?!?!?11?!!?
    }





    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}

}
