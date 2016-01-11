package com.epicmeanscookies.data;

public class ProcessMessage 
{

	public void processMessage(StringBuilder sb)
	{
		char firstChar = sb.charAt(0);
		
		if (!Character.isDigit(firstChar))
		{
			System.err.println("Unkown Packet RIP");
			return;
		}
		
		switch (firstChar)
		{
			//HandShake
			case '1':
				handshake();
				break;
			//Add Info
			case '2':
				addInfo();
				break;
			default:
				System.out.println("Unkown packet");
				break;
		}
		
	}
	
	private void handshake()
	{
		
	}
	
	private void addInfo()
	{
		
	}
	
}
