package engine;

public interface EngineAPI {
  UpdateObject getGrid();

  UpdateObject executeEvent(Event e);

}
