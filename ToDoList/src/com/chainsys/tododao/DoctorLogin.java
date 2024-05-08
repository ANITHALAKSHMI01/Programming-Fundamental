package com.chainsys.tododao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import  com.chainsys.todomodel.DoctorTodoList;
import com.chainsys.util.ConnectionUtil;
public  class DoctorLogin implements ToDoDAO
{
	public static DoctorTodoList doctor=new DoctorTodoList();
	public static Scanner scanner=new Scanner(System.in);
	static ToDoList todoList=new ToDoList();
	static DoctorLogin doctorlogin=new DoctorLogin();
	public static String doctor1,doctorName,doctorCategory,doctorName1;
	@Override
	public String doctorName() 
	{
		doctor.setDoctorName("Nikilan");
		System.out.println("Enter Doctor Name");
		doctorName=scanner.nextLine();
		String regex="^[a-zA-Z\\s.]*$";
		if(doctorName.matches(regex))
		{
			if(doctorName.equals(doctor.getDoctorName()))
			{
				doctor.setDoctorName(doctorName);
				doctor1=doctor.getDoctorName();
				System.out.println("**Patient appointed successfully**");
			}
			else
			{
				System.out.println("Please enter doctor name correctly...");
				doctorlogin.doctorName();
			}	
		}
		else
		{
			System.out.println("Name should be alphabet");
			doctorlogin.doctorName();
		}
		return doctor1;
	}
	public static String updateDoctor() throws ClassNotFoundException, SQLException
	{
		System.out.println("Enter Doctor Name");
		doctorName=scanner.nextLine();
		String regex="^[a-zA-Z\\s.]*$";
		if(doctorName.matches(regex))
		{
			doctor.setDoctorName(doctorName);
		}
		else
		{
			System.out.println("Name should be alphabet");
			DoctorLogin.updateDoctor();
		}
		return doctorName;
	}
	@Override
	public String doctorCategory() 
	{
		doctor.setDoctorCategory("General");
		return doctorCategory=doctor.getDoctorCategory();
	}
	public static String updateDoctorCategory()
	{
		System.out.println("Enter Doctor Category");
		doctorCategory=scanner.nextLine();
		String regex="^[a-zA-Z\\s]*$";
		if(doctorCategory.matches(regex))
		{
			doctor.setDoctorCategory(doctorCategory);
		}
		else
		{
			System.out.println("Doctor Category should be alphabet");
			DoctorLogin.updateDoctorCategory();
		}
		return doctorCategory;
	}
	@Override
	public void doctorLogin() throws ClassNotFoundException, SQLException
	{
		System.out.println("Doctor's Login Page");
		System.out.println("...................");
		todoList.signUp(doctorName);
		DoctorLogin.reminder();
	}
	public static void reminder() throws ClassNotFoundException, SQLException
	{
		DoctorTodoList doctor=new DoctorTodoList();
//		doctorName1=DoctorLogin.updateDoctor();
		int totalPatients=ToDoList.taskCount();
		doctor.setTotalPatients(totalPatients);
		System.out.println("                          ***Reminders***                          ");
		System.out.println("                          ===============                          ");
		System.out.println("Total Patients    : "+doctor.getTotalPatients());
		System.out.println("Patients List");
		System.out.println("*************");
//		NurseLogin.displayAllPatients();
		ToDoList.displayDetails();
		int visited=ToDoList.visitedPatients();
		doctor.setVisitedPatients(visited);
		int remainingPatient=doctor.getTotalPatients()-doctor.getVisitedPatients();
		doctor.setRemainingPatients(remainingPatient);
		System.out.println("Visited Paitients   : "+doctor.getVisitedPatients());
		System.out.println("Patients Waiting    :"+doctor.getRemainingPatients());
		System.out.println("Waiting Patients List");
		System.out.println("========================");
		ToDoList.displayDetails();
//		NurseLogin.displayAllPatients();
	}
}
