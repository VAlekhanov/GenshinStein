package org.example.gamePackage.objects;

import org.example.gamePackage.ID;

import java.awt.*;

public class Triangle extends GameObject {

    private int xBound = 32;
    private int yBound = 32;

    public Triangle(int x, int y, ID id) {
        super(x, y, id, null);

    }

    public void tick() {
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(x, y, xBound, yBound);
//        g.drawPolygon(new Polygon());
    }

    public Rectangle getBounds() {
        return null;
    }
}
