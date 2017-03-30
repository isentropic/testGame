package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.states.GameStateManager;

/**
 * Created by biddy on 3/29/17.
 */

/* Rack object class. */
public class Rack {

    /* Dimensions of the rack. */
    public static final int WIDTH = 50;
    public static final int HEIGHT = 5;

    /* Local variables. */
    private Rectangle rackRectangle;
    private Texture rack;
    private Vector2 pos;

    /* Basic initialization. */
    public Rack(float x, float y) {
        rack = GameStateManager.rack;

        /* To point the position of the rack centered where
           user touched.
         */
        pos = new Vector2(x - WIDTH/2, y - HEIGHT/2);
        rackRectangle = new Rectangle(pos.x, pos.y, WIDTH, HEIGHT);
    }

    /* Check for collision. */
    public boolean collides(Rectangle player) {
        return player.overlaps(rackRectangle);
    }

    /* Getter methods. */
    public Vector2 getPos() {
        return pos;
    }
    public Texture getRack() {
        return rack;
    }
}
