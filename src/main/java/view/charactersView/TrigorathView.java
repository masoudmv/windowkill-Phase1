package view.charactersView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Constants.RADIUS;
import static controller.Constants.TRIGORATH_RADIUS;

public class TrigorathView implements Drawable{
    String id;
    Point2D currentLocation = new Point2D.Double(0,0);
    Point2D point1= new Point2D.Double(0,0);
    Point2D point2= new Point2D.Double(0,0);
    Point2D point3= new Point2D.Double(0,0);
    private Point2D[] vertices = new Point2D[]{point1,point2,point3};
    public static ArrayList<TrigorathView> trigorathViews=new ArrayList<>();
    public TrigorathView(String id) {
        this.id = id;
        trigorathViews.add(this);
        drawables.add(this);
    }

    public Point2D getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Point2D currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getId() {
        return id;
    }

    @Override
    public void draw(Graphics g) {
        int[] xPoly = new int[]{(int) vertices[0].getX(), (int) vertices[1].getX(), (int) vertices[2].getX()};
        int[] yPoly = new int[]{(int) vertices[0].getY(), (int) vertices[1].getY(), (int) vertices[2].getY()};
        g.setColor(Color.black);
        Polygon poly = new Polygon(xPoly, yPoly, xPoly.length);
        g.fillPolygon(poly);
    }

    public void setVertices(Point2D[] vertices) {
        this.vertices = vertices;
    }
}