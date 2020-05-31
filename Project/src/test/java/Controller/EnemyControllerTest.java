package Controller;

import com.jcjr.bomberman.Controller.EnemyController;
import com.jcjr.bomberman.Controller.GameEngine;
import com.jcjr.bomberman.Model.Enemy;
import com.jcjr.bomberman.Model.GamePieceCreator;
import com.jcjr.bomberman.Model.GamePieceFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class EnemyControllerTest extends GameEngine {

    @Test
    public void testEnemyGenerator()  {
        EnemyController testingController = new EnemyController();
        GamePieceFactory testingFactory = new GamePieceCreator();
        List<Enemy> testingEnemies = testingController.generateEnemies(7, testingFactory);
        assertEquals(16, testingEnemies.size());
        testingEnemies = testingController.generateEnemies(8, testingFactory);
        assertEquals(17, testingEnemies.size());
        testingEnemies = testingController.generateEnemies(1, testingFactory);
        assertEquals(10, testingEnemies.size());
        testingEnemies = testingController.generateEnemies(3, testingFactory);
        assertEquals(12, testingEnemies.size());
    }
}
