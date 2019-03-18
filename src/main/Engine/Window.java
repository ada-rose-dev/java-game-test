package main.Engine;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    public static final long serialVersionUID = 42L;
    public Canvas canvas;
    public Window(int Width, int Height, String title, Canvas canvas) {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(Width, Height));
        frame.setMaximumSize(new Dimension(Width, Height));
        frame.setMinimumSize(new Dimension(Width, Height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(canvas);
        frame.setVisible(true);
        this.canvas = canvas;
    }

    public Window(int Width, int Height, String title, Canvas canvas, int closeOperation) {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(Width, Height));
        frame.setMaximumSize(new Dimension(Width, Height));
        frame.setMinimumSize(new Dimension(Width, Height));

        frame.setDefaultCloseOperation(closeOperation);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(canvas);
        frame.setVisible(true);
        this.canvas = canvas;
    }

}
