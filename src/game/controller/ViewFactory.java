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
    FileReader fr = new FileReader(folder + fileName);
    Map<String, String> imageMap = getImages(fr);
    boolean colors = false;
    try {
      colors = Boolean.valueOf(fr.getValue("DynamicColors"));
    } catch (Exception e) {
      colors = false;
    }
    int width = 0;
    int height = 0;
    try{
      height = Integer.parseInt(fr.getValue("height"));
      width = Integer.parseInt(fr.getValue("width"));
    }
    catch (Exception e){
      throw new XMLException("Missing height or width param in View file");
    }

    DynamicView view = new DynamicView(width, height, imageMap, colors, folder);

    makeGUIElements(view, fr);
    return view;




  }

  private static void makeGUIElements(DynamicView view, FileReader fr) throws XMLException{

    try{
      List<String> values = fr.getValues("Button");
      for (String s : values) {
        String[] split = s.split(" ");
        view.addButton(MY_RESOURCES.getString(split[0]), split[1]);
      }
    }
    catch (Exception e){
      throw new XMLException("ERROR MAKING BUTTONS: " + e.getMessage());
    }

    try{
      view.addDisplayInfo(MY_RESOURCES.getString(fr.getValue("display")));
    }
    catch (Exception e){
      throw new XMLException("ERROR MAKING DISPLAY BOX: " + e.getMessage());
    }

  }

  private static Map<String, String> getImages(FileReader fr) {
    List<String> list = fr.getValues("image");
    Map<String, String> imageMap = new HashMap<>();
    for (String s : list) {
      List<String> split = new ArrayList<>(Arrays.asList(s.split(" ", 2)));
      imageMap.put(split.get(1), split.get(0));
    }
    return imageMap;
  }
}
