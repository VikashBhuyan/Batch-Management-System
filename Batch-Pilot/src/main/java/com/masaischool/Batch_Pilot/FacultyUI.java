package com.masaischool.Batch_Pilot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.masai.dao.FacultyDAO;
import com.masai.dao.FacultyDAOImp;
import com.masai.entity.Batch;
import com.masai.entity.Faculty;
import com.masai.entity.LoggedInUserId;
import com.masai.exception.SomeThingWentWrongException;

public class FacultyUI {
	static void showBatchs() {
		FacultyDAO f = new FacultyDAOImp();
		List<Batch> b = new ArrayList<>();
		try {
		b = f.getBatchDetails();
			
		} catch (SomeThingWentWrongException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Batch x : b)
		{
			System.out.println(x);
		}
	}
	
	static void deleteAccount() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Are you sure you want to delete your account?[y/n] ");
		char choice = sc.next().toLowerCase().charAt(0);
		if(choice == 'y') {
			try {
				FacultyDAO f = new FacultyDAOImp();
				f.deleteAccount();
				System.out.println("Account Deleted SuccessFully");			
			}catch(SomeThingWentWrongException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	static void userMenu(){
		Scanner sc = new Scanner(System.in);
		System.out.println("1. Show My Batch");
		System.out.println("2. Delete Account");
		System.out.println("0. Logout");
		int choice = 0;
		do {
			System.out.print("Enter selection ");
			choice = sc.nextInt();
    		switch(choice) {
    			case 1:
    				showBatchs();
    				break;
    			case 2:
    				deleteAccount();
    				break;
    			case 0:
    				LoggedInUserId.loggedInUserId = -1;	//-1 id cannot belong to any customer
    				System.out.println("Bye Bye User");
    				break;
    			default:
    				System.out.println("Invalid Selection, try again");
    		}
    	}while(choice != 0);
	}
	static void Login(String username, String password) {
		try {
			FacultyDAO f = new FacultyDAOImp();
			f.login(username, password);
			userMenu();
		}catch( SomeThingWentWrongException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void facultyRegistration(Scanner sc) {
		// TODO Auto-generated method stub
		//code to take input
				System.out.print("Enter first name ");
				String first_name = sc.next();
				System.out.print("Enter last name ");
				String last_name = sc.next();
				System.out.print("Enter username ");
				String username = sc.next();
				System.out.print("Enter password ");
				String password = sc.next();
				System.out.print("Enter mobile_no ");
				String mobile = sc.next();
				System.out.println("Enter Address");
				String address = sc.next();
				Faculty fac = new Faculty(first_name, last_name, username, password, mobile, address, null);
				try {
					//Create an object of CustomerService
					FacultyDAO fdao = new FacultyDAOImp();
					fdao.addFaculty(fac);
					System.out.println("Faculty added successfully");
				}catch(SomeThingWentWrongException ex) {
					System.out.println(ex);
				}
	}
}
