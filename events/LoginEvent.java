package com.nkxgen.spring.jdbc.events;

public class LoginEvent {

	private String event;
	private String username;
	
	public LoginEvent(String event,String username)
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
