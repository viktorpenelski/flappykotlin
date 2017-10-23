package com.flappy.kotlin.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.flappy.kotlin.game.FlappyKotlinGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyKotlinGame.WIDTH;
		config.height = FlappyKotlinGame.HEIGHT;
		config.title = FlappyKotlinGame.TITLE;

		new LwjglApplication(new FlappyKotlinGame(), config);
	}
}
