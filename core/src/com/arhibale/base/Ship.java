package com.arhibale.base;

import com.arhibale.math.Rect;
import com.arhibale.pool.BulletPool;
import com.arhibale.pool.ExplosionPool;
import com.arhibale.sprite.Bullet;
import com.arhibale.sprite.Explosion;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Ship extends Sprite {

    private static final float DAMAGE_ANIMATE_INTERVAL = 0.1f;

    protected final Vector2 v0;
    protected final Vector2 v;

    protected Rect worldBounds;
    protected BulletPool bulletPool;
    protected ExplosionPool explosionPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletPosition;
    protected Vector2 bulletV;
    protected float bulletHeight;
    protected int bulletDamage;
    protected Sound bulletSound;
    protected int hp;

    protected float shootInterval;
    protected float shootTimer;

    private float damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;

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
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= DAMAGE_ANIMATE_INTERVAL) {
            frame = 0;
        }
    }

    public void damage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            destroy();
        }
        frame = 1;
        damageAnimateTimer = 0f;
    }

    public abstract boolean isBulletCollision(Bullet bullet);

    public int getBulletDamage() {
        return bulletDamage;
    }

    @Override
    public void destroy() {
        super.destroy();
        boom();
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPosition, bulletV, bulletHeight, worldBounds, bulletDamage);
        bulletSound.play(0.5f);
    }

    private void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(pos, getHeight());
    }

    public int getHp() {
        return hp;
    }
}
