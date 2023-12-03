package com.luna.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.luna.game.Screens.ProjectLuna;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		//config.setWindowSizeLimits(800, 480, 800, 480);
		config.setWindowedMode(720, 480);
		config.setForegroundFPS(60);
		config.setTitle("Project Luna");
		new Lwjgl3Application(new ProjectLuna(), config);
	}
}
