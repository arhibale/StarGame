package com.arhibale;

import com.arhibale.screen.MenuScreen;
import com.badlogic.gdx.Game;

public class StarGame extends Game {
	
	@Override
	public void create () {
		setScreen(new MenuScreen());
	}

}
