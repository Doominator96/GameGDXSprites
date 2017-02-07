package com.mygdx.game;

import com.david.objects.EnemyArrow;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.david.objects.EnemyKnight;
import com.david.objects.HeroDragon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter {

    private EnemyArrow arrow;
    private HeroDragon hDragon;
    private EnemyKnight eKnight;
    private List<Sprite> shootList = new ArrayList<Sprite>();
    private List<Sprite> arrowList = new ArrayList<Sprite>();
    //http://www.badlogicgames.com/forum/viewtopic.php?f=11&t=10351&p=46673#p46673
    private SpriteBatch batch;
    private Sprite dragon;
    private Sprite knight;
    private Sprite eArrow;
    private int arrowShoot;
    private int fireIntervall;
    int test = 3;

    @Override
    public void create() {
        arrow = new EnemyArrow();
        hDragon = new HeroDragon();
        eKnight = new EnemyKnight();
        arrowShoot = arrow.getShotIntervall();
        dragon = hDragon.getDragon();
        dragon.setPosition(0, 0);
        batch = new SpriteBatch();

        knight = eKnight.getKnight();
        knight.setPosition(Gdx.graphics.getWidth() - knight.getWidth(), 20);
        eKnight.setAlive(true);
        eArrow = arrow.getArrow();
        fireIntervall = hDragon.getFireIntervall();
    }

    @Override
    public void render() {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        System.out.println("You get to render!!");
        arrowShoot--;
        //may cause problems if I try to create more than 1 enemyArrow Without disposing the last.
        if (arrowShoot == 0) {
            System.out.println("DU KOMMER TILL ARROW");
            eArrow.setPosition(Gdx.graphics.getWidth(), EnemyArrow.getheightCord());
            arrowShoot = arrow.getShotIntervall();
            System.out.println("HÖJD " + eArrow.getX()+ " " + eArrow.getY());
            arrowList.add(eArrow);
            System.out.println(arrowList);
        }
        for (Sprite dragonSpit : shootList) {
            dragonSpit.translateX(hDragon.getShootSpeed());
        }
        for (Iterator<Sprite> it = arrowList.iterator(); it.hasNext();) {
            eArrow = it.next();
            eArrow.translateX(arrow.getSpeed());
            if (eArrow.getX() <= 0) {
                it.remove();
            } else {
                if (eArrow.getBoundingRectangle().overlaps(dragon.getBoundingRectangle())) {
                    hDragon.setPoints(-1);
                }
                for (Sprite dragonSpit : shootList) {
                    if (eArrow.getBoundingRectangle().overlaps(dragonSpit.getBoundingRectangle())) {
                        hDragon.setPoints(1);
                        it.remove();
                    }
                }
            }
            //it.next();
        }
        System.out.println(shootList);
        if(eKnight.isAlive()){
            knight.translateX(eKnight.getxSpeed());
            knight.translateY(eKnight.getySpeed());
        if(knight.getY()<=0){eKnight.setySpeed(1);}
        if(knight.getY()>=height){eKnight.setySpeed(1);}
        if(knight.getX()<=0){eKnight.setxSpeed(1);}
        if(knight.getX()>=width){eKnight.setxSpeed(1);}
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
        if (Gdx.input.isKeyPressed(Keys.SPACE) && fireIntervall <= 0) {

            Sprite newShoot = hDragon.getFire();
            newShoot.setPosition(dragon.getX(), (dragon.getY() - 50));
            newShoot.setScale(hDragon.getShotScale());
            shootList.add(newShoot);
            fireIntervall = hDragon.getFireIntervall();
        }

        Gdx.gl.glClearColor(100, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        knight.draw(batch);
        dragon.draw(batch);
        for (Sprite bow : arrowList) {
            bow.draw(batch);
        }
        for (Sprite dragonSpit : shootList) {
            dragonSpit.draw(batch);
        }
        fireIntervall--;
        test--;
        batch.end();
    }

    @Override
    public void dispose() {
        System.out.println(test);
        System.out.println("arrows " + arrowList.size());
        System.out.println("points: " + hDragon.getPoints());
        batch.dispose();
    }
}
