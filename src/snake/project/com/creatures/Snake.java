package snake.project.com.creatures;

import snake.project.com.architecture.Direction;
import snake.project.com.architecture.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake implements IMovable, IPointSequence {
    private Point coordinates;
    private List<Point> body;

    public Snake(Point head, List<Point> body) {
        coordinates = head;
        this.body = body;
    }

    @Override
    public int getPriorityForGameHandle() {
        return 10;
    }

    @Override
    public int getDrawingPriority() {
        return 10;
    }

    @Override
    public boolean deadInConflict(ICreature conflictedObject, Game game) {
        return false;
    }

    @Override
    public Point getCoordinates() {
        return coordinates;
    }

    @Override
    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public List<Point> getListCoordinates() {
        return body;
    }

    @Override
    public void makeMove(Direction direction) {

    }
}
