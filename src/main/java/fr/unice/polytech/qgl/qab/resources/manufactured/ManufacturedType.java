package fr.unice.polytech.qgl.qab.resources.manufactured;

/**
 * Created by Quentin Prod'Homme on 17.01.2016.
 * Raquel Lopes de Oliveira update at 01.02.2016
 */
public enum ManufacturedType {
    GLASS, INGOT, LEATHER, PLANK, RUM;

    /**
     * Method that receive a ManufacturedType like a string and return a object ManufaturedType
     * @param resource string with the name of the manufatured resource
     * @return object manufactured
     */
    public static ManufacturedType fromString(String resource){
        if (resource != null){
            for (ManufacturedType manufactured : ManufacturedType.values()){
                if (manufactured.toString().equalsIgnoreCase(resource))
                    return manufactured;
            }
        }
        return null;
    }
}