package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GameObject {
public Player (int x, int y, ID id) {
        super(x,y,id);
        xspd = 1;
        yspd = 1;
    }

    @Override
    public void tick() {




    }
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x,y,16,16);
    }

    //This may be unnecessary.
    public boolean ListensForKey(KeyEvent e) {
        var key = e.getKeyCode();
        switch(key) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                return true;

            default: return false;
        }
    }

    //Separate this into multiple functions.
    public void ExecuteKeyEvent(KeyEvent e) {
        var key = e.getKeyCode();
        switch (key) {
            default: return;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                move(key); break;

        }
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
