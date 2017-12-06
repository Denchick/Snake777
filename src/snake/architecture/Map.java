package snake.architecture;

import snake.creatures.ICreature;
import snake.creatures.IPointSequence;

import java.util.ArrayList;

public class Map {

  public int Width;
  public int Height;
  public ArrayList<ICreature> creatures;

  public Map(int width, int height) {
    creatures = new ArrayList<>();
    Width = width;
    Height = height;
  }

  public void setCreatureOnMap(ICreature creature) {
    if (!IsEmpty(creature.getCoordinates())) {
      throw new IllegalArgumentException("Impossible to put creature by this coordinates.");
    }
    creatures.add(creature);
  }

  public void DeleteCreatureFromMap(Point coordinates, ICreature creature) {
    for (int i = 0; i < creatures.size(); i++) {
      Point currentCoordinates = creatures.get(i).getCoordinates();
      if (currentCoordinates.equals(coordinates) &&
          creature.getClass().getName() == creatures.get(i).getClass().getName()) {
        creatures.remove(i);
      }
    }
  }

  public boolean IsEmpty(Point coordinates) {
    for (ICreature creature : creatures) {
      if (creature.getCoordinates().equals(coordinates)) {
        return false;
      }
      if (creature instanceof IPointSequence) {
        for (Point currentCoordinates : ((IPointSequence) creature).getBodyCoordinates()) {
          if (currentCoordinates.equals(coordinates))
            return false;
        }
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
}
