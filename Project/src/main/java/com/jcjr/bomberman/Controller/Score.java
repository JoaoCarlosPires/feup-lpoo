package com.jcjr.bomberman.Controller;

public abstract class Score {

    public static int score;

    public void inscreaseScore(int amount ) {  score+= amount; }
    public void decreaseScore(int amount ) {  score=score-amount>0?score-amount:0; }


    public int getScore() {
        return score;
    }

    public void initScore() {
        score=0;
    }

}
