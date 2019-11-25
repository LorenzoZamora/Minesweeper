package ui;

import java.awt.Dimension;  
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import model.Level;

public class LevelButton {
   private Level level;
   private JButton button;

   public LevelButton(int levels) {
      level = new Level(levels);
      this.button = new JButton("");
      this.button.setPreferredSize(new Dimension(100, 100));
      this.button.setFont(new Font("Arial", Font.PLAIN, 25));
   }
   
   public boolean open() {
      System.out.println("Hi");
      return true;
   }
   
   public Level getLevel() {
      return this.level;
   }
   
   public JButton getButton() {
      return this.button;
   }
}
