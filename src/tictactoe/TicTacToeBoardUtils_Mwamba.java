package tictactoe;

import java.util.ArrayList;
import java.util.HashSet;

public class TicTacToeBoardUtils_Mwamba {
	/*--------------------------------HELPER METHODS---------------------------*/

	// helper method to get a board position from row and column
	static int getPosition(int row, int column) {
		int position = column;
		
		for(int rowIndex = 0; rowIndex < row; rowIndex++) {
			position = position + 3;
		}
		return position;
	}
	
	// helper method to determine a row win
	static boolean getRowWinner(HashSet<Integer> movesSet) {

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
