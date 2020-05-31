package Controller;

import com.jcjr.bomberman.Controller.Score;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoreTest extends Score {

    @Test
    public void testScore() {
        initScore();
        assertEquals(0, getScore());
        inscreaseScore(10);
        assertEquals(10, getScore());
        decreaseScore(6);
        assertEquals(4, getScore());
        decreaseScore(6);
        assertEquals(0, getScore());
    }
}
