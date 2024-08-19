package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import java.awt.event.KeyListener;

/**
 * Represents the pause screen in the game.
 * This screen is displayed when the game is paused, showing options to resume or exit to the main menu.
 * It handles user interactions for resuming the game or navigating to the main menu.
 * <p>
 * The screen includes buttons for resuming the game and returning to the main menu,
 * and displays a "PAUSED" label to indicate the game state.
 * </p>
 *
 * @author Aryan Gupta
 * @author Parth Hasmukh Kathiria
 */
public class PauseScreen extends ScreenAdapter {
    private Game game;
    private Stage stage;
    private Skin skin;
    private Screen previousScreen;
    private Texture backgroundTexture;
    private static final int BUTTON_WIDTH = 281;
    private static final int BUTTON_HEIGHT = 70;
    private static final int BUTTON_Y = 250;

    /**
     * Creates a pause screen with references to the main game and the previous screen.
     *
     * @param game The main game object used for transitioning between screens.
     * @param previousScreen The screen from which the pause screen was activated, typically the gameplay screen.
     */

    public PauseScreen(Game game, Screen previousScreen)
    {
        this.game = game;
        this.previousScreen = previousScreen;
    }

    /**
     * Called when this screen becomes the current screen for the game.
     * Initializes the UI elements of the pause screen, such as buttons and labels.
     */
    @Override
    public void show() {
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        backgroundTexture = new Texture("pause.png");


        // Load or create a BitmapFont
        BitmapFont font = new BitmapFont();
        font.getData().setScale(4); // Increase font size; adjust scaling as needed

        // Create a new LabelStyle
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.BLACK; // Change to your desired color

        // Create the label with the new style
        Label titleLabel = new Label("PAUSED", labelStyle);
        titleLabel.setPosition(Gdx.graphics.getWidth() / 2f - titleLabel.getWidth() / 2, Gdx.graphics.getHeight() * 0.75f);


        int xQuitButton = SpellQuest.WIDTH / 2 - BUTTON_WIDTH / 2;
        TextButton resumeButton = new TextButton("RESUME", skin);
        resumeButton.setColor(1, 0.5f, 0, 1); // Orange color
        resumeButton.setPosition(xQuitButton, BUTTON_Y + 100);
        resumeButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameScreenLevel1.music.play();
                game.setScreen(previousScreen);
            }
        });


        TextButton quitButton = new TextButton("MAIN MENU", skin);
        quitButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        quitButton.setPosition(xQuitButton, BUTTON_Y);
        quitButton.setColor(0, 0, 1, 1); // Blue color

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameScreenLevel1.music.stop();
                GameScreenLevel1.music.dispose();
                game.setScreen(new MainMenu(game));
            }
        });


        stage.addActor(titleLabel);
        stage.addActor(resumeButton);
        stage.addActor(quitButton);
    }


    /**
     * Renders the pause screen.
     * This method is called every frame and is responsible for drawing the screen and handling user input.
     *
     * @param delta The time in seconds since the last render call.
     */
    @Override
    public void render(float delta) {
        // Check if the Escape key is pressed
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            GameScreenLevel1.music.play();
            //game.setScreen(previousScreen);
            Gdx.app.postRunnable(() -> {
                game.setScreen(previousScreen);;
            });
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().setColor(Color.WHITE); // Resetting the color to white before drawing
        stage.getBatch().draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * Called when the screen is hidden or replaced by another screen.
     * Disposes of the resources used by the pause screen to free up memory.
     */

    @Override
    public void hide() {
        stage.dispose();
        skin.dispose();
        backgroundTexture.dispose();
    }
}