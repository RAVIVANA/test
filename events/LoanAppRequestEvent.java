package com.nkxgen.spring.jdbc.events;


public class LoanAppRequestEvent {
	
	private String event;
	private String username;
	
	public LoanAppRequestEvent(String event,String username)
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
