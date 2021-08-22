package com.arhibale.sprite.button;

import com.arhibale.base.BaseButton;
import com.arhibale.math.Rect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ExitButton extends BaseButton {

    public ExitButton(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.18f);
        setBottom(worldBounds.getBottom() + 0.25f);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
