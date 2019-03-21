package main.Objects;

import main.Engine.*;
import main.Engine.Window;

import javax.swing.*;
import java.awt.*;

public class RoomEditor extends GameObject {

    //Init
    public boolean Active = true;
    private boolean ctrlHeld;
    RoomEditorCanvas rec = new RoomEditorCanvas();
    Window editorWin = new Window(300,600,"Room Editor", rec, JFrame.HIDE_ON_CLOSE);
    boolean windowOpen = Active;

    //Constructor
    public RoomEditor(Handler handler) {
        super(0,0,handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if (Active) {
            g.setColor(Color.red);
            g.drawString("RoomEditor Active", 2, 10);
            g.setColor(Color.white);
        }
        else {
            if (editorWin != null) {
                closeWindow();
            }
        }

    }

    @Override
    public void KeyHeld(String name) {
        switch (name) {
            default: return;
            case "control":
                ctrlHeld = true;
        }


    }

    @Override
    public void KeyPressed(String name) {
        switch(name) {
            default: return;
            case "r":
                if (ctrlHeld)
                    Active = !Active;
                    if (Active)
                        newWindow();
                    else
                        closeWindow();
                break;
            case "w":
                if (ctrlHeld && Active) {
                    if (windowOpen) {
                        closeWindow();
                    } else {
                        newWindow();
                    }
                }
                break;
        }

    }

    @Override
    public void KeyReleased(String name) {
        switch(name) {
            case "control":
                ctrlHeld = false;
        }

    }


    //Window
    private Window newWindow() {
        if (editorWin == null){
            editorWin = new Window(300,600,"Room Editor", rec, JFrame.HIDE_ON_CLOSE);
            System.out.println("Attempted to open a new window when window was open.");
        }
        windowOpen = true;
        return editorWin;
    }

    private boolean closeWindow() {
        if (editorWin == null) {
            System.out.println("Attempted to close editorWin when set to null.");
            return false;
        }
        editorWin.Close();
        editorWin = null;
        windowOpen = false;
        return true;
    }

}
