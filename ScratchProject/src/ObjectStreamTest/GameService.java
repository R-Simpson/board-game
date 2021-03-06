package ObjectStreamTest;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class GameService implements Runnable {

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Game game;
	private GameService[] user;

	public GameService(Socket socket, Game game, GameService[] user) throws Exception
	{
		out = new ObjectOutputStream(socket.getOutputStream());

		in = new ObjectInputStream(socket.getInputStream());

		this.game = game;
		this.user = user;
		System.out.println("Game Service created");
	}

	@Override
	public void run() {

		while(true)
		{
			try {
				GameMessage message = (GameMessage) in.readObject();
				System.out.println("Received value " + message.getSentValue() + " " + message.getSentString()  + ", adding to game Value");
				game.add(message.getSentValue());
				for(int i = 0; i < 6; i++)
				{
					if(user[i] != null)
					{
						GameMessage returnMessage = new GameMessage(game.getValue(), "Returning new game state value");
						user[i].out.writeObject(returnMessage);
						user[i].out.flush();
						System.out.println("Updated game value and sent back to client " + i);
					}
				}
			} catch (IOException | ClassNotFoundException e) {
				// do nothing
			} 
		}
	}
}
