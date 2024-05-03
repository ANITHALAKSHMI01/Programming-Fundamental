package com.chainsys.tododao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import com.chainsys.util.ConnectionUtil;
import com.chaisys.todomodel.DoctorTodoList;
public class NurseLogin 
{
	public static Scanner scanner=new Scanner(System.in);
	public static int tokenNumber,patientAge,totalPatients;
	public static String patientName,age,disease,nameOfDoctor,doctorCategory;
//	public static LocalDate LocalTime;
//	public static LocalTime timeNow;
	public static DoctorTodoList doctor=new DoctorTodoList();
	static DoctorLogin doctorLogin=new DoctorLogin();
//	public static ArrayList<LocalDate> date=new ArrayList<>();
//	public static ArrayList<LocalTime> time=new ArrayList<>();
	public  static void userInput() throws ClassNotFoundException, SQLException
	{
		boolean flag=true;
		while(flag)
		{
			System.out.println("1.AddPatient\n2.removePatient\n3.DisplayAllPatients\n4.Quit");
			System.out.println("Please make a choice");
			String choice=scanner.next();
			scanner.nextLine();
			String regex="\\d";
			if(choice.matches(regex))
			{
				int select=Integer.parseInt(choice);
				switch(select)
				{
				case 1    :NurseLogin.insertPatient();
				           break;	   
				case 2    :NurseLogin.removePatient();
			               break;
				case 3    :NurseLogin.displayAllPatients();
				           break;
				case 4    :flag=false;

				System.out.println("Exit successfully...");
				           break;
				default  :System.out.println("*Please enter choice 1-4*");
				          NurseLogin.userInput();
				          break;
				}
			}
			else
			{
				System.out.println("*Your choice should be numeric (1-4)*");
				NurseLogin.userInput();
			}
		}
	}
	public static void patientName()
	{
		System.out.println("Enter Patient Name");
		patientName=scanner.nextLine();
		String regex="^[a-zA-Z\\s.]*$";
		if(patientName.matches(regex))
		{
			NurseLogin.patientAge();
		}
		else
		{
			System.out.println("Name should be alphabet");
			NurseLogin.patientName();
		}
	}
	public static void patientAge()
	{
		System.out.println("Enter Age");
		age=scanner.next();
		scanner.nextLine();
		String regex="\\d{1,3}$";
		if(age.matches(regex))
		{
			patientAge=Integer.parseInt(age);
			if(patientAge>0 && patientAge<=100)
			{
				NurseLogin.disease();
			}
			else
			{
				System.out.println("Age should be 1-100");
				NurseLogin.patientAge();
			}		
		}
		else
		{
			System.out.println("Age should be positive and Numeric");
			NurseLogin.patientAge();
		}	
	}
	public static void disease()
	{
		System.out.println("Enter disease");
		disease=scanner.nextLine();
		String regex="^[a-zA-Z,\\s]*$";
		if(disease.matches(regex))
		{

            nameOfDoctor=doctorLogin.doctorName();
		}
		else
		{
			System.out.println("<Disease should be alphabet>");
			NurseLogin.disease();
		}
	}
	public static boolean insertPatient() throws ClassNotFoundException, SQLException
	{
		LocalDate dateToday = LocalDate.now();
		LocalTime timeNow = LocalTime.now();
//		date.add(dateToday);
//		time.add(timeNow);
		NurseLogin.patientName();
		doctor.setPatientName(patientName);
		doctor.setAge(patientAge);
		doctor.setDisease(disease);
		doctor.setDoctorName(nameOfDoctor);
		doctorCategory=doctorLogin.doctorCategory();
		doctor.setDoctorCategory(doctorCategory);
		ArrayList existingPatient=new ArrayList();
		Connection connection=ConnectionUtil.getConnection();
		String checkPatient="select patient_name from todo_list";
		PreparedStatement prepareStatement=connection.prepareStatement(checkPatient);
		ResultSet resultSet=prepareStatement.executeQuery();
		while(resultSet.next())
		{
			String name=resultSet.getString(1);
			existingPatient.add(name);
		}
		if(existingPatient.contains(doctor.getPatientName()))
		{
			 System.out.println("name already exist");
	         return true;
		}
		else
		{
			String insert="insert into todo_list(patient_name,age,disease,doctor_name,doctor_category)values(?,?,?,?,?)";
			PreparedStatement prepareStatement1=connection.prepareStatement(insert);
//			prepareStatement1.setDate(1,dateToday);
			prepareStatement1.setString(1,doctor.getPatientName());
			prepareStatement1.setInt(2, doctor.getAge());
			prepareStatement1.setString(3, doctor.getDisease());
			prepareStatement1.setString(4, doctor.getDoctorName());
			prepareStatement1.setString(5, doctor.getDoctorCategory());
			System.out.println("Patient details : "+doctor.getPatientName()+" "+doctor.getAge()+" "+doctor.getDisease());
			int rows=prepareStatement1.executeUpdate();
			return false;
		}
	}
	public static List<DoctorTodoList> listOfPatients() throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.getConnection();
		String display="select patient_name,age,disease from todo_list";
		PreparedStatement prepareStatement=connection.prepareStatement(display);
		ArrayList<DoctorTodoList> patients=new ArrayList();
		ResultSet resultSet=prepareStatement.executeQuery();
		 while (resultSet.next()) 
		 {
			 String name=resultSet.getString(1);
			 int age=resultSet.getInt(2);
			 String disease=resultSet.getString(3);
			 doctor.setPatientName(name);
			 doctor.setAge(age);
			 doctor.setDisease(disease);
			 patients.add(doctor);
		 }
		 return patients;
	}
	public static void displayAllPatients() throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.getConnection();
		String display="select * from todo_list";
		PreparedStatement prepareStatement=connection.prepareStatement(display);
		ResultSet resultSet=prepareStatement.executeQuery();
		System.out.println("Patient Name\t\t\tPatient Age\t\tDisease\t\t\tDoctor Name\t\tDoctor Category");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
		while(resultSet.next())
		{
			System.out.println(resultSet.getString(2)+"\t\t\t\t"+resultSet.getInt(3)+"\t\t\t"+resultSet.getString(4)+"\t\t\t"+resultSet.getString(5)+"\t\t\t"+resultSet.getString(6));
		}
