package org.example.game_package.main;

import org.example.game_package.MainConstants;
import org.example.game_package.objects.GameObject;

public class Camera {

    private float x, y;
    private static final int width = MainConstants.width;
    private static final int height = MainConstants.height;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject object) {

        x += (((object.getX() - x) - 580 / 2) * 0.0035f);
        y += (((object.getY() - y) - 370 / 2) * 0.0035f);

        if (x <= 0) x = 0;
        if (x >= width) x = width;
        if (y <= 0) y = 0;
        if (y >= height) y = height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
