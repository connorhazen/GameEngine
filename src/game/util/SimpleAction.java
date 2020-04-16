package game.util;

import javafx.scene.input.KeyEvent;

public class SimpleAction implements Action {

  private String code;
  public SimpleAction(KeyEvent e) {
    code =  e.getCode().toString();
  }

  public SimpleAction(String e){
    code = e;
  }

  public String getCode(){
    return code;
  }

  /**
   * TODO: fix this shit
   * @param obj
   * @return
   */
  @Override
  public boolean equals(Object obj) {
    return code.equals(obj.toString());
  }

  @Override
  public int hashCode() {
    return code.hashCode();
  }

  @Override
  public String toString() {
    return code;
  }
}
