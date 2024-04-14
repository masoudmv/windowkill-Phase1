package controller;

import java.awt.geom.Point2D;
import java.util.Vector;

public class Utils {
    public static Point2D relativeLocation(Point2D point,Point2D anchor){
        return new Point2D.Double(point.getX()-anchor.getX(),point.getY()-anchor.getY());
    }
    public static Point2D multiplyVector(Point2D point,double scalar){
        return new Point2D.Double(point.getX()*scalar,point.getY()*scalar);
    }
    public static Point2D multiplyVectorXYComponent(Point2D point,double scalarX, double scalarY){
        return new Point2D.Double(point.getX()*scalarX, point.getY() * scalarY);
    }
    public static Point2D addVectors(Point2D point1,Point2D point2){
        return new Point2D.Double(point1.getX()+point2.getX(),point1.getY()+point2.getY());
    }
    public static Point2D weightedAddVectors(Point2D point1,Point2D point2,double weight1,double weight2){
        return multiplyVector(addVectors(multiplyVector(point1,weight1),multiplyVector(point2,weight2)),1/(weight1+weight2));
    }
    public static double dotVectors(Point2D point1, Point2D point2){
        return point1.getX() * point2.getX() + point1.getY() * point2.getY();
    }
    public static Point2D normalizeVector(Point2D point){
        double size = Math.hypot(point.getX(), point.getY());
        double x = point.getX() / size;
        double y = point.getY() / size;
        point.setLocation(x, y);
        return point;
    }
    public static Point2D PerpendicularClockwise(Point2D vector)
    {
        return new Point2D.Double(vector.getY(), -vector.getX());
    }

    public static Point2D PerpendicularCounterClockwise(Point2D vector)
    {
        return new Point2D.Double(-vector.getY(), vector.getX());
    }

}