package io.forsome.fighter;

import io.forsome.gameartifacts.Position;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Fighter {

    private Picture fighter;

    public Fighter(Picture fighter) {
        this.fighter = fighter;
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

    // Positions
    public abstract int getX();
    public abstract int getY();
    public abstract int getMaxX();
    public abstract int getMaxY();

}
