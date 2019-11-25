package model;
import java.awt.Color;
import java.util.Random;
import ui.TileButton;
import model.Gameboard;

public class Gameboard {
   public TileButton[][] gameArray;
   public int diff;
   public int rows;
   public int cols;
   protected int mines;
   
    /**
    * This constructor creates the gameboard that is appropriate
    *  for the specified level of difficulty.
    * @param diff an integer to choose level difficulty
    */
   public Gameboard(int diff) {
      this.diff = diff;
      if (diff == 3) {
         this.rows = 16;
         this.cols = 30;
         this.mines = 99;
      } else if (diff == 2) {
         this.rows = 16;
         this.cols = 16;
         this.mines = 40;
      } else {
         this.rows = 8;
         this.cols = 8;
         this.mines = 10;
      }
      gameArray = new TileButton[rows][cols];
   }
   
   public void placeMines() {
      int count = this.mines;
      while (count != 0) {
         Random random = new Random();
         int row = random.nextInt(rows);
         int col = random .nextInt(cols);
         if(gameArray[row][col].getTile().isMine() == false) {
            gameArray[row][col].getTile().setMine();
            count = count - 1;
         }
      }
   }
   
   public void removeMines() {
      for(int row = 0; row < rows; row++) {
         for (int col = 0; col < cols; col++) {
            gameArray[row][col].getTile().clearMine();
            gameArray[row][col].getTile().mineCount = 0;
         }
      }
   }
   
   public int getMines() {
      return this.mines;
   }
   
   public void calculateMines(){
      for(int row = 0; row < rows; row++) {
         for (int col = 0; col < cols; col++) {
            if(col+1 < cols && gameArray[row][col+1].getTile().isMine() == true) {
               gameArray[row][col].getTile().addMine(1);
            }
            if(col -1 > -1 && gameArray[row][col-1].getTile().isMine() == true) {
               gameArray[row][col].getTile().addMine(1);
            }
            if(row+1 < rows && gameArray[row+1][col].getTile().isMine() == true) {
               gameArray[row][col].getTile().addMine(1);
            }
            if(row-1 > -1 && gameArray[row-1][col].getTile().isMine() == true) {
               gameArray[row][col].getTile().addMine(1);
            }
            if(row+1 < rows && col+1 < cols && gameArray[row+1][col+1].getTile().isMine() == true) {
               gameArray[row][col].getTile().addMine(1);
            }
            if(row+1 < rows && col-1 > -1 && gameArray[row+1][col-1].getTile().isMine() == true) {
               gameArray[row][col].getTile().addMine(1);
            }
            if(row-1 > -1 && col+1 < cols && gameArray[row-1][col+1].getTile().isMine() == true) {
               gameArray[row][col].getTile().addMine(1);
            }
            if(row-1 > -1 && col-1 > -1 && gameArray[row-1][col-1].getTile().isMine() == true) {
               gameArray[row][col].getTile().addMine(1);
            }
         }
      }
   }
   
      public boolean isFinished(int flags) {
         int minesFlagged = 0;
         if(flags == 0) {
            for (int y = 0; y < this.rows; y++){
               for(int x = 0; x < this.cols; x++) {
                  if(this.gameArray[y][x].getTile().isMine() && this.gameArray[y][x].getTile().isFlagged()) {
                     minesFlagged++;
                  }
               }
            }
            if(minesFlagged == this.mines) {
               return true;
            }
         }
         return false;
      }
      
