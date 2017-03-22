package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by biddy on 3/22/17.
 */

public class Tube {

    private static final int FLUCTUATION    = 130;
    private static final int TUBE_GAP       = 100;
    private static final int LOWEST_OPENING = 120;

    private Texture topTube   , botTube;
    private Vector2 posTopTube, posBotTube;

    private static final Random random = new Random();

    public Tube(float x) {
        topTube = new Texture("toptube.png");
        botTube = new Texture("bottomtube.png");

        posTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - botTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBotTube() {
        return botTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }
}
