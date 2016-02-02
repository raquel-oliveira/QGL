package fr.unice.polytech.qgl.qab.enums;

import fr.unice.polytech.qgl.qab.util.enums.Phase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Gabriela
 * @version 4.9
 */
public class PhaseTest {
    private Phase phase;

    @Test
    public void testPhaseGround() {
        phase = Phase.GROUND;
        assertEquals(Phase.GROUND, phase);
    }

    @Test
    public void testPhaseAerial() {
        phase = Phase.AERIAL;
        assertEquals(Phase.AERIAL, phase);
    }

    @Test
    public void testToStringPhaseGround() {
        phase = Phase.GROUND;
        String phaseString = phase.toString();
        assertTrue(phaseString.equalsIgnoreCase(Phase.GROUND.toString()));
    }

    @Test
    public void testToStringPhaseAerial() {
        phase = Phase.AERIAL;
        String phaseString = phase.toString();
        assertTrue(phaseString.equalsIgnoreCase(Phase.AERIAL.toString()));
    }
}
