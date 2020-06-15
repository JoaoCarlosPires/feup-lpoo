package com.aor.refactoring.example5;

public class Turtle {
    private int row;
    private int column;
    private TurtleState turtle;

    public Turtle(int row, int column) {
        this.row = row;
        this.column = column;
        this.turtle = new TurtleNorth(row, column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void execute(TurtleCommand command) {
        if (command.getCommand() == 'L') {
            this.turtle = turtle.rotateLeft();
        } else if (command.getCommand() == 'R') {
            this.turtle = turtle.rotateRight();
        } else if (command.getCommand() == 'F'){
            turtle.moveForward();
        }
    }


}
