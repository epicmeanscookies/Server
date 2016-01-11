package com.epicmeanscookies;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;

import com.epicmeanscookies.data.Message;
import com.epicmeanscookies.data.ProcessMessage;

public class Main 
{

	public static Main main = new Main();
	
	private ProcessMessage pm = new ProcessMessage();
	private ServerSocket serverSocket;
	private int port = 7887;
	
	private HashMap<UUID, Socket> users = new HashMap<UUID, Socket>();
	
	private void start() throws IOException
	{
		serverSocket = new ServerSocket(port);
		if (!serverSocket.isClosed()) System.out.println("Server has opened on port " + port); 
		
		while (true)
		{

			for (UUID uuid : users.keySet())
			{
				Socket socket = users.get(uuid);
				recieveMessage(socket);
			}
			
			Socket user = serverSocket.accept();
			System.out.println();
			
			if (user != null)
			{
				UUID uuid = UUID.randomUUID();
				users.put(uuid, user);
				recieveMessage(user);
				System.out.println("Client connected. User info, UUID: " + uuid + " Ip: " + user.getInetAddress() + " Current Amount Of Users: " + users.size() + 1);
			}			
		}
	}
	
	public void recieveMessage(Socket socket) throws IOException
	{
		DataInputStream stream = new DataInputStream(socket.getInputStream());
		StringBuilder sb = new StringBuilder();
		sb.append(stream.readUTF());
		pm.processMessage(sb);
	}
	
	public void sendMessage(Socket user, Message msg)
	{
		if (user != null)
		{
			try 
			{
				DataOutputStream stream = new DataOutputStream(user.getOutputStream());
				String message = msg.getMsg();
				stream.writeUTF(message);
				stream.flush();
			} 
				catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public ServerSocket getServerSocket() 
	{
		return serverSocket;
	}

	public int getPort() 
	{
		return port;
	}

	public HashMap<UUID, Socket> getUsers()
	{
		return users;
	}

	public static void main(String[] args)
	{
		try 
		{
			main.start();
		} 
			catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		if (args != null)
		{
			//main.port = Integer.parseInt(args[0]);
		}
	}
}
