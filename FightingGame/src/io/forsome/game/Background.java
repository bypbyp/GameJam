package io.forsome.game;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import static org.academiadecodigo.simplegraphics.graphics.Canvas.*;


public class Background {

    Picture backgroundRelva;
    Picture backgroundSala;
    Picture menu;
    private int randomLevel;
    Picture win;
    Picture loose;

    public Background(){
        this.backgroundRelva = new Picture(10, 10, "rsc/BackGroundSalinha.JPG");
        this.backgroundSala = new Picture(10, 10, "rsc/BackGroundRelva.JPG");
        this.menu = new Picture(10,10,"rsc/MENU.png");
        this.win = new Picture(10,10,"rsc/YouWin.jpeg");
        this.loose = new Picture(10,10,"rsc/YouWin.jpeg");
    }

    public void createLevel() {
        randomLevel = (int) Math.floor(Math.random() * 2);
        switch (randomLevel) {
            case 0:
                backgroundRelva.draw();
                break;
            case 1:
                backgroundSala.draw();
                break;
        }
    }

    public void showMenu(){
        menu.draw();
    }

    public void hideMenu(){
        menu.delete();
    }

    public void showWin(){
        win.draw();
    }

    public void showLoose(){
        loose.draw();
    }

    public void hideWin(){
        win.delete();
    }
    public static void pause(){
        Canvas.pause();
    }

    public int getX(){
        return menu.getX();
    }

    public int getY(){
        return menu.getY();
    }

    public int getMaxX(){
        return menu.getMaxX();
    }

    public int getMaxY(){
        return menu.getMaxY();
    }
}