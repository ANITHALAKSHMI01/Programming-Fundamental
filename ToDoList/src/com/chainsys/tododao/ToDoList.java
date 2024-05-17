package com.chainsys.tododao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.chainsys.todomodel.DoctorTodoList;
import com.chainsys.util.ConnectionUtil;
public class ToDoList
{
	public static DoctorTodoList doctor=new DoctorTodoList();
	public static ToDoList todoList=new ToDoList();
	public static ArrayList<String> existingEmail = new ArrayList<String>();
	public static ArrayList<String> existingPassword = new ArrayList<>();
	public static ArrayList<String> existingDoctor = new ArrayList<>();
	static DoctorLogin doctorlogin=new DoctorLogin();
	public static String emailId,nameOfDoctor,username,email,password,role;
	public static long phoneNo,phoneNo1;
	public static int totalPatients,count,tokenNumber;
	public static Scanner scanner=new Scanner(System.in);
	public void signUp() throws ClassNotFoundException, SQLException
	{
		System.out.println("Do you have Account(Yes or No)");
		String ch=scanner.next();
		if(ch.equals("yes") || ch.equals("Yes"))
		{
			System.out.println("Nurse SignIn Page");
		    System.out.println("````````````````"); 
		    todoList.signIn();
		    NurseLogin.userInput();
		}
		else if(ch.equals("no") || ch.equals("No"))
		{
			  System.out.println("Nurse SignUp Page");
		      System.out.println("````````````````"); 
		      NurseLogin.signUpDetails();
		      NurseLogin.userInput();
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
			todoList.signUp();
		}

	}
	public void signUp(String doctorName) throws ClassNotFoundException, SQLException
	{

		System.out.println("Do you have Account(Yes or No)");
		String ch=scanner.next();
		if(ch.equals("yes" )|| ch.equals("Yes"))
		{
			System.out.println("Doctor SignIn Page");
		    System.out.println("````````````````"); 
		    todoList.signIn(nameOfDoctor);
		}
		else if(ch.equals("no") || ch.equals("No"))
		{
			System.out.println("Doctor SignUp Page");
		    System.out.println("````````````````");
		    NurseLogin.signUpDetails(nameOfDoctor);
		    todoList.signIn(nameOfDoctor);
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
			todoList.signUp(nameOfDoctor);
		}

	}
	public static String userName() throws ClassNotFoundException, SQLException
	{
		System.out.println("Enter the Username");
		String username1=scanner.next();
		String regex="^[a-zA-Z0-9]*";
		if(username1.matches(regex))
		{
		    ArrayList<String> existingUsername = new ArrayList<>();
	        Connection connection = ConnectionUtil.getConnection();
	        String username2 = "select username from todo_register_login";
	        PreparedStatement prepareStatement = connection.prepareStatement(username2);
	        ResultSet resultSet = prepareStatement.executeQuery();
	        while (resultSet.next()) 
	        {
	            String user = resultSet.getString(1);
	            existingUsername.add(user);
	        }
	        if(existingUsername.contains(username1))
	        {
	        	System.out.println("*Username already exist*");
	        	ToDoList.userName();
	        }
	        else
	        {
	        	doctor.setUsername(username1);
				username=doctor.getUsername();
	        }
		}
		else
		{
			System.out.println("*Username should be alphanumeric*");
			ToDoList.userName();
		}
		return username;
	}
	public static long phoneNo() throws SQLException, ClassNotFoundException
	{
		System.out.println("Enter Phone number");
		try 
		{
			phoneNo1=scanner.nextLong();
		}
		catch(InputMismatchException i1)
		{
			System.out.println(i1.getMessage());
		}
		scanner.nextLine();
		String stringPhoneNo=Long.toString(phoneNo1);
		String regex="(91|0)?[6-9][0-9]{9}$";
		if(stringPhoneNo.matches(regex))
		{
			ArrayList<String> existingPhoneNo = new ArrayList<>();
			Connection connection = ConnectionUtil.getConnection();
			String phoneNumber = "select phone_no from todo_register_login";
			PreparedStatement prepareStatement = connection.prepareStatement(phoneNumber);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) 
			{
				String phoneno = resultSet.getString(1);
				existingPhoneNo.add(phoneno);
			}
			if(existingPhoneNo.contains(stringPhoneNo))
			{
				System.out.println("*PhoneNo already exist*");
				ToDoList.phoneNo();
			}
			else
			{
				doctor.setPhoneNo(phoneNo1);
				phoneNo=doctor.getPhoneNo();
			}
		}
		else
		{
			System.out.println("Phone Number should start 6-9 & must 10 digits");
			ToDoList.phoneNo();
		}
		return phoneNo;
	}
	public static String emailId() throws ClassNotFoundException, SQLException
	{
		System.out.println("Enter the EmailId");
		String email=scanner.next();
		String regex="(^[a-z])[a-z0-9]+[@][a-z0-9]+\\.[a-zA-Z]{2,}";
		if(email.matches(regex))
		{
			Connection connection = ConnectionUtil.getConnection();
			String emailId1 = "select email_id from todo_register_login";
			PreparedStatement prepareStatement = connection.prepareStatement(emailId1);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) 
			{
				String emailId2 = resultSet.getString(1);
				existingEmail.add(emailId2);
			}
			if(existingEmail.contains(email))
			{
				System.out.println("*Email already exist*");
				ToDoList.emailId();
			}
			else
			{
				doctor.setEmail(email);
				emailId=doctor.getEmail();
			}
		}
		else
		{
			System.out.println("*Email should start with LowerCase,Don't use UpperCase inbetween also*");
			ToDoList.emailId();
		}
		return emailId;
	}
	public static String password1() throws ClassNotFoundException, SQLException
	{
		System.out.println("Enter the password");
		String password1=scanner.next();
		String regex="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@!#$%^&*]).{6}$";
		if(password1.matches(regex))
		{
			Connection connection = ConnectionUtil.getConnection();
			String passWord = "select password from todo_register_login";
			PreparedStatement prepareStatement = connection.prepareStatement(passWord);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) 
			{
				String password2 = resultSet.getString(1);
				existingPassword.add(password2);
			}
			if(existingPassword.contains(password1))
			{
				System.out.println("*Password already exist*");
				ToDoList.password1();	
			}
			else
			{
				doctor.setPassword(password1);
				password=doctor.getPassword();
				System.out.println("Registered Successfully...");
			}
		}
		else
		{
			System.out.println("*Password should contain atleast one UpperCase,LowerCase,Number and Special Character*");
			System.out.println("*Password must be 6 characters*");
			ToDoList.password1();	
		}
		return password;
	}
	public static String role()
	{
		doctor.setRole("Nurse");
        role=doctor.getRole();
        return role;
	}
	public static String role(String doctorName) throws ClassNotFoundException, SQLException
	{
		doctor.setRole("Doctor");
        role=doctor.getRole();
        return role;
	}
	public void signIn() throws ClassNotFoundException, SQLException
	{
	    System.out.println("Sign In");
	    System.out.println("-------");
		System.out.println("Enter the EmailId");
		email=scanner.next();
		String regex="(^[a-z])[a-z0-9]+[@][a-z0-9]+\\.[a-zA-Z]{2,}";
		if(email.matches(regex))
		{
			Connection connection = ConnectionUtil.getConnection();
			String emailId1 = "select email_id from todo_register_login where role='Nurse'";
			PreparedStatement prepareStatement = connection.prepareStatement(emailId1);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) 
			{
				String emailId2 = resultSet.getString(1);
				existingEmail.add(emailId2);
			}
			if(existingEmail.contains(email))
			{
				todoList.password();
			}
			else
			{
				System.out.println("!!Please enter registered email");
				todoList.signIn();
			}
			
		}
		else
		{
			System.out.println("*Email should start with LowerCase,Don't use UpperCase inbetween also*");
			todoList.signIn();
		}
	}
	public void signIn(String doctorName) throws ClassNotFoundException, SQLException
	{
	    System.out.println("Sign In");
	    System.out.println("-------");
		System.out.println("Enter the EmailId");
		email=scanner.next();
		String regex="(^[a-z])[a-z0-9]+[@][a-z0-9]+\\.[a-zA-Z]{2,}";
		if(email.matches(regex))
		{
			Connection connection = ConnectionUtil.getConnection();
			String emailId1 = "select email_id from todo_register_login where role='Doctor'";
			PreparedStatement prepareStatement = connection.prepareStatement(emailId1);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) 
			{
				String emailId2 = resultSet.getString(1);
				existingEmail.add(emailId2);
			}
			if(existingEmail.contains(email))
			{
				ToDoList.doctor();
			}
			else
			{
				System.out.println("!!Please enter registered email");
				todoList.signIn(nameOfDoctor);
			}
			
		}
		else
		{
			System.out.println("*Email should start with LowerCase,Don't use UpperCase inbetween also*");
			todoList.signIn(nameOfDoctor);
		}
	}
	public void password() throws ClassNotFoundException, SQLException
	{
		System.out.println("Enter the password");
		String password1=scanner.next();
		String regex="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@!#$%^&*]).{6}$";
		if(password1.matches(regex))
		{
			Connection connection = ConnectionUtil.getConnection();
			String passWord = "select password from todo_register_login where email_id=? && role='Nurse'";
			PreparedStatement prepareStatement = connection.prepareStatement(passWord);
			prepareStatement.setString(1, email);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) 
			{
				String password2 = resultSet.getString(1);
				existingPassword.add(password2);
			}
			if(existingPassword.contains(password1))
			{	
				System.out.println("Sign In Successfully...");
			}
			else
			{
				System.out.println("!!Please enter registered password");
				todoList.password();
			}	
		}
		else
		{
			System.out.println("*Password should contain atleast one UpperCase,LowerCase,Number and Special Character*");
			System.out.println("*Password must be 6 characters*");
			todoList.password();
		}
	}
	public void password(String doctorName) throws ClassNotFoundException, SQLException
	{
		System.out.println("Enter the password");
		String password1=scanner.next();
		String regex="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@!#$%^&*]).{6}$";
		if(password1.matches(regex))
		{
			Connection connection = ConnectionUtil.getConnection();
			String passWord = "select password from todo_register_login where email_id=? && role='Doctor' && doctor_name=?";
			PreparedStatement prepareStatement = connection.prepareStatement(passWord);
			prepareStatement.setString(1, email);
			prepareStatement.setString(2,nameOfDoctor);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) 
			{
				String password2 = resultSet.getString(1);
				existingPassword.add(password2);
			}
			if(existingPassword.contains(password1))
			{	
				System.out.println("Sign In Successfully...");
				DoctorLogin.reminder();
		    }
			else
			{
				System.out.println("!!Please enter registered password");
				todoList.password(nameOfDoctor);
			}	
		}
		else
		{
			System.out.println("*Password should contain atleast one UpperCase,LowerCase,Number and Special Character*");
			System.out.println("*Password must be 6 characters*");
			todoList.password(nameOfDoctor);
		}
	}
	public static void work() throws ClassNotFoundException, SQLException
	{
		int choice=0;
		System.out.println("1.Nurse\n2.Doctor\n3.Quit");
		System.out.println("Please make a choice");
		try
		{
			choice=scanner.nextInt();
		}
		catch(InputMismatchException i1)
		{
			i1.getMessage();
		}
		String choice1=String.valueOf(choice);
		scanner.nextLine();
		String regex="[0-9]";
		if(choice1.matches(regex))
		{
			int select=Integer.parseInt(choice1);
			switch(select)
			{
			   case 1:todoList.signUp();
			          break;
			   case 2:doctorlogin.doctorLogin();        
		              break;
			   case 3:System.out.println("Exit Successfully...");
			          break;
			   default:System.out.println("Please enter choice 1 or 2");
			           ToDoList.work();
			}
		}
		else
		{
			System.out.println("*Your choice should be numeric (1 or 2)*");
			ToDoList.work();
		}
	}
	public static void doctor() throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.getConnection();
		nameOfDoctor=DoctorLogin.updateDoctor();
		ArrayList<String> existingDoctor=new ArrayList<>();
		String doctor="select doctor_name from todo_register_login where email_id=?";
		PreparedStatement prepareStatement1= connection.prepareStatement(doctor);
		prepareStatement1.setString(1, email);
		ResultSet resultSet1 = prepareStatement1.executeQuery();
		while (resultSet1.next()) 
		{
			String doctor1 = resultSet1.getString(1);
			existingDoctor.add(doctor1);
		}
		if(existingDoctor.contains(nameOfDoctor))
		{
			todoList.password(nameOfDoctor);
		}
		else
		{
			System.out.println("Please enter correct Name...");
			ToDoList.doctor();
		}	
	}
	public static void displayDetails() throws SQLException, ClassNotFoundException
	{
		Connection connection=ConnectionUtil.getConnection();
		String display="select s_no,date,appointed_on,patient_name,age,phone_no,disease,doctor_name,doctor_category,status from todo_list where status='Unvisited' && doctor_name=?";
		PreparedStatement prepareStatement2=connection.prepareStatement(display);
		prepareStatement2.setString(1, nameOfDoctor);
		ResultSet resultSet1=prepareStatement2.executeQuery();
		System.out.println("Token\\t\\tDate\t\tAppointed on\t\tPatient Name\t\t\tPatient Age\t\tPhoneNo\t\tDisease\t\t\tDoctor Name\t\tDoctor Category\t\tStatus");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		while(resultSet1.next())
		{
			System.out.println(resultSet1.getInt(1)+"\t\t"+resultSet1.getString(2)+"\t"+resultSet1.getString(3)+"\t\t"+resultSet1.getString(4)+"\t\t\t\t"+resultSet1.getInt(5)+"\t\t\t"+resultSet1.getLong(6)+"\t"+resultSet1.getString(7)+"\t\t\t"+resultSet1.getString(8)+"\t\t\t"+resultSet1.getString(9)+"\t\t\t"+resultSet1.getString(10));
		}
	}
	public static int taskCount() throws SQLException, ClassNotFoundException
	{
		Connection connection=ConnectionUtil.getConnection();
		String count="select count(*) from todo_list where doctor_name=?"; 
		PreparedStatement prepareStatement=connection.prepareStatement(count);
		prepareStatement.setString(1, nameOfDoctor);
		ResultSet rs =prepareStatement.executeQuery();
		if(rs.next()) 
		{ 
			doctor.setTotalPatients(rs.getInt(1));
			totalPatients=doctor.getTotalPatients();
		}
		return totalPatients;
	}
	public static int visitedPatients() throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.getConnection();
		String total="select count(*) from todo_list where status='Visited' && doctor_name=?"; 
		PreparedStatement prepareStatement1=connection.prepareStatement(total);
		prepareStatement1.setString(1, nameOfDoctor);
		ResultSet rs =prepareStatement1.executeQuery();
		while(rs.next()) 
		{ 
			doctor.setVisitedPatients(rs.getInt(1));
			count=doctor.getVisitedPatients();
		}
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
		return count;
	}
}
