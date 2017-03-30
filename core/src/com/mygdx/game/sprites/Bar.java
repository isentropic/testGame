package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.states.GameStateManager;

import java.util.Random;

/**
 * Created by biddy on 3/22/17.
 */

/* Bar object class. */
public class Bar {

    /* Length of the bar. */
    public static final int LENGTH = 60;

    /* Fluctuation constant that is needed for random positioning
       of the bar, and lowest point where it could be positioned.
    */
    private static final int FLUCTUATION_HEIGHT = 300;
    private static final int LOWEST_OPENING = 70;

    /* Texture and position of the bar. */
    private Texture bar;
    private Vector2 pos;

    /* Bounds of the bars, to check if it collides with any other object. */
    private Rectangle bounds;

    /* Random variable to randomize the y-axis of the new bar. */
    private static final Random random = new Random();

    /* Initialization of the class. */
    public Bar(float x) {
        bar = GameStateManager.bar;

        /* Take random y-axis position. */
        pos = new Vector2(x, random.nextInt(FLUCTUATION_HEIGHT) + LOWEST_OPENING);

        bounds = new Rectangle(pos.x, pos.y, LENGTH, LENGTH);
    }

    /* Reposition our current bar to argument as x-axis, and random on y-axis. */
    public void reposition(float x) {
        pos.set(x, random.nextInt(FLUCTUATION_HEIGHT) + LOWEST_OPENING);
        bounds.setPosition(pos.x, pos.y);
    }

    /* Check if collides. */
    public boolean collides(Rectangle player) {
        return player.overlaps(bounds);
    }

    /* Getter methods. */
    public Texture getBar() {
        return bar;
    }
    public Vector2 getPos() {
        return pos;
    }
}
