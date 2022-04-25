package org.example.game_package.objects;

import org.example.game_package.main.Handler;
import org.example.game_package.main.ID;

import java.awt.*;

public class Box extends GameObject {
    public Box(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x,y,32,32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
}
