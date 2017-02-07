/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author daca97002
 */
public class EnemyArrow extends Sprite {

    FileHandle arrowFile = Gdx.files.internal("arrow.png");
    private Sprite arrow = new Sprite(new Texture(arrowFile));

    private int shotIntervall = 180;

    public int getShotIntervall() {
        return shotIntervall;
    }

    public void setShotIntervall(int shotIntervall) {
        this.shotIntervall = shotIntervall;
    }
    private int speed = -3;
    private boolean active = false;

    public Sprite getArrow() {
        return arrow;
    }

    public void setArrow(Sprite arrow) {
        this.arrow = arrow;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static int getheightCord() {
        int temp = (int) (Math.random() * 1000);
        if (temp > 500) {
            while (temp > 500) {
                temp = temp / 2;
            }
            if (temp < 50) {
                temp = temp + 50;
                return temp;
            } else {
                return temp;
            }
        } else if (temp <= 50) {
            while (temp <= 50) {
                temp = temp * 2;
            }
            if (temp > 500) {
                temp = temp - 50;
                return temp;
            } else {
                return temp;

            }
        } else {
            return temp;
        }
    }
}
