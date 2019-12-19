package Client.Backend;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private keyLogic keyThread;

    public KeyInput(keyLogic keyThread) {
        this.keyThread = keyThread;
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
            keyThread.setRight(true);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            keyThread.setLeft(true);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            keyThread.setUp(true);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            keyThread.setDown(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyThread.setRight(false);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            keyThread.setLeft(false);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            keyThread.setUp(false);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            keyThread.setDown(false);
        }
    }
}
