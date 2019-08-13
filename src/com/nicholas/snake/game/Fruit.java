package com.nicholas.snake.game;

import com.nicholas.snake.GameFramework;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Fruit extends GameObject{
    private static Image fruitImage = new ImageIcon("resources\\fruit.png").getImage();
    private int cellSize;
    private int widthBound, heightBound;
    public Fruit(GameField field){
        cellSize = field.getCellSize();
        widthBound = field.getFieldWidth();
        heightBound = field.getFieldHeight();
        x = new Random().nextInt(widthBound) * cellSize;
        y = new Random().nextInt(heightBound) * cellSize;
    }
    public Fruit(GameField field, int x, int y){
        cellSize = field.getCellSize();
        widthBound = field.getFieldWidth();
        heightBound = field.getFieldHeight();
        super.x = x * cellSize;
        super.y = y * cellSize;
    }
    public static Image getFruitImage(){return fruitImage;}
    /**
     * Когда фрукт съедается ("уничтожается"), его координаты просто меняют своё значение.
     * Змея прибавляет в размерах
     */
    @Override
    public void destroy() {
        GameFramework.getSnake().addSnakeSize();
        x = new Random().nextInt(widthBound - 3) * cellSize;
        y = new Random().nextInt(heightBound - 3) * cellSize;
    }
}
