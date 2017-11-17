package snake.project.com.creatures;

import snake.project.com.architecture.Direction;
import snake.project.com.architecture.Game;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake implements IMovable, IPointSequence {
    public Snake(List<Point> coordinates) {
        throw new NotImplementedException();
    }

    public Snake(Point head, List<Point> body) {

    }

    @Override
    public void move(Direction direction) {

    }
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

    @Override
    public ArrayList<Point> getListCoordinates() {
        return null;
    }
}
