package com.mygdx.game;

/**
 * Used to store save data within users.json.
 * Stores a String diff, an int level, and an int score. Save is stored as an object within {@link com.mygdx.game.SaveState}
 *
 * @author Jack Scott
 * @version 1.00
 */

public class Save {
    private String diff;
    private int level;
    private int score;

    /**
     * Constructor for Save
     *
     * @param diff String of difficulty
     * @param level int of level completed
     * @param score int of score
     */
    public Save(String diff, int level, int score) {
        this.diff = diff;
        this.level = level;
        this.score = score;
    }

    /**
     * level getter
     * @return int level
     */
    public int getLevel() {
        return level;
    }

    /**
     * score getter
     * @return int score
     */
    public int getScore() {
        return score;
    }

    /**
     * diff getter
     * @return String diff
     */
    public String getDiff() {
        return diff;
    }

    /**
     * level setter
     * @param level new int level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * diff setter
     * @param diff new String diff
     */
    public void setDiff(String diff) {
        this.diff = diff;
    }

    /**
     * score setter
     * @param score new int score
     */
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Save{" +
                "diff='" + diff + '\'' +
                ", level=" + level +
                ", score=" + score +
                '}';
    }
}
