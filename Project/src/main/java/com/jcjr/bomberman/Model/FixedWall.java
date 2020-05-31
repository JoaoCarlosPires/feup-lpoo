package com.jcjr.bomberman.Model;

import com.googlecode.lanterna.Symbols;

public class FixedWall extends GamePieceObj {

    public FixedWall(int x, int y) {
        super(x, y);
        this.icon = String.valueOf(Symbols.BLOCK_MIDDLE);
        this.color = "#d3d3d3";
    }


}