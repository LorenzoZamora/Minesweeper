package model;

import ui.Menubar;

public class TimeCounter {
   public boolean exit;
   public boolean shouldCount = false;
   public int int_sec = 0;
   public int int_min = 0;
   public int int_mil = 0;
   private Menubar menubar;

   public TimeCounter(Menubar mb) {
      exit = false;
      this.menubar = mb;
   }

   public void count() {
      Thread thread = new Thread(new Runnable() {
         @Override
         public void run() {
            long now = System.currentTimeMillis();
            while (!exit) {
               if (shouldCount) {
                  if (System.currentTimeMillis() - now >= 100) {
                     now = System.currentTimeMillis();
                     int_mil++;
                     if (int_mil > 9) {
                        int_mil = 0;
                        int_sec++;
                        menubar.incSec();
                        if (int_sec >= 60) {
                           int_sec = 1;
                           int_min++;
                           menubar.incMin();
                        }
                     }
                  }
               }
            }
         }
      });
      thread.start();
   }

   public void stop() {
      exit = true;
   }
}