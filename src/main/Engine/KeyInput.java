package main.Engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    /* * * * * * * * * * * * * * * * * * * * * * *
    * trackedKey Array Set-up and Helper Methods
    * * * * * * * * * * * * * * * * * * * * * * * */
    LinkedList<KeyTuple> keyList = new LinkedList<>();

    //Array to hold keys we want to keep track of. This will make things easier later.


    private void initKeys() {
        keyList.push(new KeyTuple("up", KeyEvent.VK_UP));
        keyList.push(new KeyTuple("down", KeyEvent.VK_DOWN));
        keyList.push(new KeyTuple("left", KeyEvent.VK_LEFT));
        keyList.push(new KeyTuple("right", KeyEvent.VK_RIGHT));
        keyList.push(new KeyTuple("escape", KeyEvent.VK_ESCAPE));
        keyList.push(new KeyTuple("tilde",KeyEvent.VK_DEAD_TILDE));
        keyList.push(new KeyTuple("control",KeyEvent.VK_CONTROL));
        keyList.push(new KeyTuple("r",KeyEvent.VK_R));
    }
    private KeyTuple getKey(String name) {
        for (var i = 0; i < keyList.size(); i++) {
            if (keyList.get(i).name == name)
                return keyList.get(i);
        }
        return KeyTuple.undefined;
    }
    private String getName(KeyEvent e) {
        for (var i = 0; i < keyList.size(); i++) {
            if (keyList.get(i).code == e.getKeyCode()) {
                return keyList.get(i).name;
            }
        }
        return "Undefined";
    }
    private boolean isHeld(KeyEvent e) {
        var code = e.getKeyCode();
        for (var i = 0; i < keyList.size(); i++) {
            if (keyList.get(i).code == code) {
                return keyList.get(i).held;
            }
        }
        return false; //If undefined it will not be held.
    }
    private boolean setHeld(KeyEvent e, boolean val) {
        var code = e.getKeyCode();
        for (var i = 0; i < keyList.size(); i++) {
            if (keyList.get(i).code == code) {
                keyList.get(i).held = val;
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
        for (var i = 0; i < keyList.size(); i++) {
            if (keyList.get(i).held) {
                for (var j = 0; j < handler.objList.size(); j++) {
                    var temp = handler.objList.get(j);
                    var name = keyList.get(i).name;
                    temp.KeyHeld(name);
                }
            }
        }
    }

    public void keyPressed (KeyEvent e) {

        var name = getName(e);
        for (var i = 0; i < handler.objList.size(); i++) {
            //Get game object.
            GameObject temp = handler.objList.get(i);
            //Execute event.
            if (!isHeld(e)) {
                //System.out.println("Executing KeyPressed in Game Obj id="+temp.id);
                temp.KeyPressed(name);
            }
            else {
                //System.out.println("Executing KeyHeld in Game Obj id="+temp.id);
                temp.KeyHeld(name);
            }
        }
        //Set held. It should be false when this is called the first time, as set in the release and init methods.
        setHeld(e, true);
    }

    public void keyReleased(KeyEvent e) {
        //Perform key event as long as the key is held. Stop on release.
        //This is where we call key released methods.
        var name = getName(e);
        if (isHeld(e)) {
            for (var i = 0; i < handler.objList.size(); i++) {
                GameObject temp = handler.objList.get(i);
                if (isHeld(e)) {
                    //System.out.println("Executing KeyHeld in Game Obj id="+temp.id);
                    temp.KeyHeld(name);
                }
                //System.out.println("Executing KeyReleased in Game Obj id="+temp.id);
                temp.KeyReleased(name);
            }
        }
        setHeld(e, false);
    }


}
