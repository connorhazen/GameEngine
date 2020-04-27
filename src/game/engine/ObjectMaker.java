package game.engine;

import game.parse.XMLException;
import java.lang.reflect.Constructor;
import java.util.List;

public class ObjectMaker {

  public static Object createObjectOf(String rules, String pack, List<String> argument)
      throws XMLException {
    Object obj = null;

    try {
      Class c = Class.forName(pack + rules);
      Constructor objConstruct;
      try {
        Class[] params = new Class[]{List.class};
        objConstruct = c.getDeclaredConstructor(params);
        objConstruct.setAccessible(true);
        obj = objConstruct.newInstance(argument);

      } catch (Exception e) {
        objConstruct = c.getDeclaredConstructor();
        objConstruct.setAccessible(true);
        obj = objConstruct.newInstance();
      }
    } catch (Exception e) {
      throw new XMLException("Bad event declaration: type - " + rules + " Args - " + argument);
    }
    return obj;
  }

}
