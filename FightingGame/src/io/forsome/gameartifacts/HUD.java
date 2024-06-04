package io.forsome.gameartifacts;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class HUD {

    private Rectangle playerHealthBar;
    private Rectangle enemyHealthBar;

    private Text timer;
    private Text playerName;
    private Text enemyName;

    public HUD() {
        this.playerHealthBar = new Rectangle(100, 10, 200, 20);
        this.enemyHealthBar = new Rectangle(400, 10, 200, 20);
        this.timer = new Text(510, 30, "60"); // X Y String
        this.playerName = new Text(100, 60, "Player"); // X Y String
        this.enemyName = new Text(600, 60, "Enemy"); // X Y String
        drawHUD();
    }

    public void drawHUD() {
        playerHealthBar.draw();
        playerHealthBar.setColor(Color.GREEN);
        playerHealthBar.fill();

        enemyHealthBar.draw();
        enemyHealthBar.setColor(Color.GREEN);
        enemyHealthBar.fill();

        timer.draw();
        playerName.draw();
        enemyName.draw();
    }

    /*
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
     */

}
