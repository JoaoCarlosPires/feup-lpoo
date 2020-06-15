package com.aor.refactoring.example5;

public class TurtleNorth implements TurtleState {
    private TurtleDirection direction;
    private Turtle turtle;

    public TurtleNorth(int row, int column) {
        this.turtle = new Turtle(row, column);
        this.direction = new TurtleDirection('N');
    }

    public void moveForward() {
        turtle.setRow(turtle.getRow()-1);
    }

    public TurtleEast rotateRight() {
        return new TurtleEast(turtle.getRow(), turtle.getColumn());
    }

    public TurtleWest rotateLeft() {
        return new TurtleWest(turtle.getRow(), turtle.getColumn());
    }
}
