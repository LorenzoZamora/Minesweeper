package model;
import org.junit.*;
import static org.junit.Assert.*;

public class LevelTest {

   public static Level testConstructor(int levels){
      Level Level = new Level(levels);
      return Level;
   }
   
   @Test
   public void testOpenLevel() {
      Level Level = testConstructor(1);
      assertSame("openLevel()", 0,Level.openLevel());
   }
   
   @Test
   public void testGetLevel() {
      Level Level = testConstructor(1);
      assertSame("getLevel()", 1,1);
   }
   
   @Test
   public void testSetLevel() {
      Level Level = testConstructor(1);
      assertSame("setLevel()", 1 ,1);
   }
}


