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
import view.charactersView.EpsilonView;
import view.charactersView.SquarantineView;
import view.myFrame;
import view.myPanel;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.Random;

import static controller.Constants.*;
import static controller.Controller.calculateViewLocationEpsilon;
import static controller.Controller.calculateViewLocationSquarantine;

public class Main {
    static Random rng = new Random();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            myFrame.getINSTANCE();
            myPanel.getINSTANCE();
            new Update();
            new EpsilonModel(new Point2D.Double((double) myFrame.getINSTANCE().getWidth() /2,(double) myFrame.getINSTANCE().getHeight() /2));
//            System.out.println(;EpsilonModel.getINSTANCE().getAnchor()

////                            double randomX = rng.nextDouble(myPanel.getINSTANCE().getX(),myPanel.getINSTANCE().getX()+myPanel.getINSTANCE().getWidth());
////                double randomY = rng.nextDouble(myPanel.getINSTANCE().getY(),myPanel.getINSTANCE().getY()+myPanel.getINSTANCE().getHeight());
            new SquarantineModel(new Point2D.Double(500,500), (double) SQUARANTINE_EDGE /2);
////                            new model.charactersModel.EpsilonModel(new Point2D.Double(500,500),MAX_RADIUS);
//
            new KeyController();
//
        });

//        new Timer((int) FRAME_UPDATE_TIME, e -> updateView()){{setCoalesce(true);}}.start();

    }
//    static long lastUpdateTime = System.currentTimeMillis();
//    static int frameCount = 0;
//
//    public static void updateView(){
//        // Increment frame count every time updateView is called
//        frameCount++;
//
//        // Current time in milliseconds
//        long currentTime = System.currentTimeMillis();
//
//        // Check if one second has passed
//        if (currentTime - lastUpdateTime >= 1000) {
//            // Print the FPS (which is frameCount since it's been a second)
//            System.out.println("FPS: " + frameCount);
//
//            // Reset frame counter and last update time for the next second
//            frameCount = 0;
//            lastUpdateTime = currentTime;
//        }
//
//        for (EpsilonView epsilonView: EpsilonView.epsilonViews){
//            epsilonView.setCurrentLocation(calculateViewLocationEpsilon(myPanel.getINSTANCE(),epsilonView.getId()));
//        }
//        for (SquarantineView squarantineView: SquarantineView.squarantineViews){
//            squarantineView.setCurrentLocation(calculateViewLocationSquarantine(myPanel.getINSTANCE(),squarantineView.getId()));
////            System.out.println(squarantineView.getCurrentLocation());
//        }
//        myFrame.getINSTANCE().repaint();
//    }
}