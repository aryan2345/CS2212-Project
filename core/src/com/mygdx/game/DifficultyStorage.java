package com.mygdx.game;
/**
 * Stores the current difficulty being used.
 *
 * TO ACCESS DIFFICULTY AS A STRING CALL: new DifficultyStorage().getDiff()
 *
 * @author Jack Scott
 * @version 1.00
 */
public class DifficultyStorage {

    private static String diff;

    /**
     * Default constructor with no parameters.
     */
    public DifficultyStorage () {}

    /**
     * Stores the current difficulty being used
     * @param newdiff new difficulty being used
     */
    public DifficultyStorage (String newdiff) {
        diff = newdiff;
    }

    /**
     * Getter for current difficulty being used
     * @return String of current difficulty
     */
    public String getDiff() {
        return diff;
    }

    /**
     * Setter for current difficulty being used
     * @param newdiff new difficulty being used
     */
    public void setDiff(String newdiff) {
        diff = newdiff;
    }
}