package ui;

import javax.swing.*;
import java.awt.*;

public class Menubar {
   private JPanel menu = new JPanel(new GridLayout(1, 6, 10, 10));
   private int flagsLeft;
   private int sec;
   private int min;
   private JLabel flags;
   public JButton easy;
   public JButton medium;
   public JButton hard;
   public JButton leaderboard;
   private JLabel time;


   public Menubar(int diff) {

      if(diff == 3) {
         this.flagsLeft = 99;
      } else if (diff == 2) {
         this.flagsLeft = 40;
      } else {
         this.flagsLeft = 10;
      }

      sec = 0;
      min = 0;
      Font font = new Font("Arial", Font.PLAIN, 18);

      easy = new JButton("Easy");
      easy.setBackground(new Color(148,160,255));
      easy.setOpaque(true);
      easy.setBorderPainted(false);
      easy.setPreferredSize(new Dimension(100, 80));
      easy.setFont(font);
      menu.add(easy);

      medium = new JButton("Medium");
      medium.setBackground(new Color(119,130,255));
      medium.setBorderPainted(false);
      medium.setOpaque(true);
      medium.setFont(font);
      menu.add(medium);

      hard = new JButton("Hard");
      hard.setBackground(new Color(91,100,255));
      hard.setBorderPainted(false);
      hard.setOpaque(true);
      hard.setFont(font);
      menu.add(hard);

      leaderboard = new JButton("Leaderboard");
      leaderboard.setBackground(new Color(252,255,120));
      leaderboard.setBorderPainted(false);
      leaderboard.setOpaque(true);
      menu.add(leaderboard);

      time = new JLabel("Time: "+ min + ":"+String.format("%02d",sec));
      time.setFont(font);
      time.setBackground(new Color(255,145,90));
      time.setOpaque(true);
      time.setHorizontalAlignment(SwingConstants.CENTER);
      menu.add(time);

      flags = new JLabel("Flag Left: "+ flagsLeft);
      flags.setFont(font);
      flags.setBackground(new Color(255,90,90));
      flags.setOpaque(true);
      flags.setHorizontalAlignment(SwingConstants.CENTER);
      menu.add(flags);
   }

   public Component getComponent() {
      return this.menu;
   }

   public void changeFlags(int i) {
      this.flagsLeft = this.flagsLeft + i;
      flags.setText("Flags Left: " + flagsLeft);
   }

   public int getFlags() {
      return this.flagsLeft;
   }

   public void incSec() {
      sec++;
      time.setText("Time: "+ min + ":"+String.format("%02d",sec));
   }

   public void incMin() {
      min++;
      sec = 0;
      time.setText("Time: "+ min + ":"+String.format("%02d",sec));
   }

   public int getSec() {
      return this.sec;
   }

   public int getMin() {
      return this.min;
   }
}
