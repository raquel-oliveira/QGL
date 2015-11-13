package fr.unice.polytech.qgl.qab;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;

public class Explorer implements IExplorerRaid{

    public Explorer() {
        //initialize();
        System.out.println("Work");
    }

    public void initialize(String context) {
       /* {
            "men": 12,
                "budget": 10000,
                "contracts": [
            { "amount": 600, "resource": "WOOD" },
            { "amount": 200, "resource": "GLASS" }
            ],
            "heading": "W"
        }*/
    }


    public String takeDecision() {
        throw new UnsupportedOperationException("IExplorerRaid::takeDecision is not yet implemented");
    }


    public void acknowledgeResults(String results) {
        throw new UnsupportedOperationException("IExplorerRaid::acknowledgeResults is not yet implemented");
    }
}