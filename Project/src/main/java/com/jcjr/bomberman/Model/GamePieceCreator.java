package com.jcjr.bomberman.Model;

public class GamePieceCreator implements GamePieceFactory {

    public GamePieceCreator() {}

    public GamePiece createPiece(int x, int y, String Description) {
        switch (Description) {
            case "Enemy":
                return createEnemy(x,y);
            case "DestructiveWall":
                return createDestructiveWall( x, y);
            case "FixedWall":
                return createFixedWall(x,y);
            case "Explosion":
                return createExplosion(x,y);

            //Power-Ups
            case "PUExplosionRadius":
                return createPUExplosionRadius(x,y);
            case "PUExtraBomb":
                return createPUExtraBomb(x,y);
            case "PUExtraLife":
                return createPUExtraLife(x,y);
            case "PUKey":
                return createPUKey(x,y);
            case "PUPortal":
                return createPUPortal(x,y);
            default:
                return null;
        }
    }


    public GamePiece createDestructiveWall(int x, int y) {
        return new DestructibleWall(x, y);
    }

    public GamePiece createFixedWall(int x, int y) {
        return new FixedWall(x, y);
    }

    public GamePiece createEnemy(int x, int y) {
        return new Enemy(x,y);
    }

    public GamePiece createExplosion(int x, int y) {
        return new Explosion(x,y);
    }

    public GamePiece createPUExtraBomb(int x, int y){
        return new PUExtraBomb(x,y);
    }

    public GamePiece createPUExplosionRadius(int x, int y){
        return new PUExplosionRadius(x,y);
    }

    public GamePiece createPUExtraLife(int x, int y){
        return new PUExtraLife(x,y);
    }

    public GamePiece createPUKey(int x, int y){
        return new Key(x,y);
    }

    public GamePiece createPUPortal(int x, int y){
        return new Portal(x,y);
    }


}
