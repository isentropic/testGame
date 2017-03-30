package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Bar;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Rack;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by biddy on 3/22/17.
 */

/* Play state class. The main action is happening here.
   Basically, bird jumps with the help of the racks, and
   if it falls to ground or collides with bars, then game
   starts all over.
 */
public class PlayState extends State {

    /* Local final variables. */
    private static final int BAR_OFFSET = 125;
    private static final int BARS_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -70;


    /* Local objects and textures. */
    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    /* Arrays of bars  and racks. */
    private Array<Bar> bars;
    private ArrayList<Rack> racks;

    /* Basic initialization. */
    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird   = new Bird(50, 300);
        bg     = gsm.bg;
        ground = gsm.ground;

        /* Just put two ground textures and exchange them once
           camera passes.
         */
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth/2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2(cam.position.x - cam.viewportWidth/2 + ground.getWidth(), GROUND_Y_OFFSET);
        cam.setToOrtho(false, MyGdxGame.WIDTH/2, MyGdxGame.HEIGHT/2);

        bars = new Array<Bar>();
        racks = new ArrayList<Rack>();

        /* Put bars in front in advance. */
        for (int i = 2; i <= BARS_COUNT + 1; i++) {
            bars.add(new Bar(i * (BAR_OFFSET + Bar.LENGTH)));
        }
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            /* Need to project the touch input into our coordinate
               system, thus we translate the input coordinates to
               our current system.
             */
            Vector3 np = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            np = cam.unproject(np);

            /* If player touches then put there a rack. */
            racks.add(new Rack(np.x, np.y));
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        updateGround();

        /* Update bird object. */
        bird.update(deltaTime);

        /* Move camera along with bird. */
        cam.position.x =  bird.getPosition().x + 80;

        /* Position bars and if bird collides then restart play state. */
        for (Bar bar: bars) {
            if (cam.position.x - cam.viewportWidth / 2 > bar.getPos().x + bar.LENGTH)
                bar.reposition(bar.getPos().x + ((Bar.LENGTH + BAR_OFFSET) * BARS_COUNT));

            if (bar.collides(bird.getBounds())) {
                gsm.set(new PlayState(gsm));
                break;
            }
        }

        /* Position racks, and if bird collides then jump. */
        for (Iterator<Rack> iterator = racks.iterator(); iterator.hasNext(); ) {
            Rack rack = iterator.next();

            if (rack.collides(bird.getBounds()))
                bird.jump();

            if (cam.position.x - cam.viewportWidth / 2 > rack.getPos().x + rack.WIDTH/2)
                iterator.remove();
        }

        /* If bird hits ground, restart. */
        if (bird.getPosition().y < ground.getHeight() + GROUND_Y_OFFSET)
            gsm.set(new PlayState(gsm));

        /* Recalculate camera's matrix. */
        cam.update();
    }

    @Override
    public void render(SpriteBatch sp) {
        sp.setProjectionMatrix(cam.combined);
        sp.begin();

        /* Draw background with position of camera and bird. */
        sp.draw(bg, cam.position.x - (cam.viewportWidth/2), 0);
        sp.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        /* Draw bars. */
        for (Bar bar : bars) {
            sp.draw(bar.getBar(), bar.getPos().x, bar.getPos().y);
        }

        /* Draw racks. */
        for (Rack rack: racks) {
            sp.draw(rack.getRack(), rack.getPos().x, rack.getPos().y);
        }

        /* Draw grounds. */
        sp.draw(ground, groundPos1.x, groundPos1.y);
        sp.draw(ground, groundPos2.x, groundPos2.y);
        sp.end();
    }

    /* Update grounds if their position is outpassed by camera. */
    private void updateGround() {
        if (cam.position.x - cam.viewportWidth/2 > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if (cam.position.x - cam.viewportWidth/2 > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }
}
