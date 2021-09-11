import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HalfLife {
	private static double halfLife;
	private static double years = 0;
	private static double weight;
	private static double finalHL;
	private static String eleName;
	private static String eleSymbol;
	private static double atomicNum;

	/*
	 * Main method that Formats the heading of the output then calls the methos that calculates halflife
	 * this also sends args to setVars
	 */
	public static void main(String [] args)
	{
		setVars(args);
		System.out.println(eleName + "(" + atomicNum + eleSymbol + ")");
		System.out.println("Elapsed Years" + "     " + "Amount");
		System.out.println("-------------------------");
		calHalfLife();
	}
	
	/*
	 * This take in the argsuments sent from the main method and sets the global variable
	 */
	public static void setVars(String [] x)
	{
			atomicNum = Integer.parseInt(x[0]);
			eleName = x[1];
			eleSymbol = x[2];
			halfLife = Double.parseDouble(x[3]);
			weight = Integer.parseInt(x[4]);
			finalHL = weight;
	}
	
	/*
	 * This does the calulation for the program
	 * It calls the printer and goes line by line spitting out the calculations
	 */
	public static void calHalfLife()
	{
		if(finalHL > weight/2)
		{
			double temp = (years/halfLife)*-0.693;
			finalHL = weight * Math.exp(temp);
			printHL();
			years++;
			calHalfLife();
		}
	}
	
	/*
	 * This prints the results of the calculations
	 */
	public static void printHL()
	{
		System.out.println(years + "              " + finalHL + "g");
	}
}