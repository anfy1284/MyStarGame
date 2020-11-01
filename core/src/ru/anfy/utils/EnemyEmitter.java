package ru.anfy.utils;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

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

    private EnemySettingsDto[] enemySettingsArr;

    public EnemyEmitter(Rect worldBounds, EnemyShipPool enemyShipPool, Sound bulletSound, TextureAtlas atlas) {
        this.worldBounds = worldBounds;
        this.enemyShipPool = enemyShipPool;

        enemySettingsArr = new EnemySettingsDto[3];
        enemySettingsArr[0] = new EnemyBigSettingsDto(atlas, bulletSound);
        enemySettingsArr[1] = new EnemyMediumSettingsDto(atlas, bulletSound);
        enemySettingsArr[2] = new EnemySmallSettingsDto(atlas, bulletSound);
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= GENERATE_INTERVAL) {
            generateTimer = 0;
            EnemyShip enemyShip = enemyShipPool.obtain();
            float type = (float) Math.random();
            boolean notSetted = true;
            for (int i = 0; i < enemySettingsArr.length; i++) {
                if(type < enemySettingsArr[i].getExpectation()){
                    enemyShip.set(enemySettingsArr[i]);
                    notSetted = false;
                    break;
                }
            }
            if(notSetted) enemyShip.set(enemySettingsArr[enemySettingsArr.length - 1]);

            enemyShip.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemyShip.getHalfWidth(), worldBounds.getRight() - enemyShip.getHalfWidth());
            enemyShip.setBottom(worldBounds.getTop());

            //set Warp
            //enemyShip.setWarpMode(true);
        }
    }
}
