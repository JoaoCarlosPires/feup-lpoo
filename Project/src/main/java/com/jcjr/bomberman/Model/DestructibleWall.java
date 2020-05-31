package com.jcjr.bomberman.Model;

import com.googlecode.lanterna.Symbols;

public class DestructibleWall extends GamePieceObj {

    public DestructibleWall(int x, int y) {
        super(x, y);
        this.icon = String.valueOf(Symbols.BLOCK_SPARSE);
        this.color = "#696969";
    }
}