package model;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class HighScoreFileTest {

    public static HighScoreFile testConstructor() {
        HighScoreFile highScoreFile = new HighScoreFile();
        int easy = highScoreFile.getScore(1);
        int med = highScoreFile.getScore(2);
        int hard = highScoreFile.getScore(3);
        assertSame("HighScoreFile constructor set incorrect easyScore boolean", easy, highScoreFile.easyScore);
        assertSame("HighScoreFile constructor set incorrect medScore boolean", med, highScoreFile.medScore);
        assertSame("HighScoreFile constructor set incorrect hardScore boolean", hard, highScoreFile.hardScore);
        return highScoreFile;
    }

    @Test
    public void constructorTestHighScoreFile() {
        testConstructor();
    }
}
