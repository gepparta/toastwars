package toastwars.server.datamodel.user;

import toastwars.server.datamodel.core.Game;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Master implements IUser, IsSerializable
{
	private String username;

	private String password;

	private Game game;

	// Eine (versteckte) Klassenvariable vom Typ der eigenen Klasse
	private static Master masterInstance;

	public Master()
	{
	}

	private Master(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	// Diese Methode sorgt daf�r, dass nur ein Master erzeugt werden kann
	public static Master getInstance(String username, String password)
	{
		if (Master.masterInstance == null)
			Master.masterInstance = new Master(username, password);
		return Master.masterInstance;
	}

	public static Master getInstance()
	{
		return Master.masterInstance;
	}

	public String getPassword()
	{
		return password;
	}

	public String getUsername()
	{
		return username;
	}

	public boolean isMaster()
	{
		return true;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

//	public void startGame(int userAmount) throws Exception
//	{
//		if (Game.isGameStarted())
//			throw new Exception("Spiel bereits gestartet");
//		else
//		game = Game.getInstance(userAmount);
//	}

	public Game getCurrentGame()
	{
		return game;
	}

//	public boolean endGame()
//	{
//		return DAOGame.resetGame();
//	}

	public void simulate() throws Exception
	{	
			Game.getInstance().simulate();
	}

	public void setGame(Game game) {
		this.game = game;
	}

//	public void createInitialData(int userAmount)
//	{
//		for (int i = 0; i == userAmount; i++)
//		{
//			String name = "Group" + i;
//			String pass = "Group" + i;
//			// UserFactory.createUser("Group", name, pass);
//		}
//	}

}
