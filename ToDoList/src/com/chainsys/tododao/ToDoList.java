package com.chainsys.tododao;
import java.sql.SQLException;
import java.util.Scanner;
public class ToDoList
{
	public static ToDoList todoList=new ToDoList();
	public static String password1,emailId,nameOfDoctor;
	public static Scanner scanner=new Scanner(System.in);
	public void signUp() throws ClassNotFoundException, SQLException
	{
		System.out.println("Do you have Account(Yes-'Y or y' No-'N or n')");
		char ch=scanner.next().charAt(0);
		if(ch=='y' || ch=='Y' || ch=='n' || ch=='N')
		{
			if(ch=='y' || ch=='Y')
			{
				System.out.println("Nurse SignIn Page");
			    System.out.println("````````````````"); 
			    todoList.signIn();
			}
			else
			{
				  System.out.println("Nurse SignUp Page");
			      System.out.println("````````````````"); 
			      ToDoList.userName();
			      todoList.signIn();
			}
	    }
		else
		{
			System.out.println("Please enter Yes-'Y or y' No-'N or n'");
			todoList.signUp();
		}
	}
	public void signUp(String doctorName) throws ClassNotFoundException, SQLException
	{
		System.out.println("Do you have Account(Yes-'Y or y' No-'N or n')");
		char ch=scanner.next().charAt(0);
		if(ch=='y' || ch=='Y' || ch=='n' || ch=='N')
		{
			if(ch=='y' || ch=='Y')
			{
				System.out.println("Doctor SignIn Page");
			    System.out.println("````````````````"); 
			    todoList.signIn(doctorName);
			}
			else
			{
				System.out.println("Doctor SignUp Page");
			    System.out.println("````````````````"); 
				ToDoList.userName();
				todoList.signIn(doctorName);
			}
	    }
		else
		{
			System.out.println("Please enter Yes-'Y or y' No-'N or n'");
			todoList.signUp(nameOfDoctor);
		}
	}
	public static void userName()
	{
		System.out.println("Enter the Username");
		String username=scanner.next();
		String regex="^[a-zA-Z0-9]*";
		if(username.matches(regex))
		{
			ToDoList.phoneNo();
		}
		else
		{
			System.out.println("*Username should be alphanumeric*");
			ToDoList.userName();
		}
	}
	public static void phoneNo()
	{
		System.out.println("Enter Phone number");
		long phoneNo=scanner.nextLong();
		String stringPhoneNo=Long.toString(phoneNo);
		String regex="(91|0)?[6-9][0-9]{9}$";
		if(stringPhoneNo.matches(regex))
		{
			ToDoList.emailId();
			System.out.println("Registered Successfully...");
		}
		else
		{
			System.out.println("Phone Number should start 6-9 & must 10 digits");
			ToDoList.phoneNo();
		}
	}
	public static String emailId()
	{
		System.out.println("Enter the EmailId");
		String email=scanner.next();
		String regex="(^[a-z])[a-z0-9]+[@][a-z0-9]+\\.[a-zA-Z]{2,}";
		if(email.matches(regex))
		{
			emailId=email;
			ToDoList.password1();
		}
		else
		{
			System.out.println("*Email should start with LowerCase,Don't use UpperCase inbetween also*");
			ToDoList.emailId();
		}
		return emailId;
	}
	public static String password1()
	{
		System.out.println("Enter the password");
		String password=scanner.next();
		String regex="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@!#$%^&*]).{6}$";
		if(password.matches(regex))
		{
			password1=password;
		}
		else
		{
			System.out.println("*Password should contain atleast one UpperCase,LowerCase,Number and Special Character*");
			System.out.println("*Password must be 6 characters*");
			ToDoList.password1();	
		}
		return password1;
	}
	public void signIn() throws ClassNotFoundException, SQLException
	{
		System.out.println("Enter the EmailId");
		String email=scanner.next();
		String regex="(^[a-z])[a-z0-9]+[@][a-z0-9]+\\.[a-zA-Z]{2,}";
		if(email.matches(regex))
		{
			if(email.equals("jansi01@ramar.rkhosp") || email.equals(emailId))
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
		System.out.println("Enter the EmailId");
		String email=scanner.next();
		String regex="(^[a-z])[a-z0-9]+[@][a-z0-9]+\\.[a-zA-Z]{2,}";
		if(email.matches(regex))
		{
			if(email.equals("nikilan@ani.rkhosp")|| email.equals(emailId))
			{
				todoList.password(nameOfDoctor);
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
		String password=scanner.next();
		String regex="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@!#$%^&*]).{6}$";
		if(password.matches(regex))
		{
			if(password.equals("Jan12#") || password.equals(password1))
			{
				System.out.println("Sign In Successfully...");
				NurseLogin.userInput();
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
		String password=scanner.next();
		String regex="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@!#$%^&*]).{6}$";
		if(password.matches(regex))
		{
			if(password.equals("Nik05*")  || password.equals(password1))
			{
				System.out.println("Sign In Successfully...");
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
			todoList.password();
		}
	}
}
