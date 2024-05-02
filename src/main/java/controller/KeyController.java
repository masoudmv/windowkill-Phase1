//package controller;
//
//import model.charactersModel.EpsilonModel;
//import model.movement.Direction;
//import view.myFrame;
//
//import javax.swing.*;
//import java.awt.event.*;
//import java.awt.geom.Point2D;
//import java.util.HashSet;
//import java.util.Set;
//
//import static controller.Utils.addVectors;
//import static controller.Utils.multiplyVector;
//
//
//public class KeyController implements KeyListener {
//    private Set<Integer> keysPressed = new HashSet<>();
//    public static boolean movementInProgress = false;
//    private final int MOVEMENT_DELAY = 10; // Delay in milliseconds
//    private Timer movementTimer;
////    public static double deltaX = 0;
////    public static double deltaY = 0;
//
//    public KeyController() {
//        myFrame.getINSTANCE().addKeyListener(this);
//    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {}
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        keysPressed.add(e.getKeyCode());
//        if (!movementInProgress) {
//            startMovementTimer();
//            movementInProgress = true;
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        keysPressed.remove(e.getKeyCode());
//        if (keysPressed.isEmpty()) {
//            stopMovementTimer();
//            movementInProgress = false;
////            deltaX = 0; deltaY=0;
//        }
//    }
//
//    private void startMovementTimer() {
//        movementTimer = new Timer(MOVEMENT_DELAY, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                updateMovement();
//            }
//        });
//        movementTimer.start();
//
//    }
//
//    private void stopMovementTimer() {
//        if (movementTimer != null) {
//            movementTimer.stop();
//            movementTimer = null;
//
//        }
//    }
//
//    private void updateMovement() {
//
//        double deltaX=0;
//        double deltaY=0;
//
//
//        if (keysPressed.contains(KeyEvent.VK_D)) {
//            deltaX += 1;
//        }
//        if (keysPressed.contains(KeyEvent.VK_A)) {
//            deltaX -= 1;
//        }
//        if (keysPressed.contains(KeyEvent.VK_W)) {
//            deltaY -= 1;
//        }
//        if (keysPressed.contains(KeyEvent.VK_S)) {
//            deltaY += 1;
//        }
//
//        Point2D.Double vector = new Point2D.Double(deltaX, deltaY);
//        Point2D point = multiplyVector(EpsilonModel.getINSTANCE().getDirection().getNormalizedDirectionVector(),
//                EpsilonModel.getINSTANCE().getDirection().getMagnitude());
//        Direction direction = new Direction(addVectors(point, vector));
//        direction.adjustEpsilonDirectionMagnitude();
//        EpsilonModel.getINSTANCE().setDirection(direction);
//    }
//}
//
