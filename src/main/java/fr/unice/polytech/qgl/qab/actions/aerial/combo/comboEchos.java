package fr.unice.polytech.qgl.qab.actions.aerial.combo;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 9.12.2015
 */
public class comboEchos {
    private List<Echo> actions;

    public comboEchos() {
        actions = new ArrayList<>();
    }

    public void defineEchos(Direction head) {
        if (head.isHorizontal()) {
            if (head.compareTo(Direction.EAST) == 0) {
                actions.add(new Echo(Direction.EAST));
            } else {
                actions.add(new Echo(Direction.WEST));
            }
            actions.add(new Echo(Direction.NORTH));
            actions.add(new Echo(Direction.SOUTH));
        }
        if (head.isVertical()) {
            if (head.compareTo(Direction.NORTH) == 0) {
                actions.add(new Echo(Direction.NORTH));
            } else {
                actions.add(new Echo(Direction.SOUTH));
            }
            actions.add(new Echo(Direction.WEST));
            actions.add(new Echo(Direction.EAST));
        }
    }

    public List<Echo> getActions() {
        return actions;
    }

    public boolean isEmpty() {
        return actions.isEmpty();
    }

    public Action get(int index) {
        return actions.get(index);
    }

    public Action remove(int index) {
        return actions.remove(index);
    }
}
