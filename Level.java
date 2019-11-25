package model;

public class Level {
   protected int levels;
   
   public Level(int levels) {
      this.levels = levels;
   }

   public int getLevel() {
      return this.levels;
   }
   
   public void setLevel() {
      this.levels = 1;
   }

   public int openLevel() {
      return 0;
   }
   
}