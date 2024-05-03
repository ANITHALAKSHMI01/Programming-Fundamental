
public class DoctorTodoList 
{
	String patientName;
	int age;
	String disease;
	String doctorName;
	String doctorCategory;
	int totalPatients;
	int visitedPatients;
	int remainingPatients;
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
	@Override
	public String toString() 
	{
		return "DoctorTodoList [patientName=" + patientName + ", age=" + age + ", disease=" + disease + ", doctorName="
				+ doctorName + ", doctorCategory=" + doctorCategory + ", totalPatients=" + totalPatients
				+ ", visitedPatients=" + visitedPatients + ", remainingPatients=" + remainingPatients + "]";
	}	
}
