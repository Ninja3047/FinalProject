package TheDarkAges;

import java.util.Random;

public class Map
{
	int height; // map height
	int width; // map width
	boolean[][] map; // map matrix

	public Map(int height, int width) //
	{
		this.height = height;
		this.width = width;
	}

	public boolean[][] mapGen(double landMassPercentage) // percentage of map that is
													     // land
	{
		map = new boolean[height][width]; // creates a map matrix
		Random random = new Random();

		int x;
		int y;
		int area = height * width; // area of entire map
		int seed;
		
		if(landMassPercentage > 20) // if land mass percentage is greater then 10%
		{
			seed = (int)(landMassPercentage / 20); //set the number of seeds to 1/10 the land mass percentage
		} else //otherwise
		{
			seed = 1; //set it to 1
		}
		
		for (int squares = (int) (area * (landMassPercentage / 100)) - 1; squares > 0; squares--) // counts the amount of land squares left to place on the map
		{
			boolean squareFound = false; //possible square found is intially set to false
			do
			{
				x = random.nextInt(width); //go to random x position on the map
				y = random.nextInt(height); //go to random y position on the map
				squareFound = (!map[y][x] && ((x > 0 && map[y][x-1]) || (y > 0 && map[y-1][x]) || (x<(width-1) && map[y][x+1]) || (y<(height-1) && map[y+1][x]) || ((seed > 0) && (seed-- > 0)) ) ); // if the square is not taken, and there is at least one land square around the square or there are seeds left, fill it
			} while(!squareFound); //look for new square if a valid one isn't found
			
			map[y][x] = true; //change that location on map array to land (which is true)
		}
		return map;
	}
	
	public void printMap(boolean[][] matrix, int rowSize, int columnSize) // size goes rows, columns
	{
		for(int rows = 0; rows < rowSize; rows++)
		{
			for(int columns = 0; columns < columnSize; columns++)
			{
				if(matrix[rows][columns])
				{
					System.out.print("#");
				} else
				{
					System.out.print("~");
				}
			}
			System.out.print("\n");
		}
	}

}
