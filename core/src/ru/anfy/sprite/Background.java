package ru.anfy.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.anfy.base.Sprite;
import ru.anfy.math.Rect;

public class Background extends Sprite {

    public Background(Texture region) {
        super(new TextureRegion(region));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.pos);
    }
}
