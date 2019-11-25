package model;
import ui.TileButton;
import org.junit.*;
import static org.junit.Assert.*;

public class GameboardTest {

   public static Gameboard testConstructor(int difficulty) {
      Gameboard board = new Gameboard(difficulty);
      assertSame("Constructor set difficulty incorrectly", difficulty, board.diff);
      return board;
   }
   
   public static Gameboard placeTileButtons() {
      Gameboard board = testConstructor(3);
      for (int row = 0; row < board.rows; row++){
         for(int col = 0; col < board.cols; col++) {
            board.gameArray[row][col] = new TileButton(false, false, 0, row, col);
         }
      }
      return board;
   }
   
   @Test
   public void constructorTestDiff() {
      testConstructor(2);      
   }
   
   @Test
   public void constructorSizeTest1() {
      Gameboard board = testConstructor(1);
      assertSame("Board Rows", 8, board.rows);
      assertSame("Board Cols", 8, board.cols);
   }
   
   @Test
   public void constructorSizeTest2() {
      Gameboard board = testConstructor(2);
      assertSame("Board Rows", 16, board.rows);
      assertSame("Board Cols", 16, board.cols);
   }
   
   @Test
   public void constructorSizeTest3() {
      Gameboard board = testConstructor(3);
      assertSame("Board Rows", 16, board.rows);
      assertSame("Board Cols", 30, board.cols);
   }
   
   @Test
   public void constructorSizeTestOther() {
      Gameboard board = testConstructor(7);
      assertSame("Board Rows", 8, board.rows);
      assertSame("Board Cols", 8, board.cols);
   }
   
   @Test
   public void testGetMines() {
      Gameboard board = testConstructor(1);
      assertSame("getMines()", board.mines, board.getMines());
   }
   
   @Test
   public void testPlaceMines() {
      Gameboard board = placeTileButtons();
      board.placeMines();
      int count = 0;
      for (int row = 0; row < board.rows; row++){
         for(int col = 0; col < board.cols; col++) {
            if (board.gameArray[row][col].getTile().isMine() == true) {
               count++;
            }
         }
      }
      assertSame("placeMines()", board.mines, count);
   }
   
   @Test
   public void testIsFinished() {
      Gameboard board = placeTileButtons();
      board.placeMines();
      for (int row = 0; row < board.rows; row++){
         for(int col = 0; col < board.cols; col++) {
            if (board.gameArray[row][col].getTile().isMine() == true) {
               board.gameArray[row][col].getTile().Flag();
            }
         }
      }
      assertSame("isFinished()",true, board.isFinished(0));
   }
   
   @Test
   public void testIsFinishedNot() {
      Gameboard board = placeTileButtons();
      board.placeMines();
      assertSame("isFinished()",false, board.isFinished(0));
   }
   
   @Test
   public void testCalculateMines() {
      Gameboard board = placeTileButtons();
      board.placeMines();
      board.calculateMines();
      for (int row = 0; row < board.rows; row++){
         for(int col = 0; col < board.cols; col++) {
            if(board.gameArray[row][col].getTile().getMines() < 0 || board.gameArray[row][col].getTile().getMines() > 8) {
               assertSame("Invalid mine count on " + row +":" + col, true, false);
            }
         }
      }
   }
   
   @Test
   public void testRecursiveOpen() {
      Gameboard board = placeTileButtons();
      board.recursiveOpen(board.gameArray[0][0]);
      for (int row = 0; row < board.rows; row++){
         for(int col = 0; col < board.cols; col++) {
            assertSame("recursiveOpen",false, board.gameArray[row][col].getButton().isEnabled());
         }
      }
   }
}
