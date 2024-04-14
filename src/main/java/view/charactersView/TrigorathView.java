package view.charactersView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Constants.RADIUS;
import static controller.Constants.TRIGORATH_RADIUS;

public class TrigorathView implements Drawable{
    String id;
    Point2D currentLocation = new Point2D.Double(0,0);
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
        g.setColor(Color.black);
        Point2D location = this.getCurrentLocation();
        double radius = TRIGORATH_RADIUS;
        int[] xPoly = {(int) location.getX(), (int) (location.getX()+ radius*Math.cos(Math.PI/6)), (int) (location.getX()-radius*Math.cos(Math.PI/6))};
        int[] yPoly = {(int) (location.getY()-radius), (int) (location.getY()+radius/2), (int) (location.getY()+radius/2)};
        Polygon poly = new Polygon(xPoly, yPoly, xPoly.length);
        g.fillPolygon(poly);
    }
}