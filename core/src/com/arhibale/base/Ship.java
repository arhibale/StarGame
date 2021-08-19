package com.arhibale.base;

import com.arhibale.math.Rect;
import com.arhibale.pool.BulletPool;
import com.arhibale.sprite.Bullet;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Ship extends Sprite {

    protected final Vector2 v0;
    protected final Vector2 v;

    protected Rect worldBounds;

    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletPosition;
    protected Vector2 bulletV;
    protected float bulletHeight;
    protected int bulletDamage;
    protected Sound bulletSound;
    protected float shootInterval;
    protected float shootTimer;
    protected int hp;

    public Ship() {
        v0 = new Vector2();
        v = new Vector2();
        bulletPosition = new Vector2();
        bulletV = new Vector2();
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
        v0 = new Vector2();
        v = new Vector2();
        bulletPosition = new Vector2();
        bulletV = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        shootTimer += delta;
        if (shootTimer >= shootInterval) {
            shootTimer = 0f;
            shoot();
        }
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPosition, bulletV, bulletHeight, worldBounds, bulletDamage);
        bulletSound.play();
    }
}
