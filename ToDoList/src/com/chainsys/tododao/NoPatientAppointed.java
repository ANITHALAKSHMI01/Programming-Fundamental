package com.chainsys.tododao;
public class NoPatientAppointed extends RuntimeException 
{
	@Override
	public String getMessage() 
	{
		return "No patient";
	}
}
