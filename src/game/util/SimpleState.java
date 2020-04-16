package game.util;

public class SimpleState implements State {
  private String type;
  private int value;



  public SimpleState(String type, int value){
    this.type = type;
    this.value = value;
  }



  @Override
  public String getType() {
    return type;
  }

  @Override
  public int getValue() {
    return value;
  }
}
