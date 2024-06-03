package io.forsome.game;

import io.forsome.fighter.Enemy;
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
    private HealthBar playerHealth;
    private Enemy enemy;
    private HealthBar enemyHealth;
    private Keyboard keyboard;
    private int round;
    private int timer;
    private int playerWins;
    private int enemyWins;
    private Background background;

    public FightingGame() {
        //Rectangle gameScene = new Rectangle(10, 10, 1030, 603);
        timer = 60;
        round =3;
        this.keyboard = new Keyboard(this);
        addKeyboard();
        //gameScene.draw();
        player = new Player( playerHealth,new Position(200,200));
        enemy = new Enemy(enemyHealth,new Position(800,200));
        playerHealth = new HealthBar(100);
        enemyHealth = new HealthBar(100);
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
        playerHealth.reset();
        System.out.println(player.getPosition());
        enemyHealth.reset();
        System.out.println(enemy.getPosition());
        timer = 60;

        // Round logic here
        while (timer > 0) {
            updateGame();
            //background.pause();
            System.out.println("timer: " + timer);
            timer--;//this pause is giving error
            Canvas.pause();// Wait for 1 second

            if(enemyHealth.getHealth() <= 0){
                playerWins++;
                round--;

                player.setFighterSpriteWon();
                new Picture(440,100,"rsc/player/roundcounter.png");
                return;
                // load a picture of 1 round won like a start under health bar

            }
            if(playerHealth.getHealth() <= 0){
                enemyWins++;
                round--;

                enemy.setFighterSpriteWon();
                Picture counter = new Picture(440,100,"rsc/player/roundcounter.png");
                counter.draw();
                return;
                // load a picture of 1 round won like a start under health bar
            }
                if(timer == 0){
                    //playerHealth.getHealth() > enemyHealth.getHealth() ? playerWins += 1 : enemyWins += 1; //Fix this
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
            playerHealth.damage(10);
            player.setRectangleOflife(10);//change the rectangle life
            enemyHealth.damage(10);
            enemy.setRectangleOflife(10);//change the rectangle life

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

