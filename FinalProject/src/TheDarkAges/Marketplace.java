package TheDarkAges;

import java.util.Random;

public class Marketplace
{
	int civId;
	
	public Marketplace(int civId)
	{
		this.civId = civId;
	}
	
	public int foodMarket() //prints out the market screen and returns the price of food and land in gold
	{
		int foodPrice = (int)(Math.random()*20 + 1);
		//int landPrice = (int)Math.random()*100 + 120;
		//int[] foodLand = {foodPrice, landPrice};
		
		System.out.println("Welcome to the Marketplace");
		System.out.println("Market Prices");
		System.out.println("+=======+======================+");
		System.out.printf("| Food  |             %d golds | \n", foodPrice);
		System.out.println("+=======+======================+");
		return foodPrice;
	}
	
	public int buyFood(int amount, int food) //enter negative to sell, positive to buy and 0 for food and 1 for land
	{
		food = food + amount; //changes the current gold you have

		return food;
	}
	
	public int sellFood(int amount, int gold)
	{
		gold = gold + amount;
		
		return gold;
	}
	
	public int landMarket() //prints out the market screen and returns the price of food and land in gold
	{
		int landPrice = (int)(Math.random()*20 + 2);
		
		System.out.println("Welcome to the Marketplace");
		System.out.println("Market Prices");
		System.out.println("+=======+======================+");
		System.out.printf("| Land  |             %d golds | \n", landPrice);
		System.out.println("+=======+======================+");
		return landPrice;
	}
	
	public int buyLand(int amount, int land) //enter negative to sell, positive to buy and 0 for food and 1 for land
	{
		land = land + amount; //changes the current gold you have

		return land;
	}
	
	public int sellLand(int amount, int gold)
	{
		gold = gold + amount;
		
		return gold;
	}
}
