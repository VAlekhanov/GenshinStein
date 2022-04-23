package org.example.gamePackage;

import org.example.gamePackage.control.KeyInput;
import org.example.gamePackage.objects.Box;
import org.example.gamePackage.objects.Player;
import org.example.gamePackage.objects.Triangle;
import org.example.windowGame.WindowGame;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    private static final String title = "TY SUPER LOX";
    private static final int width = 640;
    private static final int height = width / 12 * 9;
    private Thread thread;
    public boolean running = false;
    private Handler handler;

    public Game() throws InterruptedException {
        new WindowGame(width, height, title, this);
        start();

        handler = new Handler();

        this.addKeyListener(new KeyInput(handler));

        handler.addObject(new Player(300, 300, ID.Player, handler));

        handler.addObject(new Box(100, 100, ID.Block));
        handler.addObject(new Triangle(500, 100, ID.Block));

    }
//
//    public Game(GraphicsConfiguration config) {
//        super(config);
//
//    }

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
