package game.parse;

/**
 * XML Exception is thrown when an XML file is invalid More specifically if the <simulation>
 * </simulation> element is missing or the file is completely blank
 */

public class XMLException extends RuntimeException {

  public XMLException(String message, Object... values) {
    super(String.format(message, values));
  }

  /**
   * Create an exception based on a caught exception with a different message.
   */
  public XMLException(Throwable cause, String message, Object... values) {
    super(String.format(message, values), cause);
  }

  /**
   * Create an exception based on a caught exception, with no additional message.
   */
  public XMLException(Throwable exception) {
    super(exception);
  }

}
