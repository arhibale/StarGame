package com.arhibale.sprite;

import com.arhibale.base.Sprite;
import com.arhibale.math.Rect;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class MainShip extends Sprite {

    private final float HEIGHT = 0.10f;
    private final Vector2 M;
    private final Vector2 V;

    public MainShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"), 190);
        M = new Vector2(1.0f, 0);
        V = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + HEIGHT);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(V, delta);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return super.touchUp(touch, pointer, button);
    }

    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            moveLeft();
        } else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            moveRight();
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        if (
                keycode == Input.Keys.A ||
                keycode == Input.Keys.LEFT ||
                keycode == Input.Keys.D ||
                keycode == Input.Keys.RIGHT) {
            stop();
        }
        return false;
    }

    public void moveRight() {
        V.set(M);
    }

    public void moveLeft() {
        V.set(M).rotateDeg(180);
    }

    public void stop() {
        V.setZero();
    }
}