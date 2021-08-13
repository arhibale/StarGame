package com.arhibale.pool;

import com.arhibale.base.SpritePool;
import com.arhibale.sprite.Bullet;

public class BulletPool extends SpritePool<Bullet> {

    @Override
    protected Bullet newSprite() {
        return new Bullet();
    }
}
