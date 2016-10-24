package main;

import java.util.ArrayList;
import java.util.Random;

/**
 * Generating random data for exporting.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 21.10.2016
 */
public class DataGenerator {

    /**
     * Generates random number from 1 to 41 and
     * returns list of numbers from 0 to this
     * generated number.
     *
     * @return list of number from 0 to random number
     */
    public ArrayList<Integer> generate() {
        ArrayList<Integer> randomData = new ArrayList<>();
        int random = new Random().nextInt(40) + 1;
        for (int i = 0; i < random; i++) {
            randomData.add(i);
        }
        return randomData;
    }
}
