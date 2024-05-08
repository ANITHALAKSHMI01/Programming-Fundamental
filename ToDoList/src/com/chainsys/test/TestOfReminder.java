package com.chainsys.test;
import java.sql.SQLException;
import com.chainsys.tododao.ToDoList;
public class TestOfReminder 
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		System.out.println("Welcome to Doctor's ToDoList Reminder App");
		System.out.println(".........................................");
		ToDoList.work();
	}
}