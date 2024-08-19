package com.mygdx.game;

/**
 * Used to store save data within users.json.
 * Stores a String username and a Save {@link com.mygdx.game.Save}
 *
 * @author Jack Scott
 * @version 1.00
 */
public class SaveState {
    private String username;
    private Save save;

    /**
     * Constructor for SaveState
     * {@see com.mygdx.game.Save}
     *
     * @param username  The username of the save
     * @param save      The save information for the username
     */
    public SaveState(String username, Save save) {
        this.username = username;
        this.save = save;
    }

    /**
     * username getter
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * save getter
     * @return Save save {@link com.mygdx.game.Save}
     */
    public Save getSave() {
        return save;
    }

    /**
     * username setter
     * @param username new String for username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Save setter
     * @param save new Save {@link com.mygdx.game.Save} for save
     */
    public void setSave(Save save) {
        this.save = save;
    }

    /**
     * Getter method for difficulty
     *
     * @return  {@link Save#getDiff()}
     */
    public String getDiff(){
        return save.getDiff();
    }

    /**
     * Getter method for level
     *
     * @return {@link Save#getLevel()}
     */
    public int getLevel(){
        return save.getLevel();
    }

    /**
     * Getter method for score
     *
     * @return {@link Save#getScore()}
     */
    public int getScore(){
        return save.getScore();
    }

    /**
     * Setter method for difficulty
     *
     * {@link Save#setDiff(String)}
     * @param diff new difficulty
     */
    public void setDiff(String diff) {
        this.save.setDiff(diff);
    }

    /**
     * Setter method for level
     *
     * {@link Save#setLevel(int)}
     * @param level new level
     */
    public void setLevel(int level) {
        this.save.setLevel(level);
    }

    /**
     * Setter method for score
     *
     * {@link Save#setScore(int)}
     * @param score new score
     */
    public void setScore(int score) {
        this.save.setScore(score);
    }


    @Override
    public String toString() {
        return "SaveState{" +
                "username='" + username + '\'' +
                ", save=" + save +
                '}';
    }
}
