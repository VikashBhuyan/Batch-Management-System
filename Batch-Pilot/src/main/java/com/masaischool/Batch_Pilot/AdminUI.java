package com.masaischool.Batch_Pilot;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.masai.dao.BatchDAO;
import com.masai.dao.BatchDAOImp;
import com.masai.dao.EMUtils;
import com.masai.dao.FacultyDAO;
import com.masai.dao.FacultyDAOImp;
import com.masai.entity.Batch;
import com.masai.entity.Faculty;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;
import com.masai.services.BatchService;
import com.masai.services.BatchServiceImp;

import jakarta.persistence.EntityManager;


public class AdminUI {
	public static void createBatch(Scanner sc) {
		System.out.print("Enter course name ");
		String courseName = sc.next();
		System.out.print("Enter number of seats ");
		int seat = sc.nextInt();
		System.out.print("Enter start date");
		LocalDate date = LocalDate.parse(sc.next());
		System.out.println("Enter duration in weeks");
		double duration = sc.nextDouble();
		Batch b = new Batch(courseName, seat, date, duration, null);

		BatchService bso = new BatchServiceImp();
		try {
			bso.addBatch(b);
			System.out.println("Batch Added SuccessFully");
		} catch (SomeThingWentWrongException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void updateBatch(Scanner sc)
	{
		System.out.println("Enter Batch id to update");
		int id = sc.nextInt();
		System.out.println("Enter Updated Course Name");
		String name = sc.next();
		System.out.println("Enter UpdatedNumber of Seats");
		int seat = sc.nextInt();
		System.out.println("Enter Updated Duration");
		double duration = sc.nextDouble();
		BatchService bso = new BatchServiceImp();
		try {
			bso.updateBatch(id, name, seat, duration);
			System.out.println("Batch updated SuccessFully");
		} catch (SomeThingWentWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void viewBranchbyId(Scanner sc) {
		Batch b = null;
		System.out.println("Enter Batch id to view Batch");
		int id = sc.nextInt();
		BatchService bso = new BatchServiceImp();
		try {
			b = bso.findBatchById(id);
		} catch (NoRecordFoundException | SomeThingWentWrongException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(b);
	}
	public static void viewAllBatch() {
		// TODO Auto-generated method stub
		List<Batch> bl = null;
		BatchService bso = new BatchServiceImp();
		try {
			bl = bso.getBatchList();
			for(Batch x : bl)
			{
				System.out.println(x);
			}
		}catch(SomeThingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}
	public static void deleteBranchById(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("Eneter id of Batch to be deleted");
		int id = sc.nextInt();
		BatchService bso = new BatchServiceImp();
		try {
			bso.deleteBatch(id);
		} catch (NoRecordFoundException | SomeThingWentWrongException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Batch Deleted SuccessFully");
	}
	public static void batchviewbyfaculty(Scanner sc) {
		// TODO Auto-generated method stub
		List<Batch> li = null;
		System.out.println("ENter Faculty id to view Batch");
		int id = sc.nextInt();
		FacultyDAO fdao = new FacultyDAOImp();
		try {
			li = fdao.getBatchByFaculty(id);
		} catch (SomeThingWentWrongException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Batch x : li)
		{
			System.out.println(x);
		}
	}
	public static void assignBatchToFaculty(Scanner sc) {
		// TODO Auto-generated method stub
		Batch b = null;
		Faculty f = null;
		System.out.println("Enter batch id to be assigned");
		int batch_id = sc.nextInt();
		System.out.println("Enter faculty id to whom to assign");
		int fac_id = sc.nextInt();
		
		BatchService bso = new BatchServiceImp();
		try {
			b = bso.findBatchById(batch_id); 
			
		} catch (NoRecordFoundException | SomeThingWentWrongException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacultyDAO fdao = new FacultyDAOImp();
		try {
			f = fdao.getFacultyById(fac_id);
		} catch (NoRecordFoundException | SomeThingWentWrongException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bso.assignBatchToFec(b,f);
		System.out.println("Batch added to feculty successful");
		
	}
}
