package model.movement;

import model.collision.Collidable;
import model.collision.CollisionState;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;

import static controller.Constants.*;
import static controller.Utils.*;
import static controller.Utils.addVectors;
import static model.collision.Collidable.collidables;

public interface Movable {
    LinkedList<Movable> movables = new LinkedList<>();
    void setDirection(Direction direction);
    Direction getDirection();
    void move(Direction direction);
    void move();
    void friction();
    Point2D getAnchor();
    default void impact(CollisionState collisionState){
//        System.out.println("impact");

        Point2D collisionPoint = collisionState.collisionPoint;
        Point2D collisionRelativeVector = relativeLocation(this.getAnchor(), collisionPoint);
        double distance = Math.hypot(collisionRelativeVector.getX(), collisionRelativeVector.getY());
        double impactCoefficient;

        if (distance < LARGE_IMPACT_RADIUS) {
            impactCoefficient = IMPACT_COEFFICIENT;
        } else if (distance > IMPACT_RADIUS) {
            impactCoefficient = 0;
        } else {
            double coefficient = 1-(distance-LARGE_IMPACT_RADIUS)/(IMPACT_RADIUS-LARGE_IMPACT_RADIUS);
            impactCoefficient = coefficient * IMPACT_COEFFICIENT;
        }
//        System.out.println(impactCoefficient);
        Point2D impactVector = normalizeVector(relativeLocation(this.getAnchor(), collisionState.collisionPoint));
//        System.out.println(impactCoefficient);
        impactVector = multiplyVector(impactVector ,impactCoefficient);
        Point2D r2 = addVectors(this.getDirection().getDirectionVector(), impactVector);
        Direction direction = new Direction(r2);
        this.setDirection(direction);


    }
}