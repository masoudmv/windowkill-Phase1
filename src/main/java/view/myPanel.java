package view;

import model.collision.Collidable;
import view.charactersView.EpsilonView;

import view.charactersView.EpsilonView;
import view.charactersView.SquarantineView;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static controller.Constants.*;

//public final class myPanel extends JPanel  implements Collidable {
public final class myPanel extends JPanel  {

    private static myPanel INSTANCE;
    private final Random rng=new Random();
    private myPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black,5));
        setBackground(new Color(0,0,0,0));

        setSize(PANEL_SIZE);
//        setBackground(Color.BLACK);
        setLocationToCenter(myFrame.getINSTANCE());
        myFrame.getINSTANCE().add(this);
//        Collidable.collidables.add(this);
    }
    public void setLocationToCenter(myFrame glassFrame){
        setLocation(glassFrame.getWidth()/2-getWidth()/2,glassFrame.getHeight()/2-getHeight()/2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (EpsilonView epsilonView: EpsilonView.epsilonViews){
            g.setColor(Color.BLACK);
            Point2D location=epsilonView.getCurrentLocation();
            double radius=RADIUS;
            g.fillOval((int) (location.getX()-radius), (int) (location.getY()-radius), (int) (2 *radius), (int) (2*radius));
        }
        for (SquarantineView squarantineView: SquarantineView.squarantineViews){
            g.setColor(Color.BLACK);
            Point2D location=squarantineView.getCurrentLocation();
            g.fillRect((int)location.getX()-SQUARANTINE_EDGE/2, (int)location.getY()-SQUARANTINE_EDGE/2,SQUARANTINE_EDGE,SQUARANTINE_EDGE);
        }
    }

    public static myPanel getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new myPanel();
        return INSTANCE;
    }

//    @Override
//    public boolean isCircular() {
//        return false;
//    }
//
//    @Override
//    public Point2D getAnchor() {
//        return null;
//    }
//
//    @Override
//    public ArrayList<Point2D> getVertices() {
//        return new ArrayList<>(List.of(new Point2D.Double(getX(),getY()),new Point2D.Double(getX()+getWidth(),getY()),
//                new Point2D.Double(getX()+getWidth(),getY()+getHeight()),new Point2D.Double(getX(),getY()+getHeight())));
//    }
}