package fr.unice.polytech.qgl.qab.util.enums;

/**
 * @version 11.12.2015.
 */
public enum States {
    STATE0("STATE0"),
    STATE1("STATE1"),
    STATE2("STATE2"),
    STATE3("STATE3"),
    TRANSITION("TRANSITION");

    private String name = "";

    States(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean equals(States states) {
        if (states.toString().equalsIgnoreCase(name)) return true;
        return false;
    }

    public static States fromString(String state){
        if (state != null){
            for (States s : States.values()){
                if (s.toString().equalsIgnoreCase(state)) return s;
            }
        }
        return null;
    }
}
