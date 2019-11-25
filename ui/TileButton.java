package ui;

import java.awt.Color;
import java.awt.Dimension;  
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.UIManager;

import model.Tile;

public class TileButton {
   private Tile tile;
   private JButton button;

   public TileButton(boolean mine, boolean flag, int mineCount, int row, int col) {
      UIManager.put("Button.disabledText", Color.black);
      tile = new Tile(mine, flag, mineCount, row, col);
      this.button = new JButton("");
      this.button.setPreferredSize(new Dimension(53, 53));
      this.button.setFont(new Font("Arial", Font.BOLD, 22));
      this.button.setBackground(new Color(110,243,150));
      this.button.setBorderPainted(true);
      this.button.setOpaque(true);
   }
   
   public void flag() {
      String s = tile.Flag();
      
      this.button.setText(s);
      if (s == "F"){
         this.button.setBackground(new Color(243,113,113));
         this.button.setOpaque(true);
      }else {
         this.button.setBackground(new Color(110,243,150));
         this.button.setOpaque(true);
      }
   }
   
   public boolean open() {
      int value = tile.openTile();
      if(value == 2) {
         return true;
      } else if (value == 0) {
         this.button.setText(Integer.toString(this.tile.getMines()));
         this.button.setEnabled(false);
         this.button.setBackground(new Color(20,198,78));
      }
      return false;
   }
   
   public Tile getTile() {
      return this.tile;
   }
   
   public JButton getButton() {
      return this.button;
   }
}
