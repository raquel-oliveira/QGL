package fr.unice.polytech.qgl.qab.map.tile;

/**
 * @version 12.12.2015.
 */
public class Creek {
    private String idCreek;

    public Creek() {
        idCreek = null;
    }

    public Creek(String numCreeks) {
        idCreek = numCreeks;
    }

    public String getIdCreek() { return idCreek; }

    public void setIdCreek(String idCreek) { this.idCreek = idCreek; }
}
