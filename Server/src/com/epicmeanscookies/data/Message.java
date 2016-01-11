package com.epicmeanscookies.data;

public class Message 
{

	private String msg;
	private int type;
	
	public Message(int type, String msg) 
	{
		this.type = type;
		this.msg = type + msg;
	}

	public int getType() 
	{
		return type;
	}

	public void setType(int type) 
	{
		this.type = type;
	}

	public String getMsg() 
	{
		return msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}
	
}
