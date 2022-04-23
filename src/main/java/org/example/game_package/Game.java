package org.example.game_package;

import org.example.game_package.control.KeyInput;
import org.example.game_package.objects.Block;
import org.example.game_package.objects.Enemy;
import org.example.game_package.objects.Player;
import org.example.windows_and_frames.WindowGame;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game extends Canvas implements Runnable {
    private static final String title = "TY SUPER LOX";
    private static final int width = 640;
    private static final int height = width / 12 * 9;
    private Thread thread;
    public boolean running = false;
    private Handler handler;
    private BufferedImage level = null;

    public Game() throws InterruptedException, IOException {
        new WindowGame(width, height, title, this);
        start();

        handler = new Handler();

        this.addKeyListener(new KeyInput(handler));

        BufferedImageLoader loader = new BufferedImageLoader();
        String path = "../resources_game/maps/sprite.png";
        level = loader.loadImage(path);
        loadLevel(level);

//        handler.addObject(new Player(300, 300, ID.Player, handler));
//        handler.addObject(new Enemy(300, 100, ID.Enemy, handler));
//        handler.addObject(new Box(100, 100, ID.Block));
//        handler.addObject(new Triangle(500, 100, ID.Block));

    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 120.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
    }

    private void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bufferStrategy.getDrawGraphics();
        //////////////

        g.setColor(Color.gray);
        g.fillRect(0, 0, width, height);

        handler.render(g);

        //////////////
        g.dispose();
        bufferStrategy.show();
    }

    public void loadLevel(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 255){
                    handler.addObject(new Block(xx*32,yy*32,ID.Block, handler));
                }
                if(blue == 255){
                    handler.addObject(new Player(xx*32,yy*32,ID.Block, handler));
                }
            }
        }
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
