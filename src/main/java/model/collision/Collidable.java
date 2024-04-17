//package model.collision;
//
//import java.awt.geom.Line2D;
//import java.awt.geom.Point2D;
//import java.util.ArrayList;
//import java.util.List;
//
//import static controller.Constants.RADIUS;
//import static controller.Utils.*;
//import static controller.Utils.PerpendicularCounterClockwise;
//
//public interface Collidable {
//    ArrayList<Collidable> collidables = new ArrayList<>();
//    boolean isCircular();
//    //    double getRadius();
//    Point2D getAnchor();
//    Point2D[] getVertices();
//    boolean isImpactInProgress();
//    void setImpactInProgress(boolean impactInProgress);
//    void impact(CollisionState collisionState);
//    void impact(Point2D normalVector, CollisionState collisionState);
//    void impact(Point2D normalVector);
//    //        direction.adjustEpsilonDirectionMagnitude();
//    double getImpactCoefficient(Point2D collisionRelativeVector);
//
////    default void collides(Collidable collidable){
////        if (isCircular() && !collidable.isCircular()){
////            Point2D intersection = closestPointOnPolygon(getAnchor(), collidable.getVertices());
////            if (intersection.distance(getAnchor()) <= RADIUS){
////
////
////                for (Collidable coll : collidables){
//////                    if (!coll.isImpactInProgress())
//////                        coll.impact(new CollisionState(intersection));
////                    if (coll == collidable){
////                        coll.impact(relativeLocation(intersection, getAnchor()), new CollisionState(intersection));
////                    } else if (coll == this){
////                        coll.impact(relativeLocation(getAnchor(), intersection), new CollisionState(intersection));
////                    } else {
////                        coll.impact(new CollisionState(intersection));
////                    }
////                }
////            }
////
////        } else if (!isCircular() && collidable.isCircular()){
////            collidable.collides(this);
////        } else if (!isCircular() && !collidable.isCircular()){
////            Point2D[] normalVectors = separatingAxisTheorem(this, collidable);
////            if (normalVectors!=null){
////                this.impact(normalVectors[0]);
////                collidable.impact(normalVectors[1]);
////            }
////
////
//////            Point2D intersection1 = findIntersectionPoint(this, collidable).head1;
//////            Point2D intersection2 = findIntersectionPoint(this, collidable).head2;
//////            System.out.println(intersection1+"   "+ intersection2);
////
////
////
//////            if (intersection1!=null && intersection2!=null) {
////
//////                Point2D intersection = closest.get(0);
//////                for (Collidable coll : collidables){
//////                    coll.impact(new CollisionState(intersection));
//////                    if (!coll.isImpactInProgress())
//////                        coll.impact(new CollisionState(intersection1));
//////                    if (coll == collidable){
//////                        collidable.impact(getCollisionNormalVector(collidable.getAnchor(), this), new CollisionState(intersection2));
//////                        collidable.impact(new CollisionState(intersection2));
//////                    } else if (coll == this){
//////                        this.impact(getCollisionNormalVector(this.getAnchor(), collidable), new CollisionState(intersection1));
//////                        this.impact(new CollisionState(intersection1));
//////                    } else {
//////                        coll.impact(new CollisionState(intersection2));
//////                    }
//////                }
//////                return new CollisionState(intersection);
////
////
////        }
////    }
//
//    default void collides(Collidable collidable){
//        if (isCircular() && !collidable.isCircular()){
//            Point2D intersection = closestPointOnPolygon(getAnchor(), collidable.getVertices());
//            if (intersection.distance(getAnchor()) <= RADIUS){
//
//
//                for (Collidable coll : collidables){
////                    if (!coll.isImpactInProgress())
////                        coll.impact(new CollisionState(intersection));
//                    if (coll == collidable){
//                        coll.impact(relativeLocation(intersection, getAnchor()), new CollisionState(intersection));
//                    } else if (coll == this){
//                        coll.impact(relativeLocation(getAnchor(), intersection), new CollisionState(intersection));
//                    } else {
//                        coll.impact(new CollisionState(intersection));
//                    }
//                }
//            }
//
//        } else if (!isCircular() && collidable.isCircular()){
//            collidable.collides(this);
//        } else if (!isCircular() && !collidable.isCircular()) {
//            Point2D intersection = findSingleIntersectionPoint(this, collidable);
//            if (intersection != null) {
////                Point2D intersection = closest.get(0);
//                for (Collidable coll : collidables) {
////                    coll.impact(new CollisionState(intersection));
//                    if (!coll.isImpactInProgress())
//                        coll.impact(new CollisionState(intersection));
//                    if (coll == collidable) {
//                        collidable.impact(getCollisionNormalVector(collidable.getAnchor(), this), new CollisionState(intersection));
//                    } else if (coll == this) {
//                        this.impact(getCollisionNormalVector(this.getAnchor(), collidable), new CollisionState(intersection));
//                    } else {
//                        coll.impact(new CollisionState(intersection));
//                    }
//                }
////                return new CollisionState(intersection);
//
//            }
//        }
//    }
//
//    private static Point2D getIntersectionPoint(Line2D line1, Line2D line2) {
//        if (!line1.intersectsLine(line2)) return null;
//
//        double px = line1.getX1(),
//                py = line1.getY1(),
//                rx = line1.getX2() - px,
//                ry = line1.getY2() - py;
//        double qx = line2.getX1(),
//                qy = line2.getY1(),
//                sx = line2.getX2() - qx,
//                sy = line2.getY2() - qy;
//
//        double det = rx * sy - ry * sx;
//        if (det == 0) {
//            return null;  // Lines are parallel
//        } else {
//            double t = ((qx - px) * sy - (qy - py) * sx) / det;
//            return new Point2D.Double(px + t * rx, py + t * ry);
//        }
//    }
//    public static Point2D findSingleIntersectionPoint(Collidable poly1, Collidable poly2) {
//        List<Point2D> intersectionPoints = new ArrayList<>();
//
//        Point2D[] vertices1 = poly1.getVertices();
//        Point2D[] vertices2 = poly2.getVertices();
//
//        for (int i = 0; i < vertices1.length; i++) {
//            Line2D line1 = new Line2D.Double(vertices1[i], vertices1[(i + 1) % vertices1.length]);
//
//            for (int j = 0; j < vertices2.length; j++) {
//                Line2D line2 = new Line2D.Double(vertices2[j], vertices2[(j + 1) % vertices2.length]);
//                Point2D intersection = getIntersectionPoint(line1, line2);
//                if (intersection != null) {
//                    intersectionPoints.add(intersection);
//                }
//            }
//        }
//
//        // Determine the strategy for returning a single intersection point
//        if (intersectionPoints.isEmpty()) {
//            return null;  // No intersections
//        } else if (intersectionPoints.size() == 1) {
//            return intersectionPoints.get(0);
//        } else {
//            // Find centroid or closest vertex
//            Point2D centroid = intersectionPoints.stream()
//                    .reduce(new Point2D.Double(0, 0), (acc, p) -> {
//                        acc.setLocation(acc.getX() + p.getX(), acc.getY() + p.getY());
//                        return acc;
//                    });
//            centroid.setLocation(centroid.getX() / intersectionPoints.size(), centroid.getY() / intersectionPoints.size());
//
//            double minDistance = Double.MAX_VALUE;
//            Point2D closestVertex = null;
//            for (Point2D vertex : vertices1) { // Check vertices of polygon 1
//                double distance = centroid.distance(vertex);
//                if (distance < minDistance) {
//                    minDistance = distance;
//                    closestVertex = vertex;
//                }
//            }
//            for (Point2D vertex : vertices2) { // Check vertices of polygon 2
//                double distance = centroid.distance(vertex);
//                if (distance < minDistance) {
//                    minDistance = distance;
//                    closestVertex = vertex;
//                }
//            }
//            return closestVertex; // Nearest vertex to centroid of all intersection points
//        }}
//
//
//    default Point2D closestPointOnPolygon (Point2D point, Point2D[] vertices){
//        double minDistance = Double.MAX_VALUE;
//        Point2D closest = null;
//        for (int i = 0; i < vertices.length; i++) {
//            Point2D temp = getClosestPointOnSegment(vertices[i], vertices[(i + 1) % vertices.length], point);
//            double distance = temp.distance(point);
//            if (distance < minDistance) {
//                minDistance = distance;
//                closest = temp;
//            }
//        }
//        return closest;
//    }
//}
//
//Point2D getCollisionNormalVector(Point2D point, Collidable collidable){
//    double minDistance = Double.MAX_VALUE;
//    Point2D normalVector = null;
//    if (collidable.isCircular()){
//        Point2D intersection = closestPointOnPolygon(this.getAnchor(), collidable.getVertices());
//        return relativeLocation(intersection, collidable.getAnchor());
//    } else {
//        Point2D[] vertices = collidable.getVertices();
//        for (int i=0;i<vertices.length;i++){
//            Point2D temp = getClosestPointOnSegment(vertices[i],vertices[(i+1)%vertices.length], point);
//            Point2D edge;
//            double distance = temp.distance(point);
//            if (distance < minDistance) {
//                edge = relativeLocation(vertices[(i+1)%vertices.length], vertices[i]);
//                minDistance = distance;
//                normalVector = PerpendicularCounterClockwise(edge);
//            }
//        }
//    }
//    return normalVector;
//}
//
//default boolean doPolygonsIntersect(Collidable poly1, Collidable poly2) {
//    // Combine both polygons' vertices; assume they are defined in a clockwise manner
//    Point2D[] vertices1 = poly1.getVertices();
//    Point2D[] vertices2 = poly2.getVertices();
//
//    // Check all axes formed by the edges of both polygons
//    for (int i = 0; i < vertices1.length + vertices2.length; i++) {
//        Point2D p1 = i < vertices1.length ? vertices1[i] : vertices2[i - vertices1.length];
//        Point2D p2 = i < vertices1.length ? vertices1[(i + 1) % vertices1.length] : vertices2[(i + 1 - vertices1.length) % vertices2.length];
//
//        Point2D edge = new Point2D.Double(p2.getX() - p1.getX(), p2.getY() - p1.getY());
//        Point2D axis = new Point2D.Double(-edge.getY(), edge.getX()); // Perpendicular to the edge
//
//        double minA = Float.MAX_VALUE, maxA = -Float.MAX_VALUE;
//        for (Point2D p : vertices1) {
//            double projection = (p.getX() * axis.getX() + p.getY() * axis.getY()) / (axis.getX() * axis.getX() + axis.getY() * axis.getY());
//            minA = Math.min(minA, projection);
//            maxA = Math.max(maxA, projection);
//        }
//
//        double minB = Float.MAX_VALUE, maxB = -Float.MAX_VALUE;
//        for (Point2D p : vertices2) {
//            double projection = (p.getX() * axis.getX() + p.getY() * axis.getY()) / (axis.getX() * axis.getX() + axis.getY() * axis.getY());
//            minB = Math.min(minB, projection);
//            maxB = Math.max(maxB, projection);
//        }
//
//        if (maxA < minB || maxB < minA) {
//            // No overlap on this axis, polygons do not intersect
//            return false;
//        }
//    }
//
//    // All axes have overlap, polygons intersect
//    return true;
//}
//
//
//
//static Point2D getClosestPointOnSegment(Point2D head1, Point2D head2, Point2D point) {
//    double u =((point.getX()-head1.getX())*(head2.getX()-head1.getX())+(point.getY()-head1.getY())*(head2.getY()-head1.getY()))/head2.distanceSq(head1);
////        head1.distanceSq()
//    if (u > 1.0) return (Point2D) head2.clone();
//    else if (u <= 0.0) return (Point2D) head1.clone();
//    else return new Point2D.Double(head2.getX()* u + head1.getX() * (1.0 - u) + 0.5,head2.getY() * u + head1.getY() * (1.0 - u) + 0.5);
//}
//
//// Method to find all intersection points between two polygons
////    default Segment findIntersectionPoint(Collidable poly1, Collidable poly2) {
////
////        Point2D[] verticesOfPoly1 = poly1.getVertices();
////        Point2D[] verticesOfPoly2 = poly2.getVertices();
////
////        Segment[] segmentsOfPoly1 = new Segment[verticesOfPoly1.length];
////        Segment[] segmentsOfPoly2 = new Segment[verticesOfPoly2.length];
////
////        Point2D firstPolyIntersection = null;
////        Point2D secondPolyIntersection = null;
////
////        for (int i = 0; i < verticesOfPoly1.length; i++) {
////            Segment segment = new Segment(verticesOfPoly1[i], verticesOfPoly1[(i+1)%verticesOfPoly1.length]);
////            segment.collidable = poly1;
////            segmentsOfPoly1[i] = segment;
////        } for (int i = 0; i < verticesOfPoly2.length; i++) {
////            Segment segment = new Segment(verticesOfPoly2[i], verticesOfPoly2[(i+1)%verticesOfPoly2.length]);
////            segment.collidable = poly2;
////            segmentsOfPoly2[i] = segment;
////        }
////
////        ArrayList<Point2D> intersections = new ArrayList<>();
////
////        for (int i = 0; i < segmentsOfPoly1.length; i++) {
////            for (int j = 0; j < segmentsOfPoly2.length; j++) {
////                Point2D intersectionPoint = findIntersection(segmentsOfPoly1[i], segmentsOfPoly2[j]);
////
////                if (intersectionPoint != null) {
////                    intersections.add(intersectionPoint);
////                    new Intersection(
////                            intersectionPoint, segmentsOfPoly1[i], segmentsOfPoly2[j]
////                    );
////                }
////
////            }
////        }
////
////        for (int i = 0; i < Intersection.possibleIntersections.size(); i++) {
////            for (int j = i+1; j < Intersection.possibleIntersections.size(); j++) {
////
////                Intersection inter1 = Intersection.possibleIntersections.get(i);
////                Intersection inter2 = Intersection.possibleIntersections.get(j);
////
////                if (inter1.firstEdge == inter2.firstEdge){
////                    if (inter1.firstEdge.collidable == poly1) firstPolyIntersection = findMidPoint(inter1.intersectionPoint, inter2.intersectionPoint);
////                    else secondPolyIntersection = findMidPoint(inter1.intersectionPoint, inter2.intersectionPoint);
////
////                };
////                if (inter1.firstEdge == inter2.secondEdge){
////                    if (inter1.firstEdge.collidable == poly1) firstPolyIntersection = findMidPoint(inter1.intersectionPoint, inter2.intersectionPoint);
////                    else secondPolyIntersection = findMidPoint(inter1.intersectionPoint, inter2.intersectionPoint);
////
////                };
////                if (inter1.secondEdge == inter2.firstEdge){
////                    if (inter1.secondEdge.collidable == poly1) firstPolyIntersection = findMidPoint(inter1.intersectionPoint, inter2.intersectionPoint);
////                    else secondPolyIntersection = findMidPoint(inter1.intersectionPoint, inter2.intersectionPoint);
////                };
////                if (inter1.secondEdge == inter2.secondEdge){
////                    if (inter1.secondEdge.collidable == poly1) firstPolyIntersection = findMidPoint(inter1.intersectionPoint, inter2.intersectionPoint);
////                    else secondPolyIntersection = findMidPoint(inter1.intersectionPoint, inter2.intersectionPoint);
////                };
////
////
////
////                if (inter1.firstEdge.collidable == inter2.firstEdge.collidable){
////                    if (inter1.firstEdge.collidable == poly1) firstPolyIntersection = findIntersection(inter1.firstEdge, inter2.firstEdge);
////                    else secondPolyIntersection = findIntersection(inter1.firstEdge, inter2.firstEdge);
////
////                };
////                if (inter1.firstEdge.collidable == inter2.secondEdge.collidable){
////                    if (inter1.firstEdge.collidable == poly1) firstPolyIntersection = findIntersection(inter1.firstEdge, inter2.secondEdge);
////                    else secondPolyIntersection = findIntersection(inter1.firstEdge, inter2.secondEdge);
////                };
////                if (inter1.secondEdge.collidable == inter2.firstEdge.collidable){
////                    if (inter1.secondEdge.collidable == poly1) firstPolyIntersection = findIntersection(inter1.secondEdge, inter2.firstEdge);
////                    else secondPolyIntersection = findIntersection(inter1.secondEdge, inter2.firstEdge);
////                };
////                if (inter1.secondEdge.collidable == inter2.secondEdge.collidable){
////                    if (inter1.secondEdge.collidable == poly1) firstPolyIntersection = findIntersection(inter1.secondEdge, inter2.secondEdge);
////                    else secondPolyIntersection = findIntersection(inter1.secondEdge, inter2.secondEdge);
////                };
////            }
////        }
////        return new Segment(firstPolyIntersection, secondPolyIntersection);
////    }
////
////    class Intersection {
////        static ArrayList<Intersection> possibleIntersections = new ArrayList<>();
////        Point2D intersectionPoint;
////        Segment firstEdge;
////        Segment secondEdge;
////
////        public Intersection(Point2D intersectionPoint, Segment firstEdge, Segment secondEdge) {
////            this.intersectionPoint = intersectionPoint;
////            this.firstEdge = firstEdge;
////            this.secondEdge = secondEdge;
////            possibleIntersections.add(this);
////        }
////    }
//
//
//default Projection project(Point2D axis){
//    Point2D[] vertices = this.getVertices();
//    double min = dotVectors(axis, vertices[0]);
//
//    double max = min;
//    for (int i = 1; i < vertices.length; i++) {
//        // NOTE: the axis must be normalized to get accurate projections
//        double p = dotVectors(axis, vertices[i]);
//        if (p < min) {
//            min = p;
//        } else if (p > max) {
//            max = p;
//        }
//    }
//    return new Projection(min, max);
//}
//
//default Point2D[] getAxes(){
//    Point2D[] vertices = this.getVertices();
//    Point2D[] axis = new Point2D[vertices.length];
//    for (int i = 0; i < vertices.length; i++) {
//        Point2D edge = relativeLocation(vertices[(i+1)%vertices.length], vertices[i]);
//        axis[i] = PerpendicularCounterClockwise(edge);
//    }
//    return axis;
//}
//
//
//static Point2D[] separatingAxisTheorem(Collidable polygon1, Collidable polygon2){
//    double overlap = Double.MAX_VALUE;
//    Point2D smallest = null;
//    Collidable collidable = null;
//    Point2D edge = null;
//
//
//    Point2D[] vertices1 = polygon1.getVertices();
//    Point2D[] vertices2 = polygon2.getVertices();
//
//    Point2D[] edges1 = new Point2D[vertices1.length];
//    Point2D[] edges2 = new Point2D[vertices2.length];
//
//    Point2D[] axes1 = new Point2D[vertices1.length];
//    Point2D[] axes2 = new Point2D[vertices2.length];
//
//
//    for (int i = 0; i < vertices1.length; i++) {
//        edges1[i] = relativeLocation(vertices1[(i+1)%vertices1.length], vertices1[i]);
//        axes1[i] = PerpendicularCounterClockwise(edges1[i]);
//    }
//
//    for (int i = 0; i < vertices2.length; i++) {
//        edges2[i] = relativeLocation(vertices2[(i+1)%vertices2.length], vertices2[i]);
//        axes2[i] = PerpendicularCounterClockwise(edges2[i]);
//    }
//
////        Point2D[] axes1 = polygon1.getAxes();
////        Point2D[] axes2 = polygon2.getAxes();
//    // loop over the axes1
//    for (int i = 0; i < axes1.length; i++) {
//        Point2D axis = axes1[i];
//        // project both shapes onto the axis
//        Projection p1 = polygon1.project(axis);
//        Projection p2 = polygon2.project(axis);
//        // do the projections overlap?
//        if (!p1.overlap(p2)) {
//            // then we can guarantee that the shapes do not overlap
////                return;
//            return null;
//        } else {
//            // get the overlap
//            double o = p1.getOverlap(p2);
//            // check for minimum
//            if (o < overlap) {
//                // then set this one as the smallest
//                overlap = o;
//                smallest = axis;
//                collidable = polygon1;
//                edge = edges1[i];
//            }
//        }
//    }
//    // loop over the axes2
//    for (int i = 0; i < axes2.length; i++) {
//        Point2D axis = axes2[i];
//        // project both shapes onto the axis
//        Projection p1 = polygon1.project(axis);
//        Projection p2 = polygon2.project(axis);
//        // do the projections overlap?
//        if (!p1.overlap(p2)) {
//            // then we can guarantee that the shapes do not overlap
////                return;
//            return null;
//        } else {
//            // get the overlap
//            double o = p1.getOverlap(p2);
//            // check for minimum
//            if (o < overlap) {
//                // then set this one s the smallest
//                overlap = o;
//                smallest = axis;
//                collidable = polygon2;
//                edge = edges2[i];
//            }
//        }
//    }
//
//    Point2D[] vectors = new Point2D[2];
//
//    Point2D normalVector1 = null;
//    Point2D normalVector2 = null;
//
//
//
//    if (collidable == polygon1 ) {
//        normalVector1 = normalizeVector(PerpendicularClockwise(edge));
//        normalVector2 = normalizeVector(PerpendicularCounterClockwise(edge));
//    }
//
//
//
//
//    if (collidable == polygon2 ) {
//        normalVector2 = normalizeVector(PerpendicularClockwise(edge));
//        normalVector1 = normalizeVector(PerpendicularCounterClockwise(edge));
//    }
//
//    vectors[0] = normalVector1;
//    vectors[1] = normalVector2;
//
//    System.out.println(normalVector1 +"    "+normalVector2);
//
//    return vectors;
//
//
//
//
//
//
//    // i assume no two polygons intersect with their vertices only ...
//
//    // get the normal vector of the colliding edge of polygon
//    // this polygon intersects with its edge
////        Point2D normalVector = normalizeVector(PerpendicularClockwise(edge));
////        return normalVector;
//}
//
//
//
//public static List<Point2D> findIntersectionPoints(Collidable poly1, Collidable poly2) {
//    List<Point2D> intersectionPoints = new ArrayList<>();
//
//    Point2D[] vertices1 = poly1.getVertices();
//    Point2D[] vertices2 = poly2.getVertices();
//
//    for (int i = 0; i < vertices1.length; i++) {
//        Line2D line1 = new Line2D.Double(vertices1[i], vertices1[(i + 1) % vertices1.length]);
//
//        for (int j = 0; j < vertices2.length; j++) {
//            Line2D line2 = new Line2D.Double(vertices2[j], vertices2[(j + 1) % vertices2.length]);
//            Point2D intersection = getIntersectionPoint(line1, line2);
//            if (intersection != null) {
//                intersectionPoints.add(intersection);
//            }
//        }
//    }
//    return intersectionPoints;
//
//
//}
//
//
