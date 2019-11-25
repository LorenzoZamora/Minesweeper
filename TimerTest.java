package model;

import org.junit.Test;
import ui.Game;

public class TimerTest {
    public static TimeCounter testConstructor() {
        Game game = new Game();
        game.newGame(1);
        TimeCounter timer = new TimeCounter(game.getMenubar());
        return timer;
    }

    @Test
    public void runTimerTest() {
    TimeCounter timer = testConstructor();
    timer.count();
    timer.stop();
    }
}
