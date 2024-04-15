package view.charactersView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Constants.SQUARANTINE_EDGE;

public class SquarantineView implements Drawable{
    String id;
    Point2D currentLocation=new Point2D.Double(0,0);
    public static ArrayList<SquarantineView> squarantineViews=new ArrayList<>();
    public SquarantineView(String id) {
        this.id = id;
        squarantineViews.add(this);
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
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        Point2D location = this.getCurrentLocation();
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.black);
        Rectangle rect2 = new Rectangle(
                (int)location.getX()-SQUARANTINE_EDGE/2, (int)location.getY()-SQUARANTINE_EDGE/2,
                SQUARANTINE_EDGE, SQUARANTINE_EDGE);

//        g2d.rotate(Math.toRadians(5));
        g2d.draw(rect2);
        g2d.fill(rect2);
    }

}