package com.jcjr.bomberman.Model;

import com.jcjr.bomberman.View.GUI;

public class Explosion extends GamePieceObj {

        public Explosion(int x, int y) {
            super(x, y);
            this.icon = "$";
            this.color ="#FFA500";
        }

    @Override
    public void draw(GUI canvas) {
            String backcolor = canvas.getBackgroundColor();
            canvas.setBackgroundColor(color);
            canvas.drawColoredText(this.getX(), this.getY(), icon, color);
            canvas.setBackgroundColor(backcolor);
    }
}
