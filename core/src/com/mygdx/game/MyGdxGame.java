package com.mygdx.game;

import com.david.objects.EnemyArrow;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter {

    private List<Sprite> shootList = new ArrayList<Sprite>();
    //http://www.badlogicgames.com/forum/viewtopic.php?f=11&t=10351&p=46673#p46673
    private float shootSpeed = 4;
    private Sprite dragon;
    private SpriteBatch batch;
    private Sprite knight;
    int test = 3;
    private EnemyArrow arrow = new EnemyArrow();
    private Sprite eArrow = arrow.getArrow();
    private int arrowShoot = arrow.getShotIntervall();

    @Override
    public void create() {
        FileHandle dragonFile = Gdx.files.internal("gDragon.png");
        dragon = new Sprite(new Texture(dragonFile));
        dragon.setPosition(0, 0);
        batch = new SpriteBatch();

        FileHandle knightFile = Gdx.files.internal("knight.png");
        knight = new Sprite(new Texture(knightFile));
        knight.setPosition(70, 20);

    }

    @Override
    public void render() {
        System.out.println("You get to render!!");
        arrowShoot--;
        //may cause problems if I try to create more than 1 enemyArrow Without disposing the last.
        if (arrowShoot == 0) {

            eArrow.setPosition(Gdx.graphics.getWidth(), EnemyArrow.getheightCord());
            arrowShoot = arrow.getShotIntervall();
        }
        if (eArrow.getX() > 0 && eArrow.getX() <= Gdx.graphics.getWidth()) {
            arrow.translateX(arrow.getSpeed());
        }
        System.out.println(shootList);
        for (Sprite dragon_spit : shootList) {
            dragon_spit.translateX(shootSpeed);
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT) && dragon.getX() > 0) {
            dragon.translateX(-2);
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT) && dragon.getX() < Gdx.graphics.getWidth() - dragon.getWidth()) {
            dragon.translateX(2);
        }
        if (Gdx.input.isKeyPressed(Keys.UP) && dragon.getY() < Gdx.graphics.getHeight() - dragon.getHeight()) {
            dragon.translateY(2);
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN) && dragon.getY() > 0) {
            dragon.translateY(-2);
        }
        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            FileHandle shotFile = Gdx.files.internal("badlogic.jpg");//animation = fire.gif
            Sprite newShoot = new Sprite(new Texture(shotFile));
            newShoot.setPosition(dragon.getX(), (dragon.getY() - 50));
            newShoot.setScale(0.1f);
            shootList.add(newShoot);
        }

        Gdx.gl.glClearColor(100, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        knight.draw(batch);
        dragon.draw(batch);
        if (eArrow.getX() > 0 && eArrow.getX() <= Gdx.graphics.getWidth()) {
            eArrow.draw(batch);
        }
        for (Sprite dragon_spit : shootList) {
            dragon_spit.draw(batch);
        }
        test--;
        batch.end();
    }

    @Override
    public void dispose() {
        System.out.println(test);
        batch.dispose();
    }
}
