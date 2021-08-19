package com.arhibale.pool;

import com.arhibale.base.SpritePool;
import com.arhibale.sprite.Explosion;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ExplosionPool extends SpritePool<Explosion> {

    private final TextureAtlas atlas;
    protected final Sound explosionSound;

    public ExplosionPool(TextureAtlas atlas, Sound explosionSound) {
        this.atlas = atlas;
        this.explosionSound = explosionSound;
    }

    @Override
    protected Explosion newSprite() {
        return new Explosion(atlas, explosionSound);
    }
}
