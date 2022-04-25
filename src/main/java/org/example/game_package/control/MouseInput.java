package org.example.game_package.control;

import com.sun.javafx.scene.CameraHelper;
import org.example.game_package.main.Bullet;
import org.example.game_package.main.Camera;
import org.example.game_package.main.Handler;
import org.example.game_package.main.ID;
import org.example.game_package.objects.GameObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Handler handler;
    private Camera camera;

    public MouseInput(Handler handler, Camera camera) {
        this.handler = handler;
        this.camera = camera;
    }

    public void mousePressed(MouseEvent e) {
        int mouseX = (int) (e.getX() + camera.getX());
        int mouseY = (int) (e.getY() + camera.getY());

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject object = handler.objects.get(i);

            if (object.getId() == ID.Player) {
                handler.addObject(new Bullet(object.getX()+16, object.getY()+16, ID.Bullet, handler, mouseX, mouseY));
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }
}
