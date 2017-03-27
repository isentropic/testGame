package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by biddy on 3/28/17.
 */

public class Animation {
    private Array<TextureRegion> frames;
    private int maxFrameTime;
    private int currentFrameTime;
    private int frameCount;
    private int frame;

    public Animation(Texture region, int frameCount, int oneFrameCount) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getWidth() / frameCount;
        for (int i = 0; i < frameCount; i++)
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getHeight()));
        this.frameCount = frameCount;
        maxFrameTime = oneFrameCount;
        frame = 0;
        currentFrameTime = 0;
    }

    public void update(float dt) {
        if (++currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }

        if (frame >= frameCount)
            frame = 0;
    }

    public TextureRegion getFrame() {
        return frames.get(frame);
    }
}
