import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Graphics;
import java.awt.Color;

public class Recursive {

	/**
	 * Problem 1: convert a base 10 int to binary recursively.<br>
	 *   pre: n >= 0<br>
	 *   post: Returns a String that represents N in binary.
	 *   All chars in returned String are '1's or '0's. Most significant digit is at position 0
	 *   @param n the base 10 int to covnert to base 2
	 */
	public String getBinary(int n) {        
		if (n < 0)
			throw new IllegalArgumentException("Failed precondition: getBinary. n must be >= 0. n: " + n);
		String result = "";
		// Base Case: If the number if 0 or 1, return 0 or 1 respectively
		if (n == 0)
			result += "0";
		else if (n == 1)
			result += "1";
		else {
			// Recursive step: Call the method dividing the number in half (because of base 2) while
			// adding on the remainder AT THE END of result
			result += getBinary(n/2) + n%2;
		}
		return result;
	}


	/**
	 * Problem 2: reverse a String recursively.<br>
	 *   pre: stringToRev != null<br>
	 *   post: returns a String that is the reverse of stringToRev
	 *   @param stringToRev the String to reverse.
	 */
	public String revString(String stringToRev) {
		if (stringToRev == null)
			throw new IllegalArgumentException("Failed precondition: revString. parameter may not be null.");
		// Base case: If the string is of length 0
		if (stringToRev.length() == 0)
			return stringToRev;
		// Recursive step: Call the method with the first letter taken off, adding on the first letter 
		// AT THE END of the return
		return revString(stringToRev.substring(1)) + stringToRev.charAt(0);
	}


	/**
	 * Problem 3: Returns the number of elements in data
	 * that are followed directly by value that is
	 * double that element.
	 * pre: data != null
	 * post: return the number of elements in data that are followed immediately by double the value
	 */
	public int nextIsDouble(int[] data) {
		return getDoubles(data, 0);
	}

	public int getDoubles(int[] data, int index) {
		// Base case: If the end of the array has been reached, return 0 because there can
		// be no doubles after the last value.
		if (index == data.length-1)
			return 0;
		// Recursive step: If the next value is double the current one, return 1 + how ever many
		// doubles are left. If the next value is not double the current one, return 0 + how ever
		// many doubles are left.
		else if (data[index]*2 == data[index+1])
			return 1 + getDoubles(data, index+1);
		else
			return 0 + getDoubles(data, index+1);
	}


	/**  Problem 4: Find all combinations of mnemonics for the given number.<br>
	 *   pre: number != null, all characters in number are digits<br>
	 *   post: see tips section of assignment handout
	 *   @param number The number to find mnemoics for
	 */
	public ArrayList<String> listMnemonics(String number) {
		if (number == null || !allDigits(number))
			throw new IllegalArgumentException("Failed precondition: listMnemonics");
		ArrayList<String> result = new ArrayList<String>();
		recursiveMnemonics(result, "", number);
		return result;
	}


	/*
	 * Helper method for listMnemonics
	 * mnemonics stores completed mnemonics
	 * mneominicSoFar is a partial (possibly complete) mnemonic
	 * digitsLeft are the digits that have not been used from the original number
	 */
	private void recursiveMnemonics(ArrayList<String> mnemonics,
			String mnemonicSoFar, String digitsLeft) {
		// Base case: If there are no digits left to get letters for, add the mnemonic to the ArrayList
		if (digitsLeft.equals(""))
			mnemonics.add(mnemonicSoFar);
		// Recursive step: Get the potential characters, and for each one call the method again with
		// that character added on the end and one less digit left to get potential characters for
		else {
			String characters = digitLetters(digitsLeft.charAt(0));
			for (int i=0; i<characters.length(); i++)
				recursiveMnemonics(mnemonics, mnemonicSoFar + characters.charAt(i), digitsLeft.substring(1));
		}
	}


