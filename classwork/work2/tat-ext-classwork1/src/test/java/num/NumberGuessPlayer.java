package num;

import java.util.Random;

/**
 * Plays numberGuessBean trying to find answer.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 02.11.2016
 */
public class NumberGuessPlayer {

    /**
     * Plays NumberGuess and finds answer if
     * there is an opportunity to win.
     *
     * @param numberGuessBean game object to play
     * @return answer if it wins the game,
     * -1 if it can't win.
     */
    public int findAnswer(NumberGuessBean numberGuessBean) {
        int answer = new Random().nextInt(99) + 1;
        int maxBound = 100;
        int minBound = 0;
        int i = maxBound;
        while (i > 0) {
            numberGuessBean.setGuess(String.valueOf(answer));
            if (numberGuessBean.getSuccess()) {
                return answer;
            }

            if ("lower".equals(numberGuessBean.getHint())) {
                maxBound = answer;
                answer -= (maxBound - minBound) / 2;

            }

            if ("higher".equals(numberGuessBean.getHint())) {
                minBound = answer;
                answer += (maxBound - minBound) / 2;
            }

            i--;
        }

        return -1;
    }
}
