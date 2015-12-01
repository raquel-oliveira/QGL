package fr.unice.polytech.qgl.qab.enums;

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
    HEADING("heading");

    private String name = "";

    ActionBot(String name) { this.name = name; }

    public String toString() {
            return name;
        }

    public boolean equals(ActionBot action) {
        return (action.toString().equalsIgnoreCase(name));
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
