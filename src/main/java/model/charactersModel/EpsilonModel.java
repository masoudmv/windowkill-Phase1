package model.charactersModel;

import model.collision.Collidable;
import model.collision.CollisionState;
import model.movement.Direction;
import model.movement.Movable;
import view.myFrame;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.UUID;

import static controller.Constants.*;
import static controller.Controller.createEpsilonView;
import static controller.Utils.*;
import static controller.Utils.normalizeVector;

public class EpsilonModel implements Movable, Collidable {
    private static EpsilonModel INSTANCE;
    Point2D anchor;
    double radius;
    private boolean impactInProgress = false;
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
        return direction;
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
//        System.out.println(impactVector);
        Point2D r2 = addVectors(this.getDirection().getNormalizedDirectionVector(), impactVector);
        if(!isCircular()){
            Direction direction = new Direction(normalizeVector(r2));
            this.setDirection(direction);
        } else {
//           Direction direction = new Direction(r2);
//            this.setDirection(direction);
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
        if (this.getDirection().getMagnitude() < 2){
            setDirection(new Direction(normalizeVector(relativeLocation(getAnchor(), collisionPoint))));
        }
        else {
            setDirection(new Direction(normalizeVector(impactVector)));
        }
    }

    @Override
    public double getImpactCoefficient(Point2D collisionRelativeVector) {
        double distance = Math.hypot(collisionRelativeVector.getX(), collisionRelativeVector.getY());
        double impactCoefficient;
        if (distance < SMALL_IMPACT_RADIUS) {
            setImpactInProgress(true);
            impactCoefficient = IMPACT_COEFFICIENT;
        } else if (distance > LARGE_IMPACT_RADIUS) {
            setImpactInProgress(false);
            impactCoefficient = 0;
        } else {
            setImpactInProgress(true);
            double coefficient = 1-(distance- SMALL_IMPACT_RADIUS)/(LARGE_IMPACT_RADIUS - SMALL_IMPACT_RADIUS);
            impactCoefficient = coefficient * IMPACT_COEFFICIENT;
        }
        return impactCoefficient;
    }

    @Override
    public Point2D[] getVertices() {
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
        if (isImpactInProgress()){
//            direction.setMagnitude(direction.getMagnitude() * FRICTION);
            if (direction.getMagnitude() < 1){
//                direction.setMagnitude(0);
                setImpactInProgress(false);
            }
        } else {
            direction.setMagnitude(direction.getMagnitude() * 0.93);
            if (direction.getMagnitude() < 0.5){
                direction.setMagnitude(0);
            }
        }

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