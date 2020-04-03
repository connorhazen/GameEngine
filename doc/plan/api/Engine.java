package ooga;

public interface Engine {
  UpdateObject getGrid();

  UpdateObject executeEvent(Event e);

}
