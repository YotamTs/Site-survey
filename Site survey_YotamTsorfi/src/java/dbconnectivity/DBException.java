package dbconnectivity;

/**
 * DB Exception - contains error info for the client
 * @author: Yotam Tsorfi
 */
public class DBException extends Exception{
      public DBException(String message) {
        super(message);
    }
}
