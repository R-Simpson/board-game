package com.websocketgame.socketTest;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Users implements Runnable {
	DataOutputStream out;
	DataInputStream in;
	Users[] user = new Users[6];
	int pid;
	int remotePid;

	
	public Users(DataOutputStream out, DataInputStream in, Users[] user, int pid) {
		this.out = out;
		this.in = in;
		this.user = user;
		this.pid = pid;
	}

	public void run() {
		try {
			System.out.println("Writing playerId to client");
			this.out.writeInt(pid);
		} catch (IOException e1) {
			System.out.println("Failed to write playerId to client");
			e1.printStackTrace();
		}
		
		while(true)
		{
			try {
				int message = in.readInt();
				for(int i = 0; i < 6; i++)
				{
					if(user[i] != null)
					{
						user[i].out.writeInt(message);
					}
				}

			} catch (IOException e) {
				user[pid] = null;
			} 
		}
	}
}
