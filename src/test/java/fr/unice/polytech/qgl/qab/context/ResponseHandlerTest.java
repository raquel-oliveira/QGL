package fr.unice.polytech.qgl.qab.context;

/**
 * Created by huang on 07/02/16.
 */
import static org.junit.Assert.*;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ResponseHandler;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ResponseHandlerTest {
    ResponseHandler r;

    @Before
    public void defineContext() {
        r = new ResponseHandler();
    }

    @Ignore
    public void TestreadDataFromEcho() throws NegativeBudgetException {
        String data = "{ \n" +
                "  \"cost\": 1,\n" +
                "  \"extras\": [\n" +
                "    { \"range\": 2, \"found\": \"GROUND\" },\n" +
                "  ],\n" +
                "  \"status\": \"OK\"\n" +
                "}\n";

        String context = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 1000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}\n";

        Context c1 = new Context();
        c1.read(context);
        Context c2 = new Context();
        c2 = r.readData(data, new Echo(Direction.EAST), c1 );
        assertEquals(999,c2.getBudget());
    }

}
