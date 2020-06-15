package com.aor.refactoring.example5;

public class TurtleWest implements TurtleState{
    private TurtleDirection direction;
    private Turtle turtle;

    public TurtleWest(int row, int column) {
        this.turtle = new Turtle(row, column);
        this.direction = new TurtleDirection('W');
    }

    public void moveForward() {
        turtle.setColumn(turtle.getColumn()-1);
    }

    public TurtleNorth rotateRight() {
        return new TurtleNorth(turtle.getRow(), turtle.getColumn());
    }

    public TurtleSouth rotateLeft() {
        return new TurtleSouth(turtle.getRow(), turtle.getColumn());
    }
}
