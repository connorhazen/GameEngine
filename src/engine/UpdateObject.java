package engine;

import javafx.animation.Animation;

public interface UpdateObject {
  Grid getGrid();
  Animation getAnimation();
  boolean getLevelOver();
}
