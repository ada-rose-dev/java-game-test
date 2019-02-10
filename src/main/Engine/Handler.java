package main.Engine;

import java.awt.*;
import java.util.LinkedList;

//Handles objects in the room.
public class Handler {

    LinkedList<GameObject> objList = new LinkedList<>();

    //Loop through game objects, update them all.
    public void tick() {
        for (var i = 0; i < objList.size(); i++) {
            var tempObj = objList.get(i);
            tempObj.tick();
        }
    }

    //Loop through game objects, render them all.
    public void render(Graphics g) {
        for (var i = 0; i < objList.size(); i++) {
            var tempObj = objList.get(i);
            tempObj.render(g);
        }
    }

    //Add objects
    public void addObj(GameObject obj) {
        this.objList.add(obj);
    }
    public void rmvObj(GameObject obj) {
        this.objList.remove(obj);
    }

    //Handle rooms
    public void initRooms() {

    }

}
