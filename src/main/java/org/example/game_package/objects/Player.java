package org.example.game_package.objects;

import org.example.game_package.MainConstants;
import org.example.game_package.main.Handler;
import org.example.game_package.main.ID;

import java.awt.*;

public class Player extends GameObject {

    Handler handler;
    private int xBound = 23;
    private int yBound = 23;
    private int playerStep = MainConstants.playerStep;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
        this.handler = handler;
    }

    public void tick() {
        x += velX;
        y += velY;

        collision();

        if (handler.isUp()) {
            velY = -playerStep;
        } else if (!handler.isDown()) {
            velY = 0;
        }

        if (handler.isDown()) {
            velY = playerStep;
        } else if (!handler.isUp()) {
            velY = 0;
        }

        if (handler.isRight()) {
            velX = playerStep;
        } else if (!handler.isLeft()) {
            velX = 0;
        }

        if (handler.isLeft()) {
            velX = -playerStep;
        } else if (!handler.isRight()) {
            velX = 0;
        }
    }

    public void collision() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject object = handler.objects.get(i);
            if(object.getId() ==  ID.Block){
                if(getBounds().intersects(object.getBounds())){
                    x += velX - 1;
                    y += velY - 1;

                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect(x, y, xBound, yBound);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, xBound, yBound);
    }
}
