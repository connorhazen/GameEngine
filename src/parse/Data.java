package parse;

import engine.Engine;
import view.View;

public interface Data {

  View viewFactory(String file);

  Engine engineFactory();

}
