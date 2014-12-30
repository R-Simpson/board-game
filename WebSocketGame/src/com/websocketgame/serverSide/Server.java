package com.websocketgame.serverSide;

import java.net.ServerSocket;
import java.net.Socket;

import com.websocketgame.shared.Game;
import com.websocketgame.shared.Land;
import com.websocketgame.shared.Unit;


public class Server {

	static ServerSocket serverSocket;
	static UserService[] user = new UserService[6];

	public static void main(String[] args) throws Exception
	{
		serverSocket = new ServerSocket(7777);
		System.out.println("Server Started...");

		setUpGamePieces();

		while(true)
		{
			Socket socket = serverSocket.accept();
			for (int i = 0; i < 6; i++)
			{
				if(user[i] == null)
				{
					System.out.println("Connection from " + socket.getInetAddress());
					user[i] = new UserService(socket, Game.INSTANCE, user, i+1);
					Thread thread = new Thread(user[i]);
					thread.start();
					break;
				}
			}
		}
	}

	static void setUpGamePieces()
	{
		for(Land land : Game.INSTANCE.getGameState()){
			switch (land.getLandId()){
			case 2:
				//land.setColor("BLUE");
				land.setOwner(1);
				land.addUnit(new Unit(1,2,land));
				//land.addUnit(new Unit(1,1,land));
				break;
			case 4:
				//land.setColor("BLUE");
				land.setOwner(1);
				land.addUnit(new Unit(1,1,land));
				break;

			case 15:
				//land.setColor("RED");
				land.setOwner(2);
				land.addUnit(new Unit(2,2,land));
				//land.addUnit(new Unit(2,1,land));
				break;
			case 16:
				//land.setColor("RED");
				land.setOwner(2);
				land.addUnit(new Unit(2,1,land));
				break;

			case 36:
				//land.setColor("YELLOW");
				land.setOwner(3);
				land.addUnit(new Unit(3,2,land));
				//land.addUnit(new Unit(3,1,land));
				break;
			case 24:
				//land.setColor("YELLOW");
				land.setOwner(3);
				land.addUnit(new Unit(3,1,land));
				break;

			case 35:
				//land.setColor("PURPLE");
				land.setOwner(4);
				land.addUnit(new Unit(4,2,land));
				//land.addUnit(new Unit(4,1,land));
				break;
			case 7:
				//land.setColor("PURPLE");
				land.setOwner(4);
				land.addUnit(new Unit(4,1,land));
				break;

			case 22:
				//land.setColor("GREEN");
				land.setOwner(5);
				land.addUnit(new Unit(5,2,land));
				//land.addUnit(new Unit(5,1,land));
				break;
			case 26:
				//land.setColor("GREEN");
				land.setOwner(5);
				land.addUnit(new Unit(5,1,land));
				break;

			case 32:
				//land.setColor("DARKORANGE");
				land.setOwner(6);
				land.addUnit(new Unit(6,2,land));
				//land.addUnit(new Unit(6,1,land));
				break;
			case 34:
				//land.setColor("DARKORANGE");
				land.setOwner(6);
				land.addUnit(new Unit(6,1,land));
				break;
			}	
		}
	}
}

