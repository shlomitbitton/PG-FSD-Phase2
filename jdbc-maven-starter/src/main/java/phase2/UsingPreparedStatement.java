package phase2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class UsingPreparedStatement {
	String qry;
	Connection dbCon;
	PreparedStatement preparedStatement;

	public UsingPreparedStatement() {

		try {
			// Define the URL to connect
			String urlToConnect = "jdbc:mysql://localhost:3306/simplilearn_Phase2";

			// Define the username for db to connect
			String dbUserName = "root";

			// Define the password
			String dbUserPassword = "oiccmmp135";

			// Define the driver for the database
			String mySqlDriver = "com.mysql.cj.jdbc.Driver";

			// Load the Driver
			Class.forName(mySqlDriver);

			// Try to establish the connection
			dbCon = DriverManager.getConnection(urlToConnect, dbUserName, dbUserPassword);

		} catch (ClassNotFoundException e) {
			System.out.println("Can't load the Driver : " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Can't connect to DB : " + e.getMessage());
		}
	}



	// Insert a new record in the table:learners using PreparedStatement
	void addNewLearner(String learnerName, String learnerAddress) {
		// Write the query to insert
		qry = "insert into learners(learnerName, learnerAddress) values(?, ?)";

		try {
			// Get a reference to the PreparedStatement
			preparedStatement = dbCon.prepareStatement(qry);

			// Set the values for ?
			preparedStatement.setString(1, learnerName);
			preparedStatement.setString(2, learnerAddress);

			// Execute the query
			if (preparedStatement.executeUpdate() > 0)
				System.out.println("Record added...");

		} catch (SQLException e) {
			System.out.println("Can't get a reference to the PreparedStatement : " + e.getMessage());
		}
	}

	void deleteLearnerById(int learnerId){

		qry="delete from learners where learnerId=?";
		try{
			preparedStatement = dbCon.prepareStatement(qry);
			preparedStatement.setInt(1, learnerId);
			if (preparedStatement.executeUpdate() > 0)
				System.out.println("Record got deleted...");

		}catch(SQLException e){
			System.out.println("Can't get a reference to the PreparedStatement : " + e.getMessage());
		}
	}
	void updateLearnerNameAndAddress(String learnerAddress, int learnerId){
		System.out.println(learnerId+ " "+ learnerAddress);
		getLearnerDetailsById(learnerId);
		// qry="UPDATE learners SET learnerAddress='"+learnerAddress+"' WHERE learnerId='"+learnerId+"'";
		qry="UPDATE learners SET learnerAddress=? WHERE learnerId=?";
		// try {
		// 	Statement s =dbCon.createStatement();
		// 	s.executeUpdate(qry);
		// } catch (SQLException e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }

		try{
			preparedStatement = dbCon.prepareStatement(qry);
			preparedStatement.setInt(2, learnerId);
			preparedStatement.setString(1, learnerAddress);	
				if(preparedStatement.executeUpdate()>0)//Very important to not have qry insid executeUpdate when usig perparedStatement!!
					System.out.println("Successfully updated learner...");
		}catch(SQLException e){
			System.out.println("Can't get a reference to the PreparedStatement : " + e.getMessage());
		}
	}

	// Fetch all details of a learner by id from table:learners
	void getLearnerDetailsById(int id) {
		// Write the query
		qry = "select * from learners where learnerId = ?";

		try {
			// Get a reference to the PreparedStatement
			preparedStatement = dbCon.prepareStatement(qry);

			// Set the value for ?
			preparedStatement.setInt(1, id);

			// Execute the query
			ResultSet theResultSet = preparedStatement.executeQuery();

			// Traverse through the results
			while (theResultSet.next()) {
				System.out.print("Name : " + theResultSet.getString("learnerName"));
				System.out.println(", Address : " + theResultSet.getString("learnerAddress"));
			}

		} catch (SQLException e) {
			System.out.println("Can't get a reference to the PreparedStatement : " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		Scanner theScanner = new Scanner(System.in);
	//	Scanner addressScanner = new Scanner(System.in);

		// System.out.println("Please enter name and address");

		// new UsingPreparedStatement().addNewLearner(theScanner.nextLine(),
		// theScanner.nextLine());

		System.out.println("Enter id : ");

		int id = theScanner.nextInt();
		theScanner.nextLine();

		System.out.println("Enter new Address : ");
		String newAddress=theScanner.nextLine();

	//	new UsingPreparedStatement().getLearnerDetailsById(id);
	//	new UsingPreparedStatement().deleteLearnerById(id);
		new UsingPreparedStatement().updateLearnerNameAndAddress(newAddress, id);

		
	}

}
