package io.forsome.gameartifacts;

import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.pictures.*;

public class HealthBar {
    private Rectangle lifeBar;
    private int health;

    public HealthBar(int x, int y, int health) {
        this.health = health;

    }
    public void createLifeBar(){
        lifeBar.setColor(Color.GREEN);
        lifeBar.fill();
    }

    public int getHealth(){
        return health;
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
        createLifeBar();
        //lifeBar.setSize(100, 10);
    }

    public void showLifeBar(){
        lifeBar.draw();
    }
}