package phase2;

import java.util.Scanner;

public class LearnersProjectMain {

  static Scanner s = new Scanner(System.in);
  MenuOperations mo= new MenuOperations();


  void menuSwicth(){
    String exit = "";
    while (!exit.equalsIgnoreCase("exit")) {
      System.out.println("Please type your menu choice: ");
      String choice = s.nextLine();
      
      switch (choice) {
        case "A":
          System.out.println("Enter learner name");
          String learnerName = s.nextLine();
          System.out.println("Enter learner address");
          String learnerAddress = s.nextLine();
          System.out.println("Enter learner last name");
          String learnerLastName = s.nextLine();
          mo.add(learnerAddress, learnerName, learnerLastName);
          break;
        case "L":
          System.out.println("Display all learners details");
          mo.list();
          break;
        case "S":
          System.out.println("Enter learner name or address to search");
          String searchTerm=s.nextLine();
          mo.search(searchTerm);
          break;
        case "U":
          System.out.println("Enter learner to update");  
          int learnerId = s.nextInt();
          s.nextLine();
          String updatedLearnerName=s.nextLine();
          mo.fetchAndUpdate(learnerId,updatedLearnerName);
          break;
        case "D":
          mo.list();
          System.out.println("Enter learner id to delete"); 
          int learnerIdToDelete=s.nextInt();
          s.nextLine();
          mo.delete(learnerIdToDelete);
          break;
        case "Sort":
          System.out.println("Sort by 1. First name, 2. Last name, 3. Address or 4. Id?"); 
          int sortingChoice = s.nextInt();
          s.nextLine();
          mo.sort(sortingChoice);
          break;
        case "exit":
          System.out.println("Goodbye!");
          System.exit(1);
          s.close();
          break;
      }
    }
  }

  void manu(){
    System.out.println("                                                ");
    System.out.println("Main menu");
    System.out.println("==================");
    System.out.println("A: Enter New learner details");
    System.out.println("L: Display all learners with their details");
    System.out.println("S: Search learner by name or address");
    System.out.println("U: Update learner details");
    System.out.println("D: Delete learner");
    System.out.println("Sort: Sort learners list by first name, last name, address or id");
    System.out.println("E: Exit");
  
  }
  public static void main(String[] args){
    new DataBaseConnection();
    new LearnersProjectMain().manu();
    new LearnersProjectMain().menuSwicth();

  }
}
