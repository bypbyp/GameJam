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

    public FightingGame() {
        Rectangle gameScene = new Rectangle(10, 10, 1030, 603);
        timer = 60;
        round =3;
        this.keyboard = new Keyboard(this);
        addKeyboard();
        gameScene.draw();
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
        Picture menu = new Picture(10, 10, "rsc/menu.png");
        menu.draw();
    }

    public void gameInit() {
        // Stage Creation
        Background background = new Background();
        background.createBackground();
        // Player Creation
        player = new Player(new Picture(200, 300, "rsc/player.png"),
                new HealthBar(50, 50, 200),
                new Position(200, 300));
        player.createFighter();

        // Enemy Creation
        enemy = new Enemy(new Picture(200, 300, "rsc/enemy.png"),
                new HealthBar(780, 50, 200),
                new Position(830, 300));
        enemy.createFighter();

        // Main game loop:
        while (round > 0) {
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

