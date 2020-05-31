package Controller;

import com.jcjr.bomberman.Controller.Observer;
import com.jcjr.bomberman.Controller.Tick;
import com.jcjr.bomberman.Controller.BombsObserver;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BombsObserverTest {
public class TicksTest extends Tick{
    public TicksTest(int ticks) {
        super(ticks);
    }
    public List<Observer> getSubscribers(){return this.observers;}
}

    private int ticknumber = 20;
    private TicksTest t1;

    @Before
    public void init()
    {
        t1 = new TicksTest(ticknumber);
    }


    @Test
    public void PublishAndUpdate(){
        BombsObserver b1 = new BombsObserver(20,20,20,ticknumber+1);
        t1.register(b1);
        BombsObserver b2 = new BombsObserver(4,250,5,ticknumber+2);
        t1.register(b2);
        assertEquals(2, t1.getSubscribers().size());
        assertEquals(b1, t1.getSubscribers().get(0));
        assertEquals(b2, t1.getSubscribers().get(1));
        t1.publish(ticknumber);
        assertFalse(b1.explosion());
        assertFalse(b2.explosion());
        t1.increase();
        assertTrue(b1.explosion());
        assertFalse(b2.explosion());

        t1.increase();
        assertTrue(b1.explosion());
        assertTrue(b2.explosion());
    }


    @Test
    public void UnitTestBombsObserver() {
        BombsObserver testingBombs = new BombsObserver(10, 10, 5, 3);
        assertEquals(false, testingBombs.explosion());

        testingBombs.update(3);
        assertEquals(true, testingBombs.explosion());
    }
}
