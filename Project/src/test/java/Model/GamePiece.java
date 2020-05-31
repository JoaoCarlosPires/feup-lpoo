package Model;

import com.jcjr.bomberman.Model.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// classe que extende "diretamente" da GamePieceObj, que é generalização das outras peças todas
class NonAbstractGamePiece extends GamePieceObj
{
    public NonAbstractGamePiece(int x, int y) {
        super(x, y);
    }
}

public class GamePiece {

    private PlayerModel p1;
    private NonAbstractGamePiece model1;

    @Before
    public void setUp() {
        p1 = new PlayerModel(20, 30, "#FFFFFF", 3);
        model1 = new NonAbstractGamePiece(10,10);
    }

    @Test
    public void InitialPosition() {
        assertEquals(20, p1.getX());
        assertEquals(30, p1.getY());
    }

     /// Moving Piece
    @Test
    public void SetPosition() {
        p1.setPosition(new Position(149, 49));
        assertEquals(149, p1.getX());
        assertEquals(49, p1.getY());
    }

    @Test
    public void MovePositionY() {
        Position p2 =  new Position(10,10);
        assertEquals(model1.getPosition(), p2);
        model1.moveDown();
        model1.moveDown();
        model1.moveDown();
        assertTrue((model1.getY() == 13));
        model1.moveUp();
        model1.moveUp();
        model1.moveUp();
        model1.moveUp();
        assertTrue((model1.getY() == 9));
        model1.moveDown();
        assertFalse((model1.getY() == 9));
    }

    @Test
    public void MovePositionX() {
        Position p4 =  new Position(10,10);
        assertEquals(model1.getPosition(), p4);
        model1.moveRight();
        model1.moveRight();
        model1.moveRight();
        assertEquals(true,(model1.getX() == 13) );
        model1.moveLeft();
        model1.moveLeft();
        model1.moveLeft();
        model1.moveLeft();
        assertEquals(true,(model1.getX() == 9) );
        model1.moveRight();
        assertEquals(false,(model1.getX() == 9 ) );

    }

    @Test
    public void MovePositionXY() {
        Position p2 =  new Position(10,10);
        assertEquals(true,( model1.getPosition().equals(p2)) );
        model1.moveRight();
        model1.moveUp();
        model1.moveRight();
        assertEquals(true,(model1.getX() == 12 ) );
        assertEquals(true,(model1.getY() == 9 ) );
    }

    @Test
    public void SetGetPosition() {
        Position p2 =  new Position(10,10);
        assertEquals(true,( model1.getPosition().equals(p2)) );
        Position p3 = new Position(12,50);
        model1.setPosition(p3);
        assertEquals(true,  model1.getPosition().equals(p3));
    }



}
