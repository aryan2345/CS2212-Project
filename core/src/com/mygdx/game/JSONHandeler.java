package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.*;

import java.util.*;
import java.util.Collections;

/**
 * Custom methods that simplify certain actions for Json. More methods may be added for more functionality. 
 * Certain more advanced functions as found in the <a href="https://libgdx.com/wiki/utils/reading-and-writing-json">LibGDX Wiki</a> will need problem specific implementation.
 * 
 * @author Jack Scott
 * @version 2.00
 */
public class JSONHandeler {

    //Names WIP
    private Json JJson;
    private JsonReader JJsonReader;
    private JsonValue JJsonValue;
    private JsonWriter JJsonWriter;

    /**
     * Default constructor; no params needed
     */
    public JSONHandeler() {}

    /**
     * Creates a new Json and JsonReader for the class.
     */
    public void JSONHandeler() {
        this.JJson = new Json();
        this.JJsonReader = new JsonReader();
    }
    
    /**
     * Example for getting values from the user Json.
     * Notes: users.json is a json with a list of objects that stores user data (5 Strings as named below).
     *
     * Takes in a username (Could also be name, init, userType, or saves). And iterates through the Json looking for said username, if found the username is returned (could also be the previously mentioned).
     *
     * @param username takes in a username that we seek to find in the list of jsons
     * @return the username if found, or the string ERROR otherwise
     */

    //THIS IS AN EXAMPLE, NOT TECHNICALLY USEFUL
    public String getUsername(String username) {
        this.JJsonReader = new JsonReader();
        JsonValue base = JJsonReader.parse(Gdx.files.internal("other/jsons/saves.json"));
        for (JsonValue thing : base) {
            if (Objects.equals(thing.getString("username"), username)) {
                System.out.println(thing.getString("username"));
                return thing.getString("username");
            }
        }
        return "ERROR";
    }


    /**
     * Gets a Save {@see com.mygdx.game.Save} from saves.json based on the entered username or null if username not in json
     *
     * @param username used to find associated Save
     * @return a Save {@link com.mygdx.game.Save} or null if not found
     */
    public Save getSave(String username) {
        this.JJsonReader = new JsonReader();

        //Gets the list of SaveStates in saves.json
        JsonValue base = JJsonReader.parse(Gdx.files.internal("other/jsons/saves.json"));

        //Iterates through each SaveState checking if the username matches, if it does return the SaveState for that username
        for (JsonValue thing : base) {
            if (Objects.equals(thing.getString("username"), username)) {
                Save save = new Save(thing.get("save").getString("diff"), thing.get("save").getInt("level"), thing.get("save").getInt("score"));
                return save;
            }
        }

        //Otherwise return null
        return null;
    }

    /**
     * Gets a SaveState {@see com.mygdx.game.SaveState} from saves.json based on the entered username or null if username not in json
     *
     * @param username used to find associated SaveState
     * @return a SaveState {@link com.mygdx.game.SaveState} or null if not found
     */
    public SaveState getSaveState(String username) {
        this.JJsonReader = new JsonReader();

        //Gets the list of SaveStates in saves.json
        JsonValue base = JJsonReader.parse(Gdx.files.internal("other/jsons/saves.json"));

        //Iterates through each SaveState checking if the username matches, if it does return the SaveState for that username
        for (JsonValue thing : base) {
            if (Objects.equals(thing.getString("username"), username)) {
                SaveState saveState = new SaveState(username, new Save(thing.get("save").getString("diff"), thing.get("save").getInt("level"), thing.get("save").getInt("score")));
                return saveState;
            }
        }

        //Otherwise return null
        return null;
    }

    /**
     * Returns an arraylist of save states sorted from highest score to lowest.
     *
     * @return an ArrayList of SaveStates {@link com.mygdx.game.SaveState} sorted from greatest score to lowest score
     */
    public ArrayList<SaveState> getSortedScores() {

        this.JJsonReader = new JsonReader();

        ArrayList<SaveState> list = new ArrayList<SaveState>();

        //Gets the list of SaveStates in saves.json
        JsonValue base = JJsonReader.parse(Gdx.files.internal("other/jsons/saves.json"));

        //Adds each SaveState to the array list
        for (JsonValue thing : base) {
            list.add(new SaveState(thing.getString("username"), new Save(thing.get("save").getString("diff"), thing.get("save").getInt("level"), thing.get("save").getInt("score"))));
        }

        //Sorts the array list from greatest score to lowest score
        //i.e. [100, 20, 10, 5]
        Collections.sort(list, new Comparator<SaveState>() {
            @Override
            public int compare(SaveState a, SaveState b) {
                return Integer.compare(b.getSave().getScore(), a.getSave().getScore());
            }
        });

        return list;
    }

