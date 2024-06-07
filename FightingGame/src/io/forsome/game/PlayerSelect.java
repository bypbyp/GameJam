package io.forsome.game;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class PlayerSelect implements KeyboardHandler {

    Picture background;
    Picture arrow;
    private boolean selected = false;
    private Keyboard keyboard;

    public PlayerSelect() {
        this.background = new Picture(10, 10, "rsc/PlayerSelection.png");
        this.arrow = new Picture(70, 80, "rsc/arrow.png");
        this.keyboard = new Keyboard(this);
        addKeyboard();
        background.draw();
        arrow.grow(-80,-80);
        arrow.draw();
    }

    public void selection() {
        while (!selected) {
            // wait for player select;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        background.delete();
        arrow.delete();
    }

    public void moveLeft(){
        arrow.translate(-210,0);
    }

    public void moveRight(){
        arrow.translate(210,0);
    }

    public void addKeyboard() {
        KeyboardEvent selectPlayer = new KeyboardEvent();
        selectPlayer.setKey(KeyboardEvent.KEY_SPACE);
        selectPlayer.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(selectPlayer);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(left);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(right);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            selected = true;
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT) {
            moveLeft();
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) {
            moveRight();
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
