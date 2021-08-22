package com.arhibale.sprite.button;

import com.arhibale.base.BaseButton;
import com.arhibale.math.Rect;
import com.arhibale.screen.GameScreen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class NewGameButton extends BaseButton {

    GameScreen gameScreen;

    public NewGameButton(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.05f);
        setBottom(worldBounds.getBottom() + 0.45f);
    }

    @Override
    public void action() {
        gameScreen.startNewGame();
    }
}
