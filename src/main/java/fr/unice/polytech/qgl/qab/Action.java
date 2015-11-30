package fr.unice.polytech.qgl.qab;

/**
 * Class abstract to represents action
 * @version 4.9
 */
public enum Action {
    ECHO("echo"),
    SCAN("scan"),
    STOP("stop"),
    FLY("fly"),
    LAND("land"),
    HEADING("heading");

    private String name = "";

    Action(String name) { this.name = name; }

    public String toString() {
            return name;
        }

    public boolean equals(Action action) {
        return (action.toString().equalsIgnoreCase(name));
    }
}