	// used by method digitLetters
	private static final String[] letters = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};


	/* helper method for recursiveMnemonics
	 * pre: ch is a digit '0' through '9'
	 * post: return the characters associated with this digit on a phone keypad
	 */
	private String digitLetters(char ch) {
		assert ('0' <= ch) && (ch <= '9') : "Failed precondition: digitLetters";
		int index = ch - '0';
		return letters[index];
	}


	/* helper method for listMnemonics
	 * pre: s != null
	 * post: reutrn true if every character in s is a digit ('0' through '9')
	 * */
	private boolean allDigits(String s) {
		assert s != null : "Failed precondition: allDigits";
		boolean allDigits = true;
		int i = 0;
		while(i < s.length() && allDigits){
			allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
			i++;
		}
		return allDigits;
	}


	/**
	 * Problem 5: Create a DrawingPanel and place Sierpinski triangles in it.
	 * The lower left corner shall be 20 pixels
	 * from the left edge of the window
	 * and 20 pixels from the bottom of the window.
	 * @param windowSize > 20
	 * @param minSideLength > 4
	 * @param startingSideLength > minSideLength
	 */
	public void drawTriangles(int windowSize,
			int minSideLength, int startingSideLength){
		DrawingPanel p = new DrawingPanel(windowSize, windowSize);
		Graphics g = p.getGraphics();
		g.setColor(Color.BLUE);
		drawTriangles(minSideLength, startingSideLength, 20, windowSize - 20, g);
	}

	// helper method for drawTriangles
	private void drawTriangles(int minSideLength, double currentSideLength,
			double x1, double y1, Graphics g) {
		// Base case: If the current side length is less than or equals to the minimum side length, 
		// draw the triangle.
		if (currentSideLength <= minSideLength) {
			g.drawLine((int) x1, (int) y1, (int) (x1+currentSideLength), (int) y1);
			g.drawLine((int) x1, (int) y1, (int) (x1+currentSideLength/2), (int) (y1-(Math.sqrt(3)*currentSideLength/2)));
			g.drawLine((int) (x1+currentSideLength/2), (int) (y1-(Math.sqrt(3)*currentSideLength/2)),
					(int) (x1+currentSideLength), (int) y1);
		}
		// Recursive step: Call the method three times with different coordinates
		else {
			drawTriangles(minSideLength, currentSideLength/2, x1, y1, g);
			drawTriangles(minSideLength, currentSideLength/2, x1+currentSideLength/4, y1-(Math.sqrt(3)*currentSideLength/4), g);
			drawTriangles(minSideLength, currentSideLength/2, x1+currentSideLength/2, y1, g);
		}

	}


	/**
	 * Problem 6: Draw a Sierpinski Carpet.
	 * @param size the size in pixels of the window
	 * @param limit the smallest size of a sqauer in the carpet.
	 */
	public void drawCarpet(int size, int limit) {
		DrawingPanel p = new DrawingPanel(size, size);
		Graphics g = p.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,size,size);
		g.setColor(Color.WHITE);
		drawSquares(g, size, limit, 0, 0);
	}


	/* Helper method for drawCarpet
	 * Draw the individual squares of the carpet.
	 * @param g The Graphics object to use to fill rectangles
	 * @param size the size of the current square
	 * @param limit the smallest allowable size of squares
	 * @param x the x coordinate of the upper left corner of the current square
	 * @param y the y coordinate of the upper left corner of the current square
	 */
	private static void drawSquares(Graphics g, int size, int limit, double x, double y) {
		// Base case: If the size of the square is less than or equals to to limit do nothing
		// This requires no code since nothing is done for the base case.
		// Recursive step: If the size of the square is greater than the limit, draw a white rectangle in the middle
		// of the square, then draw a Sierpinski carpet in the remaining eight sub-squares.
		if (size > limit) {
			g.fillRect((int) (x+size/3), (int) (y+size/3), size/3, size/3);
			drawSquares(g, size/3, limit, x, y);
			drawSquares(g, size/3, limit, x, y+size/3);
			drawSquares(g, size/3, limit, x, y+2*size/3);
			drawSquares(g, size/3, limit, x+size/3, y);
			drawSquares(g, size/3, limit, x+size/3, y+2*size/3);
			drawSquares(g, size/3, limit, x+2*size/3, y);
			drawSquares(g, size/3, limit, x+2*size/3, y+size/3);
			drawSquares(g, size/3, limit, x+2*size/3, y+2*size/3);
		}
	}

	// size of Sudoko board
	/**
	 * The size of the Sudoko board. Value will be a perfect square greater than 0.
	 */
	public static final int BOARD_SIZE = 9; // must be perfect square

	/**
	 * The size of a mini marix on the Sodoko board.
	 */
	public static final int MINI_SIZE = (int)(Math.sqrt(BOARD_SIZE));


	/**
	 * Problem 7: Find a solution to a Sudoko puzzle.
	 * <br>pre: board != null, board is 9 by 9.
	 * <br>post: return a board that is the solved puzzle
	 * or a copy of the original board if there is no solution
	 * @param startBoard The starting board.
	 * Empty values = 0, given values = 1 through 9 may not be changed
	 */
	public int[][] getSudokoSolution(int[][] startBoard) {
		if(startBoard == null || startBoard.length != BOARD_SIZE || startBoard[0].length != BOARD_SIZE)
			throw new IllegalArgumentException("Violation of precondition in getSudo");
		// store solution in board so we don't change startBoard
		int[][] board = copyBoard(startBoard);
		recursiveSolver(board, 0, 0);
		return board;
	}

	private static boolean recursiveSolver(int[][] board, int row, int col) {
		boolean changed = false; // to see if the value in a row and column was given initially or changed
		// Base case: If the row value has reached 9, which is out of bounds, that means the whole board
		// has been solved properly. Return true to start a chain of returns.
		if (row == 9)
			return true;
		// Recursive step: If the position is zero, try 1-9. If one of the values is fine, continue on. 
		// If no values work, set the position to zero and go back. If the value at the position is not
		// zero, it was a given value, so just move on.
		else {
			if (board[row][col] == 0) {
				for (int i=1; i<=9; i++) {
					board[row][col] = i;
					changed = true;
					if (digitsOkay(board, row, col)) {
						if (col == 8) {
							boolean check = recursiveSolver(board, row+1, 0);
							if (check)
								return true;
						}
						else {
							boolean check = recursiveSolver(board, row, col+1);
							if (check)
								return true;
						}
					}
				}
			}
			else { // if the position was a given value, not something the program assigned
				if (col == 8) {
					boolean check = recursiveSolver(board, row+1, 0);
					if (check)
						return true;
				}
				else {
					boolean check = recursiveSolver(board, row, col+1);
					if (check)
						return true;
				}
			}
		}
		if (changed) // if the value was not given (avoids changing given values to 0, resulting in an incorrect solution)
			board[row][col] = 0; // change it back to zero if it had been changed
		return false;
	}

	// helper method to make a copy of a sudoko.
	// CS314 students, you DO NOT need to call this method.
	private static int[][] copyBoard(int[][] startBoard) {
		int[][] result = new int[startBoard.length][];
		for(int r = 0; r < result.length; r++)
			result[r] = Arrays.copyOf(startBoard[r], startBoard[r].length);
		return result;
	}

	// Helper method check to ensure no digit other than zero
	// is repeated in a given row, column, or mini matrix.
	private static boolean digitsOkay(int[][] board, int row, int col) {
		return portionOkay(row, 0, 0, 1, board) &&
				portionOkay(0, col, 1, 0, board) &&
				miniMatrixOkay(row, col, board);

	}

	// helper method to see if no repeat digits (other than 0) in a row or column
	// CS314 students, you don't need to call this method directly
	private static boolean portionOkay(int rowStart, int colStart, int rowChange, int colChange, int[][] board) {
		// check digits in row or column.
		// for all non zero digits update array of booleans. if digit appears twice then
		// used[digit] is set to true first time and we return false second time
		boolean[] used = new boolean[BOARD_SIZE + 1]; // don't use zero element in array

		// pretty GACKY, change to while loop?
		for(int i = 0, row = rowStart, col = colStart; i < BOARD_SIZE; i++, row += rowChange, col += colChange) {
			int digit = board[row][col];
			if (digit != 0){
				if (used[digit])
					return false; // duplicate!!
				used[digit] = true; // mark as used
			}
		}
		return true; // no repeats found!
	}

	// helper to check that no digits other than 0 are
	// repeated in the mini matric cell row, col
	// is a part of.
	// CS314 students, you don't need to call this method directly
	private static boolean miniMatrixOkay(int row,
			int col, int[][] board) {

		boolean[] used = new boolean[BOARD_SIZE + 1];

		// figure out upper left indices for mini matrix
		// we need to check

		// row 0,1,2 -> 0, row 3, 4, 5 -> 3, row 6, 7, 8 -> 6
		// same logic for column
		row = (row / MINI_SIZE) * MINI_SIZE;
		col = (col / MINI_SIZE) * MINI_SIZE;

		for(int r = 0; r < 3; r++)
			for(int c = 0; c < 3; c++)  {
				int digit = board[row + r][col + c];
				if(digit != 0){
					if(used[digit])
						return false; // duplicate!!
					used[digit] = true; // mark as used
				}
			}
		return true;
	}


	/**
	 * Problem 8: Determine if water at a given point
	 * on a map can flow off the map.
	 * <br>pre: map != null, map.length > 0,
	 * map is a rectangular matrix, 0 <= row < map.length, 0 <= col < map[0].length
	 * <br>post: return true if a drop of water starting at the location
	 * @param row The starting row of a drop of water.
	 * @param col The starting column of a drop of water.
	 */
	public boolean canFlowOffMap(int[][] map, int row, int col) {
		if( map == null || map.length == 0 || !isRectangular(map) || !inbounds(row, col, map))
			throw new IllegalArgumentException("Failed precondition: canFlowOffMap");
		// Base case: If the current position is on the edge of the map, it can always flow
		// off of the map, so return true
		if (row == 0 || col == 0 || row == map.length-1 || col == map[0].length-1)
			return true;
		// Recursive step: Try all of the options, if one works keep going with it. If none of them work,
		// return false
		else {
			if (map[row][col] > map[row-1][col]) {
				boolean check = canFlowOffMap(map, row-1, col);
				if (check)
					return true;
			}
			if (map[row][col] > map[row+1][col]) {
				boolean check = canFlowOffMap(map, row+1, col);
				if (check)
					return true;
			}
			if (map[row][col] > map[row][col-1]) {
				boolean check = canFlowOffMap(map, row, col-1);
				if (check)
					return true;
			}
			if (map[row][col] > map[row][col+1]) {
				boolean check = canFlowOffMap(map, row, col+1);
				if (check)
					return true;
			}
		}
		return false;
	}

	/* helper method for canFlowOfMap - CS314 stdents you should not have to
	 * call this method,
	 * pre: mat != null,
	 */
	private boolean inbounds(int r, int c, int[][] mat) {
		assert mat != null : "Failed precondition: inbounds";
		return r >= 0 && r < mat.length && mat[r] != null && c >= 0 && c < mat[r].length;
	}

	/*
	 * helper method for canFlowOfMap - CS314 stdents you should not have to
	 * call this method,
	 * pre: mat != null, mat.length > 0
	 * post: return true if mat is rectangular
	 */
	private static boolean isRectangular(int[][] mat) {
		assert (mat != null) && (mat.length > 0) : "Violation of precondition: isRectangular";
		boolean correct = true;
		final int numCols = mat[0].length;
		int row = 0;
		while (correct && row < mat.length) {
			correct = (mat[row] != null) && (mat[row].length == numCols);
			row++;
		}
		return correct;
	}


	/**
	 * Problem 9: Find the minimum difference possible between teams
	 * based on ability scores. The number of teams may be greater than 2.
	 * The goal is to minimize the difference between the team with the
	 * maximum total ability and the team with the minimum total ability.
	 * <br> pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
	 * <br> post: return the minimum possible difference between the team
	 * with the maximum total ability and the team with the minimum total
	 * ability.
	 * @param numTeams the number of teams to form.
	 * Every team must have at least one member
	 * @param abilities the ability scores of the people to distribute
	 * The return value will be greater than or equal to 0.
	 */
	public int minDifference(int numTeams, int[] abilities) {
		int min = Integer.MAX_VALUE;
		ArrayList<Integer> result = new ArrayList<>();
		makeTeams(numTeams, abilities, new int[abilities.length], 0, result);
		for (int i: result) {
			if (i < min)
				min = i;
		}
		return min;
	}

	private void makeTeams(int numTeams, int[] abilities, int[] teamAssignments, int current, ArrayList<Integer> result) {
		// Base case: Check to make sure that every team has at least one member. If so, find min 
		//and max vales, and return the difference between the two
		if (current == teamAssignments.length) {
			boolean atLeastOneMember = true;
			for (int i=1; i<=numTeams; i++) {
				if (!Arrays.toString(teamAssignments).contains(String.valueOf(i))) {
					atLeastOneMember = false;
				}
			}
			if (atLeastOneMember) {
				int[] abilityScore = new int[numTeams];
				for (int k=0; k<teamAssignments.length; k++) {
					abilityScore[teamAssignments[k]-1] += abilities[k];
				}
				int maxTeamScore = Integer.MIN_VALUE;
				int minTeamScore = Integer.MAX_VALUE;
				for (int i=0; i<numTeams; i++) {
					if (abilityScore[i] > maxTeamScore)
						maxTeamScore = abilityScore[i];
					if (abilityScore[i] < minTeamScore)
						minTeamScore = abilityScore[i];
				}
				result.add(maxTeamScore-minTeamScore);
			}
		}
		else {
			for (int i=1; i<=numTeams; i++) {
				teamAssignments[current] = i;
				makeTeams(numTeams, abilities, teamAssignments, current+1, result);
			}	
		}
	}
}
