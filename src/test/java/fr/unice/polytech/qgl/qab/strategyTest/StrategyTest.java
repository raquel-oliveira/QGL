package fr.unice.polytech.qgl.qab.strategyTest;

import fr.unice.polytech.qgl.qab.strategy.Strategy;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.mockito.Mockito.mock;

public class StrategyTest {

    @Test
    public void testMakeDecision() throws NoSuchFieldException {
        Strategy strategy = mock(Strategy.class);
        Field field = Strategy.class.getDeclaredField("context");
        field.setAccessible(true);
    }
}
