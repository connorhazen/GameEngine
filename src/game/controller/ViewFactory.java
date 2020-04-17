package game.controller;

import game.parse.FileReader;
import game.view.SimpleView;
import game.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewFactory {

  public static View makeView(String folder, String fileName){
    FileReader fr = new FileReader(folder+fileName);
    List<String> list = fr.getValues("image");
    Map<String, String> imageMap = new HashMap<>();
    for(String s: list){
     List<String> split = new ArrayList<>(Arrays.asList(s.split(" ", 2)));
     imageMap.put(split.get(1), split.get(0));
    }

    return new SimpleView(imageMap);
  }
}
