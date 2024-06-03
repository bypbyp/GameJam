package io.forsome.fighter;

import io.forsome.gameartifacts.HealthBar;
import io.forsome.gameartifacts.Position;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.*;

public class Enemy extends Fighter{

    private Position enemyPosition;
    private Picture fighterSprite;
    private HealthBar enemyLife;

    public Enemy(Picture fighter) {
        super(fighter);

        this.fighterSprite = new Picture(enemyPosition.getX(), enemyPosition.getY(), "rsc/player.png");
    }

    public HealthBar getEnemyLife() {
        return enemyLife;
    }

    public int getHealth(){
        return enemyLife.getHealth();
    }

    public Position getPosition(){
        return this.enemyPosition;
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
        fighterSprite.load("rsc/player/playerWin.png");
    }

    public void enemyLost(){
        fighterSprite.load("rsc/player/playerLost.png");
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


    public void resetPosition() {
        fighterSprite.translate(50, 200);
    }

    @Override
    public void createFighter() {
        fighterSprite.draw();
        enemyLife = new HealthBar(580,50,200);
        enemyLife.showLifeBar();
    }
}
