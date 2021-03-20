/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.misc;

import gamemakerstudio_.entities.RangeArea;
import gamemakerstudio_.entities.player2_;
import gamemakerstudio_.entities.player_;
import gamemakerstudio_.game_;
import gamemakerstudio_.gui.hud2_;
import gamemakerstudio_.gui.hud_;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.UUID;

/**
 *
 * @author ACER
 */
public class handler_ {
    // class
    hud_ hud;
    hud2_ hud2;

    // op
    public boolean optimize = true; // turn this off at your own risk, also electrocute lags if it's false

    // music shenanigans
    public int total_bars = 1;
    public int total_beats = 1;
    public int fourbarticks = 1;

    public int total_bars_steps = 1;
    public int total_steps = 1;
    public int fourbarsteps = 1;

    // animation shenanigans
    public static int goRight = 20;
    public static int goLeft = -20;
    public static int goUp = -20;
    public static int goDown = 20;
    public static int stay = 0;

    public int animateVelX = 0;
    public int animateVelY = 0;

    public static int spdp1 = 5;
    public static int spdp2 = 5;

    // entity, this mf lags as hell
    public LinkedList<gameobject_> object = new LinkedList <gameobject_>();

    public void sortingAlgorithm(){
        // sort this, bogo sort TODO: WHY IS THIS BUGGY!?
        // edit: weird fix
        // TODO: make objects hold IDs and LayerNos
        try {
            object.sort((o1, o2) -> {
                if (o1.getId() != null){
                    switch (o1.getId()){
                        case Trail:
                            return -2;
                        case PortalBlue:
                        case PortalRed:
                            return -3;
                        case OSC:
                            return -4;
                        case Mandelbrot:
                            return -5;
                        case RenderTexture:
                            return 0;
                        default:
                            return -1;
                    }
                }
                else return 0;
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void tick() {
        for (int i = 0; i < object.size(); i++/*int i = object.size() - 1; i >= 0; i--*/) {
            gameobject_ tempObject = object.get(i);
            tempObject.tick();
        }
        // test
        sortingAlgorithm();
    }
    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++/*int i = object.size() - 1; i >= 0; i--*/) {
            gameobject_ tempObject = object.get(i);
            tempObject.render(g);
        }
    }
    // reset everything and spawn players
    public void clearEnemies() {
        for (int i = object.size() - 1; i >= 0; i--) {
            try {
                gameobject_ tempObject = object.get(i);
                whiteList(tempObject);
            } catch (Exception e) {
                e.printStackTrace();
                int a = JOptionPane.showConfirmDialog(null, "An error occurred: " + e + ", " +
                        "\ndo you still wish to continue?", "Error", JOptionPane.INFORMATION_MESSAGE);
                if (a == JOptionPane.NO_OPTION) System.exit(0);
            }
        }
        // shit codes lol
        addObject(new player_(50, 200, ID.Player, this, hud));
        addObject(new RangeArea(0, 0, ID.P1Range, this));
        if (game_.multiplayer) {
            addObject(new player2_(game_.WIDTH - 128, 200, ID.Player2, this, hud2));
            addObject(new RangeArea(0, 0, ID.P2Range, this));
        }
    }
    // reset everything except players
    // do not kill players using these, see devconsole_ removeEntities
    public void removeObjectsExceptPlayers() {
        // remove objects except players
        for (int i = object.size() - 1; i >= 0; i--) {
            gameobject_ tempObject = object.get(i);
            // ignore players
            switch (tempObject.getId()){
                case Player:
                case Player2:
                case P1Range:
                case P2Range:
                    break;
                default:
                    whiteList(tempObject);
            }
        }
    }
    public void whiteList(gameobject_ tempObject){
        switch (tempObject.getId()){
            case Trail:
            case CURSOR:
            case NULL:
            case OSC:
            case RenderTexture:
            case Mandelbrot:
                break;
            default:
                removeObject(tempObject);
        }
    }
    // reset selected
    public void removeAllSelectedObjects(ID id) {
        for (int i = object.size() - 1; i >= 0; i--) {
            gameobject_ tempObject = object.get(i);
            if (tempObject.getId() == id) {
                removeObject(tempObject);
            }
        }
    }



    // music stuff, i dunno why is this here
    public void metronomeCode () {
        // metronome codes
        if (fourbarticks == 4) fourbarticks = 0;
        fourbarticks++;
        total_beats++;
        if (fourbarticks == 1) {
            total_bars++;
            if (game_.metronomeSounds) audioplayer_.getSound("first_tick").play();
        } else if (game_.metronomeSounds) audioplayer_.getSound("tick").play();
    }
    public void stepsBeta() {
        if (fourbarsteps == 16) fourbarsteps = 0;
        fourbarsteps++;
        total_steps++;
        if (fourbarsteps == 1) {
            total_bars_steps++;
        }
    }

    public gameobject_ addObject(gameobject_ object) {
        // modified for optimization, trails
        if (optimize){
            if (this.object.size() >= 250 /*change this number to limit trail entities*/) {
                if (object.getId() != ID.Trail) {
                    this.object.add(object);
                }
            } else this.object.add(object);
        } else this.object.add(object);

        // linkedlist
        gameobject_ tempObject = this.object.getLast();



        // System.out.println("Successfully added " + object + " with ID of " + object.getId() + " at " + object.getX() + ", " + object.getY());

        return tempObject;
    }
    public gameobject_ removeObject(gameobject_ object) {
        this.object.remove(object);

        // System.out.println("Successfully removed " + object + " with ID of " + object.getId() + " at " + object.getX() + ", " + object.getY());

        return null;
    }
    // this is still beta
    public gameobject_ getObject(ID id){
        gameobject_ tempObject;
        for (int i = object.size() - 1; i >= 0; i--) {
            tempObject = object.get(i);
            if (tempObject.getId() == id) {
                return tempObject;
            }
        }
        return null;
    }

    // util test
    public static String generateString() {
        return UUID.randomUUID().toString();
    }
}
