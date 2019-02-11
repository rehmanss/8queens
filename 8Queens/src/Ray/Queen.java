package Ray;

import java.util.Scanner;

public class Queen {

	//  This variable will hold the user queen column position for later comparison 
	int userColumn;

	int resultNumber = 1;
	
	//print 8x8 board
	String[][] board = new String[8][8];

	
	 //Fill all positions on the board with spaces and then the queen will be placed
	public void setup() {

		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {

				//This will fill all positions with spaces on the board
				board[row][column] = " ";
			}
		}
	}



	//printing the state of the board
	public void printBoard() {

		int numLocation = 1;

		// This nested loop will create the chess board
		for (int row = 0; row < board.length; row++) {
			System.out.print("\n  +-------------------------------+\n" + numLocation++ + " |");

			for (int column = 0; column < board[row].length; column++) {
				System.out.print(" ");
				
				//Will print spaces for queen
				System.out.print(board[row][column]);
				System.out.print(" |");
			}
		}
		//Print -
		System.out.print("\n  +-------------------------------");
		
		// print a to h on board
		System.out.print("\n    a   b   c   d   e   f   g   h");
		System.out.println();

	}



	
	 // With the help of this function user will be able to placed the queen on the board, such as a1,  the programme keep repeat running and replace BIO 
	public void firstQueenPlace(String userInput) {
		Scanner scan = new Scanner(System.in);
		
		String boardLocation;
		boolean success = false;
		boardLocation=userInput;

		// Catch block to handle make sure the user input is correct
		while (!success) {
			try {
			
			//board row and column please refer to ASCII table for more information.
				int column = boardLocation.charAt(0) - 'a';
				int row = boardLocation.charAt(1) - 49;
				
				// Enter user's Queen at the selected location by the user such as b1 or c2.
				board[row][column] = "♛"; 
				
				// specified position on the chess board 
				userColumn = column;
				success = true;
			}
			//If the user input is wrong below error will be printed couple time and will scan to make sure the user input is correct.
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.print("Please enter a two character position between a1 & h8: ");
				boardLocation = scan.next();
			}


			catch (StringIndexOutOfBoundsException e) {
				System.out.print("Please enter a two character position between a1 & h8: ");
				boardLocation = scan.next();
			}
		}
		
		//scanner closed
		scan.close();
	}


	
	  // Checking for all possible encounter for each new queen
	public boolean isOk(String chessBoard[][], int row, int column) {

		//backtracking
		int k, q;

		// Check for row encounter u265B is the code for the queen icon please refer to ASCII table, backtracking
		for (q = 0; q < 8; q++) {
			if (chessBoard[row][q] == "\u265B")
				return false;
		}
		
		//Check top diagonal on the left side
		for (k = row, q = column; k >= 0 && q >= 0; k--, q--) {
			if (chessBoard[k][q] == "\u265B")
				return false;
		}
		
		//Check bottom diagonal on the left side
		for (k = row, q = column; k < 8 && q >= 0; k++, q--) {
			if (chessBoard[k][q] == "\u265B")
				return false;
		}
		
		//Check top diagonal on right side
		for (k = row, q = column; k >= 0 && q < 8; k--, q++) {
			if (chessBoard[k][q] == "\u265B")
				return false;
		}
		
		 //Check bottom diagonal on right side
		for (k = row, q = column; k < 8 && q < 8; k++, q++) {
			if (chessBoard[k][q] == "\u265B")
				return false;
		}
		
		return true;
	}



	//Start placing the queen recursively 
	public boolean remainingQueenPlace(String chessBoard[][], int column) {

		// When all queens are placed, print out all solutions that include the user placed queen    
		if (column == 8) {
			
			//Will place result number
			System.out.print("\nResult " + resultNumber++);
			printBoard();

			return false;
		}
		
		/*
		 If the the column already contains a user queen skip the column the queen to stay 
		 */
		if (column == userColumn) {
			if (remainingQueenPlace(chessBoard, column + 1) == true)
				return true;
		}
		//from 0 to 8 check all possible solutions
		for (int x = 0; x < 8; x++) {
			
			/*
		 if safe place the queen in the area and move to the next column u265B is the queen code from ASCII table, 
		 */
			
			if (isOk(chessBoard, x, column)) {
				chessBoard[x][column] = "\u265B";

				if (remainingQueenPlace(chessBoard, column + 1) == true)
					return true;
				//Else back Track
				chessBoard[x][column] = " ";
			}           
		}
		return false;
	}

	// start placing the remaining queens
	public void eightQueens() {
		remainingQueenPlace(board, 0);
	}
}
