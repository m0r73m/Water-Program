// William Rice
// Water Program
// 10/27/2020
// This program will calculate and print out bills for the city water company.
// The rates vary depending on whether the bill is for home use, commercial use, or industrial use. 

import java.io.*;

public class Waterproblem {

	public final static int GALLONS_TIER_1 = 4000000;			// Sets tier 1
	public final static int GALLONS_TIER_2 = 10000000;			// Sets tier 2
	private int gallonsUsed;									// Holds the gallons used
	private char typeUse;										// Holds the use case
	private int idNum;											// Holds the ID number
	
	/* Constructs the bill format
	 * also calculates the amount due and sets up static variables
	 */
	public Waterproblem(int ID, char code, int gallons) {

		typeUse = code;
		gallonsUsed = gallons;
		idNum = ID;
		
	}
	
	/* Returns the amount due as an integer
	 */
	public double getAmountDue() {
		
		return calcAmount();
		
	}
	
	/* Returns the use case of the bill as a character
	 */
	public char getTypeUse() {
		
		return typeUse;
		
	}
	
	public double calcAmount() {
		
		if(typeUse == 'h') {

			return (int) ((gallonsUsed * 0.0005 + 5) * 100 + 0.5) / 100.0;
			
		}
		else if(typeUse == 'c') {

			if(gallonsUsed <= GALLONS_TIER_1) {
				
				return 1000;
				
			}
			else {
				
				return 1000 + (int) (((gallonsUsed - GALLONS_TIER_1) * 0.00025) * 100 + 0.5) / 100.0 ;
				
			}
			
		}
		else if(typeUse == 'i') {

			if(gallonsUsed <= GALLONS_TIER_1) {
				
				return 1000;
				
			}
			else if(gallonsUsed <= GALLONS_TIER_2) {
				
				return 2000;
				
			}
			else {
				
				return 3000;
				
			}
			
		}
		else {
			return 0;
		}
		
	}
	
	/* Method returns the bill in a string format
	 * #ID Use Gallons
	 * Amount Due
	 */
	public String toString() {
		
		if(typeUse == 'h')
			return "ID : " + idNum + "   Use : Home   Water Used : " + gallonsUsed + "\nAmount Due : " + getAmountDue();
		else if(typeUse == 'c')	
			return "ID : " + idNum + "   Use : Commercial   Water Used : " + gallonsUsed + "\nAmount Due : " + getAmountDue();
		else
			return "ID : " + idNum + "   Use : Industrial   Water Used : " + gallonsUsed + "\nAmount Due : " + getAmountDue();
	
	}
	
	/* Method that checks that the given input is able to be turned into and integer.
	 * If the given string is unable to be converted into an integer then it outputs where the issue is.
	 */
	private static boolean checkForInt(String inputString) {
		
		// List of allowed characters in an integer
		String allowedChars = new String("1234567890");
		
		// If the integer is negative then we don't want to give an error
		if(inputString.charAt(0) == '-') {
			inputString = inputString.substring(1);
		}
		
		// Checks the string character by character
		for(int x = 0; x < inputString.length(); x++) {
			
			// Checks to see if the character is in the list of allowed characters
			if(allowedChars.indexOf(inputString.charAt(x)) == -1) {
				
				// Outputs where the issue is
				System.out.println("Error at position " + (x + 1) + " contains " + inputString.charAt(x));
				// Signifies the issue
				return false;
				
			}
			
		}
		
		// If the program gets here then the string can be converted to an integer.
		return true;
		
	}
	
	/* Main method, where the main loop is
	 * this is also where the input is given 
	 */
	public static void main(String[] args) throws IOException{
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		boolean running = false;
		
		System.out.println("Do you want to run the program? (y for yes)");
		System.out.print("> ");
		String runningStr = input.readLine();
		
		if(runningStr.equalsIgnoreCase("y") || runningStr.equalsIgnoreCase("yes")) {
			running = true;
		}
		
		while(running) {
			
			System.out.println("Input the id number (Integer)");
			System.out.print("> ");
			String idNumberStr = input.readLine();
			
			while(!checkForInt(idNumberStr)) {
				
				System.out.println("Input the id number (Integer)");
				System.out.print("> ");
				idNumberStr = input.readLine();
				
			}
			
			int idNumber = Integer.valueOf(idNumberStr);
			
			System.out.println("Input the type of use (Char)");
			System.out.print("> ");
			String typeOfUse = input.readLine();
			
			char use = typeOfUse.charAt(0);
			
			System.out.println("Input the number of gallons used (Integer)");
			System.out.print("> ");
			String gallonsStr = input.readLine();
			
			while(!checkForInt(gallonsStr)) {
				
				System.out.println("Input the number of gallons used (Integer)");
				System.out.print("> ");
				gallonsStr = input.readLine();
				
			}
			
			int gallons = Integer.valueOf(gallonsStr);
			
			Waterproblem solve = new Waterproblem(idNumber, use, gallons);
			
			System.out.println("\n   ------- Bill Start -------\n");
			System.out.println(solve);
			System.out.println("\n   ------- End -------\n");
		
			System.out.println("Do you want to run again? (y for yes)");
			System.out.print("> ");
			runningStr = input.readLine();
			
			if(runningStr.equalsIgnoreCase("y") || runningStr.equalsIgnoreCase("yes")) {
				running = true;
			}
			else {
				running = false;
			}
			
		}
		
	}
	
}

/*
Do you want to run the program? (y for yes)
> y
Input the id number (Integer)
> 10
Input the type of use (Char)
> c
Input the number of gallons used (Integer)
> 5000022

   ------- Bill Start -------

ID : 10   Use : Commercial   Water Used : 5000022
Amount Due : 1250.01

   ------- End -------

Do you want to run again? (y for yes)
> y
Input the id number (Integer)
> 11
Input the type of use (Char)
> i
Input the number of gallons used (Integer)
> 1234567

   ------- Bill Start -------

ID : 11   Use : Industrial   Water Used : 1234567
Amount Due : 1000.0

   ------- End -------

Do you want to run again? (y for yes)
> y
Input the id number (Integer)
> 12
Input the type of use (Char)
> h
Input the number of gallons used (Integer)
> 8765

   ------- Bill Start -------

ID : 12   Use : Home   Water Used : 8765
Amount Due : 9.38

   ------- End -------

Do you want to run again? (y for yes)
> y
Input the id number (Integer)
> 13
Input the type of use (Char)
> c
Input the number of gallons used (Integer)
> 444444

   ------- Bill Start -------

ID : 13   Use : Commercial   Water Used : 444444
Amount Due : 1000.0

   ------- End -------

Do you want to run again? (y for yes)
> y
Input the id number (Integer)
> 14
Input the type of use (Char)
> i
Input the number of gallons used (Integer)
> 5555555

   ------- Bill Start -------

ID : 14   Use : Industrial   Water Used : 5555555
Amount Due : 2000.0

   ------- End -------

Do you want to run again? (y for yes)
> y
Input the id number (Integer)
> 15
Input the type of use (Char)
> i
Input the number of gallons used (Integer)
> 20000000

   ------- Bill Start -------

ID : 15   Use : Industrial   Water Used : 20000000
Amount Due : 3000.0

   ------- End -------

Do you want to run again? (y for yes)
> n
*/