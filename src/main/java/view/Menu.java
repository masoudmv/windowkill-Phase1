package view;

import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import static java.awt.Color.*;

public class Menu extends JPanel implements MouseListener {
    JLabel startLabel = new JLabel("Start");
    JLabel settingsLabel = new JLabel("Settings");
    JLabel tutorialLabel = new JLabel("Tutorial");
    JLabel skillTreeLabel = new JLabel("SkillTree");
    JLabel exitLabel = new JLabel("Exit");
    Point start = new Point(400, 100);
    Point settings = new Point(400, 200);
    Point tutorial = new Point(400, 300);
    Point skillTree = new Point(400, 400);
    Point exit = new Point(400, 500);
    private int buttonHeight = 50;
    private int buttonWidth = 100;

    public Menu() {
        MainFrame frame = MainFrame.getINSTANCE();
        Dimension dimension = new Dimension(600, 600);
        setSize(dimension);
        setVisible(true);
        setLocationToCenter(frame);
        startLabel.setBounds(435, 100,buttonWidth,buttonHeight);
        settingsLabel.setBounds(425, 200,buttonWidth,buttonHeight);
        tutorialLabel.setBounds(425, 300,buttonWidth,buttonHeight);
        skillTreeLabel.setBounds(425, 400,buttonWidth,buttonHeight);
        exitLabel.setBounds(435, 500,buttonWidth,buttonHeight);
        add(startLabel);
        add(settingsLabel);
        add(tutorialLabel);
        add(skillTreeLabel);
        add(exitLabel);
        setLayout(null);
        addMouseListener(this);


        frame.add(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.black);
        g.setColor(gray);
        g.fillRect(start.x,start.y, 100, 50);
        g.fillRect(settings.x,settings.y,100,50);
        g.fillRect(tutorial.x,tutorial.y,100,50);
        g.fillRect(skillTree.x,skillTreeLabel.getY(),100,50);
        g.fillRect(400,500,100,50);
        labelRepaint();
    }

    private void labelRepaint(){
        startLabel.repaint();
        settingsLabel.repaint();
        tutorialLabel.repaint();
        skillTreeLabel.repaint();
        exitLabel.repaint();
    }

    public void setLocationToCenter(MainFrame frame){
        setLocation(frame.getWidth()/2-getWidth()/2,frame.getHeight()/2-getHeight()/2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ( e.getY()>start.y && e.getY()<start.y+buttonHeight && start.x<e.getX() && e.getX()<start.x+buttonWidth) System.out.println("start chosen");
        if ( e.getY()>settings.y && e.getY()<settings.y+buttonHeight && settings.x<e.getX() && e.getX()<settings.x+buttonWidth) System.out.println("settings chosen");
        if ( e.getY()>tutorial.y && e.getY()<tutorial.y+buttonHeight && tutorial.x<e.getX() && e.getX()<tutorial.x+buttonWidth) System.out.println("tutorial chosen");
        if ( e.getY()>skillTree.y && e.getY()<skillTree.y+buttonHeight && skillTree.x<e.getX() && e.getX()<skillTree.x+buttonWidth){
            MainFrame.getINSTANCE().remove(this);
            MainFrame.getINSTANCE().repaint();
            new SkillTreeMenu();
        }
        if ( e.getY()>exit.y && e.getY()<exit.y+buttonHeight && exit.x<e.getX() && e.getX()<exit.x+buttonWidth){
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println(e.getX());
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
