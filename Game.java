package ui;

import model.Gameboard;
import model.HighScoreFile;
import model.TimeCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game {
   private Menubar menubar;
   private Gameboard gameboard;
   private JFrame frame;
   private BoardUI boardUI;
   private JPanel grid;
   private JPanel top;
   private JPanel divider;
   private Component menu;
   private JPanel leaderboard;
   private boolean first;
   private TimeCounter t;
   private HighScoreFile scores;
   private JLabel message;
   
   public Game() {
      message = new JLabel();
      message.setFont(new Font("Arial", Font.PLAIN, 30));
      this.first = true;
      frame = new JFrame("Minesweeper");
      frame.setBackground(new Color(110,243,150));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      divider = new JPanel();
      divider.add(Box.createVerticalStrut(100));
      top = new JPanel();
      menubar = new Menubar(1);
      menu = menubar.getComponent();
      grid = new JPanel();
      scores = new HighScoreFile();
      scores.loadScores();
   }
   
   public void initFrame(int diff) {
      frame = new JFrame("Minesweeper");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      leaderboard = new Leaderboard(scores).getLeaderboard();
      divider = new JPanel();
      divider.add(Box.createVerticalStrut(100));
      top = new JPanel();
      menubar = new Menubar(diff);
      menu = menubar.getComponent();
      grid = new JPanel();
      menubar.easy.addMouseListener(new LevelListener(1));
      menubar.medium.addMouseListener(new LevelListener(2));
      menubar.hard.addMouseListener(new LevelListener(3));
      menubar.leaderboard.addMouseListener(new LevelListener(4));
   }
   
   public void newGame(int diff) {
         frame.dispose();
         initFrame(diff);
         first = true;
         top.add(menu, BorderLayout.NORTH);
         t = new TimeCounter(menubar);
         t.shouldCount = true;
         t.count();
         frame.add(top, BorderLayout.NORTH);
         boardUI = new BoardUI(diff);
         this.gameboard = boardUI.getGameboard();
         grid = boardUI.makeBoard(menubar);
         gameboard.placeMines();
         gameboard.calculateMines();
         for (int y = 0; y < gameboard.rows; y++){
            for(int x = 0; x < gameboard.cols; x++) {
               gameboard.gameArray[y][x].getButton().addMouseListener(new TileListener(gameboard.gameArray[y][x]));
            }
         }
         frame.add(grid);
         frame.pack();
         frame.setVisible(true);
      }
   
   public void leaderboard() {
         frame.dispose();
         initFrame(1);
         top.add(menu, BorderLayout.NORTH);
         frame.add(top, BorderLayout.NORTH);
         frame.add(leaderboard);
         frame.pack();
         frame.setVisible(true);
   }
   
   public void win(JLabel winMessage) {
      t.stop();
      JOptionPane.showMessageDialog(null, winMessage, "", JOptionPane.INFORMATION_MESSAGE);
      scores.setScore(gameboard.diff, (menubar.getMin()*60)+menubar.getSec());
      System.out.println((menubar.getMin()*60)+menubar.getSec());
      scores.saveScores();
   }
   //return true if you lose
   
   public boolean open(TileButton tileButton) {
	  if(tileButton.open() == true) {
	     message.setText("You Lost! Close out of this message and select another difficulty to play again.");
         lose(message);
         return false;
      } else {
         gameboard.recursiveOpen(tileButton);
         return true;
      }
   }
   
   //return true if game is finished
   public boolean flag(TileButton tileButton) {
      if(tileButton.getTile().isFlagged() == false && menubar.getFlags() > 0) {
         menubar.changeFlags(-1);
         tileButton.flag();
         if(gameboard.isFinished(menubar.getFlags()) == true) {
            message.setText("You Won! Close out of this message and select another difficulty to play again.");
            win(message);
            return true;
         }
      } else if (tileButton.getTile().isFlagged() == true) {
         menubar.changeFlags(1);
         tileButton.flag();
      }
      return false;
   }
   
   public void lose(JLabel message) {
      gameboard.loseGame();
      t.stop();
      JOptionPane.showMessageDialog(null, message,"", JOptionPane.INFORMATION_MESSAGE);
   }
   
   public void leaderboards() {
   }
   
   public Menubar getMenubar() {
      return this.menubar;
   }
   
   private class LevelListener implements MouseListener {
      int level;
      
      public LevelListener(int level) {
         this.level = level;
      }

      @Override
      public void mouseClicked(MouseEvent e) {
         if(e.getButton() == 1) {
            t.stop();
            if(level < 4) {
               newGame(level);
            } else{
               leaderboard();
            }
         }
      }

      @Override
      public void mousePressed(MouseEvent e) {}
      @Override
      public void mouseReleased(MouseEvent e) {}
      @Override
      public void mouseEntered(MouseEvent e) {}
      @Override
      public void mouseExited(MouseEvent e) {}
   }
   
   public class TileListener implements MouseListener {
      private TileButton tileButton;
      
      public TileListener(TileButton button) {
         this.tileButton = button;
      }
      
      @Override
      public void mouseClicked(MouseEvent e) {
         if(e.getButton() == 3) {
            if(tileButton.getButton().isEnabled()) {
               flag(tileButton);
            }
         }
            
         if(e.getButton() == 1) {
            if(first == true) {
               while(tileButton.getTile().getMines() != 0 || tileButton.getTile().isMine()) {
                     gameboard.removeMines();
                     gameboard.placeMines();
                     gameboard.calculateMines();
               }
               first = false;
            }
            open(tileButton);
         }
      }
      
      @Override
      public void mouseEntered(MouseEvent e) {}
      @Override
      public void mouseExited(MouseEvent e) {}
      @Override
      public void mousePressed(MouseEvent e) {}
      @Override
      public void mouseReleased(MouseEvent e) {}
   }
}
