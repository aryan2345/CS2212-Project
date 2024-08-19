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
 * MainMenu screen of the game.
 * This class is responsible for rendering the main menu screen with various buttons like
 * 'New Game', 'Load Game', 'Leaderboard', 'Tutorial', and 'Exit'.
 * Each button performs its own action, such as starting a new game, loading an existing game,
 * showing the leaderboard, starting a tutorial, or exiting the game.
 *
 * The screen includes a title and a footer with the author credits and game information.
 * This screen is used as the first interaction point for users when they launch the game.
 *
 * @author Aryan Gupta
 * @author Parth Hasmukh Kathiria
 * @version Fall 2024
 */
public class MainMenu extends ScreenAdapter {
	private SpriteBatch batch;
	private Sound click;
	/** The background music used for the menus */
	public static Music music = Gdx.audio.newMusic (Gdx.files.internal("data/8bitSurf.mp3"));
	private Texture backgroundImage;
	private Stage stage;
	private Skin skin;
	private BitmapFont font;
	private BitmapFont footerFont; // Font for footer text

	private Game game; // Add a Game field

	// Constructor that takes the Game object

	/**
	 * Constructor for the MainMenu screen.
	 * @param game The main game object that allows for screen transitions.
	 */
	public MainMenu(Game game)
	{
		this.game = game;
		music.setVolume(0.15f);
		music.setLooping(true);
		click = Gdx.audio.newSound(Gdx.files.internal("data/click.wav"));
	}



	/**
	 * Called when this screen becomes the current screen for the game.
	 * It initializes the stage, fonts, and buttons, and sets up event listeners.
	 */
	@Override
	public void show() {
		batch = new SpriteBatch();
		backgroundImage = new Texture("wiz.jpg");
		// Initialize the font
		font = new BitmapFont();
		font.setColor(Color.WHITE); // Set the color to white
		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		font.getData().setScale(4); // Set the scale of the font. Adjust as necessary

		// Initialize the footer font
		footerFont = new BitmapFont();
		footerFont.setColor(Color.WHITE); // Set the color to white
		footerFont.getData().setScale(1); // Smaller scale for footer text

		// Set up the stage and skin for buttons
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("uiskin.json"));

		TextButton smallButton = new TextButton("", skin); // Empty label for a small button
		smallButton.setColor(135f / 255f, 206f / 255f, 235f / 255f, 0.2f); // Light blue color (RGB values with alpha)
		float buttonSize = 30f; // Set the size of the small button
		float margin = 10f; // Set margin from top-right corner
		float buttonX = Gdx.graphics.getWidth() - buttonSize - margin; // Calculate X position
		float buttonY = Gdx.graphics.getHeight() - buttonSize - margin; // Calculate Y position
		smallButton.setBounds(buttonX, buttonY, buttonSize, buttonSize); // Set button position and size
		stage.addActor(smallButton); // Add the small button to the stage
		// Create buttons
		TextButton startButton = new TextButton("New Game", skin);
		TextButton loadButton = new TextButton("Load Game", skin);
		TextButton leaderboardButton = new TextButton("Leaderboard", skin);
		TextButton tutorialButton = new TextButton("Tutorial", skin);
		TextButton exitButton = new TextButton("Exit", skin);
		// Set up exit button
		startButton.addListener (new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				click.play(0.3f);
				game.setScreen(new NewGameScreen((SpellQuest) game));
			}
		});
		smallButton.addListener (new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				click.play(0.3f);
				game.setScreen(new DebugMode((SpellQuest) game));
			}
		});
		loadButton.addListener (new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				click.play(0.3f);
				game.setScreen(new LoadGameScreen((SpellQuest) game));
			}
		});
		exitButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				click.play(0.3f);
				Gdx.app.exit();
			}
		});
		leaderboardButton.addListener (new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				click.play(0.3f);
				game.setScreen(new LeaderboardScreen((SpellQuest) game));
			}
		});
		tutorialButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				click.play(0.3f);
				game.setScreen(new TutorialScreen(game));
			}
		});

		// instructor dashboard
		TextButton instructorDashboardButton = new TextButton("Instructor Dashboard", skin);
		instructorDashboardButton.setColor(Color.ORANGE);
		instructorDashboardButton.setSize(200f, 50f);
		instructorDashboardButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new InstructorDashboard((SpellQuest) game));
			}
		});
		stage.addActor(instructorDashboardButton);


		// Set button colors
		startButton.setColor(1, 0, 0, 1); // Red
		loadButton.setColor(0, 1, 0, 1); // Green
		leaderboardButton.setColor(0, 0, 1, 1); // Blue
		tutorialButton.setColor(1, 1, 0, 1); // Yellow
		exitButton.setColor(1, 0, 1, 1); // Magenta


		// Calculate button positions for centering with spacing
		float buttonWidth = 200f;
		float buttonHeight = 50f;
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float startX = (screenWidth - buttonWidth) / 2f;
		float startY = ((screenHeight - (buttonHeight * 5f + 20 * 4)) / 2f) + 30; // Adjust spacing (20 pixels in this example)

		// Set button positions and sizes
		startButton.setBounds(startX, startY + (buttonHeight + 20) * 4f, buttonWidth, buttonHeight);
		loadButton.setBounds(startX, startY + (buttonHeight + 20) * 3f, buttonWidth, buttonHeight);
		leaderboardButton.setBounds(startX, startY + (buttonHeight + 20) * 2f, buttonWidth, buttonHeight);
		tutorialButton.setBounds(startX, startY + (buttonHeight + 20) * 1f, buttonWidth, buttonHeight);
		exitButton.setBounds(startX, startY - (buttonHeight + 20) * 1f, buttonWidth, buttonHeight);
		instructorDashboardButton.setBounds(startX, startY, buttonWidth, buttonHeight);

		// Add buttons to the stage
		stage.addActor(startButton);
		stage.addActor(loadButton);
		stage.addActor(leaderboardButton);
		stage.addActor(tutorialButton);
		stage.addActor(exitButton);
	}

	/**
	 * Renders the main menu screen, including the background image,
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
		String title = "SPELL QUEST";
		// Calculate the width of the title to center it
		float titleWidth = font.getRegion().getRegionWidth();
		float titleX = (Gdx.graphics.getWidth() - titleWidth) / 2.4f;
		// Set the y position where you want to draw the title. Adjust the value as needed
		float titleY = Gdx.graphics.getHeight() - 40f;
		font.draw(batch, title, titleX, titleY);

		// Draw the footer text
		String footerText = "  ARYAN GUPTA, PARTH HASMUKH KATHIRIA, JOHNATHAN TZE-HIN LAM, JACK SAMSON SCOTT, RAED LNU\n DEVELOPED AS PART OF TEAM 44, CREATED IN FALL 2024, CREATED AS PART OF CS2212 AT WESTERN UNIVERSITY";
		// Center the footer text horizontally
		float footerTextWidth = footerFont.getRegion().getRegionWidth();
		float footerTextX = (Gdx.graphics.getWidth() - footerTextWidth) / 15f;
		// Set the y position to draw the footer text at the bottom of the screen
		float footerTextY = 40f; // Adjust as needed to position above the bottom edge
		footerFont.draw(batch, footerText, footerTextX, footerTextY);


		batch.end();

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	/**
	 * Called when the application is destroyed. It is used to dispose of system resources.
	 */
	@Override
	public void dispose() {
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
