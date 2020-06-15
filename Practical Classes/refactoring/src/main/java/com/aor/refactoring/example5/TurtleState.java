package com.aor.refactoring.example5;

public interface TurtleState {

    void moveForward();
    TurtleState rotateRight();
    TurtleState rotateLeft();
}
