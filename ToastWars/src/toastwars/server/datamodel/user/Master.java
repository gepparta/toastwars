package toastwars.server.datamodel.user;

import toastwars.server.datamodel.core.Game;

public class Master implements IUser {
	private String	username;
	private String	password;
	private Game    game;
	public Master() {
	}

	public Master(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isMaster() {
		return true;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void startGame(int UserAmount)
	{
		game = Game.getInstance();
	}
	public Game getCurrentGame()
	{
		return game;
	}
	public void endGame()
	{
	}
	
	public void simulate()
	{
		//Game.simulate()
		//...
		//loop über User
			//User.setStatus(Started)
	}
}
