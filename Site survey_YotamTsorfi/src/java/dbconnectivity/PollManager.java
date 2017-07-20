package dbconnectivity;

import java.sql.*;

/**
 * @author: Yotam Tsorfi
 */
public class PollManager {
    
    protected Connection con; // DB connection object
    protected String url;     // DB url
    protected String dbUrl = "jdbc:mysql://localhost:3306/";
    protected String odbcDriver = "com.mysql.jdbc.Driver"; 
 
//--------------------------------------    
    /**
     * Poll Manager constructor
     * @param structure database name
     * @param user user name
     * @param pass user password
     */
    public PollManager(String structure, String user, String pass){
        url = dbUrl + structure + "?user=" + user + "&password=" + pass;
    }
//--------------------------------------    
     /**
     * connect to data base
     * @throws DBException  if driver can't connected to the DB
     */
    public void connectToDB() throws DBException
    {      
        try //throws DBException
        {
            //Register JDBC driver
            Class.forName(odbcDriver);
              
            //connect to DB
            con = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
            throw new DBException("The poll server is currently unavailable please try later");
        }
    }
//--------------------------------------    
    /**
     * insert new vote to the data base
     * @param pollname poll name request
     * @param userchoice user choice
     * @throws dbconnectivity.DBException if server is unavailable or invalid request
     */
    public void insertVoteToDB(String pollname, String userchoice) throws DBException
    {
        String query;
        Statement statement;
        
        //check poll name & user choice validity
        if (pollname == null || "".equals(pollname) || userchoice == null || "".equals(userchoice)) 
            throw new DBException("Invalid poll request");
        
         query = "INSERT INTO `results`(`id`, `pollname`, `choice`) VALUES " + 
                    "(null, '" + pollname + "', '" + userchoice + "')"; // id is AUTO_INCREMENT   
        
        try {
            //Get a statement object from the connection
            statement = con.createStatement();
            
            //Execute SQL query
            statement.executeUpdate(query);
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            throw new DBException("The poll server is currently unavailable please try later!");
        }
          
        // close statement
        try {
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    }
//--------------------------------------        
     /**
     * @param pollName the wanted poll
     * @return string in html format that include the poll result
     * @throws dbconnectivity.DBException if results not available
     */
    public String getPollResult(String pollName) throws DBException{
        try {
            //Get a statement object from the connection
            Statement statement = con.createStatement();
            
            //Execute SQL query
            String query;
            query = "SELECT choice, (count(*)/(SELECT COUNT(*) FROM results WHERE pollname = '"
                    + pollName + "')*100)" +
                    "AS percent FROM `results` WHERE pollname = '" + pollName + "' GROUP BY choice;";
            ResultSet rs = statement.executeQuery(query);
            
            //Extract data from result
            String result = "<h2>Current results of the poll are:</h2><p>";
            while(rs.next()){
                result +=  rs.getInt("percent") + "% "
                        + rs.getString("choice") + "<p>";
            }
            return result;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            throw new DBException("sorry results are not available right now");
        }
    }
//--------------------------------------            
    /**
     * close connection with the data base
     * @throws dbconnectivity.DBException if can't close connection 
     */
    public void close() throws DBException{
        try {
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            throw new DBException("Error: can't close connection");
        }
    }
    //--------------------------------------            
    /**
     * close connection with the data base
     */
    public void closeNoThrow(){
        try {
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
