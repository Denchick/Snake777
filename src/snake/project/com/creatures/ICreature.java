package snake.project.com.creatures;

import snake.project.com.architecture.Game;

import java.awt.*;

public interface ICreature {
    int GetPriorityForGameHandle();
    int getDrawingPriority();
    boolean DeadInConflict(ICreature conflictedObject, Game game);
    Point getCoordinates();
    void setCoordinates();
}
