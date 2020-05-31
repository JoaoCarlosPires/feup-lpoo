package com.jcjr.bomberman.Model;

import com.jcjr.bomberman.View.GUI;

public abstract class GamePieceObj implements GamePiece {

    private Position position;
    protected String icon;
    protected String color;

    public GamePieceObj(int x, int y) {
        position = new Position(x,y);
    }
    public int getX() { return this.position.getX(); }
    public void setX(int x) { this.position.setX(x); }
    public int getY() { return this.position.getY(); }
    public void setY(int y) { this.position.setY(y); }
    public String getIcon() { return icon; }

    public void moveUp() {
        this.position.setY(this.position.getY()-1);
    }
    public void moveDown() {
        this.position.setY(this.position.getY()+1);
    }
    public void moveLeft() {
        this.position.setX(this.position.getX()-1);
    }
    public void moveRight() { this.position.setX(this.position.getX()+1); }

    public void setPosition(Position pos) {
        this.position = pos;
    }
    public Position getPosition() {return this.position;}

    public void draw(GUI canvas) { canvas.drawColoredText(this.getX(), this.getY(), icon, color); }
    public String getColor() {  return color; }

}
