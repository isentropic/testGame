package com.mygdx.game.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.states.GameStateManager;

/**
 * Created by biddy on 3/22/17.
 */

/* Bird object class. */
public class Bird {

    /* Gravity magnitude and speed of the bird. */
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    /* Local variables. */
    private Sound flap;
    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private Animation birdAnimation;

    private Rectangle bounds;

    /* Initialization of the class. */
    public Bird(int x, int y) {

        /* Position the bird and set its velocity to static. */
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture  = GameStateManager.birdAnim;
        bounds   = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
        flap     = GameStateManager.flap;

        /* Make some animation for bird as if it flies. */
        birdAnimation = new Animation(texture, 3, 3);
    }

    public void update(float dt) {
        /* Update the animation. */
        birdAnimation.update(dt);

        /* Add gravity to the velocity. */
        if (position.y > 0)
            velocity.add(0, GRAVITY, 0);

        /* Scale according to delta time and reposition our bird. */
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if (position.y < 0)
            position.y = 0;

        /* Rescale the velocity. */
        velocity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);
    }

    /* Method when bird jumps. */
    public void jump() {
        /* Add some value to y-axis of the velocity, and play sound. */
        velocity.y = 400;
        flap.play(0.1f);
    }

    /* Getter methods. */
    public Vector3 getPosition() {
        return position;
    }
    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }
    public Rectangle getBounds() {
        return bounds;
    }
}
