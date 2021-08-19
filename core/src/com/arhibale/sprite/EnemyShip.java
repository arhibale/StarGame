package com.arhibale.sprite;

import com.arhibale.base.Ship;
import com.arhibale.math.Rect;
import com.arhibale.pool.BulletPool;
import com.arhibale.pool.ExplosionPool;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class EnemyShip extends Ship {

    public EnemyShip(Rect worldBounds, BulletPool bulletPool, ExplosionPool explosionPool) {
        super();
        this.worldBounds = worldBounds;
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        bulletPosition.set(pos.x, pos.y - getHalfHeight());
        if (getTop() < worldBounds.getTop()) {
            v.set(v0);
        } else {
            shootTimer = shootInterval * 0.8f;
        }
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
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
    }

    public void  setPos(float x, float y) {
        pos.set(x, y);
        bulletPosition.set(pos.x, pos.y - getHalfHeight());
    }

    @Override
    public boolean isBulletCollision(Bullet bullet) {
        return !(
                bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y
                );
    }

    @Override
    public void destroy() {
        super.destroy();
        shootTimer = 0f;
    }
}