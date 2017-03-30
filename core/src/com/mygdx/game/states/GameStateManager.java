package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by biddy on 3/21/17.
 */

/* Class for managing stack of the states of the game. Also, to
   avoid redundant creation of Textures, stores static textures.
   The main class MyGdxGame class renders the top of the stack
   current class holds.
 */
public class GameStateManager {

    /* Stack of states. */
    private Stack<State> states;

    /* Static textures from assets folder. */
    public static final Texture bg       = new Texture("bg.png");
    public static final Texture bar      = new Texture("bar.png");
    public static final Texture rack     = new Texture("rack.png");
    public static final Texture ground   = new Texture("ground.png");
    public static final Texture playBtn  = new Texture("playbtn.png");
    public static final Texture birdAnim = new Texture("birdanimation.png");

    public static final Sound flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));

    /* Initialization of stack. */
    public GameStateManager() {
        states = new Stack<State>();
    }

    /* Push state onto the stack. */
    public void push(State state) {
        states.push(state);
    }

    /* Pop the state from stack. */
    public void pop() {
        states.pop();
    }

    /* Push the new state and pop the stack. */
    public void set(State state) {
        states.pop  ();
        states.push(state);
    }

    /* Update the top state. */
    public void update(float dt) {
        states.peek().update(dt);
    }

    /* Render the top state. */
    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }

    /* Dispose the allocated variables. */
    public void dispose() {
        bg      .dispose();
        bar     .dispose();
        rack    .dispose();
        ground  .dispose();
        playBtn .dispose();
        birdAnim.dispose();

        flap.dispose();
    }

}

