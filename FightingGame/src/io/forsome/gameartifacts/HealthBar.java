package io.forsome.gameartifacts;

import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.pictures.*;

public class HealthBar {
    private Rectangle lifeBar;
    private int health;

    public HealthBar(int x, int y, int health) {
        this.health = health;
        lifeBar = new Rectangle(x, y, health, 20);
        lifeBar.setColor(Color.GREEN);
        lifeBar.fill();
    }

    public Rectangle showLifeBar() {
        return lifeBar;
    }

    public void damage(int value) {
        health -= value;
        if (health <= 50) {
            lifeBar.setColor(Color.YELLOW);
        }
        if (health <= 20) {
            lifeBar.setColor(Color.RED);
        }
        lifeBar.fill();
    }

    public void reset() {
        health = 100;
        //lifeBar.setSize(100, 10);
        lifeBar.setColor(Color.GREEN);
        lifeBar.fill();
    }
}