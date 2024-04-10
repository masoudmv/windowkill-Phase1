package controller;

import model.charactersModel.EpsilonModel;
import model.movement.Direction;
import view.myFrame;

import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

import static controller.Utils.addVectors;
import static controller.Utils.multiplyVector;


public class KeyController implements KeyListener{
    private Set<Integer> keysPressed = new HashSet<>();
    private Timer timer;
    public KeyController(){
        myFrame.getINSTANCE().addKeyListener(this);
//        timer = new Timer(15, this);
//        timer.start();
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        keysPressed.add(e.getKeyCode());
//        System.out.println("pressing");
        updateMovement();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed.remove(e.getKeyCode());
    }

    private void updateMovement() {
        boolean right = keysPressed.contains(KeyEvent.VK_D);
        boolean left = keysPressed.contains(KeyEvent.VK_A);
        boolean up = keysPressed.contains(KeyEvent.VK_W);
        boolean down = keysPressed.contains(KeyEvent.VK_S);

        if (right) {
//            System.out.println("right");
            Point2D.Double vector=new Point2D.Double(0.5,0);
            Point2D point = multiplyVector(EpsilonModel.getINSTANCE().getDirection().getNormalizedDirectionVector(), EpsilonModel.getINSTANCE().getDirection().getMagnitude());
            Direction direction = new Direction(addVectors(point,vector));
            direction.adjustDirectionMagnitude();
            EpsilonModel.getINSTANCE().setDirection(direction);
        }

        if (left) {
//            System.out.println("left");
            Point2D.Double vector=new Point2D.Double(-0.5,0);
            Point2D point = multiplyVector(EpsilonModel.getINSTANCE().getDirection().getNormalizedDirectionVector(), EpsilonModel.getINSTANCE().getDirection().getMagnitude());
            Direction direction = new Direction(addVectors(point,vector));
            direction.adjustDirectionMagnitude();
            EpsilonModel.getINSTANCE().setDirection(direction);
        }
        if (up) {
//            System.out.println("up");
            Point2D.Double vector=new Point2D.Double(0,-0.5);
            Point2D point = multiplyVector(EpsilonModel.getINSTANCE().getDirection().getNormalizedDirectionVector(), EpsilonModel.getINSTANCE().getDirection().getMagnitude());
            Direction direction = new Direction(addVectors(point,vector));
            direction.adjustDirectionMagnitude();
            EpsilonModel.getINSTANCE().setDirection(direction);

        }
        if (down) {
//            System.out.println("down");
            Point2D.Double vector=new Point2D.Double(0,0.5);
            Point2D point = multiplyVector(EpsilonModel.getINSTANCE().getDirection().getNormalizedDirectionVector(), EpsilonModel.getINSTANCE().getDirection().getMagnitude());
            Direction direction = new Direction(addVectors(point,vector));
            direction.adjustDirectionMagnitude();
            EpsilonModel.getINSTANCE().setDirection(direction);
        }

    }


//    @Override
//    public void actionPerformed(ActionEvent e) {
////        System.out.println("fdfdf");
////        epsilon.updatePosition(); // Update player's position based on velocity and acceleration
////        for (Trigorath trigorath: trigoraths){
////            trigorath.updatePosition();
////            trigorath.updateVelocity();
////        }
//        updateMovement();
////        view.refresh();
//    }


}