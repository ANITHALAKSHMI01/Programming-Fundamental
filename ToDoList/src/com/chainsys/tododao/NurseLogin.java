package com.chainsys.tododao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.chainsys.util.ConnectionUtil;
import  com.chainsys.todomodel.DoctorTodoList;
public class NurseLogin 
{
	public static Scanner scanner=new Scanner(System.in);
	public static int tokenNumber,patientAge;
	public static String userName,email,password,role,patientName,age,PhoneNumber,disease,nameOfDoctor,doctorCategory,status;
	public static long phoneNo;
	public static Date dateToday;
	public static DoctorTodoList doctor=new DoctorTodoList();
	public static ToDoList todoList=new ToDoList();
	static DoctorLogin doctorLogin=new DoctorLogin();
	public static void signUpDetails() throws ClassNotFoundException, SQLException
	{
		userName=ToDoList.userName();
		phoneNo=ToDoList.phoneNo();
		email=ToDoList.emailId();
		password=ToDoList.password1();
		role=ToDoList.role();
		Connection connection=ConnectionUtil.getConnection();
		String insert="insert into todo_register_login(username,doctor_name,phone_no,email_id,password,role)values(?,?,?,?,?,?)";
		PreparedStatement prepareStatement1=connection.prepareStatement(insert);
		prepareStatement1.setString(1,userName);
		prepareStatement1.setLong(2,phoneNo);
		prepareStatement1.setString(3,email);
		prepareStatement1.setString(4, password);
		prepareStatement1.setString(5,role);
		prepareStatement1.executeUpdate();
	}
	public static void signUpDetails(String doctorName) throws ClassNotFoundException, SQLException
	{
		userName=ToDoList.userName();
		nameOfDoctor=DoctorLogin.updateDoctor();
		phoneNo=ToDoList.phoneNo();
		email=ToDoList.emailId();
		password=ToDoList.password1();
		role=ToDoList.role(nameOfDoctor);
		Connection connection=ConnectionUtil.getConnection();
		String insert="insert into todo_register_login(username,doctor_name,phone_no,email_id,password,role)values(?,?,?,?,?,?)";
		PreparedStatement prepareStatement1=connection.prepareStatement(insert);
		prepareStatement1.setString(1,userName);
		prepareStatement1.setString(2,nameOfDoctor);
		prepareStatement1.setLong(3,phoneNo);
		prepareStatement1.setString(4,email);
		prepareStatement1.setString(5, password);
		prepareStatement1.setString(6,role);
		prepareStatement1.executeUpdate();
	}
	public  static void userInput() throws ClassNotFoundException, SQLException
	{
		boolean flag=true;
		while(flag)
		{
			int choice=0;
			System.out.println("1.Add Patient\n2.Remove Patient\n3.Display All Patients\n4.Update Patient Details\n5.Quit");
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
			String regex="\\d";
			if(choice1.matches(regex))
			{
				int select=Integer.parseInt(choice1);
				switch(select)
				{
				case 1    :NurseLogin.insertPatient();
				           break;	   
				case 2    :NurseLogin.removePatient();
			               break;
				case 3    :NurseLogin.displayAllPatients();
				           break;
				case 4    :NurseLogin.updatePatientdetail();
						   break;
				case 5    :flag=false;
				           System.out.println("Exit successfully...");
				   		   System.out.println("Welcome to Doctor's ToDoList Reminder App");
						   System.out.println(".........................................");
				           ToDoList.work();
//				           DoctorLogin doctor=new DoctorLogin();
//				   	       doctor.doctorLogin();
				           break;
				default  :System.out.println("*Please enter choice 1-5*");
				          NurseLogin.userInput();
				          break;
				}
			}
			else
			{
				System.out.println("*Your choice should be numeric (1-5)*");
				NurseLogin.userInput();
			}
		}
	}
	public static void patientName() throws ClassNotFoundException, SQLException
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
	public static void patientAge() throws ClassNotFoundException, SQLException
	{
		System.out.println("Enter Age");
		age=scanner.next();
		String regex="\\d{1,3}$";
		if(age.matches(regex))
		{
			patientAge=Integer.parseInt(age);
			if(patientAge>0 && patientAge<=100)
			{
				NurseLogin.phoneNo();
				NurseLogin.disease();
				nameOfDoctor=doctorLogin.doctorName();
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
	public static long phoneNo() throws ClassNotFoundException, SQLException
	{
		System.out.println("Enter patient contact number");
		try 
		{
			phoneNo=scanner.nextLong();
		}
		catch(InputMismatchException i1)
		{
			i1.getMessage();
		}
		scanner.nextLine();
		PhoneNumber=Long.toString(phoneNo);
		String regex="(91|0)?[6-9][0-9]{9}$";
		if(PhoneNumber.matches(regex))
		{
			ArrayList<String> existingPhoneNo = new ArrayList<>();
			Connection connection = ConnectionUtil.getConnection();
			String phoneNumber = "select phone_no from todo_list";
			PreparedStatement prepareStatement = connection.prepareStatement(phoneNumber);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) 
			{
				String phoneno = resultSet.getString(1);
				existingPhoneNo.add(phoneno);
			}
			if(existingPhoneNo.contains(PhoneNumber))
			{
				System.out.println("*PhoneNo already exist*");
				NurseLogin.phoneNo();
			}
			else
			{
				doctor.setPhoneNo(phoneNo);
			}
		}
		else
		{
			System.out.println("Phone Number should start 6-9 & must 10 digits");
			NurseLogin.phoneNo();
		}
		return phoneNo;
	}
	public static String disease()
	{
		System.out.println("Enter disease");
		disease=scanner.nextLine();
		String regex="^[a-zA-Z,\\s]*$";
		if(disease.matches(regex))
		{
           doctor.setDisease(disease);
		}
		else
		{
			System.out.println("<Disease should be alphabet>");
			NurseLogin.disease();
		}
		return disease;
	}
	public static void insertPatient() throws ClassNotFoundException, SQLException
	{
		LocalDate dateToday = LocalDate.now(); 
		String dateString =dateToday.toString();
		LocalTime timeNow = LocalTime.now();
		String timeString=timeNow.toString();
		NurseLogin.patientName();
		doctor.setPatientName(patientName);
		doctor.setAge(patientAge);
		doctor.setPhoneNo(phoneNo);
		doctor.setDisease(disease);
		doctor.setDoctorName(nameOfDoctor);
		doctorCategory=doctorLogin.doctorCategory();
		doctor.setDoctorCategory(doctorCategory);
		doctor.setStatus("Unvisited");
		Connection connection=ConnectionUtil.getConnection();
		String insert="insert into todo_list(date,appointed_on,patient_name,age,phone_no,disease,doctor_name,doctor_category,status)values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement prepareStatement1=connection.prepareStatement(insert);
		prepareStatement1.setString(1, dateString);
		prepareStatement1.setString(2,timeString);
		prepareStatement1.setString(3,doctor.getPatientName());
		prepareStatement1.setInt(4, doctor.getAge());
		prepareStatement1.setLong(5,doctor.getPhoneNo());
		prepareStatement1.setString(6, doctor.getDisease());
		prepareStatement1.setString(7, doctor.getDoctorName());
		prepareStatement1.setString(8, doctor.getDoctorCategory());
		prepareStatement1.setString(9, doctor.getStatus());
		System.out.println("Patient details : "+doctor.getPatientName()+" "+doctor.getAge()+" "+doctor.getDisease());
		prepareStatement1.executeUpdate();
	}
	public static void displayAllPatients() throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.getConnection();
		String display="select s_no,date,appointed_on,patient_name,age,phone_no,disease,doctor_name,doctor_category,status from todo_list";
		PreparedStatement prepareStatement=connection.prepareStatement(display);
		ResultSet resultSet=prepareStatement.executeQuery();
		System.out.println("Token\t\tDate\t\tAppointed on\t\tPatient Name\t\t\tPatient Age\t\tPhoneNo\t\tDisease\t\t\tDoctor Name\t\tDoctor Category\t\tStatus");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		while(resultSet.next())
		{
			System.out.println(resultSet.getInt(1)+"\t\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t\t"+resultSet.getString(4)+"\t\t\t\t"+resultSet.getInt(5)+"\t\t\t"+resultSet.getLong(6)+"\t"+resultSet.getString(7)+"\t\t\t"+resultSet.getString(8)+"\t\t\t"+resultSet.getString(9)+"\t\t\t"+resultSet.getString(10));
		}
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
				System.out.println(rows+" Patient didn't come removed at tokenNo "+tokenNumber+" successfully");
			}
			else
			{
				try
				{
					throw new NoPatientAppointed();
				}
				catch(NoPatientAppointed n1)
				{
					System.out.println(n1.getMessage());
					System.out.println("!!No patient appointed!! at tokenNo "+tokenNo);
				}
			}
		}
		else
		{
			System.out.println("=>Token Number should be positive and numeric");
			NurseLogin.removePatient();
		}
	}
	public static void updatePatientdetail() throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.getConnection();
		System.out.println("Enter token no for updation");
		String tokenNo=scanner.next();
		String regex="\\d";
		if(tokenNo.matches(regex))
		{
			tokenNumber=Integer.parseInt(tokenNo);
			System.out.println("1.Update PhoneNo\n2.Update Disease\n3.Update DoctorName\n4.Update DoctorCategory");
			System.out.println("Please make a choice");
			String choice=scanner.next();
			scanner.nextLine();
			String regex1="\\d";
			if(choice.matches(regex1))
			{
				int select=Integer.parseInt(choice);
				switch(select)
				{
				     case 1:String update="update todo_list set phone_no=? where s_no=?";
						    long phoneNo=NurseLogin.phoneNo();
						    PreparedStatement prepareStatement=connection.prepareStatement(update);
						    prepareStatement.setLong(1, phoneNo);
						    prepareStatement.setInt(2, tokenNumber);
						    int rows=prepareStatement.executeUpdate();
						    if(rows>0)
						    {
						    	System.out.println("Updated Successfully....");
						    }
						    else
						    {
						    	try
								{
									throw new NoPatientAppointed();
								}
								catch(NoPatientAppointed n1)
								{
									System.out.println(n1.getMessage());
									System.out.println("!!No patient appointed!! at tokenNo "+tokenNo);
								}
						    }
						    break;
						    
				     case 2:String updateDisease="update todo_list set disease=? where s_no=?";
					        String disease=NurseLogin.disease();
					        PreparedStatement prepareStatement1=connection.prepareStatement(updateDisease);
					        prepareStatement1.setString(1, disease);
					        prepareStatement1.setInt(2, tokenNumber);
					        int row= prepareStatement1.executeUpdate();
					        if(row>0)
						    {
						    	System.out.println("Updated Successfully....");
						    }
						    else
						    {
						    	try
								{
									throw new NoPatientAppointed();
								}
								catch(NoPatientAppointed n1)
								{
									System.out.println(n1.getMessage());
									System.out.println("!!No patient appointed!! at tokenNo "+tokenNo);
								}
						    }
					        break;
					        
				     case 3:String updateDoctor="update todo_list set doctor_name=? where s_no=?";
				     	    String doctor=DoctorLogin.updateDoctor();
				            PreparedStatement prepareStatement2=connection.prepareStatement(updateDoctor);
				            prepareStatement2.setString(1, doctor);
				            prepareStatement2.setInt(2, tokenNumber);
				            int row1= prepareStatement2.executeUpdate();
				            if(row1>0)
						    {
						    	System.out.println("Updated Successfully....");
						    }
						    else
						    {
						    	try
								{
									throw new NoPatientAppointed();
								}
								catch(NoPatientAppointed n1)
								{
									System.out.println(n1.getMessage());
									System.out.println("!!No patient appointed!! at tokenNo "+tokenNo);
								}
						    }
				            break;
				     case 4:String updateDoctorCategory="update todo_list set doctor_category=? where s_no=?";
				            String doctorCategory=DoctorLogin.updateDoctorCategory();;
				            PreparedStatement prepareStatement3=connection.prepareStatement(updateDoctorCategory);
			                prepareStatement3.setString(1, doctorCategory);
			                prepareStatement3.setInt(2, tokenNumber);
			                int row2=prepareStatement3.executeUpdate();
			                if(row2>0)
						    {
						    	System.out.println("Updated Successfully....");
						    }
						    else
						    {
						    	try
								{
									throw new NoPatientAppointed();
								}
								catch(NoPatientAppointed n1)
								{
									System.out.println(n1.getMessage());
									System.out.println("!!No patient appointed!! at tokenNo "+tokenNo);
								}
						    }
			                break;
				     default  :System.out.println("*Please enter choice 1-5*");
			                   NurseLogin.updatePatientdetail();
			                   break;
				}
			}
			else
			{
				System.out.println("*Your choice should be numeric (1-4)*");
				NurseLogin.updatePatientdetail();
			}
			
		}
		else
		{
			System.out.println("=>Token Number should be positive and numeric");
			NurseLogin.updatePatientdetail();
		}
	}
}
