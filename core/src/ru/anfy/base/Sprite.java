package ru.anfy.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.anfy.math.Rect;
import ru.anfy.utils.Regions;

public abstract class Sprite extends Rect {

    private static final int DEFAULT_TAIL_LENGTH = 10;

    protected float angle;
    protected float scale = 1;
    protected TextureRegion[] regions;
    protected int frame;
    protected boolean destroyed;

    protected tailFrame[] tail; //Шлейф
    protected int tailLength; //Длина шлейфа
    public boolean showTail;

    public Sprite() {
    }

    public Sprite(TextureRegion region) {
        this.regions = new TextureRegion[1];
        regions[0] = region;
    }

    public void setTail(int length){
        tail = new tailFrame[length];
        tailLength = 0;
        showTail = true;
    }

    public void resetTail(){
        tailLength = 0;
    }

    public void clearTail(){
        tail = null;
        tailLength = 0;
        showTail = false;
    }

    public void setTail(){
        setTail(DEFAULT_TAIL_LENGTH);
    }

    public boolean hasTail(){
        return !(tail == null);
    }

    public Sprite(TextureRegion region, int rows, int cols, int frames) {
        this.regions = Regions.split(region, rows, cols, frames);
    }

    public void setHeightProportion(float height) {
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(height * aspect);
    }

    public void draw(SpriteBatch batch) {
        tailFrame.draw(this,
                batch,
                regions[frame],
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                scale, scale,
                angle
        );

    }

    public void update(float delta) {

    }

    public void resize(Rect worldBounds) {

    }

    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return false;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void destroy() {
        destroyed = true;
    }

    public void flushDestroy() {
        destroyed = false;
    }
}
