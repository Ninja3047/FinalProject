package TheDarkAges;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class MainClass
{
	public static void intro()
	{
		System.out.println("The Dark Ages");
		System.out.println("Version 1.5b");
		System.out.println("By William Tan");
		System.out.println();
	}
	
	public static int civChoose()
	{
		System.out.println("Choose your faction: ");
		
		ReadData file = new ReadData();
		SaveData save = new SaveData();
		String[] factions = file.readTextFile("data/factions.txt");
		
		for(int i = 0; i < factions.length; i++)
		{
			System.out.println((i + 1) + ": " + factions[i]);
		}
		
		Scanner keyboard = new Scanner(System.in);
		int civId;
		
		do
		{
			civId = keyboard.nextInt() - 1;
		} while((civId < 0) && (civId > factions.length));
		
		if(!file.exists("save/" + civId + ".txt"))
		{
			int height = 100;
			int width = 100;
			Map map = new Map(height, width);
			map.mapGen(50);
			//map.printMap(map.map, height, width);
			
			String[] civ = file.readTextFile("data/" + civId + ".txt");
			Player player = new Player(civ[0], civ[1], civ[2]);
			
			String[] data = {"0", player.name, player.faction, player.capital, Integer.toString(player.population), Integer.toString(player.gold), Integer.toString(player.food), Integer.toString(player.land)};
			
			save.saveTextFile("save/" + civId + ".txt", data);
			save.saveTextFile("save/" + civId + "_map.txt", map.map, height, width);
		}
		
		return civId;
	}
	
	public static void showInfo(int savename)
	{
		ReadData file = new ReadData();
		String[] civ = file.readTextFile("save/" + savename + ".txt");
		
		Player player = new Player(civ[1], civ[2], civ[3], Integer.parseInt(civ[4]), Integer.parseInt(civ[5]), Integer.parseInt(civ[6]), Integer.parseInt(civ[7]));
		
		System.out.println("Name: " + player.name);
		System.out.println("Faction: " + player.faction);
		System.out.println("Capital: " + player.capital);
		System.out.println("Population: " + player.population);
		System.out.println("Gold: " + player.gold);
		System.out.println("Food: " + player.food);
		System.out.println("Land: " + player.land);
		System.out.println("Power: " + player.power);
		System.out.println();
	}
	
	public static void main(String args[])
	{
		intro();
		int civId = civChoose();
		int choice;
		boolean gameLost = false;
		
		Marketplace market = new Marketplace(civId);
		ReadData file = new ReadData();
		SaveData save = new SaveData();
		Scanner keybaord = new Scanner(System.in);
		
		String[] data = file.readTextFile("save/" + civId + ".txt");
		int year = Integer.parseInt(data[0]);
		String name = data[1];
		String faction = data[2];
		String capital = data[3];
		int population = Integer.parseInt(data[4]);
		int gold = Integer.parseInt(data[5]);
		int food = Integer.parseInt(data[6]);
		int land = Integer.parseInt(data[7]);
		double power = (population * .3) + (gold * .3) + (food * .3);
		
		int born = 0;
		int dead = 0;
		int harvest = 0;
		
		while(year < 10 && !gameLost)
		{
			System.out.println();
			System.out.println("It is the year " + year);
			
			showInfo(civId);
			
			//displays the map (note: land has nothing to do with the amount on the map... the map just shows the geography of the land, land is plantable, fertile land
			String[] map = file.readTextFile("save/" + civId + "_map.txt");
			
			for(int i=0; i < map.length; i++)
			{
				System.out.println(map[i]);
			}
			
			//Report
			System.out.println();
			System.out.println(born + " people have been born this year. ");
			System.out.println(dead + " people have died this year. ");
			System.out.println(harvest + " foods has been harvested this year. ");
			System.out.println();
			
			//Buying and selling food
			int foodPrice = market.foodMarket();
			Scanner keyboard = new Scanner(System.in);
			int tempFood = 0;
			int tempEat = 0;
			
			//buying food
			try
			{
				System.out.print("Amount of food to buy: ");
				tempFood = keyboard.nextInt();
				if(((tempFood * foodPrice) > gold) || (tempFood < 0))
				{
					throw new InputMismatchException();
				}
			} catch(InputMismatchException ime)
			{
				System.err.println("Input not valid");
			}
			food = market.buyFood(tempFood, food);
			gold = gold - tempFood * foodPrice;
			System.out.println("You now have " + food + " foods. ");
			System.out.println("You have " + gold + " golds left. ");
			
			//selling food
			try
			{
				System.out.print("Amount of food to sell: ");
				tempFood = keyboard.nextInt();
				if((tempFood > food) || (tempFood < 0))
				{
					throw new InputMismatchException();
				}
			} catch(InputMismatchException ime)
			{
				System.err.println("Input not valid");
			}
			try
			{
				System.out.print("Amount of food to sell: ");
				tempFood = keyboard.nextInt();
				if((tempFood > food) || (tempFood < 0))
				{
					throw new InputMismatchException();
				}
			} catch(InputMismatchException ime)
			{
				System.err.println("Input not valid");
			}
			gold = market.sellFood(tempFood * foodPrice, gold);
			food = food - tempFood;
			System.out.println("You now have " + gold + " golds. ");
			System.out.println("You have " + food + " foods left. ");
			
			
			//buying and selling land
			int landPrice = market.landMarket();
			int tempLand = 0;
			
			//buying land
			try
			{
				System.out.print("Amount of land to buy: ");
				tempLand = keyboard.nextInt();
				if(((tempLand * landPrice) > gold) || (tempLand < 0))
				{
					throw new InputMismatchException();
				}
			} catch(InputMismatchException ime)
			{
				System.err.println("Input not valid");
			}
			land = market.buyLand(tempLand, land);
			gold = gold - tempLand * landPrice;
			System.out.println("You now have " + land + " lands.");
			System.out.println("You have " + gold + " golds left. ");
			
			//selling land
			try
			{
				System.out.print("Amount of land to sell: ");
				tempLand = keyboard.nextInt();
				if((tempLand > land) || (tempLand < 0))
				{
					throw new InputMismatchException();
				}
			} catch(InputMismatchException ime)
			{
				System.err.println("Input not valid");
			}
			gold = market.sellFood(tempLand * landPrice, gold);
			land = land - tempLand;
			System.out.println("You now have " + gold + " golds.");
			System.out.println("You have " + land + " lands left. ");
			
			//planting seeds for next year
			try
			{
				System.out.print("Acres to plant: ");
				tempLand = keyboard.nextInt();
				if((tempLand > land) || ((tempLand / 10) > food || (tempLand < 0)))
				{
					throw new InputMismatchException();
				}
			} catch(InputMismatchException ime)
			{
				System.err.println("Input not valid");
			}
			food = food - tempLand / 10;
			System.out.println("You used " + (tempLand / 10) + " foods to plant " + tempLand + " acres");			
			
			//feeding people
			try
			{
				System.out.print("How much food do you want to feed your people? ");
				tempEat = keyboard.nextInt();
				if(tempEat > food)
				{
					throw new InputMismatchException();
				}
			} catch(InputMismatchException ime)
			{
				System.err.println("Input not valid");
			}
			food = food - tempEat; //people eating food per year
			System.out.println("You feed " + tempEat + " to your people. ");
			System.out.println("You have " + food + " foods left. ");
			
			//population gains/losses
			if((population * 5) > (tempEat + 500))
				dead = (int)(Math.random() * 10 + 2 * (tempEat / 5) * (tempEat / 5));
			
			dead = dead + (int)(Math.random() * 10); //random amounts of people die every year
			
			if((population * 5) < (tempEat - 500))
				born = (int)(Math.random() * 20 + 20) + population;
			
			born = born + (int)(Math.random() * 10); //random amounts of people are born every year
			
			population = population + born - dead; //recaculates population
			
			
			//losing options
			if(population < 50)
			{
				System.out.println("You have starved too many people!!");
				gameLost = true;
			}
				
			if(gold == 0 && food == 0)
			{
				System.out.println("You have not money or food!!");
				gameLost = true;
			}
			
			//writes to save file
			String[] saveData = {Integer.toString(year), name, faction, capital, Integer.toString(population), Integer.toString(gold), Integer.toString(food), Integer.toString(land)};
			
			save.saveTextFile("save/" + civId + ".txt", saveData);
			
			//harvest crop
			harvest = tempLand * (int)(Math.random() * 4 + 1);
			
			food = food + harvest;
			
			//increase year count
			year++;
		}
		if(gameLost)
		{
			System.out.println("You lose!!");
		} else
		{
			System.out.println("You win!!!!");
		}
		
		//delets save after game's over
		save.delete("save/" + civId + ".txt");
	}
	
}
