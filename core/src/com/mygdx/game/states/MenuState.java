package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;


/**
 * Created by biddy on 3/21/17.
 */

/* Menu state class. Basically, it just displays start
   button for player to play. Still need to add score
   state button, but it will be done in future.
 */
public class MenuState extends State {

    /* Local variables - Textures. */
    private Texture background;
    private Texture playBtn;

    /* Basic initialization. */
    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = gsm.bg;
        playBtn    = gsm.playBtn;

        /* Set the camera to show the left low quarter of
           the game world. First argument is boolean, if true
           then y axis will be pointing down, else pointing up
           as usual.
         */
        cam.setToOrtho(false, MyGdxGame.WIDTH/2, MyGdxGame.HEIGHT/2);
    }

    @Override
    public void handleInput() {

        /* If play button hit, then set new play state. */
        if (Gdx.input.justTouched()) {
            /* Need to project the touch input into our coordinate
               system, thus we translate the input coordinates to
               our current system.
             */
            Vector3 np = new Vector3(Gdx.input.getX() ,Gdx.input.getY(), 0);
            np = cam.unproject(np);
            if (np.x > cam.position.x - playBtn.getWidth() / 2 &&
                    np.x < cam.position.x + playBtn.getWidth() / 2 &&
                    np.y > cam.position.y - playBtn.getHeight() / 2 &&
                    np.y < cam.position.y + playBtn.getHeight() / 2)
                gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float deltaTime) {
        /* Check if input is done every time we update. */
        handleInput();
    }

    @Override
    public void render(SpriteBatch sp) {
        /* Set projection matrix according to our camera. */
        sp.setProjectionMatrix(cam.combined);
        sp.begin();

        /* Set play button and background. */
        sp.draw(background, 0, 0);
        sp.draw(playBtn, cam.position.x - playBtn.getWidth()/2, cam.position.y - playBtn.getHeight()/2);
        sp.end();
    }
}
