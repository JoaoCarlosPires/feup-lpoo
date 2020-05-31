package Controller;

import com.jcjr.bomberman.Controller.GameEngine;
import com.jcjr.bomberman.Controller.GameStates;
import com.jcjr.bomberman.Controller.GeneralGameController;
import com.jcjr.bomberman.Controller.MenuEngine;
import com.jcjr.bomberman.View.GUI;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class MenuEngineTest {

    public static class MenuEngineTestClass extends GameEngine
    {
        public MenuEngineTestClass(GeneralGameController gcc, GUI canvas) {
            super(gcc, canvas);
        }
        public GameStates getCurrentState()
        {return currentState;}
    }


    MenuEngine m1;
    StubGameController gcc;
    GUI gui;


    @Before
    public void init()
    {
        gcc = new StubGameController();
        gui = new StubGui();
        m1 = new MenuEngine(gcc,gui);

    }

    @Test
    public void processInput() throws IOException {
        MenuEngineTestClass m2 = new MenuEngineTestClass(gcc,gui);
        assertTrue(m2.getCurrentState().equals(GameStates.PLAYING));
        m2.processInput(gui.readInput());
        assertTrue(m2.getCurrentState().equals(GameStates.PAUSE));

    }

}
