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
    private HUD gameHUD = new HUD();
    // Fighters Attributes
    private Player player = new Player(new Picture(200, 300, "rsc/Mekie - Left/0 - Idle/0.png"));
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

    public void playerSelection() {
        PlayerSelect playerSelect = new PlayerSelect();
        playerSelect.selection();
    }

    public void newGame() {
        background.hideMenu();
        background.createLevel();
        gameHUD.drawHUD();
        // Player and Enemy Creation
        player.createFighter();
        enemy.createFighter();

        // Main game loop:
        while (round > 0) {
            playRound();
            try {
                Thread.sleep(1000);
                checkCollisions();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (playerWins == 2) {
            background.showWin();
            return;
        }
        if (enemyWins == 2) {
            background.showLoose();
            return;
        }
        //gameStarted = false;
        playerWins = 0;
        enemyWins = 0;
        round = 3;
        //gameStart();
    }

    public void playRound() {
        resetRound();
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
        if (enemyHealth <= 0) {
            try {
                player.playerWon();
                enemy.enemyLost();
                playerWins++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (playerHealth <= 0) {
            try {
                player.playerLost();
                enemy.enemyWon();
                enemyWins++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        timerRunning = false; // Stop the timer when the round ends
        if (playerHealth > enemyHealth) {
            playerWins++;
            try {
                player.playerWon();
                enemy.enemyLost();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            enemyWins++;
            enemy.enemyWon();
            player.playerLost();
        }

        gameHUD.resetRoundTimer(); // Reset the round timer
        //showRoundEnd();
        round--;
    }

    private void resetRound() {
        gameHUD.delete();
        gameHUD.drawHUD();
        playerHealth = 200;
        enemyHealth = 200;
        roundTime = 60;
        timerRunning = true;
        player.resetPosition();
        enemy.resetPosition();
        player.createFighter();
        enemy.createFighter();
    }

    private void showRoundEnd() {
        Picture counter = new Picture(340, 100, "rsc/player/roundcounter.png");
        counter.draw();
        try {
            Thread.sleep(1000);
            counter.delete();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
            if (player.isAttacking()) {
                enemyHealth -= 20;
                System.out.println(enemyHealth);
                gameHUD.damage();   //this is to decrease size of rectangle
                return;
            }
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
                new Thread(() -> {
                    playerSelection(); // Perform player selection
                    newGame();         // Start the game after selection
                }).start(); // Start the game loop in a new thread
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }

    public static void main(String[] args) {
        FightingGame fg = new FightingGame();
        fg.gameStart();
    }

}
