package io.forsome.game;

import io.forsome.fighter.Enemy;
import io.forsome.fighter.Player;
import io.forsome.gameartifacts.*;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.*;

public class FightingGame implements KeyboardHandler {

    // Game Attributes (ScreenSize: 1030, 603)
    public static boolean GAMEOVER = false;
    private int round = 3;
    private int playerWins = 0;
    private int enemyWins = 0;

    private Background background = new Background();
    //private Picture level;
    private HUD gameHUD = new HUD();

    // Fighters Attributes
    private Player player = new Player(new Picture(200, 300, "rsc/Mekie/0 - Idle/0.png"));
    private int playerHealth = 200;
    private Enemy enemy = new Enemy(new Picture(700, 300, "rsc/Mekie/0 - Idle/0.png"));
    private int enemyHealth = 200;

    // Timer
    private int roundTime = 60;
    private boolean timerRunning = false;

    // Menu Keyboard
    private boolean gameStarted = false;
    private Keyboard keyboard;

    // -------------------------------------------------------------
    // Constructor
    public FightingGame() {
        this.keyboard = new Keyboard(this);
        addKeyboard();
    }

    public int getRoundTime() {
        return roundTime;
    }

    public void gameStart() {
        // Show the menu and wait for the space key to be pressed
        background.showMenu();
        background.hideWin();
    }

    public void newGame() {
        background.hideMenu();
        //level = new Picture(10, 10, "rsc/BackGroundSalinha.JPG");
        //level.draw();
        background.createLevel();
        gameHUD.drawHUD();

        // Player and Enemy Creation
        player.createFighter();
        enemy.createFighter();

        // Main game loop:
        while (round > 0) {
            //gameHUD.resetRoundTimer();
            //gameHUD.delete();
            playRound();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Trying to fiz when finish the first game, 3 rounds.
            if(playerWins == 2 || enemyWins == 2){
                background.showWin();
                gameStarted = false;
                playerWins = 0;
                enemyWins = 0;
                round = 0;
                gameStart();
            }
        }
    }

    public void playRound() {
        //player.createFighter();
        //enemy.createFighter();
        playerHealth = 200;
        enemyHealth = 200;
        roundTime = 60;
        timerRunning = true;

        // Start the timer in a separate thread
        new Thread(this::startTimer).start();

        while (playerHealth > 0 && enemyHealth > 0 && roundTime > 0) {
            enemy.randomMove();

            // Add a delay to control the game loop speed
            try {
                Thread.sleep(300);
                checkCollisions();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        timerRunning = false; // Stop the timer when the round ends

        if (playerHealth > enemyHealth) {
            playerWins++;
            player.playerWon();
            enemy.enemyLost();
            gameHUD.resetRoundTimer(); // trying to fix the time delay
            Picture counter = new Picture(240, 100, "rsc/player/roundcounter.png");
            counter.draw();
            try {
                Thread.sleep(1000);
                player.resetPosition();
                player.createFighter();
                enemy.resetPosition();
                enemy.createFighter();
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            enemyWins++;
            enemy.enemyWon();
            player.playerLost();
            gameHUD.resetRoundTimer(); //same as above

        Picture counter = new Picture(440, 100, "rsc/player/roundcounter.png");
            counter.draw();
            try {
                Thread.sleep(1000);
                player.resetPosition();
                player.createFighter();
                enemy.resetPosition();
                enemy.createFighter();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        round--;
    }

    private void startTimer() {
        while (roundTime > 0 && timerRunning) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            roundTime--;
            gameHUD.updateTimer();
        }
    }

    private void checkCollisions() {
        if (player.getMaxX() >= enemy.getX()) {
            if(player.isAttacking()){
                enemyHealth -= 20;
                System.out.println(enemyHealth);
                gameHUD.damage();   //this is to decrease size of rectangle
                return;
            }
            //playerHealth -= 10;
        }
    }

    public void addKeyboard() {
        KeyboardEvent startGame = new KeyboardEvent();
        startGame.setKey(KeyboardEvent.KEY_SPACE);
        startGame.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(startGame);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            if (!gameStarted) {
                gameStarted = true;
                new Thread(this::newGame).start(); // Start the game loop in a new thread
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }

    public static void main(String[] args) {
        FightingGame game = new FightingGame();
        game.gameStart();
    }
}
