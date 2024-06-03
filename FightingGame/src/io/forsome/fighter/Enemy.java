package io.forsome.fighter;

import io.forsome.gameartifacts.HealthBar;
import io.forsome.gameartifacts.Position;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.*;

public class Enemy extends Fighter{

    private Position enemyPosition;
    private Picture fighterSprite;
    private HealthBar healthBar;
    private Rectangle rectangleOflife;


    public Enemy( HealthBar healthBar, Position position) {
        super(healthBar, position);
        this.enemyPosition = position;
        this.fighterSprite =  new Picture(800, 400, "rsc/enemy.png");
        this.healthBar = healthBar;
    }
    public Rectangle setRectangleOflife(int value) {
        rectangleOflife.grow(-value,0);
        rectangleOflife.fill();
        return rectangleOflife;
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
        fighterSprite.translate(0,0);
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
        System.out.println(getClass().getSimpleName() + " heavyKick");
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

    public void resetPosition() {
        enemyPosition = new Position(200,800);
    }
    public void setFighterSpriteWon(){ fighterSprite.load("rsc/player/playerWin.png");};
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

    @Override
    public void createFighter() {
        fighterSprite.draw();
        //fighterSprite.grow(130,130);
        this.rectangleOflife = new Rectangle(580,40,350,50);
        rectangleOflife.setColor(Color.GREEN);
        rectangleOflife.fill();
    }
    public Rectangle getRectangleOflife(){
        return rectangleOflife;
    }
    public Picture getFighterSprite(){return fighterSprite;};
}
