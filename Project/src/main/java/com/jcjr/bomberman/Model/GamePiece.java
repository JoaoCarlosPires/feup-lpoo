package com.jcjr.bomberman.Model;

import com.jcjr.bomberman.View.GUI;

public interface GamePiece {
    int getX();
    void setX(int x);

    int getY();
    void setY(int y);

    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();

    String getIcon();
    void setPosition(Position pos);
    String getColor();
    Position getPosition();

    void draw(GUI canvas);


}