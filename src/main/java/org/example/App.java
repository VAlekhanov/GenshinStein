package org.example;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

import org.example.gamePackage.Game;

public class App {

    public static void main(String[] args) throws InterruptedException {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        new Game();
    }
}
