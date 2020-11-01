package ru.anfy.pool;

import ru.anfy.math.Rect;
import ru.anfy.sprite.EnemyShip;
import ru.anfy.base.SpritesPool;

public class EnemyShipPool extends SpritesPool<EnemyShip> {

    private ru.anfy.pool.BulletPool bulletPool;
    private ru.anfy.math.Rect worldBounds;

    public EnemyShipPool(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    protected EnemyShip newObject() {
        EnemyShip newShip = new EnemyShip(bulletPool, worldBounds);
        newShip.setTail(50);
        return newShip;
    }

}
