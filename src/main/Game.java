package main;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static final long serialVersionUID = 42L;

    public static final int WIDTH = 640, HEIGHT = WIDTH * 9 / 16;

    private Thread thread;
    private boolean running = false;

    public Game() {
        new Window(WIDTH,HEIGHT,"This is a game!!", this);
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

        thread = new Thread(this);
        thread.start();

    }


    public void run() {
        /*
        * Set up game loop
         */
        long LastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        /*
        * GAME LOOP
         */
        while (running) {
            long now = System.nanoTime();
            delta += (now - LastTime) / ns;
            LastTime = now;
            while (delta >= 1) {
                tick();
                delta --;
            }
            if (running) {
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //Draw background
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        g.dispose();
        bs.show();
    }

    public static void main(String args[]) {
        new Game();
    }
}
