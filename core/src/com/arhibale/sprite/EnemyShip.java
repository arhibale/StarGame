package com.arhibale.sprite;

import com.arhibale.base.Ship;
import com.arhibale.math.Rect;
import com.arhibale.pool.BulletPool;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class EnemyShip extends Ship {

    public EnemyShip(Rect worldBounds, BulletPool bulletPool) {
        super();
        this.worldBounds = worldBounds;
        this.bulletPool = bulletPool;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getTop() < worldBounds.getTop()) {
            v.set(v0);
        } else {
            shootTimer = shootInterval * 0.8f;
        }
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
        bulletPosition.set(pos.x, pos.y - getHalfHeight());
    }

    public void set(TextureRegion[] regions, Vector2 v0, TextureRegion bulletRegion, Vector2 bulletV,
                    float bulletHeight, int bulletDamage, Sound bulletSound, float shootInterval, float height, int hp) {
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletV.set(bulletV);
        this.bulletHeight = bulletHeight;
        this.bulletDamage = bulletDamage;
        this.bulletSound = bulletSound;
        this.shootInterval = shootInterval;
        setHeightProportion(height);
        this.hp = hp;
        v.set(0, -0.4f);
        bulletPosition.set(pos.x, pos.y + getHalfHeight());
    }
}