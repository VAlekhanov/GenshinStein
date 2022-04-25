package org.example.game_package.main;

import org.example.game_package.MainConstants;
import org.example.game_package.control.KeyInput;
import org.example.game_package.control.MouseInput;
import org.example.game_package.objects.*;
import org.example.windows_and_frames.WindowGame;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game extends Canvas implements Runnable {
    private Thread thread;
    public boolean running = false;
    private Handler handler;
    private BufferedImage level = null;
    private Camera camera;
    private int imageWidth;
    private int imageHeight;

    public Game() throws IOException {
        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage(MainConstants.path);
        imageWidth = level.getWidth();
        imageHeight = level.getHeight();
        new WindowGame(MainConstants.width, MainConstants.height, MainConstants.title, this);
//        new WindowGame(imageWidth, imageHeight, MainConstants.title, this);
        start();

        handler = new Handler();
        camera = new Camera(0, 0,MainConstants.width,MainConstants.height);
//        camera = new Camera(0, 0,imageWidth,imageHeight);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput(handler, camera));

//        String path = "D:"+File.separator+"Projects"+File.separator+"IdeaProjects"+File.separator+"GenshinStein"+File.separator+"resources_game"+File.separator+"maps"+File.separator+"sprite.png";
        if (new File(MainConstants.path).exists()) {
//            level = loader.loadImage(path);
            loadLevel(level);
        } else {
            handler.addObject(new Player(300, 300, ID.Player, handler));
            handler.addObject(new Enemy(300, 100, ID.Enemy, handler));
            handler.addObject(new Box(100, 100, ID.Block, handler));
            handler.addObject(new Triangle(500, 100, ID.Block));
        }


    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
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
                try {
                    render();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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

        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.Player) {
                camera.tick(handler.objects.get(i));
            }
        }
        handler.tick();
    }

    private void render() throws InterruptedException {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(MainConstants.countOfBuffers);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D graphics2D = (Graphics2D) g;
        //////////////

        g.setColor(Color.gray);
        g.fillRect(0, 0, MainConstants.width, MainConstants.height);

        graphics2D.translate(-camera.getX(), -camera.getY());

        handler.render(g);

        graphics2D.translate(camera.getX(), camera.getY());
        Thread.sleep(MainConstants.threadSleepTime);

        //////////////
        g.dispose();
        bs.show();
    }

    public void loadLevel(BufferedImage image) {
        imageWidth = image.getWidth();
        imageHeight = image.getHeight();
        int consta = 2;

        for (int xx = 0; xx < imageWidth; xx++) {
            for (int yy = 0; yy < imageHeight; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255) {
                    handler.addObject(new Block(xx * consta, yy * consta, ID.Block, handler));
                }
                if (blue == 255) {
                    handler.addObject(new Player(xx * consta, yy * consta, ID.Player, handler));
                }
                if (green == 255) {
                    handler.addObject(new Box(xx * consta, yy * consta, ID.Box, handler));
                }
                if (red == 155) {
                    handler.addObject(new Enemy(xx * consta, yy * consta, ID.Enemy, handler));
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
