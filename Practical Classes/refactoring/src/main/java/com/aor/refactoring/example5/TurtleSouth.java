package com.aor.refactoring.example5;

public class TurtleSouth implements TurtleState {
    private TurtleDirection direction;
    private Turtle turtle;

    public TurtleSouth(int row, int column) {
        this.turtle = new Turtle(row, column);
        this.direction = new TurtleDirection('S');
    }

    public void moveForward() {
        turtle.setRow(turtle.getRow()+1);
    }

    public TurtleWest rotateRight() {
        return new TurtleWest(turtle.getRow(), turtle.getColumn());
    }

    public TurtleEast rotateLeft() {
        return new TurtleEast(turtle.getRow(), turtle.getColumn());
    }
}
