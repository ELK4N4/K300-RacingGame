package Backend;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {



    public void keyTyped(KeyEvent keyEvent) {

    }


    public void keyPressed(KeyEvent keyEvent) {

        if(keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_UP){

            thread.setUp(true);
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN){

            thread.setDown(true);
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){

            thread.setRight(true);
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT){

            thread.setLeft(true);
        }

    }


    public void keyReleased(KeyEvent keyEvent) {

        if(keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_UP){

            thread.setUp(flase);
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN){

            thread.setDown(flase);

        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){

            thread.setRight(flase);

        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT){

            thread.setLeft(flase);

        }
    }
}
