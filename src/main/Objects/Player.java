package main.Objects;

import main.Engine.Game;
import main.Engine.GameObject;
import main.Engine.Handler;

import java.awt.*;

public class Player extends GameObject {

    /* * * * *
    * INIT
    * * * * * */
    public Color col = Color.white;
    public float xspd, yspd;

    public Player (int x, int y, Handler handler) {
        super(x,y,handler);
        xspd = 1f;
        yspd = 1f;
    }

    /* * * * * * * * *
    * TICK !!!!
    * * * * * * * * * */
    public void tick() {

    }
    /* * * * * * * *
    * RENDER !!!!
    * * * * * * * */
    public void render(Graphics g) {
        g.setColor(col);
        g.fillRect(x,y,16,16);
    }

    /* * * * * * * * * * * * *
    * KEYBOARD ROUTERS !!!!!
    * * * * * * * * * * * * */
    public void KeyHeld(String name) {
        switch (name) {
            //Do nothing on default
            default: return;
            //Movement - extracted code so we can make it more complicated later on.
            case"left": case "right": case"up": case"down":
                move(name); break;
            //End game
            case "escape":
                Game.end(); break;
        }
    }
    public void KeyPressed(String name) {
    }
    public void KeyReleased(String name) {
    }

    /* * * * * * * * * * *
    * HELPERS
    * * * * * * * * * * */
    private void move(String key) {
        switch (key) {
            case "up":
                y -= yspd; break;
            case "down":
                y += yspd; break;
            case "left":
                x -= xspd; break;
            case "right":
                x += xspd; break;
        }
    }
}
