package snake.project.com.gui;

import snake.project.com.architecture.Game;
import snake.project.com.architecture.Point;
import snake.project.com.creatures.Food;
import snake.project.com.creatures.ICreature;
import snake.project.com.creatures.Wall;
import snake.project.com.creatures.IPointSequence;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Layout extends JPanel {

  private final Game game;
  private final int cellSize;

  public Layout(Game game, int cellSize) {
    this.game = game;
    this.cellSize = cellSize;
    setBackground(Color.BLACK);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);

    for (ICreature creature: game.getMap().Creatures) {
     paintCreature(creature, g);
    }

    if (game.isOver()) {
      String str = "Game Over";
      g.setColor(Color.white);
      g.drawString(str, game.getMap().Width * cellSize / 2 - 40 , game.getMap().Height * cellSize / 2);
    }
  }

  private void paintCell(Point coordinates, Color color, Graphics g) {
    g.setColor(color);
    g.fillRect(coordinates.getX() * cellSize, coordinates.getY() * cellSize,
        cellSize, cellSize);
  }

  private void paintCreature(ICreature creature, Graphics g) {
    if (creature instanceof IPointSequence) {
      for (Point coordinates: ((IPointSequence) creature).getListCoordinates()) {
        paintCell(coordinates, creature.getColor(), g);
      }
    } else {
      paintCell(creature.getCoordinates(), creature.getColor(), g);
    }
  }
}
