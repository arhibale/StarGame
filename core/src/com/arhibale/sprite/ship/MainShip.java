package com.arhibale.sprite.ship;

import com.arhibale.base.Ship;
import com.arhibale.math.Rect;
import com.arhibale.pool.BulletPool;
import com.arhibale.pool.ExplosionPool;
import com.arhibale.sprite.Bullet;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class MainShip extends Ship {

    private static final float HEIGHT = 0.15f;
    private static final float BOTTOM_MARGIN = 0.05f;
    private static final int INVALID_POINTER = -1;
    private static final int DEFAULT_HP = 100;

    private boolean pressedLeft;
    private boolean pressedRight;

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool, ExplosionPool explosionPool, Sound bulletSound) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.bulletSound = bulletSound;
        bulletRegion = atlas.findRegion("bulletMainShip");
        bulletV.set(0, 0.5f);
        bulletHeight = 0.01f;
        bulletDamage = 1;
        shootInterval = 0.2f;
        v0.set(0.5f, 0);
        hp = DEFAULT_HP;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
    }

    public void startNewGame() {
        hp = DEFAULT_HP;
        pressedLeft = false;
        pressedRight = false;
        leftPointer = INVALID_POINTER;
        rightPointer = INVALID_POINTER;
        stop();
        this.pos.x = worldBounds.pos.x;
        flushDestroy();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
        bulletPosition.set(pos.x, pos.y + getHalfHeight());
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else {
                stop();
            }
        }
        return false;
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
        }
        return false;
    }

    @Override
    public boolean isBulletCollision(Bullet bullet) {
        return !(
                bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > pos.y
                || bullet.getTop() < getBottom()
        );
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotateDeg(180);
    }

    private void stop() {
        v.setZero();
    }
}