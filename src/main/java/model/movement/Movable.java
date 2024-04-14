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
    boolean isCircular();
    boolean isImpactInProgress();
    public void setImpactInProgress(boolean impactInProgress);
    LinkedList<Movable> movables = new LinkedList<>();
    void setDirection(Direction direction);
    Direction getDirection();
    void move(Direction direction);
    void move();
    void friction();
    Point2D getAnchor();
    void impact(CollisionState collisionState);
//        direction.adjustEpsilonDirectionMagnitude();
    double getImpactCoefficient(Point2D collisionRelativeVector);
}