package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * This class represents the New Game Screen where the user can create a new save file with a nickname and difficulty.
 *
 * @author Johnathan
 * @author Jack Scott
 * @version 1.22
 */
public class NewGameScreen implements Screen {
    private Sound click;
    private static final int TEXT_WIDTH = 300;
    private static final int TEXT_HEIGHT = 45;
    private static final int TEXT_Y = SpellQuest.HEIGHT/2;
    private static final int TEXT_X = 300;
    private static final int SUBMIT_BUTTON_WIDTH = 64;
    private static final int SUBMIT_BUTTON_HEIGHT = 64;
    private static final int BACK_BUTTON_WIDTH = 281;
    private static final int BACK_BUTTON_HEIGHT = 70;
    private static final int BACK_BUTTON_Y = 100;
    private static final int SUBMIT_BUTTON_Y = SpellQuest.HEIGHT/2 - 5;
    private static final int SUBMIT_BUTTON_X = 730;
    private SpellQuest game;
    private Texture backgroundImage;
    private TextField userName;
    private SpriteBatch batch;
    private Stage stage;
    private BitmapFont font;
    private BitmapFont smallFont;
    private Skin skin;
    private TextButton submitButton;
    private JSONHandeler jsonHandeler;
    private boolean nameExists; // if the nickname already exists
    private SelectBox<String> difficulty;
    private boolean nullname;



    /**
     * Constructor for NewGameScreen. Creates a new NewGameScreen.
     *
     * @param game  References to the SpellQuest class for changing classes.
     */
    public NewGameScreen (SpellQuest game) {
        this.game = game;
        jsonHandeler = new JSONHandeler();
        nameExists = false;
        click = Gdx.audio.newSound(Gdx.files.internal("data/click.wav"));
        backgroundImage = new Texture("loadScreen.jpg"); // initializes background image
    }

    /**
     * Creates and adds the inputs (buttons, text inputs, dropdown menu) into the screen.
     */
    @Override
    public void show() {
        batch = new SpriteBatch();

        // big font for title (Load Screen)
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(4);

        // small font for telling user if nick exists
        smallFont = new BitmapFont();
        smallFont.setColor(Color.RED);
        smallFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        smallFont.getData().setScale(1);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // create back button with size and position
        int xBackButton = SpellQuest.WIDTH / 2 - BACK_BUTTON_WIDTH / 2;
        TextButton backButton = new TextButton ("BACK", skin);
        backButton.setSize (BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
        backButton.setPosition(xBackButton, BACK_BUTTON_Y);
        backButton.setColor(new Color(0.2f,0.3f,0.2f,1f));

        // add a listener for back button. Sends user back to main menu when clicked.
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play(0.3f);
                game.setScreen(new MainMenu(game));
            }
        });

        // create submit button with size and position
        int xSubmitButton = SUBMIT_BUTTON_X;
        submitButton = new TextButton (">>", skin);
        submitButton.setSize (SUBMIT_BUTTON_WIDTH, SUBMIT_BUTTON_HEIGHT);
        submitButton.setPosition(xSubmitButton, SUBMIT_BUTTON_Y);
        submitButton.setColor(new Color(0.5f,0.2f,0.5f,1f));
        // In your show() method or wherever you are initializing your stage and actors
        submitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play(0.3f);
                loginClicked(); // Call your loginClicked method here
            }
        });

        // add a listener for the submit button. When clicked, it will check if the save exists


        // create the textfield for entering in username
        userName = new TextField ("", skin);
        userName.setPosition(TEXT_X, TEXT_Y);
        userName.setSize (TEXT_WIDTH, TEXT_HEIGHT);
        userName.setMaxLength(4);


        // create a dropdown menu for difficulty.
        difficulty = new SelectBox <String> (skin);
        difficulty.setItems ("Easy", "Medium", "Hard");
        difficulty.setPosition(610, TEXT_Y);
        difficulty.setSize(100, TEXT_HEIGHT);


        // Adds these inputs into the screen
        stage.addActor(submitButton);
        stage.addActor (userName);
        stage.addActor (backButton);
        stage.addActor (difficulty);
    }

    /**
     * Used when the submit button gets clicked. Checks if the username already exists in a save file. If it does, it will
     * tell the user that it exists. If it does not exist, then it will create a new save file with the selected difficulty.
     */
    public void loginClicked() {
        String username = userName.getText();
        // Here you should check if the username exists using your jsonHandler
        // For demonstration, I'm assuming the username is always new
        this.jsonHandeler = new JSONHandeler();

        // if username is null
        if (username == null || username.isEmpty())
        {
            nullname = true;
            return;
        }
        nullname = false;
        // If the username does not exist, add the save to saves.json and proceed to the next screen
        if (!jsonHandeler.checkUsername(username)) {

            String diff = difficulty.getSelected().toLowerCase();

            DifficultyStorage Dpass = new DifficultyStorage(diff);
            UsernameStorage Upass = new UsernameStorage(username);

            //Creates a new SaveState in saves.json
            jsonHandeler.saveGame(username, new Save(diff, 0, 0));
            nameExists = false;
            //Proceed to the next screen
            game.setScreen(new GameScreenLevel1(game, diff));
            // Dispose of the current screen's resources
            dispose();
        }
        else {
            nameExists = true;
        }
    }


    /**
     * Create the LOAD SCREEN texts and the background image.
     *
     * @param v Delta time of the world
     */
    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(v);
        stage.draw();

        game.batch.begin();

        game.batch.draw(backgroundImage, 0, 0, 1000, 800); // put up the background image
        font.draw(game.batch, "NEW GAME", SpellQuest.WIDTH/2 - 170, SpellQuest.HEIGHT-100); // put the LOAD SCREEN text onto the screen

        // if username is empty, tell user that name cannot be empty
        if (nullname)
        {
            smallFont.draw(game.batch, "Nick cannot be empty.", SpellQuest.WIDTH / 2 - 170, 335);
        }
        else {
            smallFont.draw(game.batch, "", SpellQuest.WIDTH / 2 - 170, 335);
        }

        // if the username already exists, tell user that it exists.
        if (nameExists)
        {
            smallFont.draw(game.batch, "Nick already exists.", SpellQuest.WIDTH / 2 - 170, 335);
        }
        else {
            smallFont.draw(game.batch, "", SpellQuest.WIDTH / 2 - 170, 335);
        }

        // if the user holds CTRL + BACKSPACE, it will reset the text input
        if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE) && (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)))
        {
            userName.setText("");
        }

        game.batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
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
        click.dispose();
        batch.dispose();
        backgroundImage.dispose();
        font.dispose();
        stage.dispose();
        skin.dispose();
    }
}

