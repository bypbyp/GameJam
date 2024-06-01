package io.forsome.fighter;

import io.forsome.gameartifacts.HealthBar;
import io.forsome.gameartifacts.Position;
import org.academiadecodigo.simplegraphics.pictures.*;

public class Enemy extends Fighter{

    private Position enemyPosition;
    private Picture fighterSprite;
    private HealthBar enemyLife;

    public Enemy(Picture fighter, HealthBar healthBar, Position position) {
        super(fighter, healthBar, position);

        this.enemyPosition = position;
        this.fighterSprite = new Picture(enemyPosition.getX(), enemyPosition.getY(), "rsc/player.png");
        this.enemyLife = healthBar;
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
        System.out.println("lightPunch");
    }

    public void heavyPunch(){
        System.out.println("heavyPunch");
    }

    public void lightKick(){
        System.out.println("lightKick");
    }

    public void heavyKick(){
        System.out.println("heavyKick");
    }


    public void resetPosition() {
        fighterSprite.translate(50, 200);
    }

    @Override
    public void createFighter() {
        fighterSprite.draw();
    }
}
