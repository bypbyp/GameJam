package io.forsome.fighter;

import io.forsome.game.Background;
import org.academiadecodigo.simplegraphics.keyboard.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player extends Fighter implements KeyboardHandler {

    private Keyboard keyboard;
    private Picture fighterSprite;

    private boolean jumping = false;
    private boolean crouching = false;
    private boolean attacking = false;
    private Background playerLimits;

    public Player(Picture sprite) {
        super(sprite);
        this.fighterSprite = sprite;
        this.keyboard = new Keyboard(this);
        addKeyboard();
    }

    @Override
    public void createFighter() {
        fighterSprite.draw();
    }

    @Override
    public void resetPosition() {
        fighterSprite.translate(playerLimits.getX() - fighterSprite.getX(),
                playerLimits.getY() - fighterSprite.getY());
        createFighter();
    }

    public void addKeyboard() {
        addKeyEvent(KeyboardEvent.KEY_D, KeyboardEventType.KEY_PRESSED);
        addKeyEvent(KeyboardEvent.KEY_A, KeyboardEventType.KEY_PRESSED);
        addKeyEvent(KeyboardEvent.KEY_W, KeyboardEventType.KEY_PRESSED);
        addKeyEvent(KeyboardEvent.KEY_S, KeyboardEventType.KEY_PRESSED);
        addKeyEvent(KeyboardEvent.KEY_H, KeyboardEventType.KEY_PRESSED);
        addKeyEvent(KeyboardEvent.KEY_J, KeyboardEventType.KEY_PRESSED);
        addKeyEvent(KeyboardEvent.KEY_K, KeyboardEventType.KEY_PRESSED);
        addKeyEvent(KeyboardEvent.KEY_L, KeyboardEventType.KEY_PRESSED);

        addKeyEvent(KeyboardEvent.KEY_W, KeyboardEventType.KEY_RELEASED);
        addKeyEvent(KeyboardEvent.KEY_S, KeyboardEventType.KEY_RELEASED);
        addKeyEvent(KeyboardEvent.KEY_H, KeyboardEventType.KEY_RELEASED);
        addKeyEvent(KeyboardEvent.KEY_J, KeyboardEventType.KEY_RELEASED);
        addKeyEvent(KeyboardEvent.KEY_K, KeyboardEventType.KEY_RELEASED);
        addKeyEvent(KeyboardEvent.KEY_L, KeyboardEventType.KEY_RELEASED);
    }

    private void addKeyEvent(int key, KeyboardEventType type) {
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(key);
        event.setKeyboardEventType(type);
        keyboard.addEventListener(event);
    }

    public void moveRight() {
        fighterSprite.translate(30, 0);
    }

    public void moveLeft() {
        fighterSprite.translate(-30, 0);
    }

    public void jump() {
        fighterSprite.translate(0, -30);
    }

    public void crouch() {
        fighterSprite.translate(0, 30);
    }

    public void standUp() {
        fighterSprite.translate(0, -30);
    }

    public void land() {
        fighterSprite.translate(0, 30);
    }

    public void lightPunch() {
        System.out.println("lightPunch");
    }

    public void heavyPunch() {
        System.out.println("heavyPunch");
    }

    public void lightKick() {
        System.out.println("lightKick");
    }

    public void heavyKick() {
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

    public void playerWon() {
        fighterSprite.load("rsc/player/playerWin.png");
    }

    public void playerLost() {
        fighterSprite.load("rsc/player/playerLose.png");
    }

    public void resetIdlePosition() {
        this.jumping = false;
        this.attacking = false;
        this.crouching = false;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_D:
                if (!jumping && !crouching && !attacking) {
                    moveRight();
                }
                break;
            case KeyboardEvent.KEY_A:
                if (!jumping && !crouching && !attacking) {
                    moveLeft();
                }
                break;
            case KeyboardEvent.KEY_W:
                if (!jumping && !attacking) {
                    jump();
                    jumping = true;
                }
                break;
            case KeyboardEvent.KEY_S:
                if (!jumping && !attacking) {
                    crouch();
                    crouching = true;
                }
                break;
            case KeyboardEvent.KEY_H:
                if (!attacking) {
                    lightPunch();
                    attacking = true;
                }
                break;
            case KeyboardEvent.KEY_J:
                if (!attacking) {
                    heavyPunch();
                    attacking = true;
                }
                break;
            case KeyboardEvent.KEY_K:
                if (!attacking) {
                    lightKick();
                    attacking = true;
                }
                break;
            case KeyboardEvent.KEY_L:
                if (!attacking) {
                    heavyKick();
                    attacking = true;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_W:
                if (jumping) {
                    land();
                    jumping = false;
                }
                break;
            case KeyboardEvent.KEY_S:
                if (crouching) {
                    standUp();
                    crouching = false;
                }
                break;
            case KeyboardEvent.KEY_H:
            case KeyboardEvent.KEY_J:
            case KeyboardEvent.KEY_K:
            case KeyboardEvent.KEY_L:
                attacking = false;
                break;
        }
    }
}
