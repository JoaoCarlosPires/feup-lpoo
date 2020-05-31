package Controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.jcjr.bomberman.Controller.GameEngine;

import com.jcjr.bomberman.Controller.GameStates;
import com.jcjr.bomberman.Controller.GeneralGameController;
import com.jcjr.bomberman.Model.Position;
import com.jcjr.bomberman.View.GUI;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameEngineTest {

    public static class GameEngineTestClass extends GameEngine
    {
        public GameEngineTestClass(GeneralGameController gcc, GUI canvas) {
            super(gcc, canvas);
        }
        public GameStates getCurrentState()
        {return this.currentState;}
    }


    GameEngine g1;
    StubGameController gcc;
    GUI gui;


    @Before
    public void init(){
      gcc = new StubGameController();
      gui = new StubGui();
      g1 = new GameEngineTestClass(gcc, gui);
    }


    @Test
    public void testOverPosAndAddElement() {
        Position testingPos = new Position(7, 5);
        g1.getElement('W', 7, 5);
        assertEquals(true, g1.isPositionOverlapping(testingPos));
        testingPos.setY(testingPos.getY()+1);
        testingPos.setX(testingPos.getX()+1);
        assertEquals(false, g1.isPositionOverlapping(testingPos));
    }


    // neste é necessário o getCurrentState
    @Test
    public void testKeyStrokes() throws IOException {
        GameEngineTestClass g2 = new GameEngineTestClass(gcc,gui);
        g2.setState(GameStates.PLAYING);
        assertEquals(g2.getCurrentState(), GameStates.PLAYING);
        g2.processInput(gui.readInput());
        assertEquals(g2.getCurrentState(), GameStates.PAUSE);
        g2.processInput(gui.readInput());
        assertEquals(g2.getCurrentState(), GameStates.PLAYING);
    }

}
