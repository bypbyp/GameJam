package io.forsome.fighter;

import io.forsome.gameartifacts.HealthBar;
import io.forsome.gameartifacts.Position;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Fighter {

    private Picture fighter;
    private HealthBar healthbar;
    private Position position;
    private Rectangle rectangleOfLife;

    public Fighter( HealthBar healthbar, Position position) {
       // this.fighter = fighter;
        this.healthbar = healthbar;
        this.position = position;
        this.rectangleOfLife = new Rectangle();
    }

    // Basic Movements
    public abstract void moveRight();
    public abstract void moveLeft();
    public abstract void jump();
    public abstract void crouch();

    // Skills
    public abstract void lightPunch();
    public abstract void heavyPunch();
    public abstract void lightKick();
    public abstract void heavyKick();

    // Other Methods
    public abstract void resetPosition();
    public abstract void createFighter();

    public abstract int getX();
    public abstract int getY();
    public abstract int getMaxX();
    public abstract int getMaxY();


    @Override
    public String toString(){
        return getClass().getSimpleName() + this.healthbar;
    }
}
