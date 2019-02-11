package RehmanShabab;

import java.util.Scanner;

public class Run {

	private static Scanner scan;

	public static void main(String args[]) {

		// Print message 
		scan = new Scanner(System.in);
		
		String userInput;
		System.out.print("Please enter a position on chessboard ie a1 or b2 (lowercase) : ");
		userInput = scan.next();

		// Constructor for the Queen class
		Queen queens = new Queen();
		queens.setup();
		
		//user input
		queens.firstQueenPlace(userInput);
		queens.printBoard();
		
		//Print message when queen is placed
		System.out.println("\nAbove Queen is placed by with the userinput ↑\n");
		System.out.println("Below All 8 Queens are placed ↓");
		queens.eightQueens(); 
	}
}
