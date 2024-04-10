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

public class Update {

    public Update() {
        new Timer((int) FRAME_UPDATE_TIME, e -> updateView()){{setCoalesce(true);}}.start();
        new Timer((int) MODEL_UPDATE_TIME, e -> updateModel()){{setCoalesce(true);}}.start();
    }

    public void updateView(){

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

        for (EpsilonModel epsilonModel: EpsilonModel.epsilonModels){
            epsilonModel.move();
            epsilonModel.friction();
//            System.out.println(epsilonModel.getDirection().getMagnitude());
//            System.out.println(epsilonModel.getAnchor());
        }
        for (SquarantineModel squarantineModel: SquarantineModel.squarantineModels){
//            squarantineModel.move();
//            System.out.println(squarantineModel.getCurrentLocation());
        }
        ArrayList<Collidable> collidables = new ArrayList<>(Collidable.collidables);
        for (int i=0;i<collidables.size();i++){
            for (int j=i+1;j<collidables.size();j++){
                CollisionState collisionState = collidables.get(i).collides(collidables.get(j));
                if (collisionState != null){
                    Movable movable1 = null;
                    Movable movable2 = null;

                    if (collidables.get(i) instanceof Movable){
//                        ((Movable) collidables.get(i)).setDirection(
//                                new Direction(relativeLocation(collidables.get(i).getAnchor(),collisionState.collisionPoint
//                                )));

                        Point2D d = ((Movable) collidables.get(i)).getDirection().getNormalizedDirectionVector();
                        Point2D n = normalizeVector(relativeLocation(collidables.get(i).getAnchor(), collisionState.collisionPoint));
                        double scalar =  -2 * dotVectors(d, n);
                        Point2D r1 = multiplyVector(n, scalar);
                        Point2D r2 = addVectors(d, r1);
                        ((Movable) collidables.get(i)).setDirection(new Direction(r2));

//                        Point2D collisionAnchorVector = relativeLocation(collidables.get(i).getAnchor(), collisionState.collisionPoint);
                        movable1 = (Movable) collidables.get(i);
//                        move1 = true;
//                        Point2D currentDirection = movable.getDirection().getDirectionVector();
//                        double size = Math.hypot(currentDirection.getX(), currentDirection.getY());
//                        double coefficientX = currentDirection.getX() / size;
//                        double coefficientY = currentDirection.getY() / size;
//                        currentDirection = multiplyVectorXYComponent(collisionAnchorVector, coefficientX, coefficientY);
//                        Direction direction = new Direction(currentDirection);
//                        movable.setDirection(direction);



//                        if (Math.abs(collisionAnchorVector.getX()) > Math.abs(collisionAnchorVector.getY())){
//                            movable.getDirection().mirrorSlope();
//                            if (collisionAnchorVector.getX() > 0) movable.getDirection().setState(Direction.DirectionState.positive);
//                            else movable.getDirection().setState(Direction.DirectionState.negative);
//
//                        } else {
//                            movable.getDirection().mirrorSlope();
//                        }
//                        movable.move();
                    }
                    if (collidables.get(j) instanceof Movable){
//                        ((Movable) collidables.get(j)).setDirection(
//                                new Direction(relativeLocation(collidables.get(j).getAnchor(),collisionState.collisionPoint
//                                )));
                        Point2D d = ((Movable) collidables.get(j)).getDirection().getNormalizedDirectionVector();
                        Point2D n = normalizeVector(relativeLocation(collidables.get(j).getAnchor(), collisionState.collisionPoint));
                        double scaler =  -2 * dotVectors(d, n);
                        Point2D r1 = multiplyVector(n, scaler);
                        Point2D r2 = addVectors(d, r1);
                        ((Movable) collidables.get(j)).setDirection(new Direction(r2));


//                        Point2D collisionAnchorVector = relativeLocation(collidables.get(j).getAnchor(), collisionState.collisionPoint);

//                        Point2D collisionAnchorVector = relativeLocation(collidables.get(j).getAnchor(), collisionState.collisionPoint);
                        movable2 = (Movable) collidables.get(j);
//                        Point2D currentDirection = movable.getDirection().getDirectionVector();
//                        double size = Math.hypot(currentDirection.getX(), currentDirection.getY());
//                        double coefficientX = currentDirection.getX() / size;
//                        double coefficientY = currentDirection.getY() / size;
//                        currentDirection = multiplyVectorXYComponent(collisionAnchorVector, coefficientX, coefficientY);
//                        Direction direction = new Direction(currentDirection);
//                        movable.setDirection(direction);

//                        Point2D collisionAnchorVector = relativeLocation(collidables.get(j).getAnchor(), collisionState.collisionPoint);
//                        Movable movable = (Movable) collidables.get(j);
//                        if (Math.abs(collisionAnchorVector.getX()) > Math.abs(collisionAnchorVector.getY())){
//                            movable.getDirection().mirrorSlope();
//                            if (collisionAnchorVector.getX() > 0) movable.getDirection().setState(Direction.DirectionState.positive);
//                            else movable.getDirection().setState(Direction.DirectionState.negative);
//
//                        } else {
//                            movable.getDirection().mirrorSlope();
//                        }
                    }
//                    if (movable1 != null) movable1.move();
//                    if (movable2 != null) movable2.move();
                }
            }
        }
    }
}