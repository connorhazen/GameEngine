package game.util;

public class SimpleState implements State {
  private String type;
  private int value;
  private String direction;
  private boolean marked;



  public SimpleState(String type, int value, String direction){
    this.type = type;
    this.value = value;
    this.direction = direction;

  }

  public SimpleState(String type, int value){
    this(type, value, "UP");
  }



  @Override
  public String getType() {
    return type;
  }

  @Override
  public int getValue() {
    return value;
  }

  @Override
  public String getDirection() {
    return direction;
  }





}
