package fr.unice.polytech.qgl.qab.strategy.context.utils;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 23/02/16.
 *
 * Class to represent the datas about the action used in the states.
 */
public class ContextAction {
    private Combo comboAction;
    private Combo comboReturnBack;
    private Action simpleAction;
    private Action lastAction;
    private int contScan;
    private int indexAction;

    private List<Boolean> goodTiles;
    private int indexTile;
    private List<PrimaryType> resourcesToExploit;
    private int rangeMoveUntil;
    private Direction directionWithoutOCEAN;
    private int stop;
    private Position nextPosition;

    public ContextAction() {
        comboAction = null;
        simpleAction = null;
        lastAction = null;
        contScan = 0;
        indexAction = 0;
        comboReturnBack = null;
        goodTiles = new ArrayList<>();
        indexTile = 0;
        resourcesToExploit = new ArrayList<>();
        rangeMoveUntil = 0;
        directionWithoutOCEAN = null;
        stop = 0;
        nextPosition = null;
    }

    public Position getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition(Position nextPosition) {
        this.nextPosition = nextPosition;
    }

    public void setStop(int b) {
        this.stop = b;
    }
    public Direction getDirectionWithoutOCEAN() {
        return directionWithoutOCEAN;
    }

    public void setDirectionWithoutOCEAN(Direction direction) {
        this.directionWithoutOCEAN = direction;
    }
    /**
     * Method to return the combo action to return back.
     * @return combo action to return back
     */
    public Combo getComboReturnBack() {
        return comboReturnBack;
    }

    /**
     * Method to set the combo action to return back
     * @param comboReturnBack combo action to return back
     */
    public void setComboReturnBack(Combo comboReturnBack) {
        this.comboReturnBack = comboReturnBack;
    }

    /**
     * Method to get the combo action commum
     * @return combo action
     */
    public Combo getComboAction() {
        return comboAction;
    }

    /**
     * Method to get a simple action
     * @return simple action
     */
    public Action getSimpleAction() {
        return simpleAction;
    }

    /**
     * Method to get the last action made
     * @return last action made
     */
    public Action getLastAction() {
        return lastAction;
    }

    /**
     * Method to get the cont scan (control the list of action in the scan the ground)
     * @return cont scan
     */
    public int getContScan() {
        return contScan;
    }

    /**
     * Get the index action
     * @return index action
     */
    public int getIndexAction() {
        return indexAction;
    }

    public List<Boolean> getGoodTiles() {
        return goodTiles;
    }

    public int getIndexTile() {
        return indexTile;
    }

    /**
     * Set the index action
     * @param indexAction
     */
    public void setIndexAction(int indexAction) {
        this.indexAction = indexAction;
    }

    /**
     * Increment the index action in + 1
     */
    public void incrementIndexAction() {
        this.indexAction += 1;
    }

    /**
     * Set value in cont scan
     * @param contScan
     */
    public void setContScan(int contScan) {
        this.contScan = contScan;
    }

    /**
     * Set the last action made
     * @param lastAction
     */
    public void setLastAction(Action lastAction) {
        this.lastAction = lastAction;
    }

    /**
     * Set a simple action
     * @param simpleAction
     */
    public void setSimpleAction(Action simpleAction) {
        this.simpleAction = simpleAction;
    }

    /**
     * Set the combo action
     * @param comboAction
     */
    public void setComboAction(Combo comboAction) {
        this.comboAction = comboAction;
    }

    public void setGoodTiles(List<Boolean> goodTiles) {
        this.goodTiles = goodTiles;
    }

    public void setIndexTile(int indexTile) {
        this.indexTile = indexTile;
    }

    public List<PrimaryType> getResourcesToExploit() {
        return resourcesToExploit;
    }

    public void setResourcesToExploit(List<PrimaryType> resourcesToExploit, Context context) {
        java.util.Map<String, Integer> collectedResource = context.getCollectedResources();
        while(!resourcesToExploit.isEmpty()){
            PrimaryResource res = new PrimaryResource(resourcesToExploit.get(0));
            String resource = res.getName();
            if(!collectedResource.containsKey(resource)){
                this.resourcesToExploit.add(resourcesToExploit.get(0));
                resourcesToExploit.remove(0);
            }
            else if(collectedResource.get(resource) < context.getAcumulatedAmount(res)) {
                this.resourcesToExploit.add(resourcesToExploit.get(0));
                resourcesToExploit.get(0);
                resourcesToExploit.remove(0);
            }
            else{
                resourcesToExploit.remove(0);
            }
        }



        //this.resourcesToExploit = resourcesToExploit;
    }

    public int moveUntil() {
        return rangeMoveUntil;
    }

    public void moveUntil(int rangeMoveUntil) {
        this.rangeMoveUntil = rangeMoveUntil;
    }


    public void incrementIndexTile() {
        this.indexTile = (indexTile + 1) % 4;
    }

    public int getStop() {
        return this.stop;
    }
}
