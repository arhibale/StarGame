package com.arhibale.pool;

import com.arhibale.base.SpritePool;
import com.arhibale.math.Rect;
import com.arhibale.sprite.EnemyShip;

public class EnemyPool extends SpritePool<EnemyShip> {

    private final Rect worldBounds;
    private final BulletPool bulletPool;

    public EnemyPool(Rect worldBounds, BulletPool bulletPool) {
        this.worldBounds = worldBounds;
        this.bulletPool = bulletPool;
    }

    @Override
    protected EnemyShip newSprite() {
        return new EnemyShip(worldBounds, bulletPool);
    }
}
