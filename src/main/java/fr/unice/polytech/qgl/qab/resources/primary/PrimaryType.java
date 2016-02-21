package fr.unice.polytech.qgl.qab.resources.primary;

/**
 * @version 20/12/2015
 *
 * Enum that represents the types of the primary resources
 */
public enum PrimaryType {
    FISH, FLOWER, FRUITS, FUR, ORE, QUARTZ, SUGAR_CANE, WOOD;

    public static boolean isPrimary(String prim){
        if (prim != null){
            for (PrimaryType t : PrimaryType.values()){
                if (t.toString().equalsIgnoreCase(prim))
                    return true;
            }
        }
        return false;
    }
};
