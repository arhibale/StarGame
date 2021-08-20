package com.arhibale.sprite;

import com.arhibale.base.Sprite;
import com.arhibale.math.Rect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GameOver extends Sprite {

    public GameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.08f);
        setBottom(worldBounds.getBottom() + 0.65f);
    }
}
