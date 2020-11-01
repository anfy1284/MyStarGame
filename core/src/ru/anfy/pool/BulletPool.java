package ru.anfy.pool;

import ru.anfy.sprite.Bullet;
import ru.anfy.base.SpritesPool;

public class BulletPool extends SpritesPool<ru.anfy.sprite.Bullet> {
    @Override
    protected ru.anfy.sprite.Bullet newObject() {
        return new Bullet();
    }
}
