import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game_Info_Processer {

	private static int counter;
	private static String input;
	public static void main(String [] args)
	{
		//search for the thing I want(Lego Star wars) in every array from the list
		//print
		//reset
		//move over an index
		//search
		//print
		//reset
		input = args[0];
		System.out.println("Publisher Game Count");
		System.out.println("--------------------");
		searchDev();
		System.out.println("Game Platform Count");
		System.out.println("--------------------");
		searchPlat();
	}
	
	public static void searchDev()
	{
		//Searches the last index in the arrays and sends them to print after checking for like types
		try
		{
			counter = 0;
			Scanner scan = new Scanner(new File(input));
			String tran = scan.nextLine();
			String [] temp = {};
			if(!tran.isEmpty())
			{
				temp = tran.split(",");	
			}
			String check = temp[4];
			innerDev(check);
			print(check,counter);
		}
		catch (FileNotFoundException exe)
		{
			exe.printStackTrace();
		}
	}
	
	public static void innerDev(String x)//Have the file as an input type in order to avoid restarting the list instead of going down.
	{
		try
		{
			Scanner inScan = new Scanner(new File(input));
			String inTran = inScan.nextLine();
			String [] inTemp = {};
			if(!inTran.isEmpty())
			{
				//This doesn't account for blanks
				inTemp = inTran.split(",");
				if(inTemp[4] == x)
				{
					counter++;
				}
			    
			}
			else if(inTran.isEmpty()) 
			{
				innerDev(x);
			}
		}
		catch (FileNotFoundException exe)
		{
			exe.printStackTrace();
		}
	}
	
	public static void searchPlat()
	{
		//takes first index and goes until it can't find the same game.
		//stops, print, then runs again on the next line which should be the next game
		try
		{
			counter = 0;
			Scanner scan = new Scanner(new File(input));
			String tran = scan.nextLine();
			String [] temp = {};
			if(!tran.isEmpty())
			{
				//This doesn't account for blanks
				temp = tran.split(",");	
			}
			String check = temp[1];
			innerPlat(check);
			print(check,counter);
		}
		catch (FileNotFoundException exe)
		{
			exe.printStackTrace();
		}
		
	}
	
	public static void innerPlat(String x)
	{
		try
		{
			Scanner inScan = new Scanner(new File(input));
			String inTran = inScan.nextLine();
			String [] inTemp = {};
			if(!inTran.isEmpty())
			{
				//This doesn't account for blanks
				inTemp = inTran.split(",");
				if(inTemp[1] == x)
				{
					counter++;
				}
				else if(inTemp[1] != x)
				{
					//remove from method
					
				}
			    
			}
			else if(inTran.isEmpty()) 
			{
				innerDev(x);
			}
		}
		catch (FileNotFoundException exe)
		{
			exe.printStackTrace();
		}
	}
	
	public static void print(String name, int num)
	{
		System.out.println(name + "        " + num);
	}
}