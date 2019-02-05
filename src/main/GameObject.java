package main;

import java.awt.*;

public abstract class GameObject {

    protected int x,y;
    protected ID id;
    protected float xspd, yspd;

    public GameObject(int x,int y,ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick () ;

    public abstract void render(Graphics g);

    public abstract void KeyHeld(int e);
    public abstract void KeyPressed(int e);
    public abstract void KeyReleased(int e);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

}
