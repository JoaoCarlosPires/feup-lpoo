package com.jcjr.bomberman.Controller;

public class Tick extends Subject {

    protected int ticks;

    public void increase() {
        ticks++;
        publish(ticks);
    }

    public void decrease() {
        ticks--;
        publish(ticks);
    }

    public int getTicks() {return ticks;}

    public Tick(int ticks) {
        this.ticks = ticks;
    }

    @Override
    public void publish(int ticks) {
        super.publish(ticks);
    }
}
