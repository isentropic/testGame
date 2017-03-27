package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

/**
 * Created by biddy on 3/21/17.
 */

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playBtn    = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sp) {
        sp.begin();
        sp.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sp.draw(playBtn, MyGdxGame.WIDTH/2 - playBtn.getWidth()/2, MyGdxGame.HEIGHT/2 - playBtn.getHeight()/2);
        sp.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn   .dispose();
        System.out.println("Menu state disposed");
    }

}
