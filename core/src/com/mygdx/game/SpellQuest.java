package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.GL20;

/**
 * This class sets up the initial screen and size.
 *
 * @author Jack
 * @author Johnathan
 */
public class SpellQuest extends Game {
	/** Used for drawing and rendering */
	public SpriteBatch batch;
	/** Screen width */
	public static final int WIDTH = 1000;
	/** Screen height */
	public static final int HEIGHT = 700;

	/**
	 * Default constructor; no params needed.
	 */
	public SpellQuest () {}


	/**
	 * Sets the initial screen to main menu and creates a batch to be used in other screens.
	 *
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();

		this.setScreen(new MainMenu(this));
	}

	/**
	 * Renders the screen
	 */
	@Override
	public void render () {
		super.render();
	}
}
