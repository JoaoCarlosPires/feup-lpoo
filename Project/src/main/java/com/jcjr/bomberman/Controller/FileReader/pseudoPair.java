package com.jcjr.bomberman.Controller.FileReader;

public class pseudoPair<K,V> {
    private K key;
    private V value;

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public pseudoPair(K element0, V element1) {
        this.key = element0;
        this.value = element1;
    }
}

