package toastwars.server.datamodel.core;

public class Game
{
	private int userAmount;
	private int currentRound;
	 // Eine (versteckte) Klassenvariable vom Typ der eigenen Klasse
	private static Game gameInstance;
	private Game(){}
	public static Game getInstance()
	{
		if(Game.gameInstance == null)
			Game.gameInstance = new Game();
		return Game.gameInstance; 
	}
	public void createInitialData()
	{
		//TODO: DB-Zugriff über eine Klasse die Tabellen dort entsprechend füllt 
	}
	
	public void simulate()
	{}
	public int getUserAmount()
	{
		return userAmount;
	}
	public void setUserAmount(int userAmount)
	{
		this.userAmount = userAmount;
	}
	public int getCurrentRound()
	{
		return currentRound;
	}
	public void setCurrentRound(int currentRound)
	{
		this.currentRound = currentRound;
	}
	
	private void calculateMarketShare()
	{	}
	
	private void calculateTurnover()
	{	}
	
	private void calcualteCapital()
	{}
	private void calculateIndex()
	{}
}
