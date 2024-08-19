package com.mygdx.game;
/**
 * Stores the current username being used.
 *
 * TO ACCESS USERNAME AS A STRING CALL: new UsernameStorage().getUsername()
 *
 * @author Jack Scott
 * @version 1.00
 */
public class UsernameStorage {
    private static String username;

    /**
     * The default constructor. No parameters
     */
    public UsernameStorage() {
    }

    /**
     * Stores the current username being used
     * @param newusername new username being used
     */
    public UsernameStorage(String newusername) {
        username = newusername;
    }

    /**
     * Getter for current username being used
     * @return String of current username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for current username being used
     * @param newusername new username being used
     */
    public void setUsername(String newusername) {
        username = newusername;
    }
}
