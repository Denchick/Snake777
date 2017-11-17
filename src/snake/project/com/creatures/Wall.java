package snake.project.com.creatures;

import snake.project.com.architecture.Game;

import java.awt.*;
import java.util.ArrayList;

public class Wall implements ICreature {
    @Override
    public int GetPriorityForGameHandle() {
        return 0;
    }

    @Override
    public int getDrawingPriority() {
        return 0;
    }

    @Override
    public boolean DeadInConflict(ICreature conflictedObject, Game game) {
        return false;
    }

    @Override
    public Point getCoordinates() {
        return null;
    }

    @Override
    public void setCoordinates() {

    }
}
