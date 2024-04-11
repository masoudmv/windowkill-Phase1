package controller;

import model.charactersModel.EpsilonModel;
import model.charactersModel.SquarantineModel;
import model.collision.Collidable;
import model.collision.CollisionState;
import model.movement.Direction;
import model.movement.Movable;
import view.charactersView.SquarantineView;
import view.myFrame;
import view.myPanel;
import view.charactersView.EpsilonView;

import javax.swing.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Constants.*;
import static controller.Controller.*;
import static controller.Utils.*;
import static model.charactersModel.SquarantineModel.squarantineModels;
import static model.collision.Collidable.collidables;
import static model.movement.Movable.movables;

public class Update {
    // Add FPS tracking variables
    private long lastUpdateTime = System.currentTimeMillis();
    private int frameCount = 0;
    private long lastUpdateTimeUPS = System.currentTimeMillis();
    private int updateCount = 0;



    public Update() {
        new Timer((int) FRAME_UPDATE_TIME, e -> updateView()){{setCoalesce(true);}}.start();

//        new Timer((int) MODEL_UPDATE_TIME, e -> updateModel()){{setCoalesce(true);}}.start();


    }

    public void updateView(){
        updateModel();
        // Increment frame count every time updateView is called
        frameCount++;

        // Current time in milliseconds
        long currentTime = System.currentTimeMillis();

        // Check if one second has passed
        if (currentTime - lastUpdateTime >= 1000) {
            // Print the FPS (which is frameCount since it's been a second)
//            System.out.println("FPS: " + frameCount);

            // Reset frame counter and last update time for the next second
            frameCount = 0;
            lastUpdateTime = currentTime;
        }

        for (EpsilonView epsilonView: EpsilonView.epsilonViews){
            epsilonView.setCurrentLocation(calculateViewLocationEpsilon(myPanel.getINSTANCE(),epsilonView.getId()));
        }
        for (SquarantineView squarantineView: SquarantineView.squarantineViews){
            squarantineView.setCurrentLocation(calculateViewLocationSquarantine(myPanel.getINSTANCE(),squarantineView.getId()));
//            System.out.println(squarantineView.getCurrentLocation());
        }
        myFrame.getINSTANCE().repaint();
    }

    public void updateModel(){
        // Increment frame count every time updateView is called
        updateCount++;

        for (SquarantineModel squarantineModel: squarantineModels){
            Point2D direction =  relativeLocation(EpsilonModel.getINSTANCE().getAnchor(), squarantineModel.getAnchor());
//            normalizeVector(direction);
            Direction d = new Direction(addVectors(normalizeVector(direction), squarantineModel.getDirection().getDirectionVector()));
            d.adjustDirectionMagnitude();
            squarantineModel.setDirection(d);
//            direction.adjustDirectionMagnitude();
//            squarantineModel.setDirection(direction);
        }

        // Current time in milliseconds
        long currentTime = System.currentTimeMillis();

        // Check if one second has passed
        if (currentTime - lastUpdateTimeUPS >= 1000) {
            // Print the FPS (which is frameCount since it's been a second)
//            System.out.println("UPS: " + updateCount);

            // Reset frame counter and last update time for the next second
            updateCount = 0;
            lastUpdateTimeUPS = currentTime;
        }
        for (Movable movable: movables){
            movable.move();
            movable.friction();
//            System.out.println(movable.getDirection().getDirectionVector());
        }
//        ArrayList<Collidable> collidables = new ArrayList<>(Collidable.collidables);
        for (int i=0;i<collidables.size();i++){
            for (int j=i+1;j<collidables.size();j++){
                CollisionState collisionState = collidables.get(i).collides(collidables.get(j));
//                if (collisionState != null){
//                    Movable movable1 = null;
//                    Movable movable2 = null;

//                    if (collidables.get(i) instanceof Movable){
//
//                        if (collidables.get(i).isCircular()) {
//                            Point2D impactVector = normalizeVector(relativeLocation(collidables.get(i).getAnchor(), collisionState.collisionPoint));
//                            impactVector = multiplyVector(impactVector ,IMPACT_COEFFICIENT);
//                            Point2D r2 = addVectors(((Movable) collidables.get(i)).getDirection().getDirectionVector(), impactVector);
//                            Direction direction = new Direction(r2);
//                            ((Movable) collidables.get(i)).setDirection(direction);
//                            movable1 = (Movable) collidables.get(i);
//                        }
//                    }
//                    if (collidables.get(j) instanceof Movable){
//                        if (collidables.get(j).isCircular()) {
//                            Point2D impactVector = normalizeVector(relativeLocation(collidables.get(j).getAnchor(), collisionState.collisionPoint));
//                            impactVector = multiplyVector(impactVector ,IMPACT_COEFFICIENT);
//                            Point2D r2 = addVectors(((Movable) collidables.get(j)).getDirection().getDirectionVector(), impactVector);
//                            Direction direction = new Direction(r2);
//                            ((Movable) collidables.get(j)).setDirection(direction);
//                            movable1 = (Movable) collidables.get(j);
//                        }
//                    }
//                    if (movable1 != null) movable1.move();
//                    if (movable2 != null) movable2.move();
//                }
            }
        }
    }
}