package com.arhibale;

import com.badlogic.gdx.Game;

import com.arhibale.screen.MenuScreen;

public class StarGame extends Game {
	
	@Override
	public void create () {
		setScreen(new MenuScreen());
	}

}
