package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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

/**
 * This class is the Debug Screen for the game. It allows the user to go to any level and difficulty of their choosing.
 *
 * @author Aryan Gupta
 * @author Parth Hasmukh Kathiria
 * @author Johnathan Lam
 * @version 1.01
 */
public class DebugMode extends ScreenAdapter {
    private static final int BACK_BUTTON_WIDTH = 281;
    private static final int BACK_BUTTON_HEIGHT = 70;
    private static final int BACK_BUTTON_Y = 100;
    private Game game;
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont font;
    private Sound click;
    private Texture backgroundImage;
    private Skin skin;

    /**
     * The constructor for the Debug Screen.
     *
     * @param game References to the SpellQuest class for changing classes.
     */
    public DebugMode(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        backgroundImage = new Texture("debug_background.jpg"); // Set your debug mode background image
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        click = Gdx.audio.newSound(Gdx.files.internal("data/click.wav"));
    }

    /**
     * Adds in the buttons with their functionality.
     */
    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // big font for title (Load Screen)
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(4);

        // create back button with size and position
        int xBackButton = SpellQuest.WIDTH / 2 - BACK_BUTTON_WIDTH / 2;
        TextButton backButton = new TextButton ("BACK", skin);
        backButton.setSize (BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
        backButton.setPosition(xBackButton, BACK_BUTTON_Y);
        backButton.setColor(new Color(0.5f,0.3f,0.7f,1)); // Set the button's color to yellow

        // add a listener for back button. Sends user back to main menu when clicked.
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play(0.3f);
                game.setScreen(new MainMenu(game));
            }
        });

        // Create Level 1 EASY button
        TextButton level1ButtonE = new TextButton("Level 1 EASY", skin);
        level1ButtonE.setColor(0.8f, 0.8f, 0.2f, 1); // Set color to a visible one
        level1ButtonE.setPosition(100, 435);
        level1ButtonE.setSize(200, 50);
        level1ButtonE.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play(0.3f);
                game.setScreen(new GameScreenLevel1(game, "easy"));
            }
        });

        // Create Level 1 MEDIUM button
        TextButton level1ButtonM = new TextButton("Level 1 MEDIUM", skin);
        level1ButtonM.setColor(0.8f, 0.8f, 0.2f, 1); // Set color to a visible one
        level1ButtonM.setPosition(SpellQuest.WIDTH/2 - level1ButtonM.getWidth()/2 - 40, 435);
        level1ButtonM.setSize(200, 50);
        level1ButtonM.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play(0.3f);
                game.setScreen(new GameScreenLevel1(game, "medium"));
            }
        });

        // Create Level 1 HARD button
        TextButton level1ButtonH = new TextButton("Level 1 HARD", skin);
        level1ButtonH.setColor(0.8f, 0.8f, 0.2f, 1); // Set color to a visible one
        level1ButtonH.setPosition(690, 435);
        level1ButtonH.setSize(200, 50);
        level1ButtonH.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play(0.3f);
                game.setScreen(new GameScreenLevel1(game, "hard"));
            }
        });

        // Create Level 2 EASY button
        TextButton level2ButtonE = new TextButton("Level 2 EASY", skin);
        level2ButtonE.setColor(0.2f, 0.8f, 0.2f, 1); // Set color to a visible one
        level2ButtonE.setPosition(100, 335);
        level2ButtonE.setSize(200, 50);
        level2ButtonE.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play(0.3f);
                int score1 = 0;
                game.setScreen(new GameScreenLevel2(game, "easy", score1));
            }
        });

        // Create Level 2 MEDIUM button
        TextButton level2ButtonM = new TextButton("Level 2 MEDIUM", skin);
        level2ButtonM.setColor(0.2f, 0.8f, 0.2f, 1); // Set color to a visible one
        level2ButtonM.setPosition(SpellQuest.WIDTH/2 - level2ButtonM.getWidth()/2 - 40, 335);
        level2ButtonM.setSize(200, 50);
        level2ButtonM.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play(0.3f);
                int score1 = 0;
                game.setScreen(new GameScreenLevel2(game, "medium", score1));
            }
        });

        // Create Level 2 HARD button
        TextButton level2ButtonH = new TextButton("Level 2 HARD", skin);
        level2ButtonH.setColor(0.2f, 0.8f, 0.2f, 1); // Set color to a visible one
        level2ButtonH.setPosition(690, 335);
        level2ButtonH.setSize(200, 50);
        level2ButtonH.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play(0.3f);
                int score1 = 0;
                game.setScreen(new GameScreenLevel2(game, "hard", score1));
            }
        });

        // Create Level 3 EASY button
        TextButton level3ButtonE = new TextButton("Level 3 EASY", skin);
        level3ButtonE.setColor(0.2f, 0.2f, 0.8f, 1); // Set color to a visible one
        level3ButtonE.setPosition(100, 235);
        level3ButtonE.setSize(200, 50);
        level3ButtonE.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play(0.3f);
                int score2 = 0;
                game.setScreen(new GameScreenLevel3(game,"easy", score2));
            }
        });

        // Create Level 3 MEDIUM button
        TextButton level3ButtonM = new TextButton("Level 3 MEDIUM", skin);
        level3ButtonM.setColor(0.2f, 0.2f, 0.8f, 1); // Set color to a visible one
        level3ButtonM.setPosition(SpellQuest.WIDTH/2 - level3ButtonM.getWidth()/2 - 40, 235);
        level3ButtonM.setSize(200, 50);
        level3ButtonM.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play(0.3f);
                int score2 = 0;
                game.setScreen(new GameScreenLevel3(game,"medium", score2));
            }
        });

        // Create Level 3 HARD button
        TextButton level3ButtonH = new TextButton("Level 3 HARD", skin);
        level3ButtonH.setColor(0.2f, 0.2f, 0.8f, 1); // Set color to a visible one
        level3ButtonH.setPosition(690, 235);
        level3ButtonH.setSize(200, 50);
        level3ButtonH.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play(0.3f);
                int score2 = 0;
                game.setScreen(new GameScreenLevel3(game,"hard", score2));
            }
        });

        // adds in the buttons
        stage.addActor(level1ButtonE);
        stage.addActor(level1ButtonM);
        stage.addActor(level1ButtonH);
        stage.addActor(level2ButtonE);
        stage.addActor(level2ButtonM);
        stage.addActor(level2ButtonH);
        stage.addActor(level3ButtonE);
        stage.addActor(level3ButtonM);
        stage.addActor(level3ButtonH);
        stage.addActor(backButton);
    }

    /**
     * Renders the DEBUG screen, including the background image,
     * title and interactive buttons.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        batch.begin();
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(batch, "DEBUG GAME", SpellQuest.WIDTH/2 - 190, SpellQuest.HEIGHT-100); // put the DEBUG SCREEN text onto the screen
        batch.end();

        stage.draw();
    }

    /**
     * Called when the application is destroyed. It is used to dispose of system resources.
     */
    @Override
    public void dispose() {
        batch.dispose();
        backgroundImage.dispose();
        skin.dispose();
        click.dispose();
        stage.dispose();
    }
}

