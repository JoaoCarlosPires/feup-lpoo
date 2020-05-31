package Controller;

import com.jcjr.bomberman.Controller.Tick;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TicksTest {

    @Test
    public void testTicks() {
        Tick testingTicks = new Tick(30);
        assertEquals(30, testingTicks.getTicks());
        testingTicks.increase();
        assertEquals(31, testingTicks.getTicks());
        testingTicks.decrease();
        testingTicks.decrease();
        assertEquals(29, testingTicks.getTicks());
    }
}
