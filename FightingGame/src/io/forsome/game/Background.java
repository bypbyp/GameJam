package io.forsome.game;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import static org.academiadecodigo.simplegraphics.graphics.Canvas.*;


public class Background {
    Picture backgroundRelva = new Picture(10, 10, "rsc/BackGroundRelva.JPG");
    Picture backgroundSala = new Picture(10, 10, "rsc/BackGroundSala.JPG");

    private int randomBackground;

    public void createBackground() {
        randomBackground = (int) Math.floor(Math.random() * 2);
        System.out.println(randomBackground);
        switch (randomBackground) {
            case 0:
                backgroundRelva.draw();
                break;
            case 1:
                backgroundSala.draw();
                break;
        }
    }

    public static void limitCanvas() {
        limitCanvasWidth(1030);
        limitCanvasHeight(603);
    }
}