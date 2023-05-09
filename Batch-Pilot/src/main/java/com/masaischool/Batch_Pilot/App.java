package com.masaischool.Batch_Pilot;

import java.util.Scanner;

public class App 
{
	static void displayAdminMenu() {
		System.out.println("1. Create Batch");
		System.out.println("2. Update Batch with id");
		System.out.println("3. Assign Batch to Faculty");
		System.out.println("4. View Batch By Id");
		System.out.println("5. View Batchs by perticular Faculty");
		System.out.println("6. View All Batchs");
		System.out.println("7. Delete Batch by Id");
		System.out.println("0. Logout");
	}
	
	static void adminMenu(Scanner sc) {
		int choice = 0;
		do {
			displayAdminMenu();
			System.out.print("Enter selection ");
			choice = sc.nextInt();
    		switch(choice) {
    			case 1:
    				AdminUI.createBatch(sc);
    				break;
    			case 2:
    				AdminUI.updateBatch(sc);
    				break;
    			case 3:
    				AdminUI.assignBatchToFaculty(sc);
    				break;
    			case 4:
    				AdminUI.viewBranchbyId(sc);
    				break;
    			case 5:
    				AdminUI.batchviewbyfaculty(sc);
    				break;
    			case 6:
    				AdminUI.viewAllBatch();
    				break;
    			case 7:
    				AdminUI.deleteBranchById(sc);
    				break;
    			case 0:
    				System.out.println("Bye Bye Admin");
    				break;
    			default:
    				System.out.println("Invalid Selection, try again");
    		}
    	}while(choice != 0);	
	}
	
	static void adminLogin(Scanner sc) {
		System.out.print("Enter username ");
		String username = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();
		if(username.equals("admin") && password.equals("admin")) {
			adminMenu(sc);
		}else {
			System.out.println("Invalid Username of Password");
		}
	}
	static void userLogin(Scanner sc)
	{
		System.out.println("Enter username");
		String username = sc.next();
		System.out.println("Enter password");
		String password = sc.next();
		FacultyUI.Login(username, password);
		
	}
    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);
    	int choice = 0;
    	do {
    		System.out.println("1. Admin Login");
    		System.out.println("2. Faculty Login");
    		System.out.println("3. Faculty Registration");
    		System.out.println("0. Exit");
    		System.out.print("Enter Selection ");
    		choice = sc.nextInt();
    		switch(choice) {
    			case 1:
    				adminLogin(sc);
    				break;
    			case 2:
    				userLogin(sc);
    				break;
    			case 3:
    				FacultyUI.facultyRegistration(sc);
    				break;
    			case 0:
    				System.out.println("Thanks for using the services");
    				break;
    			default:
    				System.out.println("Invalid Selection, try again");
    		}
    	}while(choice != 0);
    	sc.close();
    }
}
