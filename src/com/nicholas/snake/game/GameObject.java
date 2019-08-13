package com.nicholas.snake.game;

public abstract class GameObject {
    public int x;
    public int y;

    public GameObject(){
        x = 0; y = 0;
    }
    public GameObject(int argX, int argY){x = argX; y = argY;}
    public abstract void destroy();
}
