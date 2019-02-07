package main;

import java.awt.*;

public abstract class GameObject {

    protected int x,y;
    protected int id;

    public GameObject(int x,int y,Handler handler) {
        this.x = x;
        this.y = y;
        this.id = handler.objList.size();
    }

    public abstract void tick () ;

    public abstract void render(Graphics g);

    public abstract void KeyHeld(String name);
    public abstract void KeyPressed(String name);
    public abstract void KeyReleased(String name);

}
