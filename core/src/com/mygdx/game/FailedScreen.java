package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
 * This class represents the FAILED screen where it will appear when the user fails a level.
 *
 * @author Johnathan
 * @version 1.01
 */
public class FailedScreen implements Screen {
    private Sound click;
    private Music music;
    private static final int BACK_BUTTON_WIDTH = 281;
    private static final int BACK_BUTTON_HEIGHT = 70;
    private static final int BACK_BUTTON_Y = 100;
    private SpellQuest game;
    private Texture backgroundImage;
    private TextField userName;
    private SpriteBatch batch;
    private Stage stage;
    private BitmapFont font;
    private Skin skin;
    private int level;
    private int score;
    private String difficulty;


    /**
     * Constructor for FailedScreen. Creates a new FailedScreen.
     *
     * @param game  References to the SpellQuest class for changing classes.
     * @param level The current level
     * @param score The score from before
     * @param difficulty The difficulty of the game
     */
    public FailedScreen (SpellQuest game, String difficulty, int level, int score) {
        this.game = game;
        this.level = level;
        this.score = score;
        this.difficulty = difficulty;
        click = Gdx.audio.newSound(Gdx.files.internal("data/click.wav")); // button click
        backgroundImage = new Texture("failed.jpg"); // initializes background image
        music = Gdx.audio.newMusic (Gdx.files.internal("data/failedScreen.mp3")); // background music for the gameplay screens
        music.setVolume(0.1f);
    }

    /**
     * Creates and adds the inputs (buttons, text inputs, dropdown menu) into the screen.
     */
    @Override
    public void show() {
        batch = new SpriteBatch();

        // big font for title (YOU DIED)
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(4);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // create main menu button with size and position
        int xBackButton = SpellQuest.WIDTH / 2 - BACK_BUTTON_WIDTH / 2;
        TextButton backButton = new TextButton ("MAIN MENU", skin);
        backButton.setSize (BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
        backButton.setPosition(xBackButton, BACK_BUTTON_Y);
        backButton.setColor(new Color(0.5f,0.3f,0.7f,1)); // Set the button's color to yellow

        // create retry button with size and position
        int xRetryButton = SpellQuest.WIDTH / 2 - BACK_BUTTON_WIDTH / 2;
        TextButton retryButton = new TextButton ("RETRY", skin);
        retryButton.setSize (BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
        retryButton.setPosition(xBackButton, BACK_BUTTON_Y + 100);
        retryButton.setColor(new Color(0.5f,0.3f,0.7f,1)); // Set the button's color to yellow

        // add a listener for back button. Sends user back to main menu when clicked.
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play(0.3f);
                music.stop();
                game.setScreen(new MainMenu(game));
            }
        });

        // add a listener for retry button. Allows user to retry the level.
        if (level == 1)
        {
            retryButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    click.play(0.3f);
                    music.stop();
                    game.setScreen(new GameScreenLevel1(game, difficulty));
                }
            });
        }
        else if (level == 2){
            retryButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    click.play(0.3f);
                    music.stop();
                    game.setScreen(new GameScreenLevel2(game, difficulty, score));
                }
            });
        }
        else if (level == 3) {
            retryButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    click.play(0.3f);
                    music.stop();
                    game.setScreen(new GameScreenLevel3(game, difficulty, score));
                }
            });
        }


        // Adds these inputs into the screen
        stage.addActor(retryButton);
        stage.addActor (backButton);
    }


    /**
     * Create the FAILED SCREEN and the background image.
     *
     * @param v Delta time of the world
     */
    @Override
    public void render(float v) {
        music.play();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(v);
        stage.draw();

        game.batch.begin();

        game.batch.draw(backgroundImage, 0, 0, 1000, 800); // put up the background image
        font.draw(game.batch, "YOU DIED", SpellQuest.WIDTH/2 - 170, SpellQuest.HEIGHT-100); // put the YOU DIED text onto the screen


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
        music.dispose();
        click.dispose();
        batch.dispose();
        backgroundImage.dispose();
        font.dispose();
        stage.dispose();
        skin.dispose();
    }
}

