package com.chainsys.tododao;
public class InvalidData extends Exception 
{
	@Override
	public String getMessage()
	{
		return "Please enter Yes or  No";
	}
	
}
