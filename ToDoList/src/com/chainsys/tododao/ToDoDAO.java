package com.chainsys.tododao;
import java.sql.SQLException;
public interface ToDoDAO 
{
//	void userInput();
//	boolean insertPatient();
//	void displayAllPatients();
//	void removePatient();
//	int visitedPatients();
	String doctorName();
	String doctorCategory();
	void doctorLogin() throws ClassNotFoundException, SQLException;
}
