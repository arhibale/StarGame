package com.arhibale.sprite;

import com.arhibale.base.BaseButton;
import com.arhibale.math.Rect;
import com.arhibale.pool.BulletPool;
import com.arhibale.pool.EnemyPool;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class NewGameButton extends BaseButton {

    private final MainShip ship;
    private final EnemyPool enemyPool;
    private final BulletPool bulletPool;

    public NewGameButton(TextureAtlas atlas, MainShip ship, EnemyPool enemyPool, BulletPool bulletPool) {
        super(atlas.findRegion("button_new_game"));
        this.ship = ship;
        this.enemyPool = enemyPool;
        this.bulletPool = bulletPool;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.06f);
        setBottom(worldBounds.getBottom() + 0.45f);
    }

    @Override
    public void action() {
        if (ship.isDestroyed()) {
            bulletPool.dispose();
            enemyPool.dispose();
            ship.resetHp();
            ship.flushDestroy();
        }
    }
}
