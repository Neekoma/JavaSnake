package com.nicholas.snake.game;

import com.nicholas.snake.GameFramework;

import javax.swing.*;
import java.awt.*;

/**
 * Класс реализует отрисовку поля
 */
public class GameField extends JPanel {
    // Размеры
    private final int CELL_SIZE = 16; // Размер одной клетки равен 16х16 px
    private final int FIELD_WIDTH; // Ширина поля (кратно CELL_SIZE)
    private final int FIELD_HEIGHT; // Высота поля (кратно (CELL_SIZE)

    /**
     * @param frame - ссылка на главное окно программы.
     *              Из frame берется ширина и высота самого окна,
     *              чтобы применить их к игровому полю
     */
    public GameField(JFrame frame){
        setBackground(Color.BLACK);
        FIELD_WIDTH = frame.getWidth() / CELL_SIZE; // 480 / 16 = 30
        FIELD_HEIGHT = frame.getHeight() / CELL_SIZE; // 480 / 16 = 30
    }
    public int getCellSize(){return CELL_SIZE;}
    public int getFieldWidth(){return FIELD_WIDTH;}
    public int getFieldHeight(){return FIELD_HEIGHT;}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Fruit.getFruitImage(), GameFramework.getFruit().x, GameFramework.getFruit().y, this);
        for(int i = 0; i < GameFramework.getSnake().getSnakeSize(); i++)
            g.drawImage(Snake.getSnakeImage(), GameFramework.getSnake().getSnakePartPosX(i), GameFramework.getSnake().getSnakePartPosY(i), this);
    }
}