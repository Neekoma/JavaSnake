package com.nicholas.snake.game;

import com.nicholas.snake.GameFramework;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
* Класс, содержащий данные о змее
*/
public final class Snake{
    private int snakeSize; // Текущий размер змеи
    private int cellSize; // Размер одной клетки
    private int xBorder; // Граница по x (по ширине)
    private int yBorder; // Граница по y (по высоте)
    private SnakeDirection direction = SnakeDirection.toRight; // Текущая сторона направления змеи
    private ArrayList<SnakePart> snakeParts; // Список всех элементов змеи

    public boolean canChangeDirection;

    /**
     * @param startSize - кол-во элементов, с которыми змея начинает игру
     * @param field - сслыка на объект игрового поля, чтобы получить информацию о нем
     */
    public Snake(int startSize, GameField field){
        snakeSize = startSize;
        xBorder = field.getFieldWidth() - 2;
        yBorder = field.getFieldHeight() - 2;
        cellSize = field.getCellSize();
        snakeParts = new ArrayList<>();
        for(int i = 0; i < snakeSize; i++)
            snakeParts.add(new SnakePart(startSize * cellSize - i * cellSize, 48));
    }
    public enum SnakeDirection{
        toUp, toDown, toRight, toLeft
    }
    public void setDirection(SnakeDirection direction){this.direction = direction;}
    public SnakeDirection getDirection(){return direction;}
    public int getSnakeSize(){return snakeSize;}
    public void addSnakeSize(){
        snakeParts.add(new SnakePart(snakeParts.get(snakeSize - 1).x, snakeParts.get(snakeSize -1).y));
        snakeSize++;
    } // Добавить один элемент к хвосту
    public int getSnakePartPosX(int index){return snakeParts.get(index).x;}
    public int getSnakePartPosY(int index){return snakeParts.get(index).y;}
    public static Image getSnakeImage(){return new ImageIcon("resources\\snake.png").getImage();}

    public void move(){
        for(int i = snakeSize - 1; i > 0; i--){
            // Значение координат каждого элемента (не головы) принимает значение предыдущего элемента
            snakeParts.get(i).x = snakeParts.get(i - 1).x;
            snakeParts.get(i).y = snakeParts.get(i - 1).y;
        }
        switch (direction){
            case toUp:
                snakeParts.get(0).y -= cellSize;
                break;
            case toDown:
                snakeParts.get(0).y += cellSize;
                break;
            case toRight:
                snakeParts.get(0).x += cellSize;
                break;
            case toLeft:
                snakeParts.get(0).x -= cellSize;
                break;
        }
        checkFruit();
        checkBorder();
        checkTail();
    }

    /**
     * Если координаты нулевого элемента (головы змеи) совпадают с координатами фрукта, то
     * мы его кушаем (см. реализацию destroy() у Fruit)
     */
    private void checkFruit(){
        if(snakeParts.get(0).x == GameFramework.getFruit().x && snakeParts.get(0).y == GameFramework.getFruit().y)
            GameFramework.getFruit().destroy();
    }
    private void checkBorder(){
        int x = snakeParts.get(0).x / cellSize, y = snakeParts.get(0).y / cellSize;
        System.out.printf("x: %d border: %d\n y: %d border: %d\n", x, xBorder, y, yBorder);
        if(x >= xBorder || x < 0 || y >= yBorder || y < 0) {
            System.out.println("СТОЛКНОВЕНИЕ С ГРАНИЦЕЙ");
            GameFramework.stopGame();
        }
    }
    private void checkTail(){
        for(int i = 1; i < snakeSize; i++){
            if(snakeParts.get(0).x == snakeParts.get(i).x && snakeParts.get(0).y == snakeParts.get(i).y)
                GameFramework.stopGame();
        }
    }

    public class SnakePart extends GameObject {


        public SnakePart(){
            super();
        }
        public SnakePart(int x, int y){
            super(x, y);
        }

        /**
         * Если мы врезаемся, то игра останавливается
         */
        @Override
        public void destroy() {
            GameFramework.stopGame();
        }
    }

}
