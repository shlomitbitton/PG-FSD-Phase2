package phase2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App 
{
	String qry;
	Connection dbCon;
	Statement theStatement;
	
	App(){

    	try {
//    		Define the URL to connect
    		String urlToConnect = "jdbc:mysql://localhost:3306/simplilearn_Phase2";
    		
//    		Define the username for db to connect
    		String dbUserName = "root";
    		
//    		Define the password
    		String dbUserPassword = "oiccmmp135";
    		
//    		Define the driver for the database
    		String mySqlDriver = "com.mysql.jdbc.Driver";
    		
    		
//        	Load the Driver
			Class.forName(mySqlDriver);
			
//			Try to establish the connection
			dbCon = DriverManager.getConnection(urlToConnect, dbUserName, dbUserPassword);
			
//        	Get a reference to the Statement
			theStatement = dbCon.createStatement();
			
			// System.out.println("Successfully connected to the database...");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Can't load the Driver : " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Can't connect to DB : " + e.getMessage());
		}
	}


    
//    Get All Records from table:learners
void getAllRecords() {
	//    	Write the query to fetch all rows from table:learners
				qry = "select * from learners";
	
				try {
	
				
	//			Execute the query
				ResultSet theResultSet = theStatement.executeQuery(qry);
				
	//			Traverse through the results
				while(theResultSet.next()) {
					System.out.print("Name : " + theResultSet.getString("learnerName"));
					System.out.print(", ID : " + theResultSet.getInt("learnerId"));
					System.out.println(", Address : " + theResultSet.getString("learnerAddress"));
				}
				
			} catch (SQLException e) {
				System.out.println("Can't get a reference to Statement : " + e.getMessage());
			}
			}
			
			
		 
	// Get Learner details for a particular id
			void getLearnerDetailsById(int id) {
	//    	Write the query to fetch details from the table:learners
				qry = "select * from learners where learnerId = " + id;
				
	
				try {
	//        	Execute the query
				ResultSet theResultSet = theStatement.executeQuery(qry);
				
				System.out.println("Details of learner for id : " + id);
				
	//			Traverse through the results
				while(theResultSet.next()) {
					System.out.println("Name : " + theResultSet.getString("learnerName") + ", Address : " + theResultSet.getString("learnerAddress"));
				}
				
				
			} catch (SQLException e) {
				System.out.println("Can't execute the query : " + e.getMessage());
			}
			}
			
			
	
			//Add a new Learner to the table:learners
			void addNewLearner() {
	//    Write the query to insert a new record
				qry = "insert into learners(learnerName, learnerAddress) values ('Alexander', 'Seattle, USA')";
				
	
				try {
	//        	Execute the query
				int result = theStatement.executeUpdate(qry);
				
				if(result > 0)
					System.out.println("New Learner details addedd successfully...");
			} catch (SQLException e) {
				System.out.println("Can't add to the table : " + e.getMessage());
			}
			}
	
			void deleteLearner(int id){
	
				qry="delete from learners where learnerId='4'";
			
				try{
					if(theStatement.executeUpdate(qry)>0){
						System.out.println("Successfully deleted the learner...");
					}else{
						System.out.println("ID not found for deletion...");
					};
				}catch(SQLException e){
						System.out.println("Cannot delete entry: " + e.getMessage());
				}
			}
			
			
	//    Add a new Learner to the table:learners - learner details taken from runtime
			void addNewLearnerFromRuntime(String lName, String lAddress) {
	//    	Write the query to insert a new record
				qry = "insert into learners(learnerName, learnerAddress) "
						+ "values "
						+ "('" 
						+ lName + "','" 
						+ lAddress + "')";
				
	
				try {
	//        	Execute the query
				if(theStatement.executeUpdate(qry) >  0)
					System.out.println("Successfully added a new learner...");
			} catch (SQLException e) {
				System.out.println("Can't add to the table : " + e.getMessage());
			}
			}

	
    public static void main( String[] args )
    {
   
    	
   	// System.out.println("Please enter the Name, Address of Learner to add :");
   	
   	// Scanner theScanner = new Scanner(System.in);
   	
   	// String learnerName = theScanner.nextLine();
   	
   	// String learnerAddress = theScanner.nextLine();
   	
  	// new App().addNewLearnerFromRuntime(learnerName, learnerAddress);
   	
//    	
   	// int id = new Scanner(System.in).nextInt();
   	
   	// new App().getLearnerDetailsById(id);
    	
   	new App().addNewLearner();

	// theScanner.close();

		new App().deleteLearner(1);
		new App().getAllRecords();

		new App().addNewLearner();
    	
    }
    
    
    
    
}
