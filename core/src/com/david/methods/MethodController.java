package com.david.methods;

import com.mygdx.game.MyGdxGame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author daca97002
 */
public class MethodController {
    public static int pauseOptions(){
            String[] options = {"End Game",
                    "New game",
                    "Keep Playing"};
int n = JOptionPane.showOptionDialog(null,
    "Do you want to return to main menu?",
    "A Silly Question",
    JOptionPane.YES_NO_CANCEL_OPTION,
    JOptionPane.QUESTION_MESSAGE,
    null,
    options,
    options[2]);
        
        System.out.println(n);
        
        if(n==0){
           MyGdxGame game = new MyGdxGame();
            game.dispose();
            return -2;}
        if(n==1){return -1;}
        if(n==2){return 1;}
        
        return -1;
    }
    public int mainMenu(){
            String[] options = {"New game",
                    "HighScore",
                    "Exit"};
int n = JOptionPane.showOptionDialog(null,
    "Do you want to return to main menu?",
    "A Silly Question",
    JOptionPane.YES_NO_CANCEL_OPTION,
    JOptionPane.QUESTION_MESSAGE,
    null,
    options,
    options[2]);
        
        System.out.println(n);
    return -1;
    }
    public int endGame(){
    //put in username to see highscore, else you view from mainmenu if not empty
    return -1;
    }
    public int showHighscore(){
    //Awt list?
    return -1;
    }
    
}
