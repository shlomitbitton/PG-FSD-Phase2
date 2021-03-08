package phase2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseConnection {

  	
	String qry;
	Connection dbCon;


  DataBaseConnection(){
  try {
    //    		Define the URL to connect
            String urlToConnect = "jdbc:mysql://localhost:3306/simplilearn_Phase2";
            
    //    		Define the username for db to connect
            String dbUserName = "root";
            
    //    		Define the password
            String dbUserPassword = "oiccmmp135";
            
    //    		Define the driver for the database
            String mySqlDriver = "com.mysql.cj.jdbc.Driver";
            
            
    //        	Load the Driver
          Class.forName(mySqlDriver);
          
    //			Try to establish the connection
          dbCon = DriverManager.getConnection(urlToConnect, dbUserName, dbUserPassword);		
          
        } catch (ClassNotFoundException e) {
          System.out.println("Can't load the Driver : " + e.getMessage());
        } catch (SQLException e) {
          System.out.println("Can't connect to DB : " + e.getMessage());
        }
      }
}
