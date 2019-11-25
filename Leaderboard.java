package ui;
import model.HighScoreFile;

import javax.swing.*;
import java.awt.*;

public class Leaderboard {
   private JPanel grid;
   
   public Leaderboard (HighScoreFile scores) {
      Font font = new Font("Arial", Font.PLAIN, 30);
      int[] highScores = scores.getScores();
      grid = new JPanel(new GridLayout(3, 1));
      grid.setBackground(new Color(252,255,120));
        
      JLabel easy = new JLabel("Easy: " + highScores[0]+ ":"+ String.format("%02d", highScores[1]));
      easy.setFont(font);
      grid.add(easy);
      JLabel med = new JLabel("Medium: " + highScores[2]+ ":"+ String.format("%02d", highScores[3]));
      med.setFont(font);
      grid.add(med);
      JLabel hard = new JLabel("Hard: " + highScores[4]+ ":"+ String.format("%02d", highScores[5]));
      
      if(highScores[0] >= 999) {
         easy.setText("Easy: N/A");
      }
      if(highScores[2] >= 999) {
         med.setText("Medium: N/A");
      }
      if(highScores[4] >= 999) {
         hard.setText("Hard: N/A");
      }
      
      hard.setFont(font);
      grid.add(hard);
   }

   public JPanel getLeaderboard() {
      return this.grid;
   }
}
