package ru.anfy.sprite;

import ru.anfy.base.Ship;
import ru.anfy.base.EnemySettingsDto;
import ru.anfy.math.Rect;
import ru.anfy.pool.BulletPool;

public class EnemyShip extends Ship {


    public EnemyShip(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        bulletPos.set(pos.x, getBottom());
        super.update(delta);
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
    }

    public void set(EnemySettingsDto settings) {
        this.regions = settings.getRegions();
        this.v.set(settings.getV0());
        this.bulletRegion = settings.getBulletRegion();
        this.bulletHeight = settings.getBulletHeight();
        this.bulletV.set(settings.getBulletV());
        this.bulletSound = settings.getBulletSound();
        this.damage = settings.getDamage();
        this.reloadInterval = settings.getReloadInterval();
        setHeightProportion(settings.getHeight());
        this.hp = settings.getHp();
        this.warpTime = settings.getWarpTime();
        this.warpV = settings.getWarpV();
        this.setWarpMode(settings.getWarpMode());
    }

}
