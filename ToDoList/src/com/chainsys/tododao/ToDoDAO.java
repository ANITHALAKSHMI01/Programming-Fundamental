package com.chainsys.tododao;
import java.sql.SQLException;
public interface ToDoDAO 
{
	String doctorName();
	String doctorCategory();
	void doctorLogin() throws ClassNotFoundException, SQLException;
}
