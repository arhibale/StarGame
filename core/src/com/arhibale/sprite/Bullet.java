package com.arhibale.sprite;

import com.arhibale.base.Sprite;
import com.arhibale.math.Rect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Sprite {

    private Rect worldBounds;
    private final Vector2 v;
    private int damage;
    private Sprite owner;

    private final Sound sound;

    public Bullet() {
        regions = new TextureRegion[1];
        v = new Vector2();
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
    }

    public void set(Sprite owner, TextureRegion region, Vector2 pos0, Vector2 v0, float height, Rect worldBounds, int damage) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(pos0);
        this.v.set(v0);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.damage = damage;
    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public Sprite getOwner() {
        return owner;
    }

    public void playSound() {
        sound.play(0.05f);
    }
}
