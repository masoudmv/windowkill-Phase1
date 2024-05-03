package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import static java.awt.Color.gray;
public class SkillTreeMenu extends JPanel implements MouseListener {
    JLabel backLabel = new JLabel("back");
    Point backButton = new Point(22, 22);

    JLabel aresLabel = new JLabel("Ares");
    Point aresRect = new Point(22, 140);

    JLabel acesoLabel = new JLabel("Aceso");
    Point acesoRect = new Point(214, 140);

    JLabel proteusLabel = new JLabel("Proteus");
    Point proteusRect = new Point(406, 140);
    private int width = 170;
    private int height = 440;

    public SkillTreeMenu() {
        MainFrame frame = MainFrame.getINSTANCE();
        Dimension dimension = new Dimension(600, 600);
        setSize(dimension);
        setVisible(true);
        setLocationToCenter(frame);
        setLayout(null);


        backLabel.setBounds(backButton.x+20, backButton.y, 75, 35);
        add(backLabel);

        aresLabel.setBounds(aresRect.x+20, aresRect.y, 75, 35);
        add(aresLabel);

        acesoLabel.setBounds(acesoRect.x+20, acesoRect.y, 75, 35);
        add(acesoLabel);

        proteusLabel.setBounds(proteusRect.x+20, proteusRect.y, 75, 35);
        add(proteusLabel);







        addMouseListener(this);


        frame.add(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.black);
        g.setColor(gray);


        g.drawRect(backButton.x, backButton.y, 75, 35);
        g.drawRect(aresRect.x, aresRect.y, width, height);
        g.drawRect(acesoRect.x, acesoRect.y, width, height);
        g.drawRect(proteusRect.x, proteusRect.y, width, height);




        labelRepaint();

    }

    private void labelRepaint(){

        backLabel.repaint();
        aresLabel.repaint();
        acesoLabel.repaint();
        proteusLabel.repaint();
//        settingsLabel.repaint();
//        tutorialLabel.repaint();
//        skillTreeLabel.repaint();
//        exitLabel.repaint();
    }

    public void setLocationToCenter(MainFrame frame){
        setLocation(frame.getWidth()/2-getWidth()/2,frame.getHeight()/2-getHeight()/2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ( e.getY()>backButton.y && e.getY()<backButton.y+35 && backButton.x<e.getX() && e.getX()<backButton.x+75) System.out.println("back chosen");
        if ( e.getY()>aresRect.y && e.getY()<aresRect.y+height && aresRect.x<e.getX() && e.getX()<aresRect.x+width) System.out.println("ares chosen");
        if ( e.getY()>acesoRect.y && e.getY()<acesoRect.y+height && acesoRect.x<e.getX() && e.getX()<acesoRect.x+width) System.out.println("aceso chosen");
        if ( e.getY()>proteusRect.y && e.getY()<proteusRect.y+height && proteusRect.x<e.getX() && e.getX()<proteusRect.x+width) System.out.println("proteus chosen");

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

