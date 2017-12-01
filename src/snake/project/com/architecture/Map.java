package snake.project.com.architecture;

import snake.project.com.creatures.Food;
import snake.project.com.creatures.ICreature;

import java.awt.*;
import java.util.ArrayList;

public class Map {

  public int Width;
  public int Height;
  public ArrayList<ICreature> Creatures;

  public Map(int width, int height) {
    Creatures = new ArrayList<>();
    Width = width;
    Height = height;
  }

  public void SetCreatureOnMap(ICreature creature) {
    if (!IsEmpty(creature.getCoordinates())) {
      throw new IllegalArgumentException("Impossible to put creature by this coordinates.");
    }
    Creatures.add(creature);
  }

  public void DeleteCreatureFromMap(Point coordinates, ICreature creature) {
    for (int i = 0; i < Creatures.size(); i++) {
      Point currentCoordinates = Creatures.get(i).getCoordinates();
      if (currentCoordinates.equals(coordinates) &&
          creature.getClass().getName() == Creatures.get(i).getClass().getName()) {
        Creatures.remove(i);
      }
    }
  }


  public boolean IsEmpty(Point coordinates) {
    for (ICreature creature : Creatures) {
      if (creature.getCoordinates() == coordinates) {
        return false;
      }
    }
    return true;
  }

  public boolean pointWithinMapBorders(Point point) {
    return point.getX() >= this.Width ||
        point.getX() < 0 ||
        point.getY() >= this.Height ||
        point.getY() < 0;

    }

  public ICreature GetElementByCoordinates(Point coordinates) {
    for (ICreature creature : Creatures) {
      if (creature.getCoordinates() == coordinates) {
        return creature;
      }
    }
    return null;
  }
}
