package org.example;

//import controller.Constants;
//import controller.Update;
//import model.charactersModel.BallModel;
//import view.GlassFrame;
//import view.MotionPanel;

import controller.KeyController;
import controller.Update;
import model.charactersModel.EpsilonModel;
import model.charactersModel.SquarantineModel;
import view.myFrame;
import view.myPanel;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.Random;

import static controller.Constants.*;

public class Main {
    static Random rng = new Random();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            myFrame.getINSTANCE();
            myPanel.getINSTANCE();
            System.out.println(FPS);
            new Update();
//                            double randomX = rng.nextDouble(myPanel.getINSTANCE().getX(),myPanel.getINSTANCE().getX()+myPanel.getINSTANCE().getWidth());
//                double randomY = rng.nextDouble(myPanel.getINSTANCE().getY(),myPanel.getINSTANCE().getY()+myPanel.getINSTANCE().getHeight());
            new SquarantineModel(new Point2D.Double(500,500), (double) SQUARANTINE_EDGE /2);
//                            new model.charactersModel.EpsilonModel(new Point2D.Double(500,500),MAX_RADIUS);

            new EpsilonModel(new Point2D.Double((double) myFrame.getINSTANCE().getWidth() /2,(double) myFrame.getINSTANCE().getHeight() /2));
            new KeyController();
            for(int i = 0; i < NUMBER_OF_BALLS; i++){
                double randomX = rng.nextDouble(myPanel.getINSTANCE().getX(),myPanel.getINSTANCE().getX()+myPanel.getINSTANCE().getWidth());
                double randomY = rng.nextDouble(myPanel.getINSTANCE().getY(),myPanel.getINSTANCE().getY()+myPanel.getINSTANCE().getHeight());
//                double randomRadius=rng.nextDouble(MAX_RADIUS, MAX_RADIUS);
//                new EpsilonModel(new Point2D.Double(randomX,randomY),RADIUS);
            }
        });
    }
}