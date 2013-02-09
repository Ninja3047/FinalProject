package TheDarkAges;

import java.io.*;

public class SaveData
{
	public void saveTextFile(String location, String[] data) //reads the content of a text file and returns an array with each line as an element of the array
	{	
		try
		{
			FileWriter file = new FileWriter(location); //creates a file input stream
			BufferedWriter out = new BufferedWriter(file); //creates a buffered writer
			
			for(int i=0; i < data.length; i++)
			{
				out.write(data[i]);
				out.newLine();
			}
			
			out.close(); //close the data input stream
		}
		catch (Exception e) //catch any errors
		{
			System.err.println("Error: " + e.getMessage()); //display them on the screen
		}
	}
	
	public boolean delete(String location)
	{
		File file = new File(location);
		if(file.exists())
		{
			file.delete();
			return true;
		}else
		{
			return false;
		}
	}

	public void saveTextFile(String location, boolean[][] data, int height, int width)
	{
		try
		{
			FileWriter file = new FileWriter(location); //creates a file input stream
			BufferedWriter out = new BufferedWriter(file); //creates a buffered writer
			
			for(int rows = 0; rows < height; rows++)
			{
				for(int columns = 0; columns < width; columns++)
				{
					if(data[rows][columns])
					{
						out.write("#");
					} else
					{
						out.write("~");
					}
				}
				out.write("\n");
			}
			
			out.close(); //close the data input stream
		}
		catch (Exception e) //catch any errors
		{
			System.err.println("Error: " + e.getMessage()); //display them on the screen
		}
	}
	
}
