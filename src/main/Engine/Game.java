package main.Engine;

import main.Objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static final long serialVersionUID = 42L;

    public static final int WIDTH = 640, HEIGHT = WIDTH * 9 / 16;
    public static final String gameTitle = "Ada makes a game engine.";

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private KeyInput keys;

    public Game() {
        //Handler before window to prevent null pointer
        handler = new Handler();
        keys = new KeyInput(handler);
        this.addKeyListener(keys);
        new Window(WIDTH, HEIGHT, gameTitle, this);
        handler.addObj(new Player(0,0, handler));
    }

    public static synchronized void end() {
        System.exit(1); //Status 1 lets us know the game was ended via code.
    }

    public synchronized void start() {

        thread = new Thread(this);
        thread.start();
        running = true;
        handler.initRooms();
    }

    public synchronized void stop() {

        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

        end();

        /*
        thread = new Thread(this);
        thread.start();
*/
    }


    public void run() {
        int ticksPerSec = 25; //Max framerate for game engine
        int skipTicks = 1000/ticksPerSec; //Ticks skipped per render frame
        int maxFrameSkip = 5; //Maximum skipped frames

        long nextGameTick = System.currentTimeMillis();
        int loops;
        float interpolation;

        //Game loop
        boolean running = true;
        while (running) {
            //Reset loops
            loops = 0;

            //Make sure you're processing game data as much as you need to before rendering,
            //but dont skip more than maxFrameSkip times until you render
            long ms = System.currentTimeMillis();
            while( ms > nextGameTick && loops < maxFrameSkip) {
                tick();

                nextGameTick += skipTicks;
                loops++;
                ms = System.currentTimeMillis();
            }

            //This should, ideally, balance out to 1
            //i.e. System.currentTimeMillis() should == nextGameTick
            //This is unlikely in heavier game loads, so we interpolate the graphics accordingly
            interpolation = (float)(System.currentTimeMillis() + skipTicks - nextGameTick) / (float)(skipTicks);
            System.out.println(interpolation);
            render(interpolation);



        }

        //End the game.
        stop();

/*
        */
/*
        * Set up game loop
         *//*

        long LastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        */
/*
        * GAME LOOP
         *//*

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
*/
    }

    private void tick() {
        handler.tick();
        keys.tick();
    }

    private void render(float interpolation) {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //Draw background
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        //Handler
        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String args[]) {
        new Game();
    }
}
