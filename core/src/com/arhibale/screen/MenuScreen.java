package com.arhibale.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import com.arhibale.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;

    private Vector2 des;
    private Vector2 pos;
    private Vector2 v;

    @Override
    public void show() {
        super.show();
        img = new Texture("anime.png");
        des = new Vector2();
        pos = new Vector2();
        v = new Vector2();

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
        moving();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        setV(screenX, screenY, 10.0f);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        setV(screenX, screenY, 15.0f);
        return super.touchDragged(screenX, screenY, pointer);
    }

    private void setV(int screenX, int screenY, float speed) {
        des.set(screenX, Gdx.graphics.getHeight() - screenY);
        v.set(des.cpy().sub(pos)).setLength(speed);
    }

    private void moving() {
        if (des.dst(pos) >= 10.0f) {
            pos.add(v);
        } else {
            pos.set(des);
        }
    }
}