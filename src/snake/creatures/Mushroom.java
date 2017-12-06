package snake.creatures;

import snake.architecture.Point;

import javax.swing.*;
import java.awt.*;

public class Mushroom implements IFood, ICreature {
    private Point coordinates;
    private Image image;

    public Mushroom(int x, int y) {
        this.coordinates = new Point(x, y);
        image = (new ImageIcon("images/mushroom.png")).getImage();
    }

    public Mushroom(Point coordinates) {
        image = (new ImageIcon("images/mushroom.png")).getImage();
        this.coordinates = coordinates;
    }

    @Override
    public int getPriorityForGameHandle() {
        return 8;
    }

    @Override
    public int getDrawingPriority() {
        return 8;
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
    public Image getImage() {
        return image;
    }

    @Override
    public boolean WasEaten() {
        return false;
    }

    @Override
    public void ActionInConflict(Snake snake) {
        snake.decrease();
    }
}
