package main;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    public static final long serialVersionUID = 42L;

    public Window(int Width, int Height, String title, Game game) {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(Width, Height));
        frame.setMaximumSize(new Dimension(Width, Height));
        frame.setMinimumSize(new Dimension(Width, Height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }

}
