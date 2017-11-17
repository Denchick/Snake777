package snake.project.com.architecture;

import jdk.jshell.spi.ExecutionControl;
import snake.project.com.creatures.ICreature;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.util.ArrayList;

public class Map {

    public int Width;
    public int Height;
    public ArrayList<ICreature> Creatures;

    public Map(int width, int height) {
        Width = width;
        Height = height;
    }

    public void SetCreatureOnMap(ICreature creature) {
        throw new NotImplementedException();
    }

    public void DeleteCreatureFromMap(int x, int y, ICreature creature)
    {
        for (int i = 0; i < Creatures.size(); i++)
        {
            Point coordinates = e.getCoordinates();
            if (coordinates.x == x && coordinates.y == y &&
                    creature.getClass().getName() == Creatures.get(i).getClass().getName()) {
                Creatures.remove(i);
            }
        }
    }

    public boolean IsEmpty(Point coordinates) {
        throw new NotImplementedException();
    }

    public ICreature GetElementByCoordinates(Point coordinates) {
        throw new NotImplementedException();
    }

}
