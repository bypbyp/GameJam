package io.forsome.fighter;

import io.forsome.game.Background;
import org.academiadecodigo.simplegraphics.pictures.*;

public class Enemy extends Fighter{

    private Picture fighterSprite;
    private Background playerLimit;

    private int playerPositionX;
    private int playerPositionY;

    public Enemy(Picture sprite) {
        super(sprite);
        //this.playerPositionX = fighter.getX();
        //this.playerPositionY = fighter.getY();
        this.fighterSprite = sprite;
        //this.playerLimit = new Background();
    }

    @Override
    public void createFighter(){
        fighterSprite.draw();
    }

    @Override
    public void resetPosition() {
        //fighterSprite.delete();
        createFighter();
    }

    public void moveRight(){
        fighterSprite.translate(30,0);
    }

    public void moveLeft(){
        fighterSprite.translate(-30,0);
    }

    public void jump(){
        fighterSprite.translate(0,-30);
    }

    public void crouch(){
        fighterSprite.translate(0,30);
    }

    public void lightPunch(){
        System.out.println(getClass().getSimpleName() + "  lightPunch");
    }

    public void heavyPunch(){
        System.out.println(getClass().getSimpleName() + " heavyPunch");
    }

    public void lightKick(){
        System.out.println(getClass().getSimpleName() + " lightKick");
    }

    public void heavyKick(){
        System.out.println("heavyKick");
    }

    public void randomMove(){
        int randomNumber = (int)Math.floor(Math.random()*8);
        switch (randomNumber){
            case 1 -> moveRight();
            case 2 -> moveLeft();
            case 3 -> jump();
            case 4 -> crouch();
            case 5 -> lightPunch();
            case 6 -> lightKick();
            case 7 -> heavyKick();
            case 8 -> heavyPunch();
        }
    }

    public void enemyWon(){
        fighterSprite.load("FightingGame/rsc/player/playerWin.png");
    }

    public void enemyLost(){
        fighterSprite.load("FightingGame/rsc/player/playerLose.png");
    }

    @Override
    public int getX() {
        return fighterSprite.getX();
    }

    @Override
    public int getY() {
        return fighterSprite.getY();
    }

    @Override
    public int getMaxX() {
        return fighterSprite.getMaxX();
    }

    @Override
    public int getMaxY() {
        return fighterSprite.getMaxY();
    }

}
