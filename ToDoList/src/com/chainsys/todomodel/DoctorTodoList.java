package com.chainsys.todomodel;
public class DoctorTodoList 
{
	String username;
	long phoneNo;
	String email;
	String password;
	String role;
	String patientName;
	int age;
	String disease;
	String doctorName;
	String doctorCategory;
	int totalPatients;
	int visitedPatients;
	int remainingPatients;
	String status;
	public DoctorTodoList()
	{
		
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public long getPhoneNo()
	{
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo)
	{
		this.phoneNo = phoneNo;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getRole() 
	{
		return role;
	}
	public void setRole(String role) 
	{
		this.role = role;
	}
	public String getPatientName() 
	{
		return patientName;
	}
	public void setPatientName(String patientName)
	{
		this.patientName = patientName;
	}
	public int getAge() 
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public String getDisease()
	{
		return disease;
	}
	public void setDisease(String disease) 
	{
		this.disease = disease;
	}
	public String getDoctorName() 
	{
		return doctorName;
	}
	public void setDoctorName(String doctorName)
	{
		this.doctorName = doctorName;
	}
	public int getTotalPatients() 
	{
		return totalPatients;
	}
	public void setTotalPatients(int totalPatients)
	{
		this.totalPatients = totalPatients;
	}
	public int getVisitedPatients()
	{
		return visitedPatients;
	}
	public void setVisitedPatients(int visitedPatients)
	{
		this.visitedPatients = visitedPatients;
	}
	public int getRemainingPatients()
	{
		return remainingPatients;
	}
	public void setRemainingPatients(int remainingPatients)
	{
		this.remainingPatients = remainingPatients;
	}
	
	public String getDoctorCategory() 
	{
		return doctorCategory;
	}
	public void setDoctorCategory(String doctorCategory)
	{
		this.doctorCategory = doctorCategory;
	}
	public String getStatus() 
	{
		return status;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	@Override
	public String toString() 
	{
		return "DoctorTodoList [username=" + username + ", phoneNo=" + phoneNo + ", email=" + email + ", password="
				+ password + ", role=" + role + ", patientName=" + patientName + ", age=" + age + ", disease=" + disease
				+ ", doctorName=" + doctorName + ", doctorCategory=" + doctorCategory + ", totalPatients="
				+ totalPatients + ", visitedPatients=" + visitedPatients + ", remainingPatients=" + remainingPatients
				+ ", status=" + status + "]";
	}	
}
