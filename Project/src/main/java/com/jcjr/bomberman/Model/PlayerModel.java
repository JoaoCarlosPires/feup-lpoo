package com.jcjr.bomberman.Model;

public class PlayerModel extends GamePieceObj {

    protected int lifes;

    public PlayerModel(int x, int y, String color, int lifes) {
        super(x, y);
        this.icon = "M";
        this.color = color;
        this.lifes = lifes;
    }

    /**
     * return True if player is alive (lifes > 0)
     * @return how many lifes are left
     */
    public boolean isAlive() {
        return (lifes > 0);
    }

    /**
     * Decreases a life and return how many are left
     * @return how many lifes are left
     */
    public int decreaseLifes(){
        if(lifes > 0)
            lifes--;
        return lifes;
    }

    /**
     * @return how many lifes are left
     */
    public int increaseLifes(){
        return lifes++;
    }

    /**
     * @return how many lifes are left
     */
    public int getLifes(){return lifes;}


    /**
     * @param  explosionRadius  Radius of the explosion that should be created when the bomb explodes
     * @return Bomb object at Player Position with given explosionRadius
     */
    public Bomb dropBomb(int explosionRadius) {
        return new Bomb(this.getX(), this.getY(), explosionRadius);
    }
}
