package com.nkxgen.spring.jdbc.events;

public class LoanAppApprovalEvent 
{
	
	private String event;
	private String username;
	
	public LoanAppApprovalEvent(String event,String username)
	{
		this.event=event;
		this.username=username;
	}
	
	public String getEvent()
	{
		return event;
	}
	public String getUsername()
	{
		return username;
	}
}
