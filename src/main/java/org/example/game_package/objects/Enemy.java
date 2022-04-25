package org.example.game_package.objects;

import org.example.game_package.MainConstants;
import org.example.game_package.main.Handler;
import org.example.game_package.main.ID;

import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject {

    Handler handler;
    private int xBound = 23;
    private int yBound = 23;
    private int enemyStep = MainConstants.enemyStep;
    Random random = new Random();
    int choose = 0;
    int hp = 100;

    public Enemy(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
        this.handler = handler;
    }

    public void tick() {
        x += velX;
        y += velY;

        if(choose == 0){
//            velX = random.nextInt((4 - -4)+4);
//            velY = random.nextInt((4 - -4)+4);
            velX = random.nextInt(2);
            velY = random.nextInt(2);
        }

        collision();

        choose = random.nextInt(10);

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
        g.setColor(Color.green);
        g.fillOval(x, y, xBound, yBound);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, xBound, yBound);
    }
}
