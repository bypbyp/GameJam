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
import org.academiadecodigo.simplegraphics.pictures.Picture;

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

    public FightingGame() {
        Rectangle gameScene = new Rectangle(10, 10, 1030, 603);
        timer = 60;
        round =3;
        this.keyboard = new Keyboard(this);
        addKeyboard();
        gameScene.draw();
        playerHealth = new HealthBar(20,20,200);
        enemyHealth = new HealthBar(20,20,200);
    }

    public void addKeyboard() {
        KeyboardEvent starGame = new KeyboardEvent();
        starGame.setKey(KeyboardEvent.KEY_SPACE);
        starGame.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(starGame);
    }

    public void startGame() {
        // Menu
        Background.limitCanvas();
        Rectangle screen = new Rectangle(10, 10, 1030, 603);
        screen.draw();
        Picture menu = new Picture(10, 10, "rsc/meno-comIntrucao.png");
        menu.draw();
    }

    public void gameInit() {
        // Stage Creation
        Background background = new Background();
        background.createBackground();
        // Player Creation
        player = new Player(new Picture(200, 200, "rsc/player.png"),
                new HealthBar(50, 50, 200),
                new Position(200, 200));
        player.createFighter();

        // Enemy Creation
        enemy = new Enemy(new Picture(200, 200, "rsc/enemy.png"),
                new HealthBar(780, 50, 200),
                new Position(800, 200));
        enemy.createFighter();

        // Main game loop:
        while (playerWins != 2 || enemyWins != 2) {
            playRound();
            round--;
        }
        startGame();
    }


    public void playRound() {
        // Reset positions, health, and timer for each round
        player.resetPosition();
        enemy.resetPosition();
        playerHealth.reset();
        enemyHealth.reset();
        timer = 60;


        // Round logic here
        while (timer > 0) {
            updateGame();
            Canvas.pause(); // Wait for 1 second
            timer--;
           System.out.println(timer);
            if(enemyHealth.getHealth() <= 0){
                playerWins++;
                return;
                // load a picture of 1 round won like a start under health bar

            }
            if(playerHealth.getHealth() <= 0){
                enemyWins++;
                return;
                // load a picture of 1 round won like a start under health bar

            }
        }
    }

    private void updateGame() {
        // Update game state, check for collisions, update health, etc.
        //enemy.randomMove();
        checkCollisions();
    }

    private void checkCollisions() {
        // Collision detection logic
        if ((player.getPosition()) == enemy.getPosition()) {
            playerHealth.damage(10); //Magic number
            enemyHealth.damage(10);
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

