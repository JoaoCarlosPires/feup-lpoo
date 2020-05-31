package Controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.jcjr.bomberman.Controller.GameController;
import com.jcjr.bomberman.Controller.GameStates;
import com.jcjr.bomberman.Controller.GeneralGameController;
import com.jcjr.bomberman.View.GUI;
import com.jcjr.bomberman.View.GameScreen;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class GameControllerTest {

    public class GameControllerTestClass extends GameController {
        public GameControllerTestClass() {
            super();
        }

        protected boolean getShowingMenu(){
            return ShowingMenu;
        }
    }


    private StubGui  canvas;
    private GameControllerTestClass ge;

    @Before
    public void init(){
        this.canvas = new StubGui();
        ge = new GameControllerTestClass();
    }

    @Test
    public void initTests() {
        assertTrue(ge.getShowingMenu());
    }

    @Test
    public void changeScreen() {
        ge.setGameState(GameStates.PLAYING);
        assertEquals(GameStates.PLAYING, ge.getGameState());
        assertTrue(ge.getShowingMenu());
        ge.setGameState(GameStates.SHOWMENU);
        assertTrue(ge.getShowingMenu());
    }








}
