package rpskata;

import org.junit.Test;

import static org.junit.Assert.*;
import static rpskata.Game.play;
import static rpskata.Throw.*;
import static rpskata.GameResult.*;

public class RPSTest {

    @Test
    public void rockVsScissors() {
        assertEquals(P1_WINS, play(ROCK, SCISSORS));
    }

    @Test
    public void rockVsPaper() {
        assertEquals(P2_WINS, play(ROCK, PAPER));
    }

    @Test
    public void paperVsScissors() {
        assertEquals(P2_WINS, play(PAPER, SCISSORS));
    }

    @Test
    public void paperVsRock() {
        assertEquals(P1_WINS, play(PAPER, ROCK));
    }

    @Test
    public void scissorsVsRock() {
        assertEquals(P2_WINS, play(SCISSORS, ROCK));
    }

    @Test
    public void scissorsVsPaper() {
        assertEquals(P1_WINS, play(SCISSORS, PAPER));
    }

    @Test
    public void rockVsRock() {
        assertEquals(DRAW, play(ROCK, ROCK));
    }

    @Test
    public void scissorsVsScissors() {
        assertEquals(DRAW, play(SCISSORS, SCISSORS));
    }

    @Test
    public void paperVsPaper() {
        assertEquals(DRAW, play(PAPER, PAPER));
    }
}
