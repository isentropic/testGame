package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Tube;

/**
 * Created by biddy on 3/22/17.
 */

public class PlayState extends State {

    private Bird bird;
    private Texture bg;
    private Tube tube;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        tube = new Tube(100);
        bg = new Texture("bg.png");
        cam.setToOrtho(false, MyGdxGame.WIDTH/2, MyGdxGame.HEIGHT/2);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        bird.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch sp) {
        sp.setProjectionMatrix(cam.combined);
        sp.begin();
        sp.draw(bg, cam.position.x - (cam.viewportWidth/2), 0);
        sp.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        sp.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
        sp.draw(tube.getBotTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        sp.end();
    }

    @Override
    public void dispose() {

    }
}
