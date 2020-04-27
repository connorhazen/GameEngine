package game.controller;

import game.parse.FileReader;
import game.parse.XMLException;
import game.view.DynamicView;
import game.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ViewFactory {

  private static final String LANGUAGE = "english";
  private static final ResourceBundle MY_RESOURCES = ResourceBundle
      .getBundle("resources." + LANGUAGE);

  public static View makeView(String folder, String fileName) throws XMLException {
    try {
      FileReader fr = new FileReader(folder + fileName);
      List<String> list = fr.getValues("image");
      Map<String, String> imageMap = new HashMap<>();
      for (String s : list) {
        List<String> split = new ArrayList<>(Arrays.asList(s.split(" ", 2)));
        imageMap.put(split.get(1), split.get(0));
      }

      boolean colors = false;
      try {
        colors = Boolean.valueOf(fr.getValue("DynamicColors"));
      } catch (Exception e) {
      }

      int height = Integer.parseInt(fr.getValue("height"));
      int width = Integer.parseInt(fr.getValue("width"));

      DynamicView view = new DynamicView(width, height, imageMap, colors, folder);
      List<String> values = fr.getValues("Button");
      for (String s : values) {
        String[] split = s.split(" ");
        view.addButton(MY_RESOURCES.getString(split[0]), split[1]);
      }

      view.addDisplayInfo(MY_RESOURCES.getString(fr.getValue("display")));

      return view;
    } catch (Exception e) {

      throw new XMLException(e.getMessage());
    }

  }
}
