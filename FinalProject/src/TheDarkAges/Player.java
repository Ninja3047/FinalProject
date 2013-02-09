package TheDarkAges;

public class Player
{
	String name; //name of civilization
	String faction; //faction type
	String capital; //capital
	
	int population; //population
	int gold; //amount of gold
	int food; //amount of food
	int land; //amount of land
	
	double power;
	
	public Player(String name, String faction, String capital, int population, int gold, int food, int land) //returning player
	{
		this.name = name;
		this.faction = faction;
		this.capital = capital;
		
		this.population = population;
		this.gold = gold;
		this.food = food;
		this.land = land;
		
		this.power = (population * .3) + (gold * .3) + (food * .3) + (land * .7);
	}
	
	public Player(String name, String faction, String capital) //new player
	{
		this.name = name;
		this.faction = faction;
		this.capital = capital;
		
		this.population = 100;
		this.gold = 1000;
		this.food = 2000;
		this.land = 1000;
		
		this.power = (population * .3) + (gold * .3) + (food * .3) + (land * .7); //power is calculated based on how many things the player has
	}
	
}
