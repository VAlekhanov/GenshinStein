package org.example.windows_and_frames;

import org.example.game_package.Game;

import javax.swing.*;
import java.awt.*;

public class MiniMap {
    public MiniMap(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);
//        frame.setContentPane();
        frame.setPreferredSize(new Dimension(width,height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
//        frame.add(game); todo Сделать что-то, чтобы добавлялись все все объекты, которые необходимо отображать на карте
        frame.setVisible(true);
        game.start();
    }
}
