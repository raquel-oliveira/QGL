package fr.unice.polytech.qgl.qab.util.enums;

/**
 * Class abstract to represents action.
 *
 * @version 4.9
 */
public enum ActionBot {
    ECHO("echo"),
    SCAN("scan"),
    STOP("stop"),
    FLY("fly"),
    LAND("land"),
    HEADING("heading"),
    EXPLORE("explore"),
    GLIMPSE("glimpse"),
    MOVE_TO("move_to");

    private String name = "";

    ActionBot(String name) { this.name = name; }

    public String toString() { return name; }

    public boolean isEquals(ActionBot action) {
        return action.toString().equalsIgnoreCase(name);
    }

    public static ActionBot fromString(String action){
        if (action != null){
            for (ActionBot ab : ActionBot.values()){
                if (ab.toString().equalsIgnoreCase(action)) return ab;
            }
        }
        return null;
    }
}
