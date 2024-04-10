package model.charactersModel;

import model.collision.Collidable;
import model.movement.Direction;
import model.movement.Movable;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.UUID;

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
        Collidable.collidables.add(this);
        createEpsilonView(id);
        INSTANCE = this;
    }

    public static EpsilonModel getINSTANCE() {
//        if (INSTANCE == null) INSTANCE = new EpsilonModel(, RADIUS);
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
    public void friction(){
        direction.setMagnitude(direction.getMagnitude() * 0.9);
        if (direction.getMagnitude() < 0.2) direction.setMagnitude(0);
    }

}