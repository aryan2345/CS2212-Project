package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


/**
 * This creates the screen used for displaying the results after every level. It displays the total score and the
 * score from the current level. It will display 1 - 2 buttons depending on what level you're onL one for main menu and
 * the other for going to the next level.
 *
 * @author Johnathan
 * @author Jack Scott
 * @version 1.0
 */
public class ResultsScreen extends ScreenAdapter {
    private SpriteBatch batch;
    private Music music;
    private static final int BACK_BUTTON_WIDTH = 281;
    private static final int BACK_BUTTON_HEIGHT = 70;
    private static final int BACK_BUTTON_Y = 100;
    private Sound click;
    private Texture backgroundImage;
    private Stage stage;
    private Skin skin;
    private BitmapFont font;
    private BitmapFont footerFont;
    private BitmapFont saveFont;
    private Game game;
    private int score;
    private int totalScore;
    private int nextLevel;
    private String difficulty; // Difficulty Easy: 1, Medium: 2, Hard: 3

    /**
     * This is the constructor for the Results Screen.
     *
     * @param game          References to the SpellQuest class for changing classes.
     * @param difficulty    The difficulty of the save.
     * @param score         The score taken from this level
     * @param totalScore    The total score accumulated
     * @param nextLevel     The level that is after the current
     */
    public ResultsScreen(Game game, String difficulty, int score, int totalScore, int nextLevel)
    {
        this.game = game;
        this.difficulty = difficulty;
        this.score = score;
        this.totalScore = totalScore;
        this.nextLevel = nextLevel;

        JSONHandeler jsonHandeler = new JSONHandeler();

        //TODO Change level if more levels added
        jsonHandeler.saveGame(new UsernameStorage().getUsername(), new Save(difficulty, nextLevel - 1, totalScore));

        click = Gdx.audio.newSound(Gdx.files.internal("data/click.wav")); // for button click
        backgroundImage = new Texture("results.jpg"); // initializes background image
        music = Gdx.audio.newMusic (Gdx.files.internal("data/results.mp3")); // background music for the gameplay screens
        music.setVolume(0.03f);
    }



    /**
     * Called when this screen becomes the current screen for the game.
     * It initializes the stage, fonts, and buttons, and sets up event listeners.
     */
    @Override
    public void show() {

        batch = new SpriteBatch();

        // big font for title (RESULTS)
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(4);

        // Initialize the footer font
        footerFont = new BitmapFont();
        footerFont.setColor(Color.WHITE); // Set the color to white
        footerFont.getData().setScale(3); // Smaller scale for footer text

        // Initialize the Save font
        saveFont = new BitmapFont();
        saveFont.setColor(Color.WHITE); // Set the color to white
        saveFont.getData().setScale(2); // Smaller scale for save text

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // create back button with size and position
        int xBackButton = SpellQuest.WIDTH / 2 - BACK_BUTTON_WIDTH / 2;
        TextButton backButton = new TextButton ("MAIN MENU", skin);
        backButton.setSize (BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
        backButton.setPosition(xBackButton, BACK_BUTTON_Y);
        backButton.setColor(new Color(0.5f,0.3f,0.7f,1)); // Set the button's color to purple


        // add a listener for back button. Sends user back to main menu when clicked.
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play(0.3f);
                music.stop();
                game.setScreen(new MainMenu(game));
            }
        });

        // adds a listener for the next level button. Only exists when there exists a next level.
        if (nextLevel == 2)
        {
            // create back button with size and position
            int xNextButton = SpellQuest.WIDTH / 2 - BACK_BUTTON_WIDTH / 2;
            TextButton nextButton = new TextButton ("NEXT LEVEL", skin);
            nextButton.setSize (BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
            nextButton.setPosition(xBackButton, BACK_BUTTON_Y + 100);
            nextButton.setColor(new Color(0.5f,0.3f,0.7f,1)); // Set the button's color to purple
            nextButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    click.play(0.3f);
                    music.stop();
                    game.setScreen(new GameScreenLevel2(game, difficulty, totalScore));
                }
            });

            stage.addActor(nextButton);
        }
        else if (nextLevel == 3){
            // create back button with size and position
            int xNextButton = SpellQuest.WIDTH / 2 - BACK_BUTTON_WIDTH / 2;
            TextButton nextButton = new TextButton ("NEXT LEVEL", skin);
            nextButton.setSize (BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
            nextButton.setPosition(xBackButton, BACK_BUTTON_Y + 100);
            nextButton.setColor(new Color(0.5f,0.3f,0.7f,1)); // Set the button's color to yellow
            nextButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    click.play(0.3f);
                    music.stop();
                    game.setScreen(new GameScreenLevel3(game, difficulty, totalScore));
                }
            });

            stage.addActor(nextButton);
        }


        // Adds these inputs into the screen
        stage.addActor (backButton);
    }

    /**
     * Renders the RESULTS screen, including the background image,
     * title, footer, and interactive buttons.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        music.play();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        // Draw the background image
        batch.draw(backgroundImage, 0, 0);

        // Draw the title
        String title = "RESULTS";
        // Calculate the width of the title to center it
        float titleWidth = font.getRegion().getRegionWidth();
        float titleX = SpellQuest.WIDTH / 2 - titleWidth /2 - 5;
        // Set the y position where you want to draw the title. Adjust the value as needed
        float titleY = Gdx.graphics.getHeight() - 40f;
        font.draw(batch, title, titleX, titleY);


        // Draw the footer text for SCORE
        String footerText = "SCORE: " + score;
        // Center the footer text horizontally
        float footerTextX = SpellQuest.WIDTH / 2 - 400;
        // Sets the y position to be near the center
        float footerTextY = 400;
        footerFont.draw(batch, footerText, footerTextX, footerTextY);

        // Draw the footer text for TOTAL SCORE
        footerText = "TOTAL: " + totalScore;
        // Center the footer text horizontally
        footerTextX = SpellQuest.WIDTH / 2 + 60;
        // Sets the y position to be near the center
        footerTextY = 400; // Adjust as needed to position above the bottom edge
        footerFont.draw(batch, footerText, footerTextX, footerTextY);

        // draw the text that tells the user progress has been saved.
        saveFont.draw (batch, "Progress saved", 760, 50);


        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    /**
     * Called when the application is destroyed. It is used to dispose of system resources.
     */
    @Override
    public void dispose() {
        music.dispose();
        click.dispose();
        music.dispose();
        batch.dispose();
        backgroundImage.dispose();
        font.dispose(); // Dispose of the font
        stage.dispose();
        footerFont.dispose();
        skin.dispose();

    }
}
