package org.example.game_package.objects;

import org.example.game_package.ID;

import java.awt.*;

public class Box extends GameObject {

    private int xBound = 32;
    private int yBound = 32;


    public Box(int x, int y, ID id) {
        super(x, y, id, null);

    }

    public void tick() {
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, xBound, yBound);
    }

    public Rectangle getBounds() {
        return null;
    }
}
