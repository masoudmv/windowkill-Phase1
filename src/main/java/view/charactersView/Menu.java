package view.charactersView;

import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.awt.Color.*;

public class Menu extends JPanel implements MouseListener {
    JLabel startLabel = new JLabel("Start");
    JLabel settingsLabel = new JLabel("Settings");
    JLabel tutorialLabel = new JLabel("Tutorial");
    JLabel skillTreeLabel = new JLabel("SkillTree");
    JLabel exitLabel = new JLabel("Exit");

    public Menu() {
        MainFrame frame = MainFrame.getINSTANCE();
        Dimension dimension = new Dimension(600, 600);
        setSize(dimension);
//        setBackground(yellow);
        setVisible(true);
        setLocationToCenter(frame);
//        JLabel label = new JLabel("sdsdsdsdsdsd");
        startLabel.setBounds(435, 100,100,50);
        settingsLabel.setBounds(425, 200,100,50);
        tutorialLabel.setBounds(425, 300,100,50);
        skillTreeLabel.setBounds(425, 400,100,50);
        exitLabel.setBounds(435, 500,100,50);
//        startLabel.setLocation(400,150);

//        label.s



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
//        g.setColor(white);
//        startLabel.setVerticalAlignment(1);
        g.setColor(gray);

        g.fillRect(400, 100,100,50);
        g.fillRect(400,200,100,50);
        g.fillRect(400,300,100,50);
        g.fillRect(400,400,100,50);
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
//        if ( e.getY()>334 && e.getY()<534 && 418<e.getX() && e.getX()<618) System.out.println("heal chosen");
//        if ( e.getY()>334 && e.getY()<534 && 668<e.getX() && e.getX()<868) System.out.println("emp chosen");
//        if ( e.getY()>334 && e.getY()<534 && 918<e.getX() && e.getX()<1118) System.out.println("banish chosen");
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
