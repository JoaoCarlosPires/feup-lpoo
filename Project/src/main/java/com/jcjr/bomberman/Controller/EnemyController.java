package com.jcjr.bomberman.Controller;

import com.jcjr.bomberman.Model.Enemy;
import com.jcjr.bomberman.Model.Position;
import com.jcjr.bomberman.Model.GamePieceFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyController extends GameEngine {

    public List<Enemy> generateEnemies(int level, GamePieceFactory ge) {
        List<Enemy> enemies = new ArrayList<>();
        Random rand = new Random();
        int randomY = 0, randomX = 0;
        for (int i = 1; i <= 9+level; i++) {
            randomY = rand.nextInt(33) + 6;
            randomX = (rand.nextInt(57)) + 8;
            Position pos = new Position(randomX,randomY);
            if(!(isPositionOverlapping(pos)))
                enemies.add((Enemy) ge.createPiece(randomX, randomY, "Enemy"));
            else
                --i;
        }
        return enemies;
    }




}
