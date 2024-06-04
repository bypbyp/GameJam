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

    // Game Attributes (ScreenSize: 1030, 603)
    public static boolean GAMEOVER = false;
    private int round = 3;
    private int timer = 60;
    private int playerWins;
    private int enemyWins;
    private Background background = new Background();
    private Background level = new Background();
    //private static Collision collision;
    //private static Music gameMusic;
    private HUD gameHUD = new HUD();

    // Fighters Attributes
    private Player player = new Player(new Picture(200, 200, "FightingGame/rsc/player.png"));
    private int playerHealth = 200;
    private Enemy enemy = new Enemy(new Picture(700, 200, "FightingGame/rsc/enemy.png"));
    private int enemyHealth = 200;

    // Menu Keyboard;
    private boolean gameStarted = false;
    private Keyboard keyboard;

    // -------------------------------------------------------------
    // Constructor
    public FightingGame() {
        this.keyboard = new Keyboard(this);
        addKeyboard();
    }

    public void gameStart() {
        // Menu
        //Background.limitCanvas();
        background.showMenu();
    }

    public void newGame(){
        //hide menu
        background.hideMenu();
        // Stage Creation
        level.createLevel();


        // Player and Enemy Creation
        player.createFighter(); // olhar estes metodos
        enemy.createFighter();// olhar estes metodos


        // Main game loop:
        while (round > 0) {
            playRound();
            System.out.println("Game has started");
        }
        gameStarted = false;
    }


    //Work HEre

    public void playRound() {
        // Reset positions, health, and timer for each round\
        System.out.println("OOOOOIIIIIIIII");
        gameHUD.drawHUD();
        player.resetPosition();
        enemy.resetPosition();
        timer = 60;

        // Round logic here
        while (timer > 0) {
            updateGame();

            System.out.println("timer: " + timer);
           // timer--;//this pause is giving error
            
            if (enemyHealth <= 0) {
                playerWins++;
                round--;
                player.playerWon();
                enemy.enemyLost();
                Picture counter = new Picture(240, 100, "FightingGame/rsc/player/roundcounter.png");
                counter.draw();
                return;
                // load a picture of 1 round won like a start under health bar

            }
            if (playerHealth <= 0) {
                enemyWins++;
                round--;
                enemy.enemyWon();
                player.playerLost();
                Picture counter = new Picture(440, 100, "FightingGame/rsc/player/roundcounter.png");
                counter.draw();
                return;
                // load a picture of 1 round won like a start under health bar
            }
            if (timer == 0) {
                if (playerHealth > enemyHealth) {
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
            playerHealth -= 10;
            enemyHealth -= 10;
        }
    }

    public void addKeyboard() {
        KeyboardEvent starGame = new KeyboardEvent();
        starGame.setKey(KeyboardEvent.KEY_SPACE);
        starGame.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(starGame);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            if(!gameStarted){
                newGame();
                gameStarted = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {}
}

