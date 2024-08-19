package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;

/**
 * The tutorial screen for the game, displaying instructions and controls to the player.
 * It contains a text display of game rules and a back button to return to the main menu.
 *
 * Created as part of Team 44, for CS2212 at Western University.
 * Created in Fall 2024.
 *
 * @author Aryan Gupta
 * @author Parth Hasmukh Kathiria
 * @version 1.02
 */
public class TutorialScreen extends ScreenAdapter {
    private Sound click;
    private Game game;
    private SpriteBatch batch;
    private BitmapFont font;
    private String tutorialText;
    private BitmapFont titleFont; // Separate font for the title
    private String titleText; // Title text
    private Stage stage;
    private Skin skin;
    private TextButton backButton;
    private Texture backgroundImage; // Background image




    /**
     * Constructs the tutorial screen with the game instance and initializes UI elements.
     *
     * @param game The game instance used to navigate between screens.
     */
    public TutorialScreen(Game game)
    {
        click = Gdx.audio.newSound(Gdx.files.internal("data/click.wav"));

        this.game = game;
        // Initialize your tutorial screen elements here
        batch = new SpriteBatch();


        font = new BitmapFont(); // Use LibGDX's default Arial font
        font.setColor(Color.WHITE);
        font.getData().setScale(1.1f); // Adjust scale as needed for body text

        // Font for the title
        titleFont = new BitmapFont();
        titleFont.setColor(Color.WHITE);
        titleFont.getData().setScale(3); // Larger scale for the title

        titleText = "TUTORIAL"; // Set your title text here




        tutorialText = "\n\nWelcome to Spell Quest, where your spelling prowess is your greatest magic!\n\n" +
                "Shortcuts:\n" +
                "ESC: Pause the gameplay.\n" +
                "CTRL + BACKSPACE: Deletes all the words in the text input.\n\n" +
                "Progress: Your progress is only saved after each level.\n\n" +
                "Load Game: Use this function to continue a previously played game.\n\n" +
                "Scoring:\n" +
                "The score is based on the number of seconds you have left in the respective level multiplied\n" +
                "by a constant. The higher the difficulty, the larger the constant but beware, there are more words\n" +
                "to type with less time on the clock.\n\n"+
                "Levels:\n" +
                "Level 1: Very simple words \n" +
                "Level 2: Not too complex or easy.\n" +
                "Level 3: Complicated words.\n\n" +
                "Leaderboard:\n" +
                "Aim to complete challenges swiftly to climb the leaderboards!\n" +
                "Best of luck on your educational journey through Spell Quest!";


        // Load the background image
        backgroundImage = new Texture("forestTut4.jpg");

        // Set up the stage and skin for buttons
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Create the back button
        backButton = new TextButton("BACK", skin);
        backButton.setColor(new Color(0.2f,0.5f,0.6f,1f));
        float buttonWidth = 200f;
        float buttonHeight = 50f;
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float buttonX = (screenWidth - buttonWidth) / 2f;
        float buttonY = (screenHeight - buttonHeight) / 13f;
        backButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play(0.3f);
                game.setScreen(new MainMenu(game)); // Switch to the main menu screen
            }
        });

        // Add the button to the stage
        stage.addActor(backButton);
    }


    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        // You can initialize other elements if needed
    }

    /**
     * Renders the tutorial screen with background image, tutorial text, and back button.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        // Draw the background image
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Calculate the position of the title text to center it horizontally
        float titleX = (Gdx.graphics.getWidth() - titleFont.getRegion().getRegionWidth() * 2) / 1.2f; // Center horizontally
        float titleY = Gdx.graphics.getHeight() - titleFont.getLineHeight() ; // Position at the top vertically
        // Draw the title
        titleFont.draw(batch, titleText, titleX, titleY);

        // Draw the body text below the title
        font.draw(batch, tutorialText, 200, Gdx.graphics.getHeight() - 70); // Adjust position as necessary for body text
        batch.end();


        // Draw the stage (for buttons)
        stage.act(delta);
        stage.draw();
    }

    /**
     * Disposes of all resources used by the TutorialScreen.
     * This includes the texture, batch, fonts, stage, and skin.
     */

    @Override
    public void dispose() {
        click.dispose();
        batch.dispose();
        font.dispose();
        titleFont.dispose(); // Dispose of the title font
        stage.dispose(); // Dispose of the stage
        skin.dispose(); // Dispose of the skin
    }


}
