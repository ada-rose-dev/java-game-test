package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    /* * * * * * * * * * * * * * * * * * * * * * *
    * trackedKey Array Set-up and Helper Methods
    * * * * * * * * * * * * * * * * * * * * * * * */
    private int numTrackedKeys = 4;

    //Array to hold keys we want to keep track of. This will make things easier later.
    protected KeyTuple[] trackedKeys = new KeyTuple[numTrackedKeys];

    private void initKeys() {
        trackedKeys[0] = new KeyTuple("up", KeyEvent.VK_UP);
        trackedKeys[1] = new KeyTuple("down", KeyEvent.VK_DOWN);
        trackedKeys[2] = new KeyTuple("left", KeyEvent.VK_LEFT);
        trackedKeys[3] = new KeyTuple("right", KeyEvent.VK_RIGHT);
    }
    private KeyTuple getKey(String name) {
        for (var i = 0; i < trackedKeys.length; i++) {
            if (trackedKeys[i].name == name)
                return trackedKeys[i];
        }
        return KeyTuple.undefined;
    }
    private boolean isHeld(KeyEvent e) {
        var code = e.getKeyCode();
        for (var i = 0; i < trackedKeys.length; i++) {
            if (trackedKeys[i].code == code) {
                return trackedKeys[i].held;
            }
        }
        return false; //If undefined it will not be held.
    }
    private boolean setHeld(KeyEvent e, boolean val) {
        var code = e.getKeyCode();
        for (var i = 0; i < trackedKeys.length; i++) {
            if (trackedKeys[i].code == code) {
                trackedKeys[i].held = val;
                return true;
            }
        }
        return false;
    }

    /* * * * * * * *
    * Main Functions
    * * * * * * * * */

    public KeyInput(Handler handler) {
        this.handler = handler;
        initKeys();
    }

    public void tick() {
        for (var i = 0; i < trackedKeys.length; i++) {
            if (trackedKeys[i].held) {
                var e = trackedKeys[i].code;
                for (var j = 0; j < handler.objList.size(); j++) {
                    var temp = handler.objList.get(j);
                    temp.KeyHeld(e);
                }
            }
        }
    }

    public void keyPressed (KeyEvent e) {

        var code = e.getKeyCode();
        for (var i = 0; i < handler.objList.size(); i++) {
            //Get game object.
            GameObject temp = handler.objList.get(i);
            //Execute event.
            if (!isHeld(e)) {
                //System.out.println("Executing KeyPressed in Game Obj id="+temp.id);
                temp.KeyPressed(code);
            }
            else {
                //System.out.println("Executing KeyHeld in Game Obj id="+temp.id);
                temp.KeyHeld(code);
            }
        }
        //Set held. It should be false when this is called the first time, as set in the release and init methods.
        setHeld(e, true);
    }

    public void keyReleased(KeyEvent e) {
        //Perform key event as long as the key is held. Stop on release.
        //This is where we call key released methods.
        var code = e.getKeyCode();
        if (isHeld(e)) {
            for (var i = 0; i < handler.objList.size(); i++) {
                GameObject temp = handler.objList.get(i);
                if (isHeld(e)) {
                    //System.out.println("Executing KeyHeld in Game Obj id="+temp.id);
                    temp.KeyHeld(code);
                }
                //System.out.println("Executing KeyReleased in Game Obj id="+temp.id);
                temp.KeyReleased(code);
            }
        }
        setHeld(e, false);
    }


}
