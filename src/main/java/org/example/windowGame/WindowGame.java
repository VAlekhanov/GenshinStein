package org.example.windowGame;

import org.example.gamePackage.Game;

import javax.swing.*;
import java.awt.*;

public class WindowGame {
    public WindowGame(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);
//        frame.setContentPane();
        frame.setPreferredSize(new Dimension(width,height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
