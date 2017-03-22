package com.mygdx.game.states;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

/**
 * Created by biddy on 3/22/17.
 */

public class PlayState extends State {

    private Texture bird;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Texture("bird.png");
        cam.setToOrtho(false, MyGdxGame.WIDTH/2, MyGdxGame.HEIGHT/2);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch sp) {
        sp.setProjectionMatrix(cam.combined);
        sp.begin();
        sp.draw(bird, 50, 50);
        sp.end();
    }

    @Override
    public void dispose() {

    }
}
