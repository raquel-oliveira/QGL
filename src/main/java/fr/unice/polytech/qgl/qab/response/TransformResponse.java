package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;

/**
 * version on 02/03/2016.
 */
public class TransformResponse {
    private ManufacturedResource resource;
    private int amount;

    public TransformResponse(){
        resource = null;
        amount = 0;
    }
    public void addData(ManufacturedResource res, int amount) {
        this.resource = res;
        this.amount = amount;
    }

    public Resource getResource(){
        return resource;
    }

    public int getAmount(){
        return amount;
    }
}
