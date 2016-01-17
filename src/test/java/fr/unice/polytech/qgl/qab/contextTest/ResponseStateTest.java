package fr.unice.polytech.qgl.qab.contextTest;

import static org.junit.Assert.*;

import org.junit.Test;


import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import fr.unice.polytech.qgl.qab.strategy.context.ResponseState;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

public class ResponseStateTest {
	ResponseState r;
	
	@Test
	public void TestgetStatus(){
		Action a = new Echo(Direction.EAST);
		r = new ResponseState(a,true);
		assertEquals(true,r.getStatus());
	}
	
	@Test
	public void TestgetAction(){
		Action a = new Fly();
		r = new ResponseState(a,false);
		assertEquals(a,r.getAction());
		
	}

}
