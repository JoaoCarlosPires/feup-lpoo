package com.jcjr.bomberman.Controller;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    protected List<Observer> observers = new ArrayList<>();

    public Subject() {}

    public void register(Observer observer) {
    observers.add(observer);
    }

    public void publish(int ticks){
        for(Observer obs : observers)
            obs.update(ticks);
    }


}