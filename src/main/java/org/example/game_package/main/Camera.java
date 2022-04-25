package org.example.game_package.main;

import org.example.game_package.MainConstants;
import org.example.game_package.objects.GameObject;

public class Camera {

    private float x, y;
    private static final int width = MainConstants.width;
    private static final int height = MainConstants.height;
    private int imageWidth, imageHeight;

    public Camera(float x, float y,int imageWidth,int imageHeight) {
        this.x = x;
        this.y = y;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public void tick(GameObject object) {

        x += (((object.getX() - x) - 580 / 2) * 0.0035f);
        y += (((object.getY() - y) - 370 / 2) * 0.0035f);

        if (x <= 0) x = 0;
        if (x >= imageWidth) x = width;
        if (y <= 0) y = 0;
        if (y >= imageHeight) y = height;
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
