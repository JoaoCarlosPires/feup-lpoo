package com.jcjr.bomberman.Model;

public class Bomb extends GamePieceObj {

    protected int explosionRadius;

    public Bomb(int x, int y, int explosionRadius) {
        super(x, y);
        this.icon = "@";
        this.color = "#FF6666";
        this.explosionRadius = explosionRadius;
    }

}
