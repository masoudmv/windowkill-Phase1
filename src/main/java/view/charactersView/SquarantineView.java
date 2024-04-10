package view.charactersView;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class SquarantineView {
    String id;
    Point2D currentLocation=new Point2D.Double(0,0);
    public static ArrayList<SquarantineView> squarantineViews=new ArrayList<>();
    public SquarantineView(String id) {
        this.id = id;
        squarantineViews.add(this);
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

}