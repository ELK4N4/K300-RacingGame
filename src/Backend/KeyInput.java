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

        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN){

        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){

        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT){

        }

    }


    public void keyReleased(KeyEvent keyEvent) {

        if(keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_UP){

        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN){

        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){

        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT){

        }
    }
}
