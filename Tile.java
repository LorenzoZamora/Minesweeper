package model;

public class Tile {
   protected boolean mine = false;
   protected boolean flagged = false;
   protected int mineCount;
   protected int row;
   protected int col;
   
   public Tile(boolean mine, boolean flag, int mineCount, int row, int col) {
      this.row = row;
      this.col = col;
      this.mine = mine;
      this.flagged = flag;
      this.mineCount = mineCount;
   }
   
   public int addMine(int add) {
      this.mineCount = this.mineCount + add;
      return this.mineCount;
   }
   
   public int getMines() {
      return this.mineCount;
   }
   
   public String Flag() {
      if(this.flagged == true) {
         this.flagged = false;
         return("");
      } else {
         this.flagged = true;
         return("F");
      }
   }
   
   public int openTile() {
      if(flagged == false && mine == true) {
        return 2;  //game over 
      } else if(flagged == false && mine == false) {
         return 0; //can open
      } else {
         return 1; //flagged so it cannot be opened
      }
   }
   
   public int getRow() {
      return this.row;
   }
   
   public int getCol() {
      return this.col;
   }
   
   public void setMine() {
      this.mine = true;
   }
   
   public void clearMine() {
      this.mine = false;
   }
   
   public boolean isMine() {
      return this.mine;
   }
   
   public boolean isFlagged() {
      return this.flagged;
   }
}
