//UsingUpdatableResultSet

package phase2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MenuOperations {
  
  DataBaseConnection dbc =new DataBaseConnection();
  


public void fetchAndUpdate(int learnerId, String newLearnrName) {
//	Write the query to fetch records from table:learners
dbc.qry = "select * from learners";
	
//	Get a reference to the PreparedStatement
	try {
		PreparedStatement pstmt = dbc.dbCon.prepareStatement(dbc.qry, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//		Execute the query
		ResultSet theResultSet = pstmt.executeQuery();
   // Learners learner = new Learners();
//		Traverse through the results
		while(theResultSet.next()) {
			if(theResultSet.getInt("learnerId") == learnerId) {
//				Update the current row
				theResultSet.updateString("learnerName", theResultSet.getString("learnerName") + newLearnrName);
//				Commit the changes
				theResultSet.updateRow();
				
				System.out.println("Name updated...");
			}
		}
	} catch (SQLException e) {
		System.out.println("Can't get a reference to the PreparedStatement : " + e.getMessage());
	}
}
void delete(int learnerId) {
 
  dbc.qry = "delete from learners where learnerId=?";
  try {
		PreparedStatement pstmt = dbc.dbCon.prepareStatement(dbc.qry, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//		Execute the query
		ResultSet theResultSet = pstmt.executeQuery();
    while(theResultSet.next()) {
			if(theResultSet.getInt("learnerId") == learnerId) {
    
      theResultSet.deleteRow();
      System.out.println("Learner got deleted...");
      }
    }
  } catch (SQLException e) {
		System.out.println("Can't get a reference to the PreparedStatement : " + e.getMessage());
	}
}

void list() {
  dbc.qry = "select * from learners";
  try{
    PreparedStatement pstmt = dbc.dbCon.prepareStatement(dbc.qry, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet theResultSet = pstmt.executeQuery();
    while (theResultSet.next()) {
      System.out.print("Id : " + theResultSet.getString("learnerId"));
      System.out.print(", Name : " + theResultSet.getString("learnerName"));
      System.out.println(", Address : " + theResultSet.getString("learnerAddress"));
    }
  }catch (SQLException e) {
		System.out.println("Can't get a reference to the PreparedStatement : " + e.getMessage());
  }
}

void add(String learnerAddress, String learnerName) {
  dbc.qry = "insert into learners(learnerName, learnerAddress) values(?, ?)";

  try {
    // Get a reference to the PreparedStatement
      PreparedStatement pstmt = dbc.dbCon.prepareStatement(dbc.qry, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    
     pstmt.setString(1, learnerName);
     pstmt.setString(2, learnerAddress);

     // Learners learner = new Learners();

    if (pstmt.executeUpdate() > 0)
      System.out.println("Record added...");

  } catch (SQLException e) {
    System.out.println( e.getMessage());
  }
}

void search(String searchTerm) {
  dbc.qry = "select * from learners where learnerName=? or learnerAddress=?";
  try {
		PreparedStatement pstmt = dbc.dbCon.prepareStatement(dbc.qry, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//		Execute the query
		ResultSet theResultSet = pstmt.executeQuery();
   // Learners learner = new Learners();
//		Traverse through the results
        if(theResultSet.getString("learnerName") == theResultSet.getString(1)|| theResultSet.getString("learnerAdress") == theResultSet.getString(2)) {
          System.out.print("Name : " + theResultSet.getString("learnerName"));
          System.out.println(", Address : " + theResultSet.getString("learnerAddress"));
        }
	} catch (SQLException e) {
		System.out.println("Can't get a reference to the PreparedStatement : " + e.getMessage());
	}
}



void sort() {
}











}


