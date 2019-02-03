package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    //Key press bools
    protected boolean upP;
    protected boolean downP;
    protected boolean leftP;
    protected boolean rightP;


    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed (KeyEvent e) {

        for (var i = 0; i < handler.objList.size(); i++) {
            GameObject temp = handler.objList.get(i);
            if (temp.ListensForKey(e)) {
                temp.ExecuteKeyEvent(e);
            }
        }

    }

    public void keyReleased(KeyEvent e) {
        for (var i = 0; i < handler.objList.size(); i++) {
            GameObject temp = handler.objList.get(i);
            if (isPressed(e) && temp.ListensForKey(e)) {
                temp.ExecuteKeyEvent(e);
            }
        }

    }

    /*
    * Why use helper functions here?
    * Allows for easier keymapping later on. Can replace the KeyEvent's with variables later on.
     */

    public boolean isPressed(KeyEvent e) {
        var key = e.getKeyCode();
        switch (key) {
            default: return false;
            case (KeyEvent.VK_UP):
                return upP;
            case (KeyEvent.VK_DOWN):
                return downP;
            case (KeyEvent.VK_LEFT):
                return leftP;
            case (KeyEvent.VK_RIGHT):
                return rightP;

        }

    }
    private boolean setPressed(KeyEvent e, boolean val) {
        var key = e.getKeyCode();

        switch (key) {
            case (KeyEvent.VK_UP):
                upP = true;
                return true;
            case (KeyEvent.VK_DOWN):
                downP = true;
                return true;
            case (KeyEvent.VK_LEFT):
                leftP = true;
                return true;
            case (KeyEvent.VK_RIGHT):
                rightP = true;
                return true;

            default: return false;
        }

    }
}
