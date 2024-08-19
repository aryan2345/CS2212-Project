package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

/**
 * This screen represents the Instructor Dashboard. It shows every save's stats (level, score, difficulty and the username)
 *
 * @author Raed
 * @author Johnathan
 */
public class InstructorDashboard implements Screen {
    private SpellQuest game;
    private Texture backgroundImage;
    private SpriteBatch batch;
    private Stage stage;
    private BitmapFont font;
    private BitmapFont smallfont;
    private Skin skin;
    private TextField usernameField, passwordField;
    private TextButton loginButton, logoutButton, viewDashboardButton, backButton;
    private boolean isLoggedIn = false;
    private final String hardcodedUsername = "instructor";
    private final String hardcodedPassword = "admin";
    private static final int TEXT_WIDTH = 300;
    private static final int TEXT_HEIGHT = 45;
    private static final int BACK_BUTTON_WIDTH = 281;
    private static final int BACK_BUTTON_HEIGHT = 70;
    private static final int BACK_BUTTON_Y = 100;
    private ScrollPane scrollPane;
    private boolean correctLogin;


    /**
     * The constructor of the dashboard.
     *
     * @param game  References to the SpellQuest class for changing classes.
     */
    public InstructorDashboard(SpellQuest game) {
        this.game = game;
        correctLogin = true;
        backgroundImage = new Texture("dashboard.png"); // sets background image
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        setupUI();
    }

    /**
     * Sets up the user interface (buttons, text inputs, title)
     *
     */
    private void setupUI() {
        // big font for title (INSTRUCTOR DASHBOARD)
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(4);

        // small font for text under the text inputs
        smallfont = new BitmapFont();
        smallfont.setColor(Color.RED);
        smallfont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        smallfont.getData().setScale(1);

        // create back button with size and position
        int xBackButton = SpellQuest.WIDTH / 2 - BACK_BUTTON_WIDTH / 2;
        backButton = new TextButton("BACK", skin);
        backButton.setColor(Color.FIREBRICK); // Set to your preferred color
        backButton.setSize(BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
        backButton.setPosition(xBackButton, BACK_BUTTON_Y); // Position at center bottom

        // goes to main menu when clicked
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });
        stage.addActor(backButton); // adds it to screen

        // Creates the textbox for username
        usernameField = new TextField("", skin);
        usernameField.setMessageText("Username");
        usernameField.setSize(TEXT_WIDTH, TEXT_HEIGHT);
        usernameField.setPosition(100, 370);
        stage.addActor(usernameField);

        // Creates the textbox for password
        passwordField = new TextField("", skin);
        passwordField.setMessageText("Password");
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);
        passwordField.setPosition(100, 300);
        passwordField.setSize(TEXT_WIDTH, TEXT_HEIGHT);
        stage.addActor(passwordField);

        // creates the button for login
        loginButton = new TextButton("Login", skin);
        loginButton.setPosition(100, 230);
        loginButton.setSize(200, 45);
        // when clicked, it will verify the login is correct. If correct, then it will bring up the dashboard
        loginButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                isLoggedIn = login(usernameField.getText(), passwordField.getText());
                if (isLoggedIn) {
                    logoutButton.setVisible(true);
                    viewDashboard();
                    loginButton.setVisible(false);
                }
            }
        });
        stage.addActor(loginButton);

        // button for logout
        logoutButton = new TextButton("Logout", skin);
        logoutButton.setPosition(100, 230);
        logoutButton.setSize(200, 45);
        logoutButton.setVisible(false);
        // when clicked, the textfields will be empty and stats will not show
        logoutButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                logOut();
            }
        });
        stage.addActor(logoutButton);
    }

    /**
     * Login helper
     *
     * @param username  Username for login
     * @param password  Password for login
     * @return          Whether the login was successful
     */
    private boolean login(String username, String password) {
        boolean success = username.equals(hardcodedUsername) && password.equals(hardcodedPassword);
        if (success)
            correctLogin = true;
        else
            correctLogin = false;
        isLoggedIn = success;
        return success;
    }

    /**
     * Logout helper, sets the textfields empty and stats hidden
     */
    private void logOut() {
        isLoggedIn = false;
        usernameField.setText("");
        passwordField.setText("");
        logoutButton.setVisible(false);
        //viewDashboardButton.setVisible(false);
        scrollPane.remove();
        loginButton.setVisible(true);
    }

    /**
     * Opens up the stats dashboard.
     */
    private void viewDashboard() {
        JSONHandeler jsonHandeler = new JSONHandeler();
        ArrayList<SaveState> scores = jsonHandeler.getSortedScores(); // gets the saves from the JSON


        List<String> scoreList = new List<>(skin);
        Array<String> items = new Array<>();

        // loops through each save and adds whitespace to some spaces to make the characters look more aligned
        for (SaveState saveState : scores) {
            String username;
            if (saveState.getUsername() == null)
                username = String.format("%-4s ", " ");
            else
                username = String.format("%-4s ", saveState.getUsername());
            String difficulty = String.format ("%-6s ", saveState.getDiff());

            String lvl;
            if (saveState.getLevel() == -1)
                lvl = "COMPLETE";
            else
                lvl = Integer.toString(saveState.getLevel());
            String level = String.format("%-8s ", lvl);

            items.add(username + ":                " + difficulty + "                " + level + "                " + saveState.getSave().getScore());
        }
        scoreList.setItems(items);


        // creates a scroll for the dashboard with the stats
        scrollPane = new ScrollPane(scoreList, skin);
        scrollPane.setBounds(450, 200, 470, 300); // Adjust position and size as needed
        scrollPane.getFadeScrollBars();
        scrollPane.fling(0.5F,0,2);
        stage.addActor(scrollPane);
    }

    /**
     * Creates and adds assets
     */
    @Override
    public void show() {
    }

    /**
     * Renders in the title of the screen.
     * @param delta Delta time of the world
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(batch, "INSTRUCTOR DASHBOARD", SpellQuest.WIDTH/2-370, SpellQuest.HEIGHT-100);

        smallfont.draw(batch, correctLogin ? "" : "Incorrect username or password", 115, 435);
        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * If the window is resized, resize it to the proper size.
     *
     * @param i     Width of the window
     * @param i1    Height of the window
     */
    @Override
    public void resize(int i, int i1) {

    }

    /**
     * If the screen is paused
     */
    @Override
    public void pause() {

    }

    /**
     * If the screen is resumed
     */
    @Override
    public void resume() {

    }

    /**
     * Hides certain assets of the screen
     */
    @Override
    public void hide() {

    }

    /**
     * Disposes certain assets of the screen
     */
    @Override
    public void dispose() {
        batch.dispose();
        backgroundImage.dispose();
        font.dispose();
        stage.dispose();
        skin.dispose();
    }
}