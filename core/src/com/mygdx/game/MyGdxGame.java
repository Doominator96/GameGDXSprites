package com.mygdx.game;

import com.david.objects.EnemyArrow;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.david.objects.EnemyKnight;
import com.david.objects.HeroDragon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter {

    private EnemyArrow eArrow;
    private HeroDragon hDragon;
    private EnemyKnight eKnight;
    private List<Sprite> shootList;
    private List<Sprite> arrowList;
    //http://www.badlogicgames.com/forum/viewtopic.php?f=11&t=10351&p=46673#p46673
    private SpriteBatch batch;
    private Sprite dragon;
    private Sprite knight;
    private Sprite arrow;
    private BitmapFont scoreFont;
    private BitmapFont healthFont;
    private String pointName = "Score: 0";
    public String dragonHealth = "Health: -err-";
    private int arrowShoot;
    private int fireIntervall;
    private int eKnightHP;
    int test = 3;

    @Override
    public void create() {
       arrowList = new ArrayList<Sprite>();
       shootList = new ArrayList<Sprite>();
       
        eArrow = new EnemyArrow();
        hDragon = new HeroDragon();
        eKnight = new EnemyKnight();
        eKnightHP = eKnight.getHealth();
        arrowShoot = eArrow.getShotIntervall();
        dragon = hDragon.getDragon();
        dragon.setPosition(0, 0);
        batch = new SpriteBatch();

        knight = eKnight.getKnight();
        knight.setPosition(Gdx.graphics.getWidth() - knight.getWidth(), 20);
        eKnight.setAlive(true);
        arrow = eArrow.getArrow();
        scoreFont = new BitmapFont();
        healthFont = new BitmapFont();
        fireIntervall = hDragon.getFireIntervall();

    }

    @Override
    public void render() {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        if (hDragon.isAlive()) {
            System.out.println("You get to render!!");
            arrowShoot--;
            if (!eKnight.isAlive()) {
                eKnight.setHealth(eKnightHP + eKnight.getDeathCounter());
                knight.setPosition(width - knight.getWidth(), 20);
            }
            //may cause problems if I try to create more than 1 enemyArrow Without disposing the last.
            if (arrowShoot == 0) {
                System.out.println("DU KOMMER TILL ARROW");
                arrow = new EnemyArrow().getArrow();
                arrow.setPosition(Gdx.graphics.getWidth(), eArrow.getheightCord());
                arrowShoot = eArrow.getShotIntervall();
                System.out.println("HÖJD " + eArrow.getX() + " " + eArrow.getY());
                arrowList.add(arrow);
                System.out.println(arrowList);
            }
            for (Iterator<Sprite> it = shootList.iterator(); it.hasNext();) {
                Sprite dragonSpit = it.next();

                dragonSpit.translateX(hDragon.getShootSpeed());
                if (dragonSpit.getBoundingRectangle().overlaps(knight.getBoundingRectangle())) {
                    eKnight.setHealth(-1);
                    hDragon.setPoints(2);
                    it.remove();
                }
                if (dragonSpit.getX() > width + 100) {
                    it.remove();
                }
            }
            for (Iterator<Sprite> it = arrowList.iterator(); it.hasNext();) {
                System.out.println("You get to arrows");
                arrow = it.next();
                arrow.translateX(eArrow.getSpeed());
                if (arrow.getX() <= 0) {
                    it.remove();
                } else {
                    if (arrow.getBoundingRectangle().overlaps(dragon.getBoundingRectangle())) {
                        hDragon.setHealth(-1);
                        //could invoke errors since i later check if the arrow I remove overlaps dragonSpit.
                        it.remove();
                        System.out.println("FÖRBASKAT KOMPLICERAT " + arrow);
                    }
                    for (Sprite dragonSpit : shootList) {
                        System.out.println("här är jag");
                        if (arrow.getBoundingRectangle().overlaps(dragonSpit.getBoundingRectangle())) {
                            hDragon.setPoints(1);
                            System.out.println("Failar härefter");
                            it.remove();
                            System.out.println("Når aldrig hit");
                        }
                    }
                }
            }
            System.out.println(shootList);
            if (eKnight.isAlive()) {
                knight.translateX(eKnight.getxSpeed());
                knight.translateY(eKnight.getySpeed());
                if (knight.getY() <= 0) {
                    eKnight.setySpeed(1);
                }
                if (knight.getY() >= height) {
                    eKnight.setySpeed(1);
                }
                if (knight.getX() <= 0) {
                    eKnight.setxSpeed(1);
                }
                if (knight.getX() >= width) {
                    eKnight.setxSpeed(1);
                }
            }

            if (Gdx.input.isKeyPressed(Keys.LEFT) && dragon.getX() > 0) {
                dragon.translateX(hDragon.getSpeed());
            }
            if (Gdx.input.isKeyPressed(Keys.RIGHT) && dragon.getX() < Gdx.graphics.getWidth() - dragon.getWidth()) {
                dragon.translateX(hDragon.getSpeed());
            }
            if (Gdx.input.isKeyPressed(Keys.UP) && dragon.getY() < Gdx.graphics.getHeight() - dragon.getHeight()) {
                dragon.translateY(hDragon.getSpeed());
            }
            if (Gdx.input.isKeyPressed(Keys.DOWN) && dragon.getY() > 0) {
                dragon.translateY(-hDragon.getSpeed());
            }
            if (Gdx.input.isKeyPressed(Keys.SPACE) && fireIntervall <= 0) {

                Sprite newDragonSpit = new HeroDragon().getFire();
                newDragonSpit.setPosition(dragon.getX(), (dragon.getY() - 50));
                newDragonSpit.setScale(hDragon.getShotScale());
                shootList.add(newDragonSpit);
                fireIntervall = hDragon.getFireIntervall();
            }
            if (dragon.getBoundingRectangle().overlaps(knight.getBoundingRectangle())) {
                eKnight.setAlive(false);
                hDragon.setHealth(-1);
                hDragon.setPoints(+2);
            }
            dragonHealth = "\nHealth: -" + hDragon.getHealth() + "-";
            pointName = "Score: " + hDragon.getPoints();

            Gdx.gl.glClearColor(100, 0, 0, 0);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();
            if (eKnight.isAlive()) {
                knight.draw(batch);
            }
            dragon.draw(batch);
            System.out.println("Storlek för arrowlist "+arrowList.size());
            for (Sprite bow : arrowList) {
                System.out.println("Kordinat för var pil "+bow.getX());
                System.out.println("FÖR VAR PIL");
                bow.draw(batch);
            }
            for (Sprite dragonSpit : shootList) {
                dragonSpit.draw(batch);
            }

            scoreFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            scoreFont.draw(batch, pointName, 25, height - 15);
            healthFont.setColor(1.0f, 100.0f, 1.0f, 1.0f);
            healthFont.draw(batch, dragonHealth, 25, height - 15);

            batch.end();
            fireIntervall--;
            test--;
        } else {
            dragonHealth = "\nHealth: -" + hDragon.getHealth() + "-";
            pointName = "GAME OVER!!!\nScore: " + hDragon.getPoints();
            Gdx.gl.glClearColor(0, 0, 0, 0);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
             batch.begin();            
             scoreFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            scoreFont.draw(batch, pointName, width/2, height/2);
            healthFont.setColor(1.0f, 100.0f, 1.0f, 1.0f);
            healthFont.draw(batch, dragonHealth, width/2, height/2-20);
             
             batch.end();
            
        }
    }

    @Override
    public void dispose() {
        System.out.println(test);
        System.out.println("arrows " + arrowList.size());
        System.out.println("points: " + hDragon.getPoints());
        batch.dispose();
    }
}
