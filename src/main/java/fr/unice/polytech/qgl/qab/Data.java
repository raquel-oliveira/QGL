package fr.unice.polytech.qgl.qab;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that represents the data json in the simulator.
 *
 * @version 4.8
 */
public class Data implements IDataFly {
    private int cost;
    private boolean status;
    private int range;
    private String found;
    private ArrayList<String> biomes;
    private ArrayList<String> creeks;

    public Data() {
        cost = 0;
        status = false;
        if (this instanceof IDataEcho)
            initializeEcho();
        else if (this instanceof IDataScan)
            initializeScan();
    }

    public void initializeEcho() {
        range = 0;
        found = new String();
    }

    private void initializeScan() {
        biomes = new ArrayList<String>();
        creeks = new ArrayList<String>();
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setStatus(String status) {
        this.status = (status.compareToIgnoreCase("ok") == 0)?true:false;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setFound(String found) {
        this.found = found;
    }

    public void setBiomes(ArrayList<String> biomes) {
        this.biomes = biomes;
    }

    public void setCreeks(ArrayList<String> creeks) {
        this.creeks = creeks;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        final Data data = (Data)obj;

        if (obj instanceof IDataFly) {
            if (this.cost != data.cost) return false;
            if (this.status != data.status) return false;
        }
        else if (obj instanceof IDataEcho) {
            if (this.cost != data.cost) return false;
            if (this.status != data.status) return false;
            if (this.found != data.found) return false;
            if (this.range != data.range) return false;
        }
        else if (obj instanceof IDataScan) {
            if (this.cost != data.cost) return false;
            if (this.status != data.status) return false;
            if (!this.biomes.containsAll(data.biomes)) return false;
            if (!this.creeks.containsAll(data.creeks)) return false;
        }
        else if(obj instanceof IDataHeading) {
            if (this.cost != data.cost) return false;
            if (this.status != data.status) return false;
        }
        return true;
    }

}
