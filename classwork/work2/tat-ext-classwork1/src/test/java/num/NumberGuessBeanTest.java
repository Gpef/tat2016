package num;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.*;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 02.11.2016
 */
public class NumberGuessBeanTest {

    private NumberGuessBean numberGuessBean;

    @DataProvider(name = "invalidGuess")
    public Object[][] getInvalidGuessData() throws Exception {
        return new Object[][]{
                {"adf"},
                {"!@#"},
                {"1e10"},
                {"0.1"},
                {"1d"},
                {""},
                {"\n"},
                {"101"},
                {"-1"},
                {"0"},
                {String.valueOf(Integer.MIN_VALUE)},
                {String.valueOf(Integer.MAX_VALUE)},
        };
    }

    @BeforeMethod
    public void setUp() throws Exception {
        numberGuessBean = new NumberGuessBean();
    }

    @Test
    public void constructorDefaultValuesSet() throws Exception {
        assertEquals(numberGuessBean.getNumGuesses(), 0);
        assertFalse(numberGuessBean.getSuccess());
        assertEquals(numberGuessBean.getHint(), "");

        int answer;
        for (answer = 0; answer < 100; answer++) {
            numberGuessBean.setGuess(String.valueOf(answer));
            if (numberGuessBean.getSuccess()) {
                break;
            }
        }
        assertTrue(answer > 0 && answer <= 100);
    }

    @Test(dataProvider = "invalidGuess")
    public void setInvalidGuess(String guess) throws Exception {
        numberGuessBean.setGuess(guess);
        assertEquals(numberGuessBean.getHint(), "a number next time");
    }

    @Test
    public void setHigherGuess() throws Exception {
        int answer = 1;
        do {
            numberGuessBean.setGuess(String.valueOf(answer));
            if (numberGuessBean.getSuccess()) {
                if (answer != 1) {
                    break;
                } else {
                    numberGuessBean.reset();
                    continue;
                }
            }
            assertEquals(numberGuessBean.getHint(), "higher");
            answer++;
        } while (true);
    }

    @Test
    public void setLowerGuess() throws Exception {
        int answer = 100;
        do {
            numberGuessBean.setGuess(String.valueOf(answer));
            if (numberGuessBean.getSuccess()) {
                if (answer != 100) {
                    break;
                } else {
                    numberGuessBean.reset();
                    continue;
                }
            }
            assertEquals(numberGuessBean.getHint(), "lower");
            answer--;
        } while (true);
    }

    @Test
    public void numberOfGuesses() {
        numberGuessBean.setGuess(String.valueOf(new Random().nextInt(99) + 1));
        int previousNumberOfGuesses = numberGuessBean.getNumGuesses();
        numberGuessBean.setGuess(String.valueOf(new Random().nextInt(99) + 1));
        assertEquals(previousNumberOfGuesses + 1, numberGuessBean.getNumGuesses());
    }

    @Test
    public void testReset() throws Exception {
        int previousAnswer = new NumberGuessPlayer().findAnswer(numberGuessBean);
        numberGuessBean.reset();
        assertEquals(numberGuessBean.getNumGuesses(), 0);
        assertFalse(numberGuessBean.getSuccess());
        assertEquals(numberGuessBean.getHint(), "");

        int answer;
        while (true) {
            for (answer = 0; answer <= 100; answer++) {
                numberGuessBean.setGuess(String.valueOf(answer));
                if (numberGuessBean.getSuccess()) {
                    break;
                }
            }

            if (answer == previousAnswer) {
                numberGuessBean.reset();
            } else {
                assertTrue(answer > 0 && answer <= 100);
                break;
            }
        }
    }

    @Test
    public void winOpportunity() throws Exception {
        new NumberGuessPlayer().findAnswer(numberGuessBean);
        assertTrue(numberGuessBean.getSuccess(), "Can't win in finite iterations count");
    }
}