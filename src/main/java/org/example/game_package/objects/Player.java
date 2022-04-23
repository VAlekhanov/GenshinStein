package org.example.game_package.objects;

import org.example.game_package.Handler;
import org.example.game_package.ID;

import java.awt.*;

public class Player extends GameObject {

    private int xBound = 23;
    private int yBound = 23;
    Handler handler;
    private int step = 3;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
        this.handler = handler;
    }

    public void tick() {
        x += velX;
        y += velY;

        if (handler.isUp()) {
            velY = -step;
        } else if (!handler.isDown()) {
            velY = 0;
        }

        if (handler.isDown()) {
            velY = step;
        } else if (!handler.isUp()) {
            velY = 0;
        }

        if (handler.isRight()) {
            velX = step;
        } else if (!handler.isLeft()) {
            velX = 0;
        }

        if (handler.isLeft()) {
            velX = -step;
        } else if (!handler.isRight()) {
            velX = 0;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, xBound, yBound);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,xBound,yBound);
    }
}