/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author daca97002
 */
public class ReadAndPrint {

    public final String path = "c:\\t4\\data";

    public int readHighscore(String username, int points) {
        int highScore = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(File("c:\\t4\\data")));
            String line = reader.readLine();
            while (line != null) // read the score file line by line
            {
                try {
                    int score = Integer.parseInt(line.trim());   // parse each line as an int
                    if (score > highScore) // and keep track of the largest
                    {
                        highScore = score;
                    }
                } catch (NumberFormatException e1) {
                    // ignore invalid scores
                    //System.err.println("ignoring invalid score: " + line);
                }
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException ex) {
            System.err.println("ERROR reading scores from file");
        }
        return -1;
    }
}
