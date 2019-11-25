package ui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Gameboard;

public class BoardUI {
   private Gameboard gameboard;
   
   public BoardUI(int diff) {
      gameboard = new Gameboard(diff);
   }
   
   public JPanel makeBoard(Menubar menubar){
      int total = gameboard.rows * gameboard.cols;
      JPanel grid = new JPanel(new GridLayout(gameboard.rows, gameboard.cols));
      for(int i = 0; i < total; i++) {
         TileButton tileButton = new TileButton(false, false, 0, i/gameboard.cols, i%gameboard.cols);
         grid.add(tileButton.getButton());
         gameboard.gameArray[i/gameboard.cols][i%gameboard.cols] = tileButton;
      }
      return grid;
   }
   
   public Gameboard getGameboard() {
      return this.gameboard;
   }
   

}
