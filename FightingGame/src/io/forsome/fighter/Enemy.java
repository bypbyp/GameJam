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
        this.fighterSprite = sprite;
    }

    @Override
    public void createFighter(){
        fighterSprite = new Picture(700,300,"rsc/Mekie/0 - Idle/0.png");
        fighterSprite.draw();
    }

    @Override
    public void resetPosition() {
        fighterSprite.delete();
        //createFighter();
    }

    public void moveRight(){
        if(getMaxX() <= 1020){
            fighterSprite.translate(30,0);
        }
    }

    public void moveLeft(){
        if(getX() >= 40){
            fighterSprite.translate(-30,0);
        }
    }

    public void jump() {
        fighterSprite.translate(0, -10);
        fighterSprite.translate(0, -10);
        fighterSprite.translate(0, -10);
        land();
        System.out.println("Enemy jumped");
    }
    public void standUp() {
        fighterSprite.translate(0, 0);
    }

    public void land() {
        fighterSprite.translate(0, 30);
    }

    public void crouch(){
        fighterSprite.translate(0,0);
        standUp();
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
    // method that will check if he is attacking, have to implement.
    /* public boolean isAttacking(){
        return attacking;
    }

     */

    public void enemyWon(){
        fighterSprite.load("rsc/Mekie/9 - Win/0.png");
    }

    public void enemyLost(){
        fighterSprite.load("rsc/Mekie/10 - Lose/0.png");
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
