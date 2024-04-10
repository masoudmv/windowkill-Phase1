package model.charactersModel;

import model.collision.Collidable;
import model.movement.Direction;
import model.movement.Movable;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import static controller.Constants.SPEED;
import static controller.Constants.SQUARANTINE_EDGE;
import static controller.Controller.createEpsilonView;
import static controller.Controller.createSquarantineView;
import static controller.Utils.addVectors;
import static controller.Utils.multiplyVector;

public class SquarantineModel implements Movable, Collidable {
    private Point2D anchor;
    Point2D currentLocation;
    double radius;
    String id;
    Direction direction;
    private ArrayList<Point2D> vertices = new ArrayList<>();
    public static ArrayList<SquarantineModel> squarantineModels =new ArrayList<>();

    public SquarantineModel(Point2D anchor, double radius) {
        this.anchor = anchor;
        this.radius = radius;
        Point2D point1 = new Point2D.Double((anchor.getX()- (double) SQUARANTINE_EDGE /2), (anchor.getY()- (double) SQUARANTINE_EDGE /2));
        Point2D point2 = new Point2D.Double((anchor.getX()+ (double) SQUARANTINE_EDGE /2), (anchor.getY()- (double) SQUARANTINE_EDGE /2));
        Point2D point3 = new Point2D.Double((anchor.getX()+ (double) SQUARANTINE_EDGE /2), (anchor.getY()+ (double) SQUARANTINE_EDGE /2));
        Point2D point4 = new Point2D.Double((anchor.getX()- (double) SQUARANTINE_EDGE /2), (anchor.getY()+ (double) SQUARANTINE_EDGE /2));
        this.currentLocation = point1;
        this.vertices.add(point1);
        this.vertices.add(point2);
        this.vertices.add(point3);
        this.vertices.add(point4);
        this.id= UUID.randomUUID().toString();
        this.direction=new Direction(new Random().nextInt());
        squarantineModels.add(this);
        Collidable.collidables.add(this);
        createSquarantineView(id);
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
        return this.direction;
    }

    @Override
    public Point2D getAnchor() {
        return anchor;
    }

    @Override
    public ArrayList<Point2D> getVertices() {
        return this.vertices;
    }

    @Override
    public void move(Direction direction) {
//        Point2D movement = multiplyVector(direction.getDirectionVector(), speed);
//        this.anchor = addVectors(anchor, movement);
//        for (int i = 0; i < 4; i++) {
//            vertices.set(i, addVectors(vertices.get(i), movement));
//        }
    }

    @Override
    public void move() {
        move(direction);
    }

    @Override
    public boolean isCircular() {
        return false;
    }

    public Point2D getCurrentLocation() {
        return currentLocation;
    }
}