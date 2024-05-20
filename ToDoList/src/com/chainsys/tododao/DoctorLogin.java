package com.chainsys.tododao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import  com.chainsys.todomodel.DoctorTodoList;
import com.chainsys.util.ConnectionUtil;
public  class DoctorLogin implements ToDoDAO
{
	public static DoctorTodoList doctor=new DoctorTodoList();
	public static Scanner scanner=new Scanner(System.in);
	static ToDoList todoList=new ToDoList();
	static LocalDate dateToday = LocalDate.now();
    static String dateString =dateToday.toString();
	static DoctorLogin doctorlogin=new DoctorLogin();
	public static String  userName,email,password,role,doctor1,doctorName,doctorCategory,doctorName1;
	static long phoneNo;
	public static ArrayList<String> existingDate = new ArrayList<>();
	public static int tokenNumber;
	public static void signUpDetails(String nameOfDoctor) throws ClassNotFoundException, SQLException
	{
		userName=ToDoList.userName();
		doctorName=DoctorLogin.updateDoctor();
		phoneNo=ToDoList.phoneNo();
		email=ToDoList.emailId();
		password=ToDoList.password1();
		role=ToDoList.role(doctorName);
		Connection connection=ConnectionUtil.getConnection();
		String insert="insert into todo_register_login(username,doctor_name,phone_no,email_id,password,role)values(?,?,?,?,?,?)";
		PreparedStatement prepareStatement1=connection.prepareStatement(insert);
		prepareStatement1.setString(1,userName);
		prepareStatement1.setString(2,doctorName);
		prepareStatement1.setLong(3,phoneNo);
		prepareStatement1.setString(4,email);
		prepareStatement1.setString(5, password);
		prepareStatement1.setString(6,role);
		prepareStatement1.executeUpdate();
	}
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
	public static int isPatientChecked(int count) throws ClassNotFoundException, SQLException 
	{
		Connection connection=ConnectionUtil.getConnection();
		System.out.println("Did you complete any patient checkup(Yes-'Y or y' No-'N or n')");
		char ch=scanner.next().charAt(0);
		if(ch=='y' || ch=='Y')
		{
			System.out.println("Enter token no for updation");
			String tokenNo=scanner.next();
			String regex="\\d";
			if(tokenNo.matches(regex))
			{
				tokenNumber=Integer.parseInt(tokenNo);
				scanner.nextLine();
				String update="update todo_list set status='Visited' where s_no=?";
				PreparedStatement prepareStatement=connection.prepareStatement(update);
				prepareStatement.setInt(1, tokenNumber);
				prepareStatement.executeUpdate();	
				count++;
			}
			else if(ch=='n' || ch=='N')
			{
				System.out.println("You didn't checkup patient...");
			}
			else
			{
				try
				{
					throw new InvalidData();
				}
				catch(InvalidData i1)
				{
					System.out.println(i1.getMessage());
				}
				ToDoList.visitedPatients();
			}
		}
		connection.close();
		return count;
	}
	@Override
	public void doctorLogin() throws ClassNotFoundException, SQLException
	{
		System.out.println("Doctor's Login Page");
		System.out.println("...................");
		todoList.signUp(doctorName);
	}
	public static void reminder() throws ClassNotFoundException, SQLException
	{
		
		
		DoctorTodoList doctor=new DoctorTodoList();
		int totalPatients=ToDoList.taskCount();
		doctor.setTotalPatients(totalPatients);
		existingDate=ToDoList.remindData();
		System.out.println("                          ***Reminders***                          ");
		System.out.println("                          ===============                          ");
		System.out.println("Patients List");
		System.out.println("*************");
		if(existingDate.contains(dateString))
		{
			System.out.println("Total Patients    : "+doctor.getTotalPatients());
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
		}
		else
		{
			System.out.println("No Patients Appointed");
		}
	}
}
