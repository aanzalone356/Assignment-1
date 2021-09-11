import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class  SudokuVerifier {
	private static String [] snums = new String [9];
	private static int [] evline = new int [9];
	private static boolean vert;
	private static boolean hori;
	private static boolean cube;
	private static boolean finished;
	public static boolean totaltf;
	public static File source;
	
	/*
	 * This takes the argument which should be a file and sends it to the sudokusolver method
	 * This is so later we can, in the main method, output whether our sudokus are valid or not
	 */
	public static void main(String [] args)
	{
		String f = args[0];
		File file = new File(f);
		sudokuSolver(file);
		if(isCorrect() == false)
		{
			System.out.println("Sudoku is Invalid");
		}
		else if(isCorrect() == true)
		{
			System.out.println("Sudoku is Valid");
		}
	}
	
	/*
	 * This calls all the other methods to check if they are all valid options
	 * it is constantly looping until either all conditions are true or until one evaluates false
	 */
	public static void sudokuSolver(File input)
	{
		source = input;
		finished = false;
		getHorizontal();
		while(finished == false)
		{
			if(hori == true)
			{
				getVertical();
			}
			else if(hori == true && vert == true)
			{
				get3x3();
			}
			else if(hori == true && vert == true && cube == true)
			{
				totaltf = true;
				finished = true;
			}
			else
			{
				totaltf = false;
				finished = true;
			}
		}
	}
	
	/*
	 * This takes in the strings from the files and makes them into an array of integers
	 * 
	 * This method solves for the 3x3 check
	 * It takes three rows of the file and makes them into arrays
	 * it they breaks them into three cubes which are all evaluated
	 * It continues this all the way down until it spits out a true or false for all the 3x3s
	 * in the sudoku puzzel
	 */
	public static void get3x3()
	{
		boolean verify = true;
		String [] line2 = {};
		String [] line3 = {};
		Integer [] evline2 = {};
		Integer [] evline3 = {};
		int [] sentline = {};
		int lim = 0;
		try 
		{
			Scanner scan = new Scanner(new File("source"));
			for(int i = 0; i < 9; i++)
			{
				while (verify == true)
				{
					String tran = scan.nextLine();
					if(!tran.isEmpty())
					{
						String [] temp = tran.split(" ");
						for(int z = 0; z < temp.length; z++)
						{
							snums[z] = temp[z];
						}
					}
					for(int z = 0; z < snums.length; z++)
					{
						evline[z] = Integer.parseInt(snums[z]);
					}
					String tran2 = scan.nextLine();
					if(!tran2.isEmpty())
					{
						String [] temp = tran.split(" ");
						for(int z = 0; z < temp.length; z++)
						{
							line2[z] = temp[z];
						}
					}
					for(int z = 0; z < line2.length; z++)
					{
						evline2[z] = Integer.parseInt(line2[z]);
					}
					String tran3 = scan.nextLine();
					if(!tran3.isEmpty())
					{
						String [] temp = tran.split(" ");
						for(int z = 0; z < temp.length; z++)
						{
							line3[z] = temp[z];
						}
					}
					for(int z = 0; z < line3.length; z++)
					{
						evline3[z] = Integer.parseInt(line3[z]);
					}
					for(int f = 0; f < evline.length; f++)
					{
						
						if(sentline.length == evline.length)
						{
							verify = solve(sentline);
							if(verify == true)
							{
								sentline = null;
								lim = 0;
							}
						}
						else
						{
							sentline[f] = evline[f];
							sentline[f+1] = evline2[f];
							sentline[f+2] = evline3[f];
							lim = lim + 3;
						}
					}
				}
			}
			cube = verify;
			
		}
		catch (FileNotFoundException exe) 
		{
			exe.printStackTrace();
		}
	}
	
	/*
	 * This takes in the strings from the files and makes them into an array of integers
	 * 
	 * it then takes the first index of all the rows and sets them into an array
	 * this array is then tested to see if all the numbers are present if so it spits out true;
	 */
	public static void getVertical()
	{
		boolean verify = true;
		try 
		{
			Scanner scan = new Scanner(new File("source"));
			for(int i = 0; i < 9; i++)
			{
				while (verify == true)
				{
					for(int z = 0; z < 9; z++)
					{
						String tran = scan.nextLine();
						if(!tran.isEmpty())
						{
							String [] temp = tran.split(" ");
							snums[z] = temp[i];
						}
					}
					for(int x = 0; x < snums.length; x++)
					{
						evline[x] = Integer.parseInt(snums[x]);
					}
					verify = solve(evline);
				}
			}
			vert = verify;
			
		}
		catch (FileNotFoundException exe) 
		{
			exe.printStackTrace();
		}
	}
	
	/*
	 * This takes in the strings from the files and makes them into an array of integers
	 * 
	 * this then just takes a line and tests to see if all the numbers are present once
	 */
	public static void getHorizontal()
	{
		boolean verify = true;
		try 
		{
			Scanner scan = new Scanner(source);
			for(int i = 0; i < 9; i++)
			{
				while (verify == true)
				{
					for(int z = 0; z < 9; z++)
					{
						String tran = scan.nextLine();
						if(!tran.isEmpty())
						{
							String [] temp = tran.split(" ");
							snums[z] = temp[i];
						}
					}
					for(int x = 0; x < snums.length; x++)
					{
						evline[x] = Integer.parseInt(snums[x]);
					}
					verify = solve(evline);
				}
			}
			vert = verify;
			
		}
		catch (FileNotFoundException exe) 
		{
			exe.printStackTrace();
		}
	}
	
	/*
	 * this is the sovle method that makes sure very number 1-9 is present once and only once
	 * then sends back true or false
	 */
	public static boolean solve(int [] inputs)
	{
		boolean [] check = {false,false,false,false,false,false,false,false,false};
		for(int i = 0; i < 9; i++)
		{
			for(int z = 0; z < inputs.length; z++)
			{
				if(inputs[z] == i)
				{
					check[i] = true;
					z = inputs.length;
				}
			}
		}
		for(int i = 0; i < check.length; i++)
		{
			int temp = 0;
			if(check[i] == false)
			{
				return false;
			}
			else if(check[i] == true)
			{
				temp++;
			}
			else
			{
				return false;
			}
			if(temp == 9)
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Final method to get the global variable
	 */
	public static boolean isCorrect()
	{
		return totaltf;
	}
}