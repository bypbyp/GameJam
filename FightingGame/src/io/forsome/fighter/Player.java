package io.forsome.fighter;

import io.forsome.game.Background;
import io.forsome.gameartifacts.HUD;
import org.academiadecodigo.simplegraphics.keyboard.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import io.forsome.fighter.Enemy;


public class Player extends Fighter implements KeyboardHandler {

    private Keyboard keyboard;
    private Picture fighterSprite;

    private boolean jumping = false;
    private boolean crouching = false;
    private boolean attacking = false;

    public Player(Picture sprite) {
        super(sprite);
        this.fighterSprite = sprite;
        this.keyboard = new Keyboard(this);
        addKeyboard();
        //this.enemy = new Enemy(fighterSprite);
    }

    @Override
    public void createFighter() {
        fighterSprite = new Picture(200, 300, "rsc/Mekie/0 - Idle/0.png");
        fighterSprite.draw();

    }

    @Override
    public void resetPosition() {
        //fighterSprite.translate(playerLimits.getX() - fighterSprite.getX(),playerLimits.getY() - fighterSprite.getY());
        fighterSprite.delete();
        //createFighter();
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
        fighterSprite.translate(0, -10);
        fighterSprite.translate(0, -10);
        fighterSprite.translate(0, -10);
    }

    public void crouch() {
        fighterSprite.translate(0, 0);
    }

    public void standUp() {
        fighterSprite.translate(0, 0);
    }

    public void land() {
        fighterSprite.translate(0, 30);
    }

    public void lightPunch() {
        fighterSprite.load("rsc/Mekie/1 - High Punch/3.png");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    public boolean isAttacking() {
        return attacking;
    }

    public void playerWon() {
        fighterSprite.load("rsc/Mekie/9 - Win/0.png");
    }

    public void playerLost() {
        fighterSprite.load("rsc/Mekie/10 - Lose/0.png");
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_D:
                if (!jumping && !crouching && getMaxX() <= 1020) {
                    //if(getMaxX() <= enemy.getX() ){
                    moveRight();
                    // }

                }
                break;
            case KeyboardEvent.KEY_A:
                if (!jumping && !crouching && getX() >= 40) {
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
                    fighterSprite.load("rsc/Mekie/1 - High Punch/2.png");
                    //punchHigh();
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

                try {
                    Thread.sleep(200);
                    fighterSprite.load("rsc/Mekie/0 - Idle/0.png"); // Delay for 2000 milliseconds (2 seconds)
                    attacking = false;
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            case KeyboardEvent.KEY_J:
                attacking = false;
                break;
            case KeyboardEvent.KEY_K:
                attacking = false;
                break;
            case KeyboardEvent.KEY_L:
                attacking = false;
                break;
        }
    }
/*
    public void punchHigh() {
        fighterSprite.load("rsc/Mekie/1 - High Punch/1.png");
        try {
            Thread.sleep(200); // Delay for 2000 milliseconds (2 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fighterSprite.load("rsc/Mekie/1 - High Punch/1.png");
        try {
            Thread.sleep(200); // Delay for 2000 milliseconds (2 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fighterSprite.load("rsc/Mekie/1 - High Punch/2.png");
        try {
            Thread.sleep(200); // Delay for 2000 milliseconds (2 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fighterSprite.load("rsc/Mekie/1 - High Punch/3.png");
        try {
            Thread.sleep(200); // Delay for 2000 milliseconds (2 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fighterSprite.load("rsc/Mekie/1 - High Punch/4.png");
        try {
            Thread.sleep(200); // Delay for 2000 milliseconds (2 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fighterSprite.load("rsc/Mekie/1 - High Punch/5.png");
        try {
            Thread.sleep(200); // Delay for 2000 milliseconds (2 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}
