package model;
import model.collision.Collidable;
import model.collision.CollisionState;
import model.collision.Impactable;
import model.movement.Direction;
import model.movement.Movable;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.UUID;

import static controller.Constants.BULLET_RADIUS;
import static controller.Constants.BULLET_VELOCITY;
import static controller.Controller.creatBulletView;
import static controller.Controller.findBulletView;
import static controller.Utils.*;

public class BulletModel implements Movable, Collidable, Impactable {
    String id;
    double radius;
    private Point2D anchor;
    Direction direction;
    public static LinkedList<BulletModel> bulletModels = new LinkedList<>();

    public BulletModel(Point2D anchor, Direction direction) {
        this.radius = BULLET_RADIUS;
        this.id= UUID.randomUUID().toString();
        this.anchor = anchor;
        this.direction = direction;
        bulletModels.add(this);
        movables.add(this);
        collidables.add(this);
        creatBulletView(id);
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean isCircular() {
        return true;
    }

    @Override
    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void setDirection(Direction direction) {}

    @Override
    public void bulletImpact(BulletModel bulletModel, Point2D collisionPoint) {
        this.remove();
    }



    public void bulletImpact(BulletModel bulletModel, Point2D collisionPoint, Collidable collidable) {

        ((Movable) collidable).bulletImpact(bulletModel, collisionPoint);
        for (Movable movable : movables){
            if (movable != this && movable != bulletModel && movable!= collidable){
                ((Impactable)movable).impact(new CollisionState(collisionPoint));
            }
        } this.remove();
    }


    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void move(Direction direction) {
        Point2D movement = multiplyVector(direction.getNormalizedDirectionVector(), BULLET_VELOCITY);
        this.anchor = addVectors(anchor, movement);
    }

    @Override
    public void move() {
        move(direction);
    }

    @Override
    public void friction() {}

    @Override
    public Point2D getAnchor() {
        return this.anchor;
    }




    @Override
    public Point2D[] getVertices() {
        return null;
    }

    @Override
    public boolean isImpactInProgress() {
        return false;
    }

    @Override
    public void setImpactInProgress(boolean impactInProgress) {

    }

    @Override
    public void impact(CollisionState collisionState) {}

    @Override
    public void impact(Point2D normalVector, Point2D collisionPoint, Collidable polygon) {}


    @Override
    public double getImpactCoefficient(Point2D collisionRelativeVector) {
        return 0;
    }

    @Override
    public void banish() {}

    public void remove(){
        collidables.remove(this);
        bulletModels.remove(this);
        movables.remove(this);
        findBulletView((this).getId()).remove();
    }
}
