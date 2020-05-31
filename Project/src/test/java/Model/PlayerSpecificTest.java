package Model;

import com.jcjr.bomberman.Model.Bomb;
import com.jcjr.bomberman.Model.PlayerModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerSpecificTest {

    private PlayerModel p1;

    @Before
    public void setUp() {
        p1 = new PlayerModel(20, 30, "#FFFFFF", 3);
    }

    @Test
    public void playerPosition() {
        assertEquals(20, p1.getX());
        assertEquals(30, p1.getY());
    }

    @Test
    public void dropBomb() {
        Bomb newBomb = p1.dropBomb(4);
        assertEquals(20, newBomb.getX());
        assertEquals(30, newBomb.getY());
        p1.moveUp();
        p1.moveUp();
        p1.moveLeft();
        Bomb newBomb2 = p1.dropBomb(3);
        assertEquals(19, newBomb2.getX());
        assertEquals(28, newBomb2.getY());
    }

    @Test
    public void playerColor() {
        assertEquals("#FFFFFF", p1.getColor());
        PlayerModel p2 = new PlayerModel(50,20,"#F0F02C", 4);
        assertEquals("#F0F02C", p2.getColor());
    }

    @Test
    public void playerLifeStatus(){
        assertEquals(3,p1.getLifes());
        PlayerModel p2 = new PlayerModel(50,20,"#F0F02C", 4);
        p2.decreaseLifes();
        p2.decreaseLifes();
        assertEquals(2,p2.getLifes());
        assertEquals(true, p2.isAlive());
        p2.decreaseLifes();
        p2.decreaseLifes();
        assertEquals(0,p2.getLifes());
        assertEquals(false, p2.isAlive());
        p2.decreaseLifes();
        p2.decreaseLifes();
        p2.decreaseLifes();
        assertEquals(0,p2.getLifes());
        assertEquals(false, p2.isAlive());
        p2.increaseLifes();
        assertEquals(1,p2.getLifes());
        assertEquals(true, p2.isAlive());
    }


}
