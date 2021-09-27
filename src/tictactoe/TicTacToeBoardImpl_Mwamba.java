package tictactoe;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class TicTacToeBoardImpl_Mwamba implements TicTacToeBoard{
	//Symbolics:
	protected static final int NO_MOVE = -1;
	protected static final int NO_MATCH = -1;
	protected int[] movesArray;

	
	public TicTacToeBoardImpl_Mwamba() {

		final int CELL_COUNT = ROW_COUNT*COLUMN_COUNT; 
		movesArray = new int[CELL_COUNT];
		// initializing movesArray
		for(int index = 0; index < CELL_COUNT; index++)
		{
			movesArray[index] = NO_MOVE;
		}
	} 
	 
	//part of pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT 
	//part of post: rv == null <==> the (row, column) spot on the board is empty
	@Override
	public Mark getMark(int row, int column) {
		// Precondition assertions
		assert 0 <= row && row < ROW_COUNT: "row is out of range";
		assert 0 <= column && column < COLUMN_COUNT : "column is out of range";
		
		// retrieving the value of the (row, column) spot
		int position = getPosition(row, column);
		
		// initializing isPositionMarked - checks if the position has been marked
		boolean isPositionMarked = false;
		
		// position's index in movesArray 
		int positionIndex = NO_MATCH;
		
		// retrieving the index of the currentTurn
		int currentTurn = getTurnIndex();
		
		for(int index = 0; index <= currentTurn; index ++) {
			if( Array.getInt(movesArray, index) == position) {
				isPositionMarked = true;
				positionIndex = index;
				break;
			}
		}
		// setting the return value, markAtPosition
		Mark markAtPosition = null;
		if(isPositionMarked) {
			if(positionIndex % 2 == 0) markAtPosition = Mark.X;
			if(positionIndex % 2 == 1) markAtPosition = Mark.O;	
		}

		return markAtPosition;
	}

	//part of pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT 
	//part of pre: getMark(row, column) == null
	//part of pre: !isGameOver()
	//post:        Array.getInt(movesArray, currentTurn - 1) != NO_MOVE
	@Override
	public void setMark(int row, int column) {
		// Precondition assertions
		assert getMark(row, column) == null : "mark cannot be set at this position";
		assert !isGameOver() : "Game Over";
		
		// retrieving the value of the (row, column) spot
		int position = getPosition(row, column);
		
		// retrieving the index of the currentTurn
		int currentTurn = getTurnIndex();
				
		// setting mark
		Array.setInt(movesArray, currentTurn, position);
		
	}
	
	//part of post: isGameOver() = isWinner() | Array.getInt(movesArray, 8) == NO_MOVE
	@Override
	public boolean isGameOver() {
		
		// declaring move sets for each player
		HashSet<Integer> xMoves = getXmoves(); 
		HashSet<Integer> oMoves = getOmoves();
		
		// testing if the game has been won
		boolean isGameWon = getRowWinner(xMoves) | getColumnWinner(xMoves) | getCrossWinner(xMoves) |
				getRowWinner(oMoves) | getColumnWinner(oMoves) | getCrossWinner(oMoves);
		// testing if X has any moves left to take
		boolean xMovesLeft = ! xMoves.contains(NO_MOVE);
		
		// isGameOver == true  <==> X has no moves left | the game is won
		boolean isGameOver = xMovesLeft | isGameWon;
		
		
		return isGameOver;
	}

	//part of pre: isGameOver()
	//part of post: rv == null <==> neither player won (i.e. the game // ended in a tie)	
	@Override
	public Mark getWinner() {
		// Precondition assertions
		assert isGameOver(): "the game is not yet over";
		
		// declaring move sets for each player
		HashSet<Integer> xMoves = getXmoves(); 
		HashSet<Integer> oMoves = getOmoves();
		
		// initializing winner
		Mark winner = null;
		
		// testing xMoves for a win
		if(getRowWinner(xMoves) | getColumnWinner(xMoves) | getCrossWinner(xMoves))
			winner = Mark.X;
		
		// testing oMoves for a win
		if(getRowWinner(oMoves) | getColumnWinner(oMoves) | getCrossWinner(oMoves))
			winner = Mark.O;
		
		return winner;
	}

	//part of post: rv == null <==> it is neither player's turn (i.e. // game is over)
	//part of post: “number of Marks on board is even” --> rv == Mark.X 
	//part of post: “number of Marks on board is odd” --> rv == Mark.O
	@Override
	public Mark getTurn() {
		int indexAtNoMove = NO_MOVE;
		Mark turnMark = null;
		
		for(int index = 0; index < movesArray.length; index++) {
			if (Array.getInt(movesArray, index) == NO_MOVE) {
				indexAtNoMove = index;
				break;
			}
		}
		if(indexAtNoMove % 2 == 0) turnMark = Mark.X;
		if(indexAtNoMove % 2 == 1) turnMark = Mark.O;
		
		return turnMark;
	}
	
	@Override
	public String toString() {
		// initializing each row.
		String row1 = "";
		String row2 = "";
		String row3 = "";
		String rowDivisor = "----------";
		
		// initializing markedMovesArray
		char[] markedMovesArray = new char[9];
		for(int index = 0; index < ROW_COUNT*COLUMN_COUNT; index++)
		{
			Array.set(markedMovesArray, index, ' ');
		}
		
		// filling in markedMovesArray with Marks according to movesArray
		for(int i = 0; i < movesArray.length; i++) {
			if(i % 2 == 0) {
				int moveIndex = Array.getInt(movesArray, i);
				char currentMark = 'X';
				if(moveIndex != -1)
					Array.set(markedMovesArray, moveIndex, currentMark);
			} 
			if(i % 2 == 1) {
				int moveIndex = Array.getInt(movesArray, i);
				char currentMark = 'O';
				if(moveIndex != -1)
					Array.set(markedMovesArray, moveIndex, currentMark);
			}
		}
		
		// writing the rows
		for(int i = 0; i < 3; i++) {
			row1 += " "+Array.getChar(markedMovesArray, i) + " |";
			if( i == 2) row1 = row1.substring(0, row1.length()-2);
		}
		for(int i = 3; i < 6; i++) {
			row2 += " "+Array.getChar(markedMovesArray, i) + " |";
			if( i == 5) row2 = row2.substring(0, row2.length()-2);
		}
		for(int i = 6; i < 9; i++) {
			row3 += " "+Array.getChar(markedMovesArray, i) + " |";
			if( i == 8) row3 = row3.substring(0, row3.length()-2);
		}
		String returnString = row1+"\n"+rowDivisor+"\n"+row2+"\n"+rowDivisor+"\n"+row3+"\n"; 

		return returnString;
	}
	
	
	/*--------------------------------HELPER METHODS---------------------------*/
	// helper method to get current turn;
	public int getTurnIndex() {
		int indexAtNoMove = NO_MOVE;
		
		for(int index = 0; index < movesArray.length; index++) {
			indexAtNoMove = index;
			if (Array.getInt(movesArray, index) == NO_MOVE) {
				break;
			}
		}

		return indexAtNoMove;
	}
	
	// helper method to get xMovesSet
	private HashSet<Integer> getXmoves() {
		// declaring movesSet for the player
		HashSet<Integer> movesSet = new HashSet<Integer>(5); 
		for(int i = 0; i < 9; i++) {
			if(i % 2 == 0) movesSet.add(Array.getInt(movesArray, i));
			}
		return movesSet;
	}
	
	// helper method to get oMovesSet
	private HashSet<Integer> getOmoves() {
		// declaring movesSet for the player
		HashSet<Integer> movesSet = new HashSet<Integer>(5); 
		for(int i = 0; i < 9; i++) {
			if(i % 2 == 1) movesSet.add(Array.getInt(movesArray, i));
			}
		return movesSet;
	}
	
	// helper method to get a board position from row and column
	private int getPosition(int row, int column) {
		int position = column;
		
		for(int rowIndex = 0; rowIndex < row; rowIndex++) {
			position = position + 3;
		}
		return position;
	}
	
	// helper method to determine a row win
	private boolean getRowWinner(HashSet<Integer> movesSet) {

		// declaring row heads
		ArrayList<Integer> rowHeads = new ArrayList<Integer>(3);
		
		boolean rowWin = false;
		
		// generating row heads
		for(int i = 0; i < 9; i++) {
			if(i % 3 == 0) rowHeads.add(i);
		}
		
		// getting row winner
		for(int i = 0; i < 3; i++) {
			int currentRowHead = rowHeads.get(i);
			if (movesSet.contains(currentRowHead) && movesSet.contains(currentRowHead +1) && movesSet.contains(currentRowHead +2)) {
				rowWin = true;
			}
		}
		return rowWin;
	}

	// helper method to determine a column win
	private boolean getColumnWinner(HashSet<Integer> movesSet) {

		// declaring column heads
		ArrayList<Integer> columnHeads = new ArrayList<Integer>(3);
		
		boolean columnWin = false;
		
		// generating row heads
		for(int i = 0; i < 4; i++) {
			columnHeads.add(i);
		}
		
		// getting column winner
		for(int i = 0; i < 3; i++) {
			int currentColumnHead = columnHeads.get(i);
			if (movesSet.contains(currentColumnHead) && movesSet.contains(currentColumnHead +3) && movesSet.contains(currentColumnHead +6)) {
				columnWin = true;
			}
		}
		
		return columnWin;
	}

	// helper method to determine a cross win
	private boolean getCrossWinner(HashSet<Integer> movesSet) {
		
		boolean crossWin = false;
		
		// testing for cross win
		if(movesSet.contains(4) && movesSet.contains(2) && movesSet.contains(6)) {
			crossWin = true;
		}
		if(movesSet.contains(4) && movesSet.contains(0) && movesSet.contains(8)) {
			crossWin = true;
		}
		
		return crossWin;
	}
	

}
