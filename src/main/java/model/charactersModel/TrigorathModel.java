
package model.charactersModel;

import model.collision.Collidable;
import model.collision.CollisionState;
import model.movement.Direction;
import model.movement.Movable;

import java.awt.geom.Line2D;
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
    public double impactMaxVelocity;
    Direction direction;

    private boolean impactInProgress = false;
    public static ArrayList<TrigorathModel> trigorathModels =new ArrayList<>();
    private final Point2D[] vertices;
    private double angle = 0;
    private double angularVelocity = Math.PI/100;

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
        Point2D collisionPoint = collisionState.collisionPoint;
        Point2D collisionRelativeVector = relativeLocation(this.getAnchor(), collisionPoint);
        double impactCoefficient = getImpactCoefficient(collisionRelativeVector);
        Point2D impactVector = normalizeVector(relativeLocation(this.getAnchor(), collisionState.collisionPoint));
        impactVector = multiplyVector(impactVector ,impactCoefficient);
        Point2D r2 = addVectors(this.getDirection().getNormalizedDirectionVector(), impactVector);
        if(!isCircular()){
            Direction direction = new Direction(normalizeVector(r2));
            this.setDirection(direction);
        } else {
            Direction direction = new Direction(normalizeVector(r2));
            this.setDirection(direction);
        }
    }

    public void impact(Point2D normalVector, CollisionState collisionState) {
        Point2D collisionPoint = collisionState.collisionPoint;
        Point2D collisionRelativeVector = relativeLocation(this.getAnchor(), collisionPoint);
        double impactCoefficient = getImpactCoefficient(collisionRelativeVector);
        Point2D impactVector = reflect(normalVector);
        impactVector = multiplyVector(impactVector ,impactCoefficient);
        this.setDirection(new Direction(normalizeVector(impactVector)));
    }

    @Override
    public double getImpactCoefficient(Point2D collisionRelativeVector) {
        double distance = Math.hypot(collisionRelativeVector.getX(), collisionRelativeVector.getY());
        double impactCoefficient;
        if (distance < SMALL_IMPACT_RADIUS) {
            setImpactInProgress(true);
            impactMaxVelocity = 2 * IMPACT_COEFFICIENT / 5;
            impactCoefficient = IMPACT_COEFFICIENT;
        } else if (distance > (LARGE_IMPACT_RADIUS + SMALL_IMPACT_RADIUS) /2) {
            setImpactInProgress(false);
            impactCoefficient = 0;
        } else {
            setImpactInProgress(true);
            double coefficient = 1 - (distance- SMALL_IMPACT_RADIUS)/(LARGE_IMPACT_RADIUS - SMALL_IMPACT_RADIUS);
            impactCoefficient = coefficient * IMPACT_COEFFICIENT;
            impactMaxVelocity = 2 * coefficient * IMPACT_COEFFICIENT / 5;
        }
        return impactCoefficient;
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

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
    public void rotate(){
        angle += angularVelocity;
        vertices[0] = new Point2D.Double(anchor.getX()-radius*Math.sin(angle), anchor.getY()-radius*Math.cos(angle));
        vertices[1] = new Point2D.Double(anchor.getX()+radius*Math.cos(Math.PI/6-angle), anchor.getY()+radius*Math.sin(Math.PI/6-angle));
        vertices[2] = new Point2D.Double(anchor.getX()-radius*Math.cos(Math.PI/6+angle), anchor.getY()+radius*Math.sin(Math.PI/6+angle));

    }
    public Point2D reflect(Point2D normalVector){
        double dotProduct = dotVectors(getDirection().getDirectionVector(), normalVector);
        Point2D reflection = addVectors(
                getDirection().getDirectionVector(),
                multiplyVector(normalVector,-2*dotProduct
                ));
        return normalizeVector(reflection);
    }
}