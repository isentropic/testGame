package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

/* Test game based on GDX library. */
public class MyGdxGame extends ApplicationAdapter {

    /* Width and height of the game world. */
    public static final int WIDTH  = 480;
    public static final int HEIGHT = 800;

    /* Title of the game. */
    public static final String TITLE = "MYGAME";

    /* Class variables. */
    private GameStateManager gsm;
    private Music music;

    /* SpriteBatch that is responsible for rendering. */
    private SpriteBatch batch;

	@Override
	public void create () {

        /* Basic initialization. */
        gsm = new GameStateManager();
        batch = new SpriteBatch();
        Gdx.gl.glClearColor(1, 0, 0, 1);

        /* Setting the background music. */
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(0.005f);
        music.play();

        /* Initial state of the game. */
        gsm.push(new MenuState(gsm));
    }

	@Override
	public void render () {

        /* Rendering. */
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /* Update the current state and render it. */
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
	}
	
	@Override
	public void dispose () {

        /* Dispose allocated objects. */
		batch.dispose();
        music.dispose();
        gsm.dispose();
	}
}
