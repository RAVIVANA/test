package com.nkxgen.spring.jdbc.events;


public class AccountAppRequestEvent {
	
	private String event;
	private String username;
	
	public AccountAppRequestEvent(String event,String username)
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
