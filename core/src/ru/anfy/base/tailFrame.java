package ru.anfy.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.lang.reflect.Array;

public class tailFrame {

    private TextureRegion region;
    private float x;
    private float y;
    private float originX;
    private float originY;
    private float width;
    private float height;
    private float scaleX;
    private float scaleY;
    private float rotation;

    static void draw(Sprite sprite, SpriteBatch batch, TextureRegion region, float x, float y, float originX, float originY, float width, float height,
                     float scaleX, float scaleY, float rotation) {

        if (sprite.hasTail() && sprite.showTail) {
            int index = 0;
            if (sprite.tailLength < sprite.tail.length) {
                index = sprite.tailLength;
                sprite.tailLength++;
                if (sprite.tail[index] == null) sprite.tail[index] = new tailFrame();
            } else {
                //shift
                for (int i = 0; i < sprite.tail.length - 1; i++) {
                    sprite.tail[i].set(sprite.tail[i + 1]);
                }
                index = sprite.tail.length - 1;
            }

            sprite.tail[index].set(region,
                    x, y,
                    originX, originY,
                    width, height,
                    scaleX, scaleY,
                    rotation);

            //draw tail
            for (int i = 0; i < sprite.tailLength; i++) {
                batch.draw(
                        sprite.tail[i].region,
                        sprite.tail[i].x, sprite.tail[i].y,
                        sprite.tail[i].originX, sprite.tail[i].originY,
                        sprite.tail[i].width, sprite.tail[i].height,
                        sprite.tail[i].scaleX, sprite.tail[i].scaleY,
                        sprite.tail[i].rotation
                );
            }
        } else {
            batch.draw(
                    region,
                    x, y,
                    originX, originY,
                    width, height,
                    scaleX, scaleY,
                    rotation
            );
        }

    }

    public void set(TextureRegion region, float x, float y, float originX, float originY,
                    float width, float height, float scaleX, float scaleY, float rotation) {
        this.region = region;
        this.x = x;
        this.y = y;
        this.originX = originX;
        this.originY = originY;
        this.width = width;
        this.height = height;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.rotation = rotation;
    }

    public void set(tailFrame src){
        set(src.region, src.x, src.y, src.originX, src.originY, src.width, src.height, src.scaleX, src.scaleY, src.rotation);
    }

}
