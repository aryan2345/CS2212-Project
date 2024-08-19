package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.SpellQuest;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument

/**
 * This class sets the configuration of the game, including the title, size and whether the game is resizable. It also starts
 * the game by instantializing the SpellQuest class (which in turn starts the game).
 *
 * @author Jack
 * @author Johnathan
 */
public class DesktopLauncher {
	/**
	 * Sets the configs of the game then starts the game.
	 *
	 * @param arg
	 */
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("SpellQuest"); // sets title to SpellQuest
		config.setResizable(false); // Does not allow user to resize the window
		config.setForegroundFPS(180); // Sets FPS to 180
		config.setWindowedMode(SpellQuest.WIDTH,SpellQuest.HEIGHT); // Sets size to the desired width and height provided by SpellQuest

		new Lwjgl3Application(new SpellQuest(), config); // Starts the game
	}
}