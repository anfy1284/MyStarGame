package ru.anfy.utils;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.anfy.sprite.EnemyShip;
import ru.anfy.base.EnemySettingsDto;
import ru.anfy.dto.EnemyBigSettingsDto;
import ru.anfy.dto.EnemyMediumSettingsDto;
import ru.anfy.dto.EnemySmallSettingsDto;
import ru.anfy.math.Rect;
import ru.anfy.math.Rnd;
import ru.anfy.pool.EnemyShipPool;

public class EnemyEmitter {

    private static final float GENERATE_INTERVAL = 4f;

    private Rect worldBounds;
    private EnemyShipPool enemyShipPool;
    private float generateTimer;

    private EnemySettingsDto enemySmallSettingsDto;
    private EnemySettingsDto enemyMediumSettingsDto;
    private EnemySettingsDto enemyBigSettingsDto;

    public EnemyEmitter(Rect worldBounds, EnemyShipPool enemyShipPool, Sound bulletSound, TextureAtlas atlas) {
        this.worldBounds = worldBounds;
        this.enemyShipPool = enemyShipPool;
        enemySmallSettingsDto = new EnemySmallSettingsDto(atlas, bulletSound);
        enemyMediumSettingsDto = new EnemyMediumSettingsDto(atlas, bulletSound);
        enemyBigSettingsDto = new EnemyBigSettingsDto(atlas, bulletSound);
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= GENERATE_INTERVAL) {
            generateTimer = 0;
            EnemyShip enemyShip = enemyShipPool.obtain();
            float type = (float) Math.random();
            if (type < 0.5f) {
                enemyShip.set(enemySmallSettingsDto);
            } else if (type < 0.8f) {
                enemyShip.set(enemyMediumSettingsDto);
            } else {
                enemyShip.set(enemyBigSettingsDto);
            }
            enemyShip.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemyShip.getHalfWidth(), worldBounds.getRight() - enemyShip.getHalfWidth());
            enemyShip.setBottom(worldBounds.getTop());
        }
    }
}
