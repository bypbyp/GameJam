package io.forsome.fighter;

import io.forsome.game.Background;
import io.forsome.gameartifacts.HealthBar;
import io.forsome.gameartifacts.Position;
import org.academiadecodigo.simplegraphics.keyboard.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player extends Fighter implements KeyboardHandler{

    private Keyboard keyboard;
    private Position playerPosition;
    private Picture fighterSprite;
    private HealthBar playerLife;

    private Background playerLimits;

    public Player(Picture fighter, HealthBar healthBar, Position position) {
        super(fighter, healthBar, position);

        this.playerPosition = position;
        this.fighterSprite = fighter;
        this.playerLife = healthBar;

        this.keyboard = new Keyboard(this);
        this.playerLimits = new Background();
        addKeyboard();
    }

    public void createFighter(){
        fighterSprite.draw();
    }

    public Position getPosition(){
        return this.playerPosition;
    }

    public void addKeyboard(){
        KeyboardEvent moveRight = new KeyboardEvent();
        moveRight.setKey(KeyboardEvent.KEY_D);
        moveRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveRight);

        KeyboardEvent moveLeft = new KeyboardEvent();
        moveLeft.setKey(KeyboardEvent.KEY_A);
        moveLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveLeft);

        KeyboardEvent jump = new KeyboardEvent();
        jump.setKey(KeyboardEvent.KEY_W);
        jump.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(jump);

        KeyboardEvent couch = new KeyboardEvent();
        couch.setKey(KeyboardEvent.KEY_S);
        couch.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(couch);

        KeyboardEvent lightPunch = new KeyboardEvent();
        lightPunch.setKey(KeyboardEvent.KEY_H);
        lightPunch.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(lightPunch);

        KeyboardEvent heavyPunch = new KeyboardEvent();
        heavyPunch.setKey(KeyboardEvent.KEY_J);
        heavyPunch.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(heavyPunch);

        KeyboardEvent softKick = new KeyboardEvent();
        softKick.setKey(KeyboardEvent.KEY_K);
        softKick.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(softKick);

        KeyboardEvent heavyKick = new KeyboardEvent();
        heavyKick.setKey(KeyboardEvent.KEY_L);
        heavyKick.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(heavyKick);
    }

    public void moveRight(){
        fighterSprite.translate(30,0);
    }

    public void moveLeft(){
        fighterSprite.translate(-30,0);
    }

    public void jump(){
        fighterSprite.translate(0,-30);
    }

    public void crouch(){
        fighterSprite.translate(0,30);
    }

    public void lightPunch(){
        System.out.println("lightPunch");
    }

    public void heavyPunch(){
        System.out.println("heavyPunch");
    }

    public void lightKick(){
        System.out.println("lightKick");
    }

    public void heavyKick(){
        System.out.println("heavyKick");
    }


    public void resetPosition() {
        fighterSprite.translate(50, 200);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_D -> moveRight();
            case KeyboardEvent.KEY_A -> moveLeft();
            case KeyboardEvent.KEY_W -> jump();
            case KeyboardEvent.KEY_S -> crouch();

            case KeyboardEvent.KEY_H -> lightPunch();
            case KeyboardEvent.KEY_J -> heavyPunch();
            case KeyboardEvent.KEY_K -> lightKick();
            case KeyboardEvent.KEY_L -> heavyKick();
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
