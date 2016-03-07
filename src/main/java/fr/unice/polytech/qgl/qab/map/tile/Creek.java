package fr.unice.polytech.qgl.qab.map.tile;

/**
 * @version 12.12.2015.
 *
 * Class the represent the creek in the map
 */
public class Creek {
    private String idCreek;

    /**
     * Creek's contructor
     */
    public Creek() {
        idCreek = null;
    }

    /**
     * Creek's contructor with parametre
     * @param numCreeks id of the creek
     */
    public Creek(String numCreeks) {
        idCreek = numCreeks;
    }

    /**
     * Return the id ot the creek
     * @return id of the creek
     */
    public String getIdCreek() {
        return idCreek;
    }
}
