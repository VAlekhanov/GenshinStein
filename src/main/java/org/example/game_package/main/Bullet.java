package org.example.game_package.main;

import org.example.game_package.MainConstants;
import org.example.game_package.objects.GameObject;

import java.awt.*;

public class Bullet extends GameObject {
    private Handler handler;
    private int mouseX;
    private int mouseY;

    public Bullet(int x, int y, ID id, Handler handler, int mouseX, int mouseY) {
        super(x, y, id, handler);
        this.handler = handler;

        velX = (mouseX - x) / 50;
        velY = (mouseY - y) / 50;
    }

    public void tick() {
        x += velX;
        y += velY;

        collision();
        outOfBounds();
    }

    public void collision() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject object = handler.objects.get(i);
            if (object.getId() == ID.Box) {
                if (getBounds().intersects(object.getBounds())) {
                    handler.removeObject(object);
                    handler.removeObject(this);
                }
            }
            if (object.getId() == ID.Block) {
                if (getBounds().intersects(object.getBounds())) {
                    handler.removeObject(this);
                }
            }
            if (object.getId() == ID.Enemy) {
                if (getBounds().intersects(object.getBounds())) {
                    handler.removeObject(object);
                    handler.removeObject(this);;
                }
            }
        }
    }

    public void outOfBounds() {
//        if (this.getX() >= MainConstants.width || this.getY() >= MainConstants.height || this.getX() <= 0 || this.getY() <= 0) {
//            handler.removeObject(this);
//        }
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(x, y, 1, 1);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 1, 1);
    }
}
