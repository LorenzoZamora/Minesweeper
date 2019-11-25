package model;
import org.junit.*; 
import static org.junit.Assert.*;

public class TileTest {

   public static Tile testConstructor(boolean mine, boolean flag, int mineCount, int row, int col) {
      Tile tile = new Tile(mine, flag, mineCount, row, col);
      assertSame("Tile constructor set incorrect mine boolean", mine, tile.mine);
      assertSame("Tile constructor set incorrect flag boolean", flag, tile.flagged);
      assertSame("Tile constructor set invalid mineCount boolean", mineCount, tile.mineCount);
      assertSame("Tile constructor set invalid mineCount boolean", row, tile.row);
      assertSame("Tile constructor set invalid mineCount boolean", col, tile.col);
      return tile;
   }
   
   @Test
   public void constructorTestMine() {
      testConstructor(true, true, 5, 3, 2);
   }
   
   @Test
   public void testAddMine() {
      Tile tile = testConstructor(true, true, 5, 3, 2);
      int mines = tile.mineCount;
      tile.addMine(1);
      assertSame("addMine(1) didnt increment mineCount", mines+1, tile.mineCount);
   }
   
   @Test
   public void testGetMines() {
      Tile tile = testConstructor(true, true, 5, 3, 2);
      assertSame("getMine() didnt return the correct mine count", tile.mineCount, tile.getMines());
   }

   @Test
   public void testFlag() {
      Tile tile = testConstructor(true, false, 5, 3, 2);
      assertSame("flag() didnt flag the tile", "F", tile.Flag());
   }
   
   @Test
   public void testFlagUnflag() {
      Tile tile = testConstructor(true, true, 5, 3, 2);
      assertSame("flag() didnt unflag the tile", "", tile.Flag());
   }
   
   @Test
   public void testOpenMine() {
      Tile tile = testConstructor(true, false, 5, 3, 2);
      assertSame("openTile()", 2,tile.openTile());
   }
   
   @Test
   public void testOpenFlagged() {
      Tile tile = testConstructor(true, true, 5, 3, 2);
      assertSame("openTile()", 1,tile.openTile());
   }
   
   @Test
   public void testOpenTile() {
      Tile tile = testConstructor(false, false, 5, 3, 2);
      assertSame("openTile()", 0,tile.openTile());
   }
   
   @Test
   public void testGetRow() {
      Tile tile = testConstructor(false, false, 5, 3, 2);
      assertSame("getRow()", tile.row,tile.getRow());
   }
   
   @Test
   public void testGetCol() {
      Tile tile = testConstructor(false, false, 5, 3, 2);
      assertSame("getCol()", tile.col,tile.getCol());
   }
   
   @Test
   public void testSetMine() {
      Tile tile = testConstructor(false, false, 5, 3, 2);
      tile.setMine();
      assertSame("setMine()", tile.mine ,true);
   }
   
   @Test
   public void testIsMine() {
      Tile tile = testConstructor(false, false, 5, 3, 2);
      assertSame("isMine()", tile.mine, tile.isMine());
   }
   
   @Test
   public void testIsMine2() {
      Tile tile = testConstructor(true, false, 5, 3, 2);
      assertSame("isMine()", tile.mine, tile.isMine());
   }
   
   @Test
   public void testIsFlggedTrue() {
      Tile tile = testConstructor(true, false, 5, 3, 2);
      assertSame("isFlagged()", tile.flagged, tile.isFlagged());
   }
   
   @Test
   public void testIsFlaggedFalse() {
      Tile tile = testConstructor(false, false, 5, 3, 2);
      assertSame("isFlagged()", tile.flagged, tile.isFlagged());
   }
}


