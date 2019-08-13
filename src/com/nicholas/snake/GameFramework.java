package com.nicholas.snake;

import com.nicholas.snake.game.Fruit;
import com.nicholas.snake.game.GameField;
import com.nicholas.snake.game.Snake;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameFramework implements ActionListener {
    private Application application;
    private static Snake snake;
    private static Fruit fruit;
    private static GameField gameField;

    private static boolean gameActive = true;

    private int snakeStartSize = 3;
    private int screenUpdateDelay = 250; //millis


    private Timer timer;

    public GameFramework(Application app){
        application = app;
        initGame();
    }

    private void initGame() {
        gameField = new GameField(application);
        snake = new Snake(snakeStartSize, gameField);
        fruit = new Fruit(gameField, 25,3);
        timer = new Timer(screenUpdateDelay, this);
        timer.start();
    }

    public static GameField getGameField(){return gameField;}
    public static Fruit getFruit(){return fruit;}
    public static Snake getSnake(){return snake;}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameActive){
            snake.move();
            System.out.printf("x: %d y: %d\n\n", fruit.x, fruit.y);
            gameField.repaint();
            snake.canChangeDirection = true;
        }
    }
    public static void changeSnakeDirection(int keyCode){
        if(snake.canChangeDirection) {
            if (keyCode == KeyEvent.VK_UP) {
                switch (snake.getDirection()) {
                    case toLeft:
                        snake.setDirection(Snake.SnakeDirection.toUp);
                        break;
                    case toRight:
                        snake.setDirection(Snake.SnakeDirection.toUp);
                        break;
                }
            }
            if (keyCode == KeyEvent.VK_DOWN) {
                switch (snake.getDirection()) {
                    case toLeft:
                        snake.setDirection(Snake.SnakeDirection.toDown);
                        break;
                    case toRight:
                        snake.setDirection(Snake.SnakeDirection.toDown);
                        break;
                }
            }
            if (keyCode == KeyEvent.VK_RIGHT) {
                switch (snake.getDirection()) {
                    case toUp:
                        snake.setDirection(Snake.SnakeDirection.toRight);
                        break;
                    case toDown:
                        snake.setDirection(Snake.SnakeDirection.toRight);
                }
            }
            if (keyCode == KeyEvent.VK_LEFT) {
                switch (snake.getDirection()) {
                    case toUp:
                        snake.setDirection(Snake.SnakeDirection.toLeft);
                        break;
                    case toDown:
                        snake.setDirection(Snake.SnakeDirection.toLeft);
                }
            }
            snake.canChangeDirection = false;
        }
    }

    public static void stopGame(){
           gameActive = false;
    }
}
