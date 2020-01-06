package Client.Backend;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private KeyTranslator keyTranslator;

    public KeyInput(KeyTranslator keyTranslator) {
        this.keyTranslator = keyTranslator;
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
            keyTranslator.setRight(true);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            keyTranslator.setLeft(true);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            keyTranslator.setUp(true);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            keyTranslator.setDown(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyTranslator.setRight(false);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            keyTranslator.setLeft(false);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            keyTranslator.setUp(false);
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            keyTranslator.setDown(false);
        }
    }
}
