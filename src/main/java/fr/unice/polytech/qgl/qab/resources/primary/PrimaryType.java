package fr.unice.polytech.qgl.qab.resources.primary;

/**
 * @version 20/12/2015
 *
 * Enum that represents the types of the primary resources
 */
public enum PrimaryType {
    FISH, FLOWER, FRUITS, FUR, ORE, QUARTZ, SUGAR_CANE, WOOD;

    /**
     * Method that receive a ManufacturedType like a string and return a object ManufaturedType
     * @param resource string with the name of the manufatured resource
     * @return object primary
     */
    public static PrimaryType fromString(String resource){
        if (resource != null){
            for (PrimaryType primary : PrimaryType.values()){
                if (primary.toString().equalsIgnoreCase(resource))
                    return primary;
            }
        }
        return null;
    }
}
