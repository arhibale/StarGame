package com.arhibale;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class StarGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private Texture back;

	private int x = 0;
	private int y = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("anime.png");
		back = new Texture(Gdx.files.internal("background.jpg"));
	}

	@Override
	public void render () {
		temporaryAnimation();
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(back, 0, 0);
		batch.draw(img, x, y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		back.dispose();
	}

	private void temporaryAnimation() {
		if (y == 0 && (x + 281) != Gdx.graphics.getWidth()) {
			x++;
		} else if ((x + 281) == Gdx.graphics.getWidth() && (y + 281) != Gdx.graphics.getHeight()) {
			y++;
		} else if ((y + 281) == Gdx.graphics.getHeight() && x != 0) {
			x--;
		} else {
			y--;
		}
	}
}
