package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


import java.util.ArrayList;

/**
 * This class represents the Load Game Screen where the user can load a save file with a nickname.
 *
 * @author Johnathan
 * @author Jack Scott
 * @author Aryan Gupta
 * @version 1.2
 */
public class LeaderboardScreen implements Screen {
    private Sound click;
    private static final int BACK_BUTTON_WIDTH = 281;
    private static final int BACK_BUTTON_HEIGHT = 70;
    private static final int BACK_BUTTON_Y = 100;
    private SpellQuest game;
    private Texture backgroundImage;
    private SpriteBatch batch;
    private Stage stage;
    private BitmapFont font;
    private BitmapFont smallFont;
    private Skin skin;
    private JSONHandeler jsonHandeler;
    private Color[] playerColors = new Color[]{
            new Color(1, 1, 0, 1),
            new Color(0, 1, 1, 1),
            new Color(1, 0, 1, 1),
            new Color(0.8f, 0.2f, 0.3f, 1),
            new Color(0.9f, 0.6f, 0.2f, 1)
    };

    // Inside your class fields
    private ArrayList<Label> leaderboardLabels;
    



    /**
     * Constructor for NewGameScreen. Creates a new NewGameScreen.
     *
     * @param game  References to the SpellQuest class for changing classes.
     */
    public LeaderboardScreen (SpellQuest game) {
        this.game = game;
        jsonHandeler = new JSONHandeler();
        click = Gdx.audio.newSound(Gdx.files.internal("data/click.wav"));
        backgroundImage = new Texture("leaderboards.png"); // initializes background image
    }

    /**
     * Creates and adds the inputs (buttons, text inputs, dropdown menu) into the screen.
     */
    @Override
    public void show() {
        batch = new SpriteBatch();
        leaderboardLabels = new ArrayList<Label>();
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
        backButton.setColor(0,0.8f,1,1);

        // add a listener for back button. Sends user back to main menu when clicked.
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play(0.3f);
                game.setScreen(new MainMenu(game));
            }
        });


        // Setup labels for the leaderboard entries
        for (int i = 0; i < 5; i++) {
            Label label = new Label("", skin);
            label.setColor(playerColors[i]); // Set the color using your predefined colors
            label.setFontScale(2.3f); // Adjust scale to match your desired size
            // Set the position of each label (example positions, adjust as necessary)
            label.setPosition(SpellQuest.WIDTH / 2 - 120, SpellQuest.HEIGHT - (200 + 60 * i));
            leaderboardLabels.add(label);
            stage.addActor(label); // Add label to the stage for it to be rendered and acted upon
        }


        // Adds these inputs into the screen
        stage.addActor (backButton);
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
        game.batch.draw(backgroundImage, 0, 0, 1000, 800);
        font.draw(game.batch, "LEADERBOARDS", SpellQuest.WIDTH/2 - 230, SpellQuest.HEIGHT-100); // put the LOAD SCREEN text onto the screen
        font.setColor(1f,0.9f,0.9f,1);
        // Update labels with leaderboard data
        ArrayList<SaveState> sortedList = jsonHandeler.getSortedScores();
        for (int i = 0; i < leaderboardLabels.size() && i < sortedList.size(); i++) {
            SaveState saveState = sortedList.get(i);
            String entryText = (i + 1) + ") " + saveState.getUsername() + " : " + saveState.getScore();
            leaderboardLabels.get(i).setText(entryText); // Update label text
        }
        game.batch.end();

        // Since we're now using Labels added to the stage, we don't need to draw the text manually in the batch
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
        click.dispose();
        batch.dispose();
        backgroundImage.dispose();
        font.dispose();
        stage.dispose();
        skin.dispose();
    }
}

