package view;

import controller.Game;
import model.charactersModel.EpsilonModel;
import org.example.Main;
import view.charactersView.Drawable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.GlyphMetrics;
import java.awt.geom.Rectangle2D;

import static controller.Constants.FRAME_DIMENSION;
import static controller.Constants.PANEL_SIZE;
import static controller.Game.ShopAbility.*;
import static java.awt.Color.blue;
import static java.awt.Color.white;
import static view.charactersView.Drawable.drawables;

public class ShopPanel extends JPanel implements MouseListener {

    static Rectangle banish = new Rectangle(50,50,200,200);
    static Rectangle empower= new Rectangle(300,50,200,200);
    static Rectangle heal= new Rectangle(550,50,200,200);


    public ShopPanel() {
        MainFrame frame = MainFrame.getINSTANCE();
        Dimension dimension = new Dimension(800, 300);
        setSize(dimension);
        setLocationToCenter(frame);
        setBackground(white);


//        Label heal = new Label("O' Apollo Heal");
//        heal.setVisible(true);
//        heal.setForeground(blue);
//        heal.setBounds(500,500,1000,1000);
//        add(heal);
//        frame.add(heal);

        setVisible(true);
        frame.add(this);
    }





    public void setLocationToCenter(MainFrame frame){
        setLocation(frame.getWidth()/2-getWidth()/2,frame.getHeight()/2-getHeight()/2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.black);


        g.drawRect(50, 50,200,200);
        g.drawRect(300,50,200,200);
        g.drawRect(550,50,200,200);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        TODO shop empower, heal, banish selection

        if ( e.getY()>334 && e.getY()<534 && 418<e.getX() && e.getX()<618) Game.shopAbility = HEAL;
        if ( e.getY()>334 && e.getY()<534 && 668<e.getX() && e.getX()<868) Game.shopAbility = EMPOWER;
        if ( e.getY()>334 && e.getY()<534 && 918<e.getX() && e.getX()<1118) Game.shopAbility = BANISH;

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }




}
