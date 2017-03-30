package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by biddy on 3/21/17.
 */

/* Abstract class for different states of the game.
   There are two local variables - OrthographicCamera
   and GameStateManager. GameStateManager is needed to
   access common functions and change the top of the
   game stack if needed. Orthographic camera to set
   our camera in game world to the position we want.
 */
public abstract class State {

    /* Local variables. */
    protected OrthographicCamera cam;
    protected GameStateManager gsm;


    /* Basic initialization. */
    public State(GameStateManager gsm) {
        this.gsm   = gsm;
        this.cam   = new OrthographicCamera();
    }

    /* Abstract methods that needed to be implemented
       by extending classes. */

    /* If there is input, deal with it. */
    public abstract void handleInput();

    /* Update the object in current state. */
    public abstract void update(float deltaTime);

    /* Render current state. */
    public abstract void render(SpriteBatch sp );
}