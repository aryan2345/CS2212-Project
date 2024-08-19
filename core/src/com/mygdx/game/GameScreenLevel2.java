package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.StretchViewport;


import java.util.List;
import java.util.Random;

/**
 * GameScreenLevel2 represents the second level of the game.
 * It handles the rendering of the background, player sprite, enemy sprite,
 * the typing word mechanics, and the level's timer. The screen also tracks
 * if all words have been typed and if the game score has been printed.
 * The level ends when the timer runs out or when all words are typed correctly.
 *
 * @author Aryan Gupta
 * @author Parth Hasmukh Kathiria
 * @author Johnathan Lam
 * @author Jack Scott
 * @version 1.10
 */

public class GameScreenLevel2 extends ScreenAdapter
{
    private Game game;
    private Sound ding1;
    private Sound ding2;
    private Sound ding3;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Texture playerTexture;
    private Texture enemyTexture;
    private Sprite playerSprite;
    private Sprite enemySprite;
    private Stage stage;
    private TextField textField;
    private List<String> words;
    private String currentWord;
    private Skin skin;
    private BitmapFont font; // Added for displaying the current word
    private BitmapFont timerFont; // Font for displaying the timer
    private float timer; // Timer in seconds
    private float elapsedTime; // Elapsed time since starting the game
    private boolean typingStarted; // Flag to track if typing has started
    private boolean allWordsTyped; // Flag to track if all words have been typed
    private boolean scorePrinted; // Flag to check if the score has been printed

    // stores the store from the first level
    private int score1;
    private String difficulty; // Difficulty Easy, Medium, Hard
    private Skin skinPause;

    private int numTyped; // Number of letters player has currently typed - 1 (think about it as an array)

    // for animations
    private Animation<TextureRegion> wizIdleAnimation;
    private Texture wizIdleSheet;
    private Animation<TextureRegion> enemyIdleAnimation;
    private Texture enemyIdleSheet;
    private SpriteBatch spriteBatch;
    private float stateTime;
    private int count = 0;


