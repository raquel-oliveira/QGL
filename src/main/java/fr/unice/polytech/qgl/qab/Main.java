package fr.unice.polytech.qgl.qab;

import eu.ace_design.island.bot.IExplorerRaid;

/**
 * Created by Gabriela
 * @version 14.11.15.
 */
public class Main {
    public static void main(String[] args) {
        IExplorerRaid raid = new Explorer();        // Empty Constructor
        String decision = "";
        String context =
                "{ \n" +
                        "  \"men\": 12,\n" +
                        "  \"budget\": 10000,\n" +
                        "  \"contracts\": [\n" +
                        "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                        "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                        "  ],\n" +
                        "  \"heading\": \"W\"\n" +
                        "}"
                ;
        raid.initialize(context);                   // Context initialization

        while ( decision.contains("stop") == false ) {
            decision = raid.takeDecision();      // Decision taking, one at a time
            System.out.println(decision);
            String result = "{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }"; // = engine.compute(decision);
            raid.acknowledgeResults(result);// Result storage
        }
    }
}