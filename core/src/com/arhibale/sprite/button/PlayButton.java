package com.arhibale.sprite.button;

import com.arhibale.base.BaseButton;
import com.arhibale.math.Rect;
import com.arhibale.screen.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class PlayButton extends BaseButton {

    private final Game game;

    public PlayButton(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btPlay"));
        this.game = game;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.23f);
        setBottom(worldBounds.getBottom() + 0.55f);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen());
    }
}