    /**
     * Constructor for GameScreenLevel2. Initializes with the Game context
     * and the score from the previous level.
     *
     * @param game   The parent game object to manage screens.
     * @param score1 The score obtained from GameScreenLevel1.
     * @param difficulty The difficulty of the save.
     */
    public GameScreenLevel2(Game game, String difficulty, int score1)
    {
        this.game = game;
        this.difficulty = difficulty;
        this.score1 = score1;
        ding1 = Gdx.audio.newSound(Gdx.files.internal("data/ding1.mp3"));
        ding2 = Gdx.audio.newSound(Gdx.files.internal("data/ding2.mp3"));
        ding3 = Gdx.audio.newSound(Gdx.files.internal("data/ding3.mp3"));

        if (difficulty.equals("easy"))
        {
            timer = 50;
        }
        else if (difficulty.equals("medium"))
        {
            timer = 50;
        }
        else
        {
            timer = 60;
        }

        // load the spritesheets
        wizIdleSheet = new Texture(Gdx.files.internal("assets/IdleWiz.png"));
        enemyIdleSheet = new Texture(Gdx.files.internal("assets/banditAttack2.png"));

        // split the spritesheets
        TextureRegion[][] tmp1 = TextureRegion.split(wizIdleSheet, wizIdleSheet.getWidth() / 6, wizIdleSheet.getHeight());
        TextureRegion[][] tmp2 = TextureRegion.split(enemyIdleSheet, enemyIdleSheet.getWidth() / 20, enemyIdleSheet.getHeight());

        // make the texture region for wiz
        TextureRegion[] wizFrames = new TextureRegion[6];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 6; j++) {
                wizFrames[index++] = tmp1[i][j];
            }
        }

        // make the texture region for enemy
        TextureRegion[] banditFrames = new TextureRegion[20];
        index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 20; j++) {
                banditFrames[index++] = tmp2[i][j];
            }
        }
        // animations
        wizIdleAnimation = new Animation<TextureRegion>(0.14f, wizFrames);
        enemyIdleAnimation = new Animation<TextureRegion>(0.17f, banditFrames);
        spriteBatch = new SpriteBatch();
        stateTime = 0f;

    }


    /**
     * Define the WordList class as a static nested class
     */
    public static class WordList
    {
        /** Word list for typing challenge */
        public List<String> words;
    }

    /**
     * Called when this screen becomes the current screen for the game.
     * It initializes the stage, the batch, and loads the necessary assets and UI components.
     */
    @Override
    public void show()
    {
        if (MainMenu.music.isPlaying())
            MainMenu.music.stop();
        if (!GameScreenLevel1.music.isPlaying()) {
            GameScreenLevel1.music.setVolume(0.1f);
            GameScreenLevel1.music.play();
        }

        scorePrinted = false; // Initialize the flag
        batch = new SpriteBatch();
        typingStarted = false; // Initialize typingStarted flag

        // Load the background image
        backgroundTexture = new Texture("game.png");

        // Load player and enemy images as textures
        playerTexture = new Texture("wiz1.png");
        enemyTexture = new Texture("enemy-2.png");

        // Create sprites from textures
        playerSprite = new Sprite(playerTexture);
        enemySprite = new Sprite(enemyTexture);

        // Adjust the positions of the sprites.
        // Let's say you want to move them closer to the center by 1/4th the width of the screen from both sides
        float offset = Gdx.graphics.getWidth() / 4f;


        // Set the position of the player sprite closer to the center
        playerSprite.setPosition(offset - playerSprite.getWidth() / 4, Gdx.graphics.getHeight() / 2 - playerSprite.getHeight() / 3);

        // Set the position of the enemy sprite closer to the center
        enemySprite.setPosition(Gdx.graphics.getWidth() - offset - enemySprite.getWidth() / 1, Gdx.graphics.getHeight() / 2 - enemySprite.getHeight() / 3);

        // Set up the stage with a StretchViewport for responsive scaling
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        // Modify the skin's font to increase the size
        font = skin.getFont("default-font");
        font.getData().setScale(3); // Scale up the font size. Adjust as necessary.

        timerFont = new BitmapFont();
        timerFont.getData().setScale(2); // Scale up the font size for the timer

        textField = new TextField("", skin);
        textField.setAlignment(1);
        textField.setPosition(10, 50);
        textField.setSize(Gdx.graphics.getWidth() - 20, 150);
        textField.getStyle().background.setLeftWidth(10);
        textField.getStyle().background.setRightWidth(10);
        textField.setTextFieldListener(new TextField.TextFieldListener()
        {
            @Override
            public void keyTyped(TextField textField, char c)
            {
                if (!typingStarted) {
                    typingStarted = true; // Set typingStarted flag to true when typing begins

                    // Changes textfield color based on if the char was correct
                    if ((Character.toLowerCase(textField.getText().charAt(0)) == Character.toLowerCase(currentWord.charAt(0)))) {
                        // if they're the same set the text field to red
                        textField.setColor(Color.GREEN);
                    } else {
                        // otherwise set it to red
                        textField.setColor(Color.RED);
                    }
                    numTyped++;
                } else {
                    // if character typed is backspace and numtyped is greater than 0 (prevents neg), lower numtyped by 1
                    if (c == '\b') { if (numTyped > 0) {numTyped--;}
                    } else {
                        // if character was not backspace, check if the number of letters typed is longer than the word to type
                        if (numTyped >= currentWord.length()) {
                            // if so make the textfield red
                            textField.setColor(Color.RED);
                        } else {
                            // otherwise check if the character that the user just typed is equal to the corresponding letter in the word they're typing
                            for (int i = 0; i < numTyped+1; i++) {
                                if ((Character.toLowerCase(textField.getText().charAt(i)) == Character.toLowerCase(currentWord.charAt(i)))) {
                                    // if they're the same set the text field to red
                                    textField.setColor(Color.GREEN);
                                } else {
                                    // otherwise set it to red
                                    textField.setColor(Color.RED);
                                    break;
                                }
                            }
                        }
                        // if the char entered wasn't backspace increase num typed by 1
                        numTyped++;
                    }
                }
            }
        });
        stage.addActor(textField);
        allWordsTyped = false; // Initialize the flag

        skinPause= new Skin(Gdx.files.internal("uiskin.json"));
        TextButton pauseButton = new TextButton("Pause", skinPause);
        // Calculate button positions for centering with spacing
        float buttonWidth = 150f;
        float buttonHeight = 50f;
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float startX = (screenWidth - buttonWidth) / 2f;
        float startY = (screenHeight - (buttonHeight * 5f + 20 * 4)) / 1.3f; // Adjust spacing (20 pixels in this example)

        pauseButton.setBounds(startX+400, startY + (buttonHeight + 20) * 5f, buttonWidth, buttonHeight);

        // Set the color of the pause button to dark blue
        pauseButton.setColor(0.5f,1,0.3f,1);

        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameScreenLevel1.music.pause();
                count--;
                game.setScreen(new PauseScreen(game, GameScreenLevel2.this));
            }
        });


        stage.addActor(pauseButton);


        loadWords();
        pickNextWord();
    }


    /**
     * Loads words from a JSON file and initializes the word list.
     */
    private void loadWords()
    {
        Json json = new Json();
        try {
            String jsonText = "";
            // Change the file name to "level1.json"
            if (difficulty.equals("easy"))
            {
                jsonText = Gdx.files.internal("level2EASY.json").readString();
            }
            else if (difficulty.equals("medium"))
            {
                jsonText = Gdx.files.internal("level2MEDIUM.json").readString();
            }
            else
            {
                jsonText = Gdx.files.internal("level2HARD.json").readString();
            }
            // Parse the JSON into the static WordList class
            WordList wordList = json.fromJson(WordList.class, jsonText);

            // Extract the list of words
            words = wordList.words;

            // Check if the words list is null or empty
            if (words == null)
            {
                throw new IllegalStateException("The 'words' property is missing or is not an array.");
            }

            //System.out.println("Loaded words: " + words);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error loading words. Make sure level1.json exists and is properly formatted.");
        }
    }

    /**
     * Selects the next word from the list for the user to type.
     */

    private void pickNextWord()
    {

        if (words != null && !words.isEmpty())
        {

            if (count >= 0 && count < words.size())
            {
                currentWord = words.get(count);

            }
            count++;
            //System.out.println("Type this word: " + currentWord);
        } else {
            //System.out.println("No more words to type or word list not loaded.");
            allWordsTyped = true; // Set the flag when all words are typed
        }
    }

    /**
     * Called every frame to update the screen elements and game logic.
     *
     * @param delta Time since last frame was rendered.
     */
    @Override
    public void render(float delta)
    {
        // Check if the Escape key is pressed
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            GameScreenLevel1.music.pause();
            count--;
            game.setScreen(new PauseScreen(game, GameScreenLevel2.this));
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        // Draw the background
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Draw the "LEVEL 1" title at the top
        font.setColor(0.8f, 1, 0.2f, 1); // Set color to a visible one
        font.getData().setScale(4); // Increase the scale for the title
        GlyphLayout titleLayout = new GlyphLayout(font, "LEVEL 2");
        float titleX = (Gdx.graphics.getWidth() - titleLayout.width) / 2;
        float titleY = Gdx.graphics.getHeight() - titleLayout.height +20; // Position it with some padding from the top
        font.draw(batch, titleLayout, titleX, titleY);



        // Display the current word to type
        String typeWordText = "TYPE: " + (currentWord != null ? currentWord : "");
        font.setColor(0.3f, 1, 0.2f, 1); // Set color to a visible one
        font.getData().setScale(3); // Reset the scale for other text
        font.draw(batch, typeWordText, 340, Gdx.graphics.getHeight() - 440);

        // Display the timer
        if (typingStarted && !allWordsTyped)
        {
            elapsedTime += Gdx.graphics.getDeltaTime();
            if (elapsedTime >= timer)
            {
                elapsedTime = timer; // Stop the timer if it reaches the limit
                allWordsTyped = true; // Mark as completed if the timer runs out
                GameScreenLevel1.music.stop();
                game.setScreen(new FailedScreen((SpellQuest) game, difficulty,2, score1));
            }
        }
        String timerText = "TIME LEFT: " + (int) (timer - elapsedTime);
        timerFont.setColor(.8f, 0, 0, 1); // Red color for the timer
        timerFont.draw(batch, timerText, Gdx.graphics.getWidth() - 600, Gdx.graphics.getHeight() - 120);

        batch.end();

        // draw the animations here
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        TextureRegion currentFrame1 = wizIdleAnimation.getKeyFrame(stateTime, true);
        TextureRegion currentFrame2 = enemyIdleAnimation.getKeyFrame(stateTime, true);
        float offset = Gdx.graphics.getWidth() / 24f;
        spriteBatch.begin();
        spriteBatch.draw(currentFrame1, offset - playerSprite.getWidth() / 4 - 60, Gdx.graphics.getHeight() / 2 - playerSprite.getHeight() / 3 +10);
        spriteBatch.draw(currentFrame2, offset + enemySprite.getWidth() + 370, Gdx.graphics.getHeight() / 2 - enemySprite.getHeight() / 3 - 20);
        spriteBatch.end();

        // if the user holds CTRL + BACKSPACE, it will reset the text input
        if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE) && (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)))
        {
            textField.setText("");
        }

        if (currentWord != null && textField.getText().equalsIgnoreCase(currentWord))
        {
            Random rand = new Random();
            int randDing = rand.nextInt(3);
            switch (randDing) {
                case 0:
                    ding1.play(0.2f);
                    break;
                case 1:
                    ding2.play(1.0f);
                    break;
                case 2:
                    ding3.play(0.1f);
                    break;
            }

            textField.setColor(Color.LIGHT_GRAY); // reset textfield to default when new word starting
            numTyped = 0; // reset number of words to 0 when new word starting


            pickNextWord();
            textField.setText("");
            if (count == words.size()+1)
            {

                allWordsTyped = true;
            }
        }

        if (!allWordsTyped)
        {
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        }
        else if (!scorePrinted)
        {
            int score2 = getScore2();
            //System.out.println(score2);
            scorePrinted = true; // Set the flag so it doesn't print again

            // If the score is greater than 0, switch to GameScreenLevel2
            if ((score2-score1) > 0)
            {
                GameScreenLevel1.music.stop();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new ResultsScreen(game,difficulty, getCurrScore(),getScore2(),3));
            }
        }
    }

    /**
     * Calculates the cumulative score for this level, including the score from
     * GameScreenLevel1 and the remaining time for typing the words in this level.
     *
     * @return The cumulative score for levels 1 and 2.
     */
    public int getScore2()
    {
        if (difficulty.equals("easy"))
            return (int)(timer - elapsedTime) * 1000 + score1;
        else if (difficulty.equals("medium"))
            return (int)(timer - elapsedTime) * 2000+ score1;
        else
            return (int)(timer - elapsedTime) * 3000 + score1;

    }

    /**
     * Calculates the current score based on the remaining time and the difficulty.
     *
     * @return The calculated score.
     */
    public int getCurrScore()
    {
        if (difficulty.equals("easy"))
            return (int)(timer - elapsedTime) * 1000;
        else if (difficulty.equals("medium"))
            return (int)(timer - elapsedTime) * 1500;
        else
            return (int)(timer - elapsedTime) * 2500;

    }



    /**
     * Called to dispose any resources allocated by the screen.
     */
    @Override
    public void dispose()
    {
        enemyIdleSheet.dispose();
        wizIdleSheet.dispose();
        spriteBatch.dispose();
        ding1.dispose();
        ding2.dispose();
        ding3.dispose();
        GameScreenLevel1.music.dispose();
        batch.dispose();
        backgroundTexture.dispose();
        playerTexture.dispose();
        enemyTexture.dispose();
        if (skin != null)
        {
            skin.dispose();
        }
        stage.dispose();
        timerFont.dispose();
    }

}






