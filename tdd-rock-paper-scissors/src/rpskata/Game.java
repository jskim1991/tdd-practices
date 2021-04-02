package rpskata;

public class Game {
    public static GameResult play(Throw playerOneChoice, Throw playerTwoChoice) {

        GameResult result = GameResult.P1_WINS;

        if (playerOneChoice.equals(playerTwoChoice)) {
            return GameResult.DRAW;
        } else {
            if (playerOneChoice.equals(Throw.ROCK)) {
                switch (playerTwoChoice) {
                    case PAPER:
                        result = GameResult.P2_WINS;
                        break;
                }
            } else if (playerOneChoice.equals(Throw.PAPER)) {
                switch (playerTwoChoice) {
                    case SCISSORS:
                        result = GameResult.P2_WINS;
                        break;
                }
            } else if (playerOneChoice.equals(Throw.SCISSORS)) {
                switch (playerTwoChoice) {
                    case ROCK:
                        result = GameResult.P2_WINS;
                        break;
                }
            }
        }

        return result;
    }
}
