package io.forsome.gameartifacts;

import io.forsome.game.FightingGame;
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
        this.playerHealthBar = new Rectangle(130, 30, 200, 30);
        this.enemyHealthBar = new Rectangle(800, 30, 200, 30);
        this.timer = new Text(510, 30, "60"); // X Y String
        this.playerName = new Text(100, 80, "Player"); // X Y String
        this.enemyName = new Text(850, 80, "Enemy"); // X Y String
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

    public static class CountDownTimer implements Runnable{

        private int seconds;
        private int timer;

        public CountDownTimer(){
            this.seconds = 10;
        }

        public int getTimer() {
            return timer;
        }

        @Override
        public void run() {
            for(int i = seconds; i >= 0; i--){
                try {
                    Thread.sleep(1000);
                    timer = i;
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
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
