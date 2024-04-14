package view.charactersView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Constants.RADIUS;

public class EpsilonView implements Drawable{
    String id;
    Point2D currentLocation=new Point2D.Double(0,0);
    public static ArrayList<EpsilonView> epsilonViews=new ArrayList<>();
    public EpsilonView(String id) {
        this.id = id;
        epsilonViews.add(this);
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
    public void draw (Graphics g){
        g.setColor(Color.BLACK);
        Point2D location = this.getCurrentLocation();
        g.fillOval((int) (location.getX()-RADIUS), (int) (location.getY()-RADIUS), (int) (2 *RADIUS), (int) (2*RADIUS));
    }

}