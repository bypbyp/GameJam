package io.forsome.game;

import io.forsome.fighter.Enemy;
import io.forsome.fighter.Fighter;
import io.forsome.fighter.Player;
import io.forsome.gameartifacts.*;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.*;

public class FightingGame implements KeyboardHandler {

    public static boolean GAMEOVER = false;
    //private static Collision collision;
    //private static Music gameMusic;
    private Player player;
    private int playerHealth;

    private Enemy enemy;
    private int enemyHealth;

    private Keyboard keyboard;

    private int round;
    private int timer;

    private int playerWins;
    private int enemyWins;

    private Background background;

    public FightingGame() {
        //Rectangle gameScene = new Rectangle(10, 10, 1030, 603);
        timer = 60;
        round = 3;
        this.keyboard = new Keyboard(this);
        addKeyboard();
        //gameScene.draw();

        player = new Player(new Picture(200, 200, ""));
        enemy = new Enemy(new Picture(700, 200, ""));

        playerHealth = player.getHealth();
        enemyHealth = enemy.getHealth();
        background = new Background();

    }

    public void addKeyboard() {
        KeyboardEvent starGame = new KeyboardEvent();
        starGame.setKey(KeyboardEvent.KEY_SPACE);
        starGame.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(starGame);
    }

    public void startGame() {
        // Menu
        background.show();
        background.limitCanvas();
    }

    public void gameInit() {
        // Stage Creation
        background.createBackground();
        // Player Creation
        player.createFighter();

        // Enemy Creation
        enemy.createFighter();
        // Main game loop:
        while (round >= 1) {
            playRound();
            System.out.println("KAWABANGA");
        }


    }


    public void playRound() {
        // Reset positions, health, and timer for each round
        player.resetPosition();
        enemy.resetPosition();
        timer = 60;

        // Round logic here
        while (timer > 0) {
            updateGame();

            System.out.println("timer: " + timer);
            timer--;//this pause is giving error
            Canvas.pause();// Wait for 1 second

            if (enemy.getHealth() <= 0) {
                playerWins++;
                round--;
                player.playerWon();
                Picture counter = new Picture(240, 100, "rsc/player/roundcounter.png");
                counter.draw();
                return;
                // load a picture of 1 round won like a start under health bar

            }
            if (player.getHealth() <= 0) {
                enemyWins++;
                round--;
                enemy.enemyWon();
                Picture counter = new Picture(440, 100, "rsc/player/roundcounter.png");
                counter.draw();
                return;
                // load a picture of 1 round won like a start under health bar
            }
            if (timer == 0) {
                if(player.getHealth() > enemy.getHealth()){
                    playerWins += 1;
                }
                enemyWins += 1;
                return;
            }

        }
    }

    private void updateGame() {
        // Update game state, check for collisions, update health, etc.
        enemy.randomMove();
        player.resetIdlePosition();
        checkCollisions();

    }

    private void checkCollisions() {
        // Collision detection logic
        if ((player.getMaxX()) != enemy.getX()) {
            System.out.println("alabama");
            player.getPlayerLife().damage(10);
            enemy.getEnemyLife().damage(10);
        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            gameInit();
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}

