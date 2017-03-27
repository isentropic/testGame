package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;


/**
 * Created by biddy on 3/21/17.
 */

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH/2, MyGdxGame.HEIGHT/2);
        background = new Texture("bg.png");
        playBtn    = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {

        if (Gdx.input.justTouched()) {
            Vector3 np = new Vector3(Gdx.input.getX() ,Gdx.input.getY(), 0);
            np = cam.unproject(np);
            System.out.println("Dauren " + np.x + " " + np.y);
            System.out.println("Dauren " + cam.position.x + " " + cam.position.y);
            if (np.x > cam.position.x - playBtn.getWidth() / 2 &&
                    np.x < cam.position.x + playBtn.getWidth() / 2 &&
                    np.y > cam.position.y - playBtn.getHeight() / 2 &&
                    np.y < cam.position.y + playBtn.getHeight() / 2)
                gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sp) {
        sp.setProjectionMatrix(cam.combined);
        sp.begin();
        sp.draw(background, 0, 0);
        sp.draw(playBtn, cam.position.x - playBtn.getWidth()/2, cam.position.y - playBtn.getHeight()/2);
        sp.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn   .dispose();
        System.out.println("Menu state disposed");
    }

}
