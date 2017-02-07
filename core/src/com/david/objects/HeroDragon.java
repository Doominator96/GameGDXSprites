/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author daca97002
 */
public class HeroDragon extends Sprite {

    //Should not be final if i´m planing "powerups"
    final int speed = 3;
    final int shootSpeed = 5;
    //allows powerupps with shorter fireIntervall.
    private int fireIntervall = 50;
    private int points = 0;
    private float shotScale = 0.15f;
    private FileHandle dragonFile = Gdx.files.internal("gDragon.png");
    private FileHandle shotFile = Gdx.files.internal("badlogic.jpg");//animation = fire.gif
    private Sprite dragon = new Sprite(new Texture(dragonFile));
    private Sprite fire = new Sprite(new Texture(shotFile));

    public float getShotScale() {
        return shotScale;
    }

    public int getFireIntervall() {
        return fireIntervall;
    }

    public Sprite getFire() {
        return fire;
    }

    public int getSpeed() {
        return speed;
    }

    public int getShootSpeed() {
        return shootSpeed;
    }

    public Sprite getDragon() {
        return dragon;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        if (points == 1 || points == 2 || points == -1 || points == -2) {
            this.points = this.points + points;
        }
    }

}
