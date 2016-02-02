package fr.unice.polytech.qgl.qab.strategy;

import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StrategyTest {
    Strategy strategy;

    @Before
    public void defineContext() throws NegativeBudgetException {
        strategy = new Strategy();
    }

    @Test
    public void test() throws IndexOutOfBoundsComboAction, PositionOutOfMapRange {
        assertEquals(new Stop().formatResponse(), strategy.makeDecision());
    }
}