//		NurseLogin.taskCount();
	}
	public static void removePatient() throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.getConnection();
		System.out.println("Enter Token Number for Visited/Un-Visited Patients");
		String tokenNo=scanner.next();
		String regex="\\d";
		if(tokenNo.matches(regex))
		{
			tokenNumber=Integer.parseInt(tokenNo);
			scanner.nextLine();
			String delete="delete from todo_list where s_no=?";
			PreparedStatement prepareStatement=connection.prepareStatement(delete);
			prepareStatement.setInt(1, tokenNumber);
			int rows=prepareStatement.executeUpdate();	
			if(rows>0)
			{
				System.out.println(rows+" Patient Visited/Unvisited removed at tokenNo "+tokenNumber+" successfully");
			}
			else
			{
				System.out.println("!!Patient Visited/No patient appointed!! at tokenNo "+tokenNo);
			}
		}
		else
		{
			System.out.println("=>Token Number should be positive and numeric");
			NurseLogin.removePatient();
		}
	}
//	public static void updatePatientdetail()
//	{
//		
//	}
	public static int taskCount() throws SQLException, ClassNotFoundException
	{
		Connection connection=ConnectionUtil.getConnection();
//		int totalPatients;
//		totalPatients=name.size();
//		return totalPatients;
		String count="select count(*) from todo_list "; 
		PreparedStatement prepareStatement=connection.prepareStatement(count);
		ResultSet rs =prepareStatement.executeQuery();
		if(rs.next()) 
		{ 
			doctor.setTotalPatients(rs.getInt(1));
			totalPatients=doctor.getTotalPatients();
//		   System.out.println(rs.getInt(1)); 
		}
		return totalPatients;
	}
	public static int visitedPatients() throws ClassNotFoundException, SQLException
	{
		int count=0;
		System.out.println("Did you complete any patient checkup(Yes-'Y or y' No-'N or n')");
		char ch=scanner.next().charAt(0);
		if(ch=='y' || ch=='Y' || ch=='n' || ch=='N')
		{
			if(ch=='y' || ch=='Y')
			{
				NurseLogin.removePatient();
				count++;
			}
			else
			{
				System.out.println("You didn't checkup patient...");
			}
		}
		else
		{
			System.out.println("Please enter Yes-'Y or y' No-'N or n'");
			NurseLogin.visitedPatients();
		}
		return count;
	}

}
