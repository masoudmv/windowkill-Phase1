package model.collision;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static controller.Constants.RADIUS;
import static controller.Utils.*;
import static model.movement.Movable.movables;
import model.movement.Movable;

public interface Collidable {
    ArrayList<Collidable> collidables = new ArrayList<>();
    boolean isCircular();
//    double getRadius();
    Point2D getAnchor();
    Point2D[] getVertices();
    default CollisionState collides(Collidable collidable){
        if (isCircular() && !collidable.isCircular()){
            Point2D closest = closestPointOnPolygon(getAnchor(), collidable.getVertices());
            if (closest.distance(getAnchor()) <= RADIUS) {
                for (Movable movable: movables){
                    movable.impact(new CollisionState(closest));
                }
                return new CollisionState(closest);
            }
        }
        else if (!isCircular() && collidable.isCircular()){
            return collidable.collides(this);
        }
        //TODO neither this or collidable are circular
        else if (!isCircular() && !isCircular()){
            List<Point2D> closest = findIntersectionPoints(this, collidable);
            if (!closest.isEmpty()){
                Point2D intersection = closest.get(0);
                for (Movable movable: movables){
                    movable.impact(new CollisionState(intersection));
                }
                return new CollisionState(intersection);

            }
        }

        return null;
    }
    default Point2D closestPointOnPolygon(Point2D point, Point2D[] vertices){
        double minDistance = Double.MAX_VALUE;
        Point2D closest = null;
        for (int i=0;i<vertices.length;i++){
            Point2D temp = getClosestPointOnSegment(vertices[i],vertices[(i+1)%vertices.length],point);
            double distance = temp.distance(point);
            if (distance < minDistance) {
                minDistance = distance;
                closest = temp;
            }
        }
        return closest;
    }

    default Point2D getIntersectionEdgeNormalVector(Point2D point, Collidable collidable){
        double minDistance = Double.MAX_VALUE;
        Point2D normalVector = null;
        if (collidable.isCircular()){
            Point2D intersection = closestPointOnPolygon(this.getAnchor(), collidable.getVertices());
            return relativeLocation(intersection, collidable.getAnchor());
        } else {
            Point2D[] vertices = collidable.getVertices();
            for (int i=0;i<vertices.length;i++){
                Point2D temp = getClosestPointOnSegment(vertices[i],vertices[(i+1)%vertices.length], point);
                Point2D edge;
                double distance = temp.distance(point);
                if (distance < minDistance) {
                    edge = relativeLocation(vertices[(i+1)%vertices.length], vertices[i]);
                    minDistance = distance;
                    normalVector = PerpendicularCounterClockwise(edge);
                }
            }
        }

        return normalVector;
    }



    default boolean doPolygonsIntersect(Collidable poly1, Collidable poly2) {
        // Combine both polygons' vertices; assume they are defined in a clockwise manner
        Point2D[] vertices1 = poly1.getVertices();
        Point2D[] vertices2 = poly2.getVertices();

        // Check all axes formed by the edges of both polygons
        for (int i = 0; i < vertices1.length + vertices2.length; i++) {
            Point2D p1 = i < vertices1.length ? vertices1[i] : vertices2[i - vertices1.length];
            Point2D p2 = i < vertices1.length ? vertices1[(i + 1) % vertices1.length] : vertices2[(i + 1 - vertices1.length) % vertices2.length];

            Point2D edge = new Point2D.Double(p2.getX() - p1.getX(), p2.getY() - p1.getY());
            Point2D axis = new Point2D.Double(-edge.getY(), edge.getX()); // Perpendicular to the edge

            double minA = Float.MAX_VALUE, maxA = -Float.MAX_VALUE;
            for (Point2D p : vertices1) {
                double projection = (p.getX() * axis.getX() + p.getY() * axis.getY()) / (axis.getX() * axis.getX() + axis.getY() * axis.getY());
                minA = Math.min(minA, projection);
                maxA = Math.max(maxA, projection);
            }

            double minB = Float.MAX_VALUE, maxB = -Float.MAX_VALUE;
            for (Point2D p : vertices2) {
                double projection = (p.getX() * axis.getX() + p.getY() * axis.getY()) / (axis.getX() * axis.getX() + axis.getY() * axis.getY());
                minB = Math.min(minB, projection);
                maxB = Math.max(maxB, projection);
            }

            if (maxA < minB || maxB < minA) {
                // No overlap on this axis, polygons do not intersect
                return false;
            }
        }

        // All axes have overlap, polygons intersect
        return true;
    }



    default Point2D getClosestPointOnSegment(Point2D head1, Point2D head2, Point2D point) {
        double u =((point.getX()-head1.getX())*(head2.getX()-head1.getX())+(point.getY()-head1.getY())*(head2.getY()-head1.getY()))/head2.distanceSq(head1);
        if (u > 1.0) return (Point2D) head2.clone();
        else if (u <= 0.0) return (Point2D) head1.clone();
        else return new Point2D.Double(head2.getX()* u + head1.getX() * (1.0 - u) + 0.5,head2.getY() * u + head1.getY() * (1.0 - u) + 0.5);
    }

    // Method to find all intersection points between two polygons
    public static List<Point2D> findIntersectionPoints(Collidable poly1, Collidable poly2) {
        List<Point2D> intersectionPoints = new ArrayList<>();

        Point2D[] vertices1 = poly1.getVertices();
        Point2D[] vertices2 = poly2.getVertices();

        for (int i = 0; i < vertices1.length; i++) {
            Line2D line1 = new Line2D.Double(vertices1[i], vertices1[(i + 1) % vertices1.length]);

            for (int j = 0; j < vertices2.length; j++) {
                Line2D line2 = new Line2D.Double(vertices2[j], vertices2[(j + 1) % vertices2.length]);
                Point2D intersection = getIntersectionPoint(line1, line2);
                if (intersection != null) {
                    intersectionPoints.add(intersection);
                }
            }
        }
        return intersectionPoints;
    }
}