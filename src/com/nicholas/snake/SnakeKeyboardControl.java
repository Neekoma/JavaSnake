package com.nicholas.snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeKeyboardControl extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        GameFramework.changeSnakeDirection(e.getKeyCode());
    }
}
