package main.task01;

import java.util.ArrayList;

/**
 * Performs age calculations like finding minimal, maximal and average age.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 21.10.2016
 */
public class Counter {

    /**
     * Finds maximal human age in array and returns int.
     * If there is no humans, returns 0.
     *
     * @param humans array of humans to count maximal age
     * @return maximal human age in array of humans
     */
    public int getMaxAge(ArrayList<Human> humans) {
        if (humans.size() == 0) {
            return 0;
        }

        int maxAge = humans.get(0).getAge();
        for (Human human : humans) {
            if (human.getAge() > maxAge) {
                maxAge = human.getAge();
            }
        }

        return maxAge;
    }

    /**
     * Finds minimal human age in array and returns int.
     * If there is no humans, returns 0.
     *
     * @param humans array of humans to count minimal age.
     * @return minimal human age in array of humans
     */
    public int getMinAge(ArrayList<Human> humans) {
        if (humans.size() == 0) {
            return 0;
        }

        int minAge = humans.get(0).getAge();
        for (Human human : humans) {
            if (human.getAge() < minAge) {
                minAge = human.getAge();
            }
        }

        return minAge;
    }

    /**
     * Finds human average age and returns int.
     * If there is no humans, returns 0.
     *
     * @param humans array of humans to count average age
     * @return average human age in array of humans
     */
    public double getAverageAge(ArrayList<Human> humans) {
        if (humans.size() == 0) {
            return 0;
        }

        int sumAge = 0;
        for (Human human : humans) {
            sumAge += human.getAge();
        }

        return sumAge / humans.size();
    }
}
