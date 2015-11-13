package fr.unice.polytech.qgl.qab;

import org.json.JSONObject;

public class Explorer implements IExplorerRaid {
    public Explorer() {
        System.out.println("Work");
    }

    public void initialize(String context) {
        throw new UnsupportedOperationException("IExplorerRaid::initialize is not yet implemented");
    }


    public String takeDecision() {
        throw new UnsupportedOperationException("IExplorerRaid::takeDecision is not yet implemented");
    }


    public void acknowledgeResults(String results) {
        throw new UnsupportedOperationException("IExplorerRaid::acknowledgeResults is not yet implemented");
    }
}