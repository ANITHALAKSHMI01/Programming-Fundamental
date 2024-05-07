package com.chainsys.tododao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import  com.chainsys.todomodel.DoctorTodoList;
import com.chainsys.util.ConnectionUtil;
public  class DoctorLogin implements ToDoDAO
{
	public static DoctorTodoList doctor=new DoctorTodoList();
	public static Scanner scanner=new Scanner(System.in);
	static ToDoList todoList=new ToDoList();
	static DoctorLogin doctorlogin=new DoctorLogin();
	public static String doctor1,doctorName,doctorCategory;
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
		int totalPatients=NurseLogin.taskCount();
		doctor.setTotalPatients(totalPatients);
		System.out.println("                          ***Reminders***                          ");
		System.out.println("                          ===============                          ");
		System.out.println("Total Patients    : "+doctor.getTotalPatients());
		System.out.println("Patients List");
		System.out.println("*************");
		NurseLogin.displayAllPatients();
		int visited=NurseLogin.visitedPatients();
		doctor.setVisitedPatients(visited);
		int remainingPatient=doctor.getTotalPatients()-doctor.getVisitedPatients();
		doctor.setRemainingPatients(remainingPatient);
		System.out.println("Visited Paitients   : "+doctor.getVisitedPatients());
		System.out.println("Patients Waiting    :"+doctor.getRemainingPatients());
		System.out.println("Remaining Patients List");
		System.out.println("========================");
		Connection connection=ConnectionUtil.getConnection();
		String display="select date,appointed_on,patient_name,age,phone_no,disease,doctor_name,doctor_category from todo_list where status='Unvisited'";
		PreparedStatement prepareStatement2=connection.prepareStatement(display);
		ResultSet resultSet=prepareStatement2.executeQuery();
		System.out.println("Date\t\tAppointed on\t\tPatient Name\t\t\tPatient Age\t\tPhoneNo\t\tDisease\t\t\tDoctor Name\t\tDoctor Category");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		while(resultSet.next())
		{
			System.out.println(resultSet.getString(1)+"\t"+resultSet.getString(2)+"\t\t"+resultSet.getString(3)+"\t\t\t\t"+resultSet.getInt(4)+"\t\t\t"+resultSet.getLong(5)+"\t"+resultSet.getString(6)+"\t\t\t"+resultSet.getString(7)+"\t\t\t"+resultSet.getString(8));
		}
//		NurseLogin.displayAllPatients();
	}
}
