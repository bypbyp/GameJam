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
    private Picture playerName;
    private Picture selectedPlayer;
    private Picture enemyName;
    private Picture selectedEnemy;

    private int enemyLifeBarX = 600;
    private int enemyLifeBarLifeSize = 350;
    private int playerLifeBarLifeSize = 350;

    public HUD() {
        //                                      x      y       lifeSize  height
        this.playerHealthBar = new Rectangle(90, 30, playerLifeBarLifeSize, 50);
        this.enemyHealthBar = new Rectangle(enemyLifeBarX, 30, enemyLifeBarLifeSize, 50);
        this.timer = new Text(515, 60, String.valueOf(roundTimer)); // X Y String
        timer.grow(70,70);
        this.playerName = new Picture(150, 100, "rsc/Names/MEKIE_NAME.png"); // X Y String
        this.enemyName = new Picture(710, 100, "rsc/Names/NOZK-NAME.png");// X Y String
        this.selectedPlayer = new Picture(40,100,"rsc/Mekie/11 - PIC/square.png");
        this.selectedEnemy = new Picture(900,100,"rsc/Mekie/11 - PIC/square.png");
    }

    public void drawHUD() {
        delete();
        enemyLifeBarX = 600;
        enemyLifeBarLifeSize = 350;
        playerLifeBarLifeSize = 350;
        // Player Life
        playerHealthBar = new Rectangle(90, 30, playerLifeBarLifeSize, 50);
        playerHealthBar.draw();
        playerHealthBar.setColor(Color.GREEN);
        playerHealthBar.fill();
        // EnemyLife
        enemyHealthBar = new Rectangle(enemyLifeBarX, 30, enemyLifeBarLifeSize, 50);
        enemyHealthBar.draw();
        enemyHealthBar.setColor(Color.GREEN);
        enemyHealthBar.fill();
        // Timer
        timer.setColor(Color.RED);
        timer.draw();
        // Names
        playerName.draw();
        selectedPlayer.draw();
        enemyName.draw();
        selectedEnemy.draw();

    }

    public void delete(){
        playerHealthBar.delete();
        enemyHealthBar.delete();
        timer.delete();
        playerName.delete();
        enemyName.delete();
        enemyName.delete();
        selectedEnemy.delete();
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
    public void reset() {
        health = 100;
        createLifeBar();
        //lifeBar.setSize(100, 10);
    }
     */

}
