package com.jcjr.bomberman.Controller;

import com.jcjr.bomberman.Model.Bomb;

public class BombsObserver extends Bomb implements Observer {

    protected int currentTick = 0;
    protected int explosionTick;

    public BombsObserver(int x, int y , int explosionRadius , int explosionTick) {
        super(x, y, explosionRadius );
        this.explosionTick = explosionTick;
    }

    public boolean explosion(){
        return (currentTick >= explosionTick);
    }

    public void update(int ticks) {
      currentTick = ticks;
    }

}
