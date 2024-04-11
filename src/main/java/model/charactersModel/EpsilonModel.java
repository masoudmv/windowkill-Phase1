package model.charactersModel;

import model.collision.Collidable;
import model.movement.Direction;
import model.movement.Movable;
import view.myFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.UUID;

import static controller.Constants.FRICTION;
import static controller.Constants.RADIUS;
import static controller.Controller.createEpsilonView;
import static controller.Utils.addVectors;
import static controller.Utils.multiplyVector;

public class EpsilonModel implements Movable, Collidable {
    private static EpsilonModel INSTANCE;
    Point2D anchor;
    double radius;

    String id;
    Direction direction;
    public static ArrayList<EpsilonModel> epsilonModels=new ArrayList<>();

    public EpsilonModel(Point2D anchor) {
        this.anchor = anchor;
        this.radius = RADIUS;

        this.id= UUID.randomUUID().toString();
//        null direction???
        Point vector = new Point(0,0);
        this.direction=new Direction(vector);
        epsilonModels.add(this);
        collidables.add(this);
        movables.add(this);
        createEpsilonView(id);
        INSTANCE = this;
    }

    public static EpsilonModel getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new EpsilonModel(
                new Point2D.Double((double) myFrame.getINSTANCE().getWidth() /2,(double) myFrame.getINSTANCE().getHeight() /2));
        return INSTANCE;
    }

    public String getId() {
        return id;
    }
    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public Point2D getAnchor() {
        return anchor;
    }

    @Override
    public ArrayList<Point2D> getVertices() {
        return null;
    }

    @Override
    public void move(Direction direction) {
        Point2D movement = multiplyVector(direction.getNormalizedDirectionVector(), direction.getMagnitude());
        this.anchor = addVectors(anchor, movement);

    }

    @Override
    public void move() {
        move(direction);
    }

    @Override
    public boolean isCircular() {
        return true;
    }
    @Override
    public void friction(){
        direction.setMagnitude(direction.getMagnitude() * FRICTION);
        if (direction.getMagnitude() < 0.2){
            direction.setMagnitude(0);
        }
    }
//    @Override
//    public void friction() {
//        move(direction);
//    }

}