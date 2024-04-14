package view;

//import view.charactersView.EpsilonView;

import view.charactersView.Drawable;
import view.charactersView.EpsilonView;
import view.charactersView.SquarantineView;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

import static controller.Constants.*;
import static view.charactersView.Drawable.drawables;

//public final class myPanel extends JPanel  implements Collidable {
public final class mainPanel extends JPanel  {

    private static mainPanel INSTANCE;
    private final Random rng=new Random();
    private mainPanel() {
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
        for(Drawable obj: drawables){
            obj.draw(g);
        }
    }

    public static mainPanel getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new mainPanel();
        return INSTANCE;
    }

}