      public void loseGame(){
         for(int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
               gameArray[row][col].getButton().setEnabled(false);
               if(gameArray[row][col].getTile().isMine() == true && gameArray[row][col].getTile().isFlagged() == false) {
                  gameArray[row][col].getButton().setText("M");
                  gameArray[row][col].getButton().setBackground(new Color(245, 97 , 7));
               } else if (gameArray[row][col].getTile().isMine() == false && gameArray[row][col].getTile().isFlagged() == true) {
                  gameArray[row][col].getButton().setText("X");
                  gameArray[row][col].getButton().setBackground(new Color(212, 30 , 30));
               } else if (gameArray[row][col].getTile().isMine() == true && gameArray[row][col].getTile().isFlagged() == true) {
                  gameArray[row][col].getButton().setText("F");
               } else {
                  gameArray[row][col].open();
               }
            }
         }
      }
      
      public void recursiveOpen(TileButton tileButton) {
         if(!tileButton.getTile().isFlagged()) {
            int row = tileButton.getTile().getRow();
            int col = tileButton.getTile().getCol();
            tileButton.open();
            if(tileButton.getTile().getMines() == 0) {
               if(row+1 < rows && gameArray[row+1][col].getTile().isMine() == false && gameArray[row+1][col].getTile().isFlagged() == false && gameArray[row+1][col].getButton().isEnabled() == true) {
                  gameArray[row+1][col].open();
                  if(gameArray[row+1][col].getTile().getMines() == 0) {
                    recursiveOpen(gameArray[row+1][col]); 
                 } 
               }
            
               if(row-1 > -1 && gameArray[row-1][col].getTile().isMine() == false && gameArray[row-1][col].getTile().isFlagged() == false && gameArray[row-1][col].getButton().isEnabled() == true) {
                  gameArray[row-1][col].open();
                  if(gameArray[row-1][col].getTile().getMines() == 0) {
                    recursiveOpen(gameArray[row-1][col]); 
                 } 
               }
               
               if(col-1 > -1 && gameArray[row][col-1].getTile().isMine() == false && gameArray[row][col-1].getTile().isFlagged() == false && gameArray[row][col-1].getButton().isEnabled() == true) {
                  gameArray[row][col-1].open();
                  if(gameArray[row][col-1].getTile().getMines() == 0) {
                    recursiveOpen(gameArray[row][col-1]); 
                 } 
               }
               
               if(col+1 < cols && gameArray[row][col+1].getTile().isMine() == false && gameArray[row][col+1].getTile().isFlagged() == false && gameArray[row][col+1].getButton().isEnabled() == true) {
                  gameArray[row][col+1].open();
                  if(gameArray[row][col+1].getTile().getMines() == 0) {
                    recursiveOpen(gameArray[row][col+1]); 
                 } 
               }
               
               if(row-1 > -1 && col-1 > -1 && gameArray[row-1][col-1].getTile().isMine() == false && gameArray[row-1][col-1].getTile().isFlagged() == false && gameArray[row-1][col-1].getButton().isEnabled() == true) {
                  gameArray[row-1][col-1].open();
                  if(gameArray[row-1][col-1].getTile().getMines() == 0) {
                    recursiveOpen(gameArray[row-1][col-1]); 
                 } 
               }
               
               if(row+1 < rows && col+1 < cols && gameArray[row+1][col+1].getTile().isMine() == false && gameArray[row+1][col+1].getTile().isFlagged() == false && gameArray[row+1][col+1].getButton().isEnabled() == true) {
                  gameArray[row+1][col+1].open();
                  if(gameArray[row+1][col+1].getTile().getMines() == 0) {
                    recursiveOpen(gameArray[row+1][col+1]); 
                 } 
               }
               
               if(row-1 > -1 && col+1 < cols && gameArray[row-1][col+1].getTile().isMine() == false && gameArray[row-1][col+1].getTile().isFlagged() == false && gameArray[row-1][col+1].getButton().isEnabled() == true) {
                  gameArray[row-1][col+1].open();
                  if(gameArray[row-1][col+1].getTile().getMines() == 0) {
                    recursiveOpen(gameArray[row-1][col+1]); 
                 } 
               }
               
               if(row+1 < rows && col-1 > -1 && gameArray[row+1][col-1].getTile().isMine() == false && gameArray[row+1][col-1].getTile().isFlagged() == false && gameArray[row+1][col-1].getButton().isEnabled() == true) {
                  gameArray[row+1][col-1].open();
                  if(gameArray[row+1][col-1].getTile().getMines() == 0) {
                    recursiveOpen(gameArray[row+1][col-1]); 
                 } 
               }
            }
         }
      }
}
