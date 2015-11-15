package fr.unice.polytech.qgl.qab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * The test class ExplorerTest for the class Test.
 * @version 14.11.2015
 */
public class ExplorerTest {

	Explorer e;

	@Before
	public void defineContext() {
		e = new Explorer();
	}

	// Basic test, will be updated when the logic behind our bot will develop
	@Test
	public void simpleTakeDecision() {
		String s = e.takeDecision();
		assertTrue(s.equals("{ \"action\": \"stop\" }"));
	}
}
