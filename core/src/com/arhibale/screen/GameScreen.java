package com.arhibale.screen;

import com.arhibale.base.BaseScreen;
import com.arhibale.math.Rect;
import com.arhibale.pool.BulletPool;
import com.arhibale.pool.EnemyPool;
import com.arhibale.sprite.Background;
import com.arhibale.sprite.Bullet;
import com.arhibale.sprite.EnemyShip;
import com.arhibale.sprite.MainShip;
import com.arhibale.sprite.Star;
import com.arhibale.utils.EnemyEmitter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;

    private Texture bg;
    private Background background;

    private TextureAtlas atlas;

    private Star[] stars;
    private BulletPool bulletPool;
    private EnemyPool enemyPool;
    private MainShip mainShip;

    private Sound bulletSound;
    private Sound laserSound;
    private Music music;

    private EnemyEmitter enemyEmitter;

    @Override
    public void show() {
        super.show();

        bg = new Texture("textures/bg.png");
        background = new Background(bg);

        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        enemyPool = new EnemyPool(worldBounds, bulletPool);

        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));

        mainShip = new MainShip(atlas, bulletPool, laserSound);
        enemyEmitter = new EnemyEmitter(worldBounds, bulletSound, enemyPool, atlas);

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        freeAllDestroyed();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        enemyPool.dispose();
        laserSound.dispose();
        bulletSound.dispose();
        music.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        mainShip.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        mainShip.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        mainShip.update(delta);
        bulletPool.updateActiveSprite(delta);
        enemyPool.updateActiveSprite(delta);
        enemyEmitter.generate(delta);
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveSprite(batch);
        enemyPool.drawActiveSprite(batch);
        batch.end();
    }

    private void checkCollisions() {
        List<EnemyShip> enemyShipList = enemyPool.getActiveSprites();
        List<Bullet> bullets = bulletPool.getActiveSprites();
        for (EnemyShip enemyShip : enemyShipList) {
            if (!mainShip.isOutside(enemyShip)) {
                enemyShip.destroy();
            }
            for (Bullet bullet : bullets) {
                if (!enemyShip.isOutside(bullet)) {
                    if (mainShip.equals(bullet.getOwner())) {
                        enemyShip.destroy();
                    }
                }
            }
        }
    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyedActiveSprite();
        enemyPool.freeAllDestroyedActiveSprite();
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    @Override
    public void pause() {
        music.pause();
    }

    @Override
    public void resume() {
        music.play();
    }
}
