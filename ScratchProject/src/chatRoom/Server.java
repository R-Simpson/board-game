package chatRoom;

import java.io.*;
import java.net.*;

public class Server {

	static ServerSocket serverSocket;
	static Socket socket;
	static DataOutputStream out;
	static DataInputStream in;
	static Users[] user = new Users[6];

	public static void main(String[] args) throws Exception
	{
		System.out.println("Starting Server...");
		serverSocket = new ServerSocket(7777);
		System.out.println("Server Started...");
		while(true)
		{
			socket = serverSocket.accept();
			for (int i = 0; i < 6; i++)
			{
				System.out.println("Connection from " + socket.getInetAddress());
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());

				if(user[i] == null)
				{
					user[i] = new Users(out, in, user, i);
					Thread thread = new Thread(user[i]);
					thread.start();
					break;
				}
			}
		}
	}
}


class Users implements Runnable{

	DataOutputStream out;
	DataInputStream in;
	Users[] user = new Users[6];
	String name;
/*	
	int playerid;
	int playeridin;
	int xin;
	int yin;
*/
	
	public Users(DataOutputStream out, DataInputStream in, Users[] user, int pid) {
		this.out = out;
		this.in = in;
		this.user = user;
	//	this.playerid = pid;
	}

	public void run() {
		try {
			name = in.readUTF();
		//	out.writeInt(playerid);
		} catch (IOException e1) {
			System.out.println("Failed to send PlayerId");
		}
		while(true)
		{
			try {
				String message = in.readUTF();
//				playeridin = in.readInt();
//				xin = in.readInt();
//				yin = in.readInt();

				for(int i = 0; i < 6; i++)
				{
					if(user[i] != null)
					{
						user[i].out.writeUTF(name + ": " + message);
//						user[i].out.writeInt(playeridin);
//						user[i].out.writeInt(xin);
//						user[i].out.writeInt(yin);
					}
				}

			} catch (IOException e) {
			//	user[playerid] = null;
				this.out = null;
				this.in = null;
			}
		}
	}
}