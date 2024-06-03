package io.forsome.fighter;

import io.forsome.game.Background;
import io.forsome.gameartifacts.HealthBar;
import io.forsome.gameartifacts.Position;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player extends Fighter implements KeyboardHandler{

    private Keyboard keyboard;
    //private Position playerPosition;
    private Picture fighterSprite;
    private HealthBar playerLife;

    private int playerPositionX;
    private int playerPositionY;

    private boolean jumping = false;
    private boolean crouch = false;
    private boolean attacking = false;
    private Background playerLimits;

    public Player(Picture fighter) {
        super(fighter);

        this.playerPositionX = fighterSprite.getX();
        this.playerPositionY = fighterSprite.getY();
        this.fighterSprite = fighter;
        this.keyboard = new Keyboard(this);
        this.playerLimits = new Background();
        addKeyboard();
    }

    public HealthBar getPlayerLife() {
        return playerLife;
    }

    public int getHealth(){
        return playerLife.getHealth();
    }

    public void createFighter(){
        fighterSprite.draw();
        playerLife = new HealthBar(120,40,200);
        playerLife.createLifeBar();
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

    @Override
    public int getX() {
        return fighterSprite.getX();
    }

    @Override
    public int getY() {
        return fighterSprite.getY();
    }

    @Override
    public int getMaxX() {
        return fighterSprite.getMaxX();
    }

    @Override
    public int getMaxY() {
        return fighterSprite.getMaxY();
    }

    public void playerWon(){
        fighterSprite.load("rsc/player/playerWin.png");
    }

    public void playerLost(){
        fighterSprite.load("rsc/player/playerLost.png");
    }

    public void resetPosition() {
        fighterSprite.delete();
        createFighter();
    }

    public void resetIdlePosition(){
            this.jumping = !jumping;
            this.attacking = !attacking;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_D:
                if((fighterSprite.getX() <= playerLimits.getMaxX() -70) && !jumping){
                    moveRight();
                }
                break;
            case KeyboardEvent.KEY_A:
                if((fighterSprite.getX() > playerLimits.getX() +20) && !jumping) {
                    moveLeft();
                }
                break;
            case KeyboardEvent.KEY_W:
                if((fighterSprite.getY() >= playerLimits.getY()+30) && !jumping) {
                    jump();
                    //jumping = !jumping;
                }
                break;
            case KeyboardEvent.KEY_S:
                if((fighterSprite.getY() < 400) && !jumping){
                    crouch();
                }
                break;
            case KeyboardEvent.KEY_H:
                if(!attacking){
                    lightPunch();
                    //attacking = !attacking;
                }
                break;
            case KeyboardEvent.KEY_J:
                if(!attacking){
                    heavyPunch();
                    // attacking = !attacking;
                }
                break;
            case KeyboardEvent.KEY_K:
                if(!attacking){
                    lightKick();
                    //attacking = !attacking;
                }
                break;
            case KeyboardEvent.KEY_L:
                if(!attacking){
                    heavyKick();
                    //attacking = !attacking;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
