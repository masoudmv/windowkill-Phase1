
package model.charactersModel;

import model.collision.Collidable;
import model.collision.CollisionState;
import model.movement.Direction;
import model.movement.Movable;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.UUID;

import static controller.Constants.*;
import static controller.Controller.createSquarantineView;
import static controller.Controller.createTrigorathView;
import static controller.Utils.*;

public class TrigorathModel implements Movable, Collidable {
    private Point2D anchor;
    Point2D currentLocation;
    double radius;
    String id;
    Direction direction;
    private boolean impactInProgress = false;
    public static ArrayList<TrigorathModel> trigorathModels =new ArrayList<>();
    private final Point2D[] vertices;

    public TrigorathModel(Point2D anchor) {
        this.anchor = anchor;
        this.radius = TRIGORATH_RADIUS;
        Point2D point1 = new Point2D.Double(anchor.getX(), anchor.getY()-radius);
        Point2D point2 = new Point2D.Double(anchor.getX()+ radius*Math.cos(Math.PI/6), anchor.getY()+radius/2);
        Point2D point3 = new Point2D.Double(anchor.getX()-radius*Math.cos(Math.PI/6), anchor.getY()+radius/2);
        vertices = new Point2D[]{point1, point2, point3};
        this.id= UUID.randomUUID().toString();
        this.direction = new Direction(0);
        trigorathModels.add(this);
        collidables.add(this);
        movables.add(this);
        createTrigorathView(id);
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean isImpactInProgress() {
        return impactInProgress;
    }
    @Override
    public void setImpactInProgress(boolean impactInProgress) {
        this.impactInProgress = impactInProgress;
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
    public void impact(CollisionState collisionState) {

    }

    @Override
    public double getImpactCoefficient(Point2D collisionRelativeVector) {
        return 0;
    }

    @Override
    public Point2D[] getVertices() {
        return this.vertices;
    }

    @Override
    public void move(Direction direction) {
        Point2D movement = multiplyVector(direction.getDirectionVector(), direction.getMagnitude());
        this.anchor = addVectors(anchor, movement);
        for (int i = 0; i < 3; i++) {
            vertices[i] = addVectors(vertices[i], movement);
        }
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
    @Override
    public void friction(){
        direction.setMagnitude(direction.getMagnitude() * FRICTION);
        if (direction.getMagnitude() < 1){
            setDirection(
                    new Direction(relativeLocation(EpsilonModel.getINSTANCE().getAnchor(), getAnchor())));
            getDirection().adjustDirectionMagnitude();
        }
    }
}