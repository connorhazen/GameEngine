package ooga;

public interface Data {

  View viewFactory(String file);

  Engine engineFactory();

}
