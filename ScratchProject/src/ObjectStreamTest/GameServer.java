package ObjectStreamTest;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

	static ServerSocket serverSocket;
	static GameService[] user = new GameService[6];
	

	
	public static void main(String[] args) throws Exception
	{
		serverSocket = new ServerSocket(7777);
		System.out.println("Server Started...");
		Game game = new Game(0);
		
		while(true)
		{
			Socket socket = serverSocket.accept();
			for (int i = 0; i < 6; i++)
			{
				if(user[i] == null)
				{
					System.out.println("Connection from " + socket.getInetAddress());
					user[i] = new GameService(socket, game, user);
					Thread thread = new Thread(user[i]);
					thread.start();
					break;
				}
				else
				{
					if (i == 5)
					{
						System.out.println("Game is full");
					}
				}
			}
		}		
	}
}

