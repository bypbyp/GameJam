package io.forsome.gameartifacts;

import io.forsome.game.FightingGame;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class HUD {

    private int roundTimer = 60;

    private Rectangle playerHealthBar;
    private Rectangle enemyHealthBar;

    private Text timer;
    private Text playerName;
    private Text enemyName;
    private Picture playerSelect;
    private Picture enemySelect;

    private int enemyLifeBarX = 600;
    private int enemyLifeBarLifeSize = 350;
    private int playerLifeBarLifeSize = 350;
    public HUD() {

        //                                      x      y       lifeSize  height
        this.playerHealthBar = new Rectangle(90, 30, playerLifeBarLifeSize, 50);
        this.enemyHealthBar = new Rectangle(enemyLifeBarX, 30, enemyLifeBarLifeSize, 50);
        this.timer = new Text(515, 60, String.valueOf(roundTimer)); // X Y String
        this.playerName = new Text(150, 110, "Player"); // X Y String
        this.enemyName = new Text(850, 110, "Enemy");// X Y String
        this.playerSelect = new Picture(20,40,"rsc/player/SelectMekie(2).png");
        this.enemySelect = new Picture(900,40,"rsc/player/SelectMekie(2).png");
    }

    public void drawHUD() {
        playerHealthBar.draw();
        playerHealthBar.setColor(Color.GREEN);
        playerHealthBar.fill();

        enemyHealthBar.draw();
        enemyHealthBar.setColor(Color.GREEN);
        enemyHealthBar.fill();

        timer.setColor(Color.RED);
        timer.draw();
        timer.grow(70,70);

        playerName.setColor(Color.RED);
        playerName.draw();
        playerName.grow(50,50);

        enemyName.setColor(Color.BLUE);
        enemyName.draw();
        enemyName.grow(50,50);

        playerSelect.draw();
        enemySelect.draw();
    }
    public void delete(){
        playerHealthBar.delete();
        enemyHealthBar.delete();

        timer.delete();
        playerName.delete();
        playerSelect.delete();
        enemyName.delete();
        enemySelect.delete();
    }
    public void updateTimer(){
        timer.delete();
        timer = new Text(515,60,String.valueOf(roundTimer) );
        timer.setColor(Color.RED);
        timer.draw();
        timer.grow(70,70);
        roundTimer--;

    }

    public void resetRoundTimer(){
        this.roundTimer = 60;
    }
    //when taking damage probably has to create a new rectangle, because grow will decrease from both sidesll
    /*public int getRoundTimer(){
        return this.roundTimer;
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

     */


    public void damage() {
        enemyLifeBarX += 35;
        enemyLifeBarLifeSize -= 35;
        enemyHealthBar.delete();
        enemyHealthBar = new Rectangle(enemyLifeBarX, 30, enemyLifeBarLifeSize, 50);
        enemyHealthBar.setColor(Color.GREEN);
        if (enemyLifeBarLifeSize <= 230) {
            enemyHealthBar.setColor(Color.YELLOW);
        }
        if (enemyLifeBarLifeSize <= 90) {
            enemyHealthBar.setColor(Color.RED);
        }
        System.out.println(enemyLifeBarLifeSize);
        enemyHealthBar.draw();
        enemyHealthBar.fill();



    }


/*
    public void reset() {
        health = 100;
        createLifeBar();
        //lifeBar.setSize(100, 10);
    }
     */

}
