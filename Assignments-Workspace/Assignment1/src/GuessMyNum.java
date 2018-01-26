import java.util.Random;
import java.util.Scanner;

/**
 * This class runs a game where the computer chooses a random number between 1
 * and 20 (default), and prompts the user to guess what number the computer has
 * chosen. The user is given feedback, saying that the guess was too high or too
 * low, until the user has guessed the correct answer.
 * 
 * @author T7
 * @version 1.1.3
 *
 */
public class GuessMyNum {
	
	// Utility objects
	static Random	_randGen	= new Random();
	static Scanner	_scanner	= new Scanner(System.in);
	
	/**
	 * Runs the game, as defined in the class JavaDoc.
	 * 
	 * @see assignment1.GuessMyNum
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		
		// Constants and variables
		final int MIN_NUM = 1, MAX_NUM = 20;
		// Random number between MIN_NUM and MAX_NUM
		final int COMP_NUM = _randGen.nextInt(MAX_NUM - MIN_NUM + 1) + MIN_NUM;
		int usrNum;
		
		// Debug Printout
		System.out.println("Computer's number : " + Integer.toString(COMP_NUM) + "\n");
		
		// Notify the user the game has started
		System.out.println("The computer has chosen a number between " + Integer.toString(MIN_NUM) + " and "
				+ Integer.toString(MAX_NUM) + ".");
		
		/*
		 * Loop
		 * 
		 * Continually prompts the user to guess the computer's number, checks the
		 * guess, and quits once the user guesses correctly.
		 */
		do {
			
			// Get the user's guess
			System.out.print("Please guess the computer's number : ");
			usrNum = safeIntInputRange(MIN_NUM, MAX_NUM);
			
			// Get an integer representing the sign of the difference between the
			// computer's number and the user's number
			int signum = Integer.signum(COMP_NUM - usrNum);
			
			// Generate a message for each case needed.
			String msg;
			switch (signum) {
				
				case -1: // usrNum > COMP_NUM
					msg = " is too high!";
					break;
				
				case 0: // usrNum == COMP_NUM
					msg = " is the correct number! Congratulations!";
					break;
				
				case 1: // usrNum < COMP_NUM
					msg = " is too low!";
					break;
				
				default: // Should never be reached
					msg = " is not a number!";
					
					// Quit the program with an error status
					_scanner.close();
					System.exit(1);
					
			}
			
			// Tell the user the outcome of their guess
			System.out.println(Integer.toString(usrNum) + msg + "\n");
			
		} while (usrNum != COMP_NUM); // Loop Condition
		
		// Quit the program with a successful status
		System.out.println("Program closing...");
		_scanner.close();
		System.exit(0);
		
	}
	
	/**
	 * Retrieves an integer within a specified value range from the console, whilst
	 * automatically dealing with any errors that arise, or the given value being
	 * outside of the specified range. It will keep accepting input until a valid
	 * integer is parsed.
	 * 
	 * @param min The minimum acceptable value.
	 * @param max The maximum acceptable value.
	 * @return The first acceptable integer from the console.
	 */
	private static int safeIntInputRange(int min, int max) {
		
		// Constants and variables
		int input;
		
		/*
		 * Loop
		 * 
		 * Continually prompts the console for input, only returning the read value once
		 * an integer within the specified range is read.
		 */
		while (true) {
			
			try {
				
				// ... to read an integer from the console
				input = Integer.parseInt(_scanner.nextLine());
				
			} catch (NumberFormatException e) { // If the value isn't an integer...
				
				// Ask the user for an integer
				System.out.print("Please enter a valid integer : ");
				continue;
				
			}
			
			if (input < min || input > max) { // If the value is outside of the specified range...
				
				// Ask the user for a different integer; inside the specified range
				System.out.print("Please enter a number between " + min + " and " + max + " : ");
				continue;
				
			} else {
				
				// Return the value
				return input;
				
			}
			
		}
	}
	
}
