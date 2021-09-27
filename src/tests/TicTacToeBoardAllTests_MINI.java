package tests;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tictactoe.Mark;
import tictactoe.TicTacToeBoard;
import tictactoe.TicTacToeBoardImpl_Mwamba;

public class TicTacToeBoardAllTests_MINI
{
	/**********************************************************************************************************************/
//	@Points(value=5)
	
	// THE ISSUE SEEMS TO BE THAT WHEN THE GAME IS COMPLETE, getMark(0, 0) returns null instead of X.

	@Test
	public void tieTest() {
		//Desired: Get all questions correct about the game: [2, 6, 7, 8, 0, 1, 3, 4, 5]
		TicTacToeBoard studentBoard = new TicTacToeBoardImpl_Mwamba();
		studentBoard.setMark(0, 2);
		studentBoard.setMark(2, 0);
		studentBoard.setMark(2, 1);
		studentBoard.setMark(2, 2);
		assertTrue("0, 0 should not be set", studentBoard.getMark(0, 0) == null);
		studentBoard.setMark(0, 0);
		assertTrue("0, 0 has been set by X", studentBoard.getMark(0, 0) == Mark.X);
		studentBoard.setMark(0, 1);
		studentBoard.setMark(1, 0);
		studentBoard.setMark(1, 1);
		assertTrue("0,0 should be X", studentBoard.getMark(0, 0) == Mark.X);
		studentBoard.setMark(1, 2);
		Mark wrongMark = studentBoard.getMark(1, 2);
		assertTrue("1, 2 should still be X", studentBoard.getMark(1, 2) == Mark.X);
		
		System.out.println(studentBoard.toString());
	}
	
	@Test
	public void fourSymbolTest(){
		TicTacToeBoard studentBoard = new TicTacToeBoardImpl_Mwamba();
		studentBoard.setMark(2, 2);
		studentBoard.setMark(0, 0);
		studentBoard.setMark(0, 2);
		studentBoard.setMark(1, 2);
		assertTrue("0,0 should be O", studentBoard.getMark(0, 0) == Mark.O);
		assertTrue("0,1 should be null", studentBoard.getMark(0, 1) == null);
		assertTrue("X turn", studentBoard.getTurn() == Mark.X);
		System.out.println("fourSymbolTest\n"+studentBoard.toString());
	}
	
	@Test
	public void fiveSymbolTest(){
		TicTacToeBoard studentBoard = new TicTacToeBoardImpl_Mwamba();
		studentBoard.setMark(2, 2);
		studentBoard.setMark(0, 0);
		studentBoard.setMark(0, 2);
		studentBoard.setMark(1, 1);
		studentBoard.setMark(1, 2);
		assertTrue("X is the winner", studentBoard.getWinner() == Mark.X);
		System.out.println("fiveSymbolTest\n"+studentBoard.toString());
	}
	@Test
	public void lateWinnterTest(){
		// Get all questions correct about the game: [0, 1, 2, 5, 8, 7, 6, 3, 4]
		TicTacToeBoard studentBoard = new TicTacToeBoardImpl_Mwamba();
		studentBoard.setMark(0, 0);
		studentBoard.setMark(0, 1);
		studentBoard.setMark(0, 2);
		studentBoard.setMark(1, 2);
		studentBoard.setMark(2, 2);
		studentBoard.setMark(2, 1);
		studentBoard.setMark(2, 0);
		studentBoard.setMark(1, 0);
		studentBoard.setMark(1, 1);
		assertTrue("0,0 should be X", studentBoard.getMark(0, 0) == Mark.X);
		assertTrue("0,1 should be O", studentBoard.getMark(0, 1) == Mark.O);
		assertTrue("X is the winner", studentBoard.getWinner() == Mark.X);
		assertTrue("Game is over", studentBoard.getTurn() == null);
	}
	
	@Test
	public void completeGamesTest(){
		//  [0, 1, 2, 6, 7, 8, 3, 4, 5]
		TicTacToeBoard studentBoard = new TicTacToeBoardImpl_Mwamba();
		studentBoard.setMark(0, 0);
		studentBoard.setMark(0, 1);
		studentBoard.setMark(0, 2);
		studentBoard.setMark(2, 0);
		studentBoard.setMark(2, 1);
		studentBoard.setMark(2, 2);
		studentBoard.setMark(1, 0);
		studentBoard.setMark(1, 1);
		studentBoard.setMark(1, 2);
		assertTrue("0,2 should be X", studentBoard.getMark(0, 0) == Mark.X);
		assertTrue("1,1 should be O", studentBoard.getMark(0, 1) == Mark.O);
		assertTrue("There is no winner", studentBoard.getWinner() == null);
		assertTrue("Game is over", studentBoard.getTurn() == null);
	}
	
	
	@Test
	public void emptyBoardTest()
	{
		TicTacToeBoard studentBoard = new TicTacToeBoardImpl_Mwamba();
		final Mark EMPTY = null;
		for(int i = 0; i < TicTacToeBoard.ROW_COUNT; i++)
		{
			for(int j = 0; j < TicTacToeBoard.COLUMN_COUNT; j++)
			{
				assertTrue(studentBoard.getMark(i, j) == EMPTY);
			}
		}
		System.out.println(studentBoard.toString());
	}
	
//	@Points(value=5)
	@Test
	public void oneSymbolTest()
	{	
		int ROW = 0;
		int COLUMN = 2;

		TicTacToeBoard studentBoard = new TicTacToeBoardImpl_Mwamba();
		studentBoard.setMark(ROW, COLUMN);
		assertEquals(Mark.X, studentBoard.getMark(ROW, COLUMN));
		
		ROW ++;
		COLUMN --;
		studentBoard.setMark(ROW, COLUMN);
		assertEquals(Mark.O, studentBoard.getMark(ROW, COLUMN));
		assertEquals(Mark.X, studentBoard.getTurn());
		System.out.println(studentBoard.toString());
	}	
	
//	@Points(value=5)
	@Test
	public void isGameOverTest()
	{	
		TicTacToeBoard studentBoard = new TicTacToeBoardImpl_Mwamba();
		for(int i = 0; i < TicTacToeBoard.ROW_COUNT; i++)
		{
			studentBoard.setMark(i, 0);
		}
		assertTrue(!studentBoard.isGameOver());
		for(int i = 0; i < TicTacToeBoard.ROW_COUNT; i++)
		{
			studentBoard.setMark(i, 1);
		}
		assertTrue(!studentBoard.isGameOver());
		studentBoard.setMark(0,2);
		assertTrue(studentBoard.isGameOver());
		System.out.println(studentBoard.toString());
	}	
}
