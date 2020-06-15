package com.aor.refactoring.example5;

public class TurtleEast implements TurtleState {

    private TurtleDirection direction;
    private Turtle turtle;

    public TurtleEast(int row, int column) {
        this.turtle = new Turtle(row, column);
        this.direction = new TurtleDirection('E');
    }

    public void moveForward() {
        turtle.setColumn(turtle.getColumn()+1);
    }

    public TurtleSouth rotateRight() {
       return new TurtleSouth(turtle.getRow(), turtle.getColumn());
    }

    public TurtleNorth rotateLeft() {
        return new TurtleNorth(turtle.getRow(), turtle.getColumn());
    }
}
