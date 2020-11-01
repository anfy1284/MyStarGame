package ru.anfy;

import com.badlogic.gdx.Game;

import ru.anfy.screen.MenuScreen;

public class StarGame extends Game {

	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}
}
