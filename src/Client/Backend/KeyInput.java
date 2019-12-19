package Client.Backend;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private keyLogic keyLogic;

    public KeyInput(keyLogic keyLogic) {
        this.keyLogic = keyLogic;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyLogic.setRight(true);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            keyLogic.setLeft(true);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            keyLogic.setUp(true);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            keyLogic.setDown(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyLogic.setRight(false);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            keyLogic.setLeft(false);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            keyLogic.setUp(false);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            keyLogic.setDown(false);
        }
    }
}
