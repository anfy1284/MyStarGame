package ru.anfy.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.anfy.math.Rect;
import ru.anfy.pool.BulletPool;
import ru.anfy.sprite.Bullet;

public abstract class Ship extends Sprite {

    protected Rect worldBounds;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Sound bulletSound;
    protected float bulletHeight;
    protected int damage;

    protected final Vector2 v;
    protected final Vector2 v0;
    protected final Vector2 bulletV;
    protected final Vector2 bulletPos;

    protected float reloadTimer;
    protected float reloadInterval;

    protected int hp;

    protected Vector2 warpV;
    protected float warpTime;
    protected float warpTimer;

    public void setWarpMode(boolean warpMode) {
        this.warpMode = warpMode;
        if(warpMode) {
            warpTimer = 0;
            showTail = true;
        }else{
            showTail = false;
        }
        reloadTimer = reloadInterval;
    }

    protected boolean warpMode;

    public Ship() {
        v = new Vector2();
        v0 = new Vector2();
        bulletV = new Vector2();
        bulletPos = new Vector2();
        warpMode = false;
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
        v = new Vector2();
        v0 = new Vector2();
        bulletV = new Vector2();
        bulletPos = new Vector2();
    }

    @Override
    public void update(float delta) {

        if(warpMode){
            pos.mulAdd(warpV, delta);
            warpTimer += delta;
            if(warpTimer > warpTime){
                showTail = false;
                warpMode = false;
            }
            return;
        }

        super.update(delta);

        pos.mulAdd(v, delta);
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0;
            shoot();
        }
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, worldBounds, damage, bulletHeight);
        bulletSound.play();
    }

}
