package model.collision;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import static controller.Constants.RADIUS;
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

//    default Point2D getIntersectionEdgeNormalVector(Point2D point, Point2D[] vertices){
//        if (vertices == null) return null;
//        double minDistance = Double.MAX_VALUE;
//        Point2D edge = null;
//        Point2D normalVector = null;
//        for (int i=0;i<vertices.length;i++){
//            Point2D temp = getClosestPointOnSegment(vertices[i],vertices[(i+1)%vertices.length],point);
//
//            double distance = temp.distance(point);
//            if (distance < minDistance) {
//                edge = relativeLocation(vertices[(i+1)%vertices.length], vertices[i]);
//                minDistance = distance;
//                normalVector = PerpendicularCounterClockwise(edge);
//            }
//        }
//        return edge;
//    }


    default Point2D getClosestPointOnSegment(Point2D head1, Point2D head2, Point2D point) {
        double u =((point.getX()-head1.getX())*(head2.getX()-head1.getX())+(point.getY()-head1.getY())*(head2.getY()-head1.getY()))/head2.distanceSq(head1);
        if (u > 1.0) return (Point2D) head2.clone();
        else if (u <= 0.0) return (Point2D) head1.clone();
        else return new Point2D.Double(head2.getX()* u + head1.getX() * (1.0 - u) + 0.5,head2.getY() * u + head1.getY() * (1.0 - u) + 0.5);
    }
}