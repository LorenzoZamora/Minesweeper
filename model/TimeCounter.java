package model;

import ui.Menubar;

public class TimeCounter {
   public boolean exit;
   public boolean shouldCount = false;
   public int int_sec = 0;
   public int int_min = 0;
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
                  if (System.currentTimeMillis() - now >= 1000) {
                     now = System.currentTimeMillis();
                     int_sec++;
                     menubar.incSec();
                     if (int_sec >= 60) {
                        int_sec = 1;
                        int_min++;
                        menubar.incMin();
                     }
                     try {
                        Thread.sleep(999);
                     } catch (InterruptedException e) {
                        System.out.println("Timer Exception");
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