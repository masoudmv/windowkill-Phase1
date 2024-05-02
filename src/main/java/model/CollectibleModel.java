package model;
import model.collision.Collidable;
import model.movement.Direction;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.UUID;

import static controller.Constants.BULLET_RADIUS;
import static controller.Controller.*;

public class CollectibleModel implements Collidable{
    String id;
    double radius;
    private Point2D anchor;
    public static LinkedList<CollectibleModel> collectibleModels = new LinkedList<>();


    public CollectibleModel(Point2D anchor) {
        this.radius = BULLET_RADIUS;
        this.id= UUID.randomUUID().toString();
        this.anchor = anchor;
        collectibleModels.add(this);
        collidables.add(this);
        createCollectibleView(id);
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

    @Override
    public Point2D getAnchor() {
        return this.anchor;
    }

    @Override
    public Point2D[] getVertices() {
        return new Point2D[0];
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }



    public void remove(){
        collidables.remove(this);
        collectibleModels.remove(this);
        findCollectibleView((this).getId()).remove();
    }


}
