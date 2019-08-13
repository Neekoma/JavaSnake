package com.nicholas.snake;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Класс главного окна во всей программе. В нем (окне) отображается вся игра
 */
public class Application extends JFrame {

    private final int WIDTH = 480, HEIGHT = 480;

    private GameFramework framework;

    /**
     * Отсюда программа начинает свою работу
     * @param args - стандартные системные аргументы, необходимые для запуска программы
     */
    public static void main(String[] args) {
        Application app = new Application("Snake 228");
    }
    // Конструктор приватный, так как не планируется создавать экземпляр Application где-либо ещё

    /**
     * @param title - заголовок окна программы
     */
    private Application(String title){
        setTitle(title); // Установить заголовок окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Закрытие программы при нажатии на стрелочку
        setSize(WIDTH, HEIGHT); // Установка размеров окна
        setLocationRelativeTo(null); // Выравнивание по центру
        setResizable(false); // Запрет на изменение размеров
        framework = new GameFramework(this); // Создание фреймворка (каркаса) игры
        add(GameFramework.getGameField()); // Добавить в окно игровое поле
        addKeyListener(new SnakeKeyboardControl());
        setVisible(true); // Окно становится видимым
    }
}
