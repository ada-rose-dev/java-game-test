package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GameObject {

    public Color col = Color.white;

    public Player (int x, int y, ID id) {
        super(x,y,id);
        xspd = 1;
        yspd = 1;
    }

    @Override
    public void tick() {




    }
    public void render(Graphics g) {
        g.setColor(col);
        g.fillRect(x,y,16,16);
    }

    /* * * * * * * * * * * * *
    * KEYBOARD METHODS
    * * * * * * * * * * * * */
    public void KeyHeld(KeyEvent e) {
        var key = e.getKeyCode();
        switch (key) {
            default: return;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                move(key); break;
        }
        col = Color.green;
    }
    public void KeyPressed(KeyEvent e) {
        col = Color.blue;
    }
    public void KeyReleased(KeyEvent e) {
        col = Color.yellow;
    }



    private void move(int key) {
        if (key == KeyEvent.VK_UP)
            y -= yspd;
        if (key == KeyEvent.VK_DOWN)
            y += yspd;
        if (key == KeyEvent.VK_LEFT)
            x -= xspd;
        if (key == KeyEvent.VK_RIGHT)
            x += xspd;


    }
}
