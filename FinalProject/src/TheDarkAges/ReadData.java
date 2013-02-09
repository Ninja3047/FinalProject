package TheDarkAges;

import java.io.*;

public class ReadData
{
	public int lineNumber(String location) //counts the number of lines in a text file
	{
		int numberOfLines = 0; //initialize the number of lines
		
		try
		{
			FileInputStream file = new FileInputStream(location); //creates a file input stream
			DataInputStream data = new DataInputStream(file); //creates a data input stream
			BufferedReader br = new BufferedReader(new InputStreamReader(data)); //creates a buffered reader
			
			String line; //string to store the current line
			while((line = br.readLine()) != null) //keep looping until there's nothing left
			{
				numberOfLines++; //increment number of lines
			}
			br.close(); //closes the buffered reader
		}
		catch (Exception e) //catch any error
		{
			System.err.println("Error: " + e.getMessage()); //display them on the screen
		}
		return numberOfLines; //return the number of lines
	}
	
	public String[] readTextFile(String location) //reads the content of a text file and returns an array with each line as an element of the array
	{	
		String[] lines = null; //initialize lines outside of try block
		try
		{
			FileInputStream file = new FileInputStream(location); //creates a file input stream
			DataInputStream data = new DataInputStream(file); //creates a data input stream
			BufferedReader br = new BufferedReader(new InputStreamReader(data)); //creates a buffered reader
			
			int numberOfLines = lineNumber(location);
			lines = new String[numberOfLines]; //after knowing the number of lines, then create the array
			
			for(int i = 0; i < numberOfLines; i++) //loop through the file
			{
				lines[i] = br.readLine(); //store each line in the array
			}
			data.close(); //close the data input stream
		}
		catch (Exception e) //catch any errors
		{
			System.err.println("Error: " + e.getMessage()); //display them on the screen
		}
		return lines; //return the array
	}
	
	public boolean exists(String location)
	{
		File file = new File(location);
		if(file.exists())
			return true;
		return false;
	}
	
}
