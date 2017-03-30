package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by biddy on 3/28/17.
 */

/* Animation class for making animations. Given texture
   of the animation - several textures within the same
   texture in horizontal order with the same length each -
   it simply displays the needed animation from the whole
   animation.
 */
public class Animation {

    /* Local variables. */

    /* Frame animations we have in our whole animation. */
    private Array<TextureRegion> frames;

    /* Max number of updates for one frame to be displayed. */
    private int maxFrameTime;

    /* Number of updates current frame has been displayed. */
    private int currentFrameTime;

    /* Max number of frames in our animation. */
    private int frameCount;

    /* Current frame number. */
    private int frame;

    /* Init class. */
    public Animation(Texture region, int frameCount, int oneFrameCount) {

        /* Divide the texture region, which is basically the whole animation, into
           parts and store it in the array of frames.
         */
        frames = new Array<TextureRegion>();
        int frameWidth = region.getWidth() / frameCount;
        for (int i = 0; i < frameCount; i++)
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getHeight()));

        /* Set local variables. */
        this.frameCount = frameCount;
        maxFrameTime = oneFrameCount;
        frame = 0;
        currentFrameTime = 0;
    }

    public void update(float dt) {
        /* If current frame has been displayed enought time ticks,
           then update to new one.
         */
        if (++currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }

        /* If this is the last frame, set it to the first one. */
        if (frame >= frameCount)
            frame = 0;
    }

    /* Get current animation, or frame of the whole animation. */
    public TextureRegion getFrame() {
        return frames.get(frame);
    }
}
