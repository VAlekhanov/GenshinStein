package org.example.game_package.control;

import org.example.game_package.main.Handler;
import org.example.game_package.main.ID;
import org.example.game_package.objects.GameObject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) handler.setUp(true);
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) handler.setLeft(true);
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) handler.setDown(true);
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) handler.setRight(true);

            }
        }
    }

    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) handler.setUp(false);
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) handler.setLeft(false);
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) handler.setDown(false);
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) handler.setRight(false);

            }
        }

    }
}
