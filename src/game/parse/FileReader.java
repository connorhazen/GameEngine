package game.parse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * FileReader is the basis of all xml file reading for the program
 * All simulation information is received from an xml file via FileReader
 * When invalid or missing data is present within an xml file the error is handled via an error popup alert
 */
public class FileReader {



  public static DocumentBuilder DOCUMENT_BUILDER;

  static {
    try {
      DOCUMENT_BUILDER = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
  }


  private Element simElement;

  /**
   * FileReader constructor creates an element and uses that element to find rows and columns
   * @param fileName the name of file wanting to be read
   */
  public FileReader(String fileName){
    File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
    simElement = getRootElement(file);
  }

  public List<Map<String, String>> makeMapsForTag(String tag){
    List<Map<String, String>> ret = new ArrayList<>();

    NodeList list = simElement.getElementsByTagName(tag);
    for(int i =0; i < list.getLength(); i++){
      NodeList current = list.item(i).getChildNodes();
      Map<String, String> toAdd = new HashMap<>();
      ret.add(toAdd);
      for(int x =0; x < current.getLength(); x++){
        toAdd.put(current.item(x).getNodeName(), current.item(x).getTextContent());
      }
    }

    return ret;
  }

  /**
   * getValue returns the value found in the element that is identified by the tag
   * the tag is usually a certain parameter the Game.controller wants to run the simulation
   * @param tag the name of the parameter wanting to be read
   * @param element the element the parameter will be found in
   * @return the int, double, string, etc value that is associated with that tag parameter
   */
  private static String getValue(String tag, Element element) {
    NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
    Node node = nodes.item(0);
    return node.getNodeValue();
  }


  // get root element of an XML file
  private Element getRootElement (File xmlFile) {
    try {
      DOCUMENT_BUILDER.reset();
      Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
      return xmlDocument.getDocumentElement();
    }
    catch (SAXException | IOException e) {
      throw new XMLException(e);
    }

  }





  /**
   * getIntValue takes in a parameter and returns the integer value associated with the parameter in the file
   * @param parameter the name of the parameter wanting to be found
   * @return the integer value of the parameter as found in the file
   */
  public int getIntValue(String parameter) {
    try {
      return Integer.parseInt(getValue(parameter, simElement));
    } catch (NullPointerException e) {

      //throw new parameterException(errorMessage, parameter);
      return 0;
    }
  }

  /**
   * getDoubleValue takes in a parameter and returns the double value associated with the parameter in the file
   * @param parameter the name of the parameter wanting to be found
   * @return the double value of the parameter as found in the file
   */
  public double getDoubleValue(String parameter) {
    try {
      return Double.parseDouble(getValue(parameter, simElement));
    } catch (NullPointerException e) {

      //throw new parameterException(errorMessage, parameter);
      return 0;
    }
  }

  /**
   * getString takes in a parameter and returns the string associated with the parameter in the file
   * @param parameter the name of the parameter wanting to be found
   * @return the string of the parameter as found in the file
   */
  public String getString(String parameter) {
    try {
      return getValue(parameter, simElement);
    } catch (NullPointerException e) {
     //TODO; fix
      //throw new parameterException(errorMessage, parameter);
      return null;
    }
  }


  /**
   * checks if a certain parameter exists within a file
   * @param parameter the parameter wanting to see if exists
   * @return true if the value of the parameter is able to be retrieved, false otherwise
   */
  public boolean checkExists(String parameter) {
    try {
      getValue(parameter, simElement);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