    /**
     * Saves the game by adding/replacing a SaveState in saves.json. (Constructs a new SaveState from inputs)
     * Takes save.json stores it then adds/replaces the new SaveState and overwrites save.json having added/changed the SaveState.
     * {@see com.mygdx.game.SaveState}
     * {@see com.mygdx.game.Save}
     *
     * @param username used to check for exisitng SaveState in saves.json. Also used to construct a new SaveState
     * @param save used to construct a new SaveState
     */
    public void saveGame(String username, Save save) {
        this.JJsonReader = new JsonReader();

        ArrayList<SaveState> list = new ArrayList<>();

        //Gets the list of SaveStates in saves.json
        JsonValue base = JJsonReader.parse(Gdx.files.internal("other/jsons/saves.json"));

        boolean found = false;

        //Iterates through each SaveState checking if the username matches
        for (JsonValue thing : base) {
            if (Objects.equals(thing.getString("username"), username)) {
                //If it does add it to the list and set found to true
                //This will replace an exisitng instance of the username with the new SaveState, overwriting it
                list.add(new SaveState(username, save));
                found = true;
            } else {
                //Otherwise add the SaveState that doesn't have a matching username
                list.add(new SaveState(thing.getString("username"), new Save(thing.get("save").getString("diff"), thing.get("save").getInt("level"), thing.get("save").getInt("score"))));
            }
        }

        //If there was no existing instance of the username add the new SaveState to the list
        //Creates a new SaveState in the list if none for the username exists
        if (!found) {
            list.add(new SaveState(username, save));
        }

        //Overwrites the json with the new list
        this.JJson = new Json();
        JJson.setOutputType(JsonWriter.OutputType.json);
        String text = JJson.prettyPrint(list);

        FileHandle file = Gdx.files.local("other/jsons/saves.json");
        file.writeString(text, false); //false to overwrite
    }

    /**
     * Saves the game by adding/replacing a SaveState in saves.json, using the entered SaveState
     * Takes save.json stores it then adds/replaces the new SaveState and overwrites save.json having added/changed the SaveState.
     * {@see com.mygdx.game.SaveState}
     * {@see com.mygdx.game.Save}
     *
     * @param saveState to be added or replace an exisitng SaveState
     */
    public void saveGame(SaveState saveState) {
        this.JJsonReader = new JsonReader();

        ArrayList<SaveState> list = new ArrayList<>();

        //Gets the list of SaveStates in saves.json
        JsonValue base = JJsonReader.parse(Gdx.files.internal("other/jsons/saves.json"));

        boolean found = false;

        //Iterates through each SaveState checking if the username matches
        for (JsonValue thing : base) {
            if (Objects.equals(thing.getString("username"), saveState.getUsername())) {
                //If it does add it to the list and set found to true
                //This will replace an exisitng instance of the username with the new SaveState, overwriting it
                list.add(saveState);
                found = true;
            } else {
                //Otherwise add the SaveState that doesn't have a matching username
                list.add(new SaveState(thing.getString("username"), new Save(thing.get("save").getString("diff"), thing.get("save").getInt("level"), thing.get("save").getInt("score"))));
            }
        }

        //If there was no existing instance of the username add the new SaveState to the list
        //Creates a new SaveState in the list if none for the username exists
        if (!found) {
            list.add(saveState);
        }

        //Overwrites the json with the new list
        this.JJson = new Json();
        JJson.setOutputType(JsonWriter.OutputType.json);
        String text = JJson.prettyPrint(list);

        FileHandle file = Gdx.files.local("other/jsons/saves.json");
        file.writeString(text, false); //false to overwrite
    }

    /**
     * Checks if a username is in the users json, returning a boolean
     * @param username that we want to find
     * @return true if found, false otherwise
     */
    public Boolean checkUsername(String username) {
        this.JJsonReader = new JsonReader();

        //Gets the list of SaveStates from saves.json
        JsonValue base = JJsonReader.parse(Gdx.files.internal("other/jsons/saves.json"));

        //Iterates through each SaveState, checking if the usernames match
        for (JsonValue thing : base) {
            if (Objects.equals(thing.getString("username"), username)) {
                //System.out.println(thing.getString("username"));
                return true;
            }
        }
        return false;
    }
}
