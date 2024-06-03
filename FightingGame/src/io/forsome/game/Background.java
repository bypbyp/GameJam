package io.forsome.game;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import static org.academiadecodigo.simplegraphics.graphics.Canvas.*;


public class Background {
    Picture backgroundRelva = new Picture(10, 10, "rsc/BackGroundRelva.JPG");
    Picture backgroundSala = new Picture(10, 10, "rsc/BackGroundSala.JPG");

    Picture menu = new Picture(10, 10, "rsc/meno-comIntrucao.png");

    private int randomBackground;
    public void createBackground() {
        randomBackground = (int) Math.floor(Math.random() * 2);
        switch (randomBackground) {
            case 0:
                backgroundRelva.draw();
                hide();

                break;
            case 1:
                backgroundSala.draw();
                hide();
                break;
        }
    }

    public void show(){
        menu.draw();
    }
    public void hide(){
        menu.delete();
    }

    public static void limitCanvas() {
        limitCanvasWidth(1030);
        limitCanvasHeight(603);
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