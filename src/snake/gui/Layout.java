package snake.gui;

import snake.architecture.Game;
import snake.architecture.Point;
import snake.creatures.GoodSnake;
import snake.creatures.ICreature;
import snake.main;

import javax.swing.*;
import java.awt.*;

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

    for (ICreature creature: game.getMap().creatures) {
     paintCreature(creature, g);
    }

    if (game.isOver()) {
      String str = "Game Over";
      g.setColor(Color.white);
      g.drawString(str, game.getMap().Width * cellSize / 2 - 10 , game.getMap().Height * cellSize / 2 + 10);
    }
  }

  private void paintCreature(ICreature creature, Graphics g) {
    if (creature instanceof GoodSnake) {
      paintSnake((GoodSnake) creature, g);
    } else {
      g.drawImage(creature.getImage(),
          creature.getCoordinates().getX() * main.CELL,
          creature.getCoordinates().getY() * main.CELL,
          main.CELL, main.CELL, this);
    }
  }

  private void paintSnake(GoodSnake goodSnake, Graphics g) {
    g.drawImage(goodSnake.getHead().getImage(),
        goodSnake.getHead().coordinates.getX() * main.CELL,
        goodSnake.getHead().coordinates.getY() * main.CELL,
        main.CELL, main.CELL, this);
    for (Point bodyCoordinates: goodSnake.getBodyCoordinates() ) {
      g.drawImage(goodSnake.getBody().getImage(),
          bodyCoordinates.getX() * main.CELL,
          bodyCoordinates.getY() * main.CELL,
          main.CELL, main.CELL, this);
    }
  }
}
