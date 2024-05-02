package controller;

import model.BulletModel;
import model.CollectibleModel;
import model.charactersModel.EpsilonModel;
import model.charactersModel.SquarantineModel;
import model.charactersModel.TrigorathModel;
import model.movement.Direction;
import model.movement.Movable;
import org.example.Main;
import view.*;
import view.charactersView.SquarantineView;
import view.charactersView.TrigorathView;
import view.charactersView.EpsilonView;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

import static controller.Constants.BULLET_VELOCITY;
import static controller.Constants.TRIGORATH_MAX_VEL_RADIUS;
import static controller.Controller.*;
//import static controller.KeyController.deltaX;
//import static controller.KeyController.deltaY;
import static controller.Game.*;
import static controller.Game.ShopAbility.*;
import static controller.Game.SkillTreeAbility.*;
import static controller.MouseController.*;
import static controller.Utils.addVectors;
import static controller.Utils.multiplyVector;
import static model.charactersModel.SquarantineModel.squarantineModels;
import static model.charactersModel.TrigorathModel.trigorathModels;
import static model.collision.Collidable.collidables;
import static model.movement.Movable.movables;
import static view.BulletView.bulletViews;
import static view.CollectibleView.collectibleViews;
import static view.charactersView.SquarantineView.squarantineViews;
import static view.charactersView.TrigorathView.trigorathViews;

public class Update implements KeyListener {
    // Add FPS tracking variables
    private long lastUpdateTime = System.currentTimeMillis();
    private int frameCount = 0;
    private long lastUpdateTimeUPS = System.currentTimeMillis();
    private int updateCount = 0;
    private Set<Integer> keysPressed = new HashSet<>();
    public static boolean movementInProgress = false;
    private final int MOVEMENT_DELAY = 10; // Delay in milliseconds
    private Timer movementTimer;
    private Timer gameLoop;
    private ShopPanel shopPanel=null;
    private int extraBullet=0;
//    public static double elap



    public Update() {
        MainFrame.getINSTANCE().addKeyListener(this);
        gameLoop = new Timer(10, e -> updateView()){{setCoalesce(true);}};
        gameLoop.start();
//        new Timer(10, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                updateView();
//            }
//        });


//        new Timer((int) MODEL_UPDATE_TIME, e -> updateModel()){{setCoalesce(true);}}.start();


    }


    private void startMovementTimer() {
//        movementTimer = new Timer(MOVEMENT_DELAY, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                updateMovement();
//            }
//        });
//        movementTimer.start();

    }

    private void stopMovementTimer() {
        if (movementTimer != null) {
            movementTimer.stop();
            movementTimer = null;

        }
    }

    private void updateMovement() {

        double deltaX=0;
        double deltaY=0;


        if (keysPressed.contains(KeyEvent.VK_D)) {
            deltaX += 0.7;
        }
        if (keysPressed.contains(KeyEvent.VK_A)) {
            deltaX -= 0.7;
        }
        if (keysPressed.contains(KeyEvent.VK_W)) {
            deltaY -= 0.7;
        }
        if (keysPressed.contains(KeyEvent.VK_S)) {
            deltaY += 0.7;
        }


        Point2D.Double vector = new Point2D.Double(deltaX, deltaY);
        Point2D point = multiplyVector(EpsilonModel.getINSTANCE().getDirection().getNormalizedDirectionVector(),
                EpsilonModel.getINSTANCE().getDirection().getMagnitude());
        Direction direction = new Direction(addVectors(point, vector));
        direction.adjustEpsilonDirectionMagnitude();
        EpsilonModel.getINSTANCE().setDirection(direction);
    }

    public void updateView(){



        elapsedTime += 0.0153846;
//        System.out.println(elapsedTime);

        updateMovement();

        updateModel();
        // Increment frame count every time updateView is called
        frameCount++;

        // Current time in milliseconds
        long currentTime = System.currentTimeMillis();

        // Check if one second has passed
        if (currentTime - lastUpdateTime >= 1000) {
            // Print the FPS (which is frameCount since it's been a second)
//            System.out.println("FPS: " + frameCount);

            // Reset frame counter and last update time for the next second
            frameCount = 0;
            lastUpdateTime = currentTime;
        }

        for (EpsilonView epsilonView: EpsilonView.epsilonViews){
            epsilonView.setCurrentLocation(
                    calculateViewLocationEpsilon(MainPanel.getINSTANCE(),epsilonView.getId())
            );
            epsilonView.setVertices(
                    calculateViewLocationOfEpsilonVertices(MainPanel.getINSTANCE(), epsilonView.getId())
            );
        }
        for (SquarantineView squarantineView: squarantineViews){
            squarantineView.setVertices(calculateViewLocationSquarantine(MainPanel.getINSTANCE(),squarantineView.getId()));
        }
        for (TrigorathView trigorathView : trigorathViews){
//            trigorathView.setCurrentLocation(calculateViewLocationTrigorath(mainPanel.getINSTANCE(),trigorathView.getId()));
            trigorathView.setVertices(calculateViewLocationTrigorath(MainPanel.getINSTANCE(), trigorathView.getId()));
        }
        for (BulletView bulletView : bulletViews){
            bulletView.setCurrentLocation(calculateViewLocationBullet(MainPanel.getINSTANCE(), bulletView.getId()));
//            System.out.println(calculateViewLocationBullet(mainPanel.getINSTANCE(), bulletView.getId()));
        }
        for (CollectibleView collectibleView : collectibleViews){
            collectibleView.setCurrentLocation(calculateViewLocationCollectible(MainPanel.getINSTANCE(), collectibleView.getId()));
        }


        MainFrame.getINSTANCE().repaint();
    }

    public void updateModel(){

        // Increment frame count every time updateView is called
        updateCount++;
//        mainPanel.getINSTANCE().panelMotion();

        for (SquarantineModel squarantineModel: squarantineModels){
//            System.out.println(squarantineModel.getDirection().getMagnitude());
            if (squarantineModel.isImpactInProgress()){
                squarantineModel.getDirection().accelerateDirection(squarantineModel.impactMaxVelocity);
                if (squarantineModel.getDirection().getMagnitude() > squarantineModel.impactMaxVelocity){
                    squarantineModel.setImpactInProgress(false);
                }
            }
        }
        for (TrigorathModel trigorathModel: trigorathModels){
//            System.out.println(trigorathModel.getDirection().getMagnitude());
            if (trigorathModel.isImpactInProgress()){
                trigorathModel.getDirection().accelerateDirection(trigorathModel.impactMaxVelocity);
//                double distanceByEpsilon = trigorathModel.getAnchor().distance(EpsilonModel.getINSTANCE().getAnchor());
//                if (distanceByEpsilon > TRIGORATH_MAX_VEL_RADIUS ){
//                    if (trigorathModel.getDirection().getMagnitude() > trigorathModel.impactMaxVelocity) {
//                        trigorathModel.setImpactInProgress(false);
//                    }
//                    if (trigorathModel.getDirection().getMagnitude() < 1.5) trigorathModel.setImpactInProgress(false);
//                }
//                else {
                    if (trigorathModel.getDirection().getMagnitude() > trigorathModel.impactMaxVelocity) {

                        trigorathModel.setImpactInProgress(false);
                    }
//                }
            }
        }
        EpsilonModel epsilonModel = EpsilonModel.getINSTANCE();
        if (epsilonModel.isImpactInProgress()){
            epsilonModel.getDirection().accelerateDirection(6);
            if (epsilonModel.getDirection().getMagnitude() > 4){
                epsilonModel.setImpactInProgress(false);
            }
        }

        // Current time in milliseconds
        long currentTime = System.currentTimeMillis();

        // Check if one second has passed
        if (currentTime - lastUpdateTimeUPS >= 1000) {
            // Print the FPS (which is frameCount since it's been a second)
            System.out.println("UPS: " + updateCount);

            // Reset frame counter and last update time for the next second
            updateCount = 0;
            lastUpdateTimeUPS = currentTime;
        }
        for (Movable movable: movables){
            movable.move();
            movable.friction();
        }
        for (TrigorathModel t:trigorathModels){
            t.rotate();
        }
        for (SquarantineModel s:squarantineModels){
            s.rotate();
        }
        for (int i=0;i<collidables.size();i++){
            for (int j=i+1;j<collidables.size();j++){
                collidables.get(i).collides(collidables.get(j));
            }
        }



        if (elapsedTime > empowerEndTime){
            empowerIsOn=false;
            extraBullet =0;
            tripleShot=false;
        }
        if (empowerIsOn && tripleShot && mousePosition!=null && extraBullet<2){
            if (elapsedTime-lastShot>0.05){
                new BulletModel(EpsilonModel.getINSTANCE().getAnchor(), lastBullet.getDirection());
                extraBullet++;
                lastShot=elapsedTime;
            }

        }
        if (extraBullet==2) {
            extraBullet =0;
            tripleShot=false;
        }



    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_R){

            if (gameLoop.isRunning()) {
                //   TODO shop ...
                shopPanel = new ShopPanel();
                MainFrame.getINSTANCE().removeMouseListener(MainPanel.getINSTANCE().getMouseController());

                MainFrame.getINSTANCE().repaint();
                MainFrame.getINSTANCE().addMouseListener(shopPanel);
//                MainFrame.getINSTANCE().addKeyListener(this);
                gameLoop.stop();
            }
            else {
                MainFrame.getINSTANCE().addMouseListener(MainPanel.getINSTANCE().getMouseController());
                MainFrame.getINSTANCE().remove(shopPanel);
                MainFrame.getINSTANCE().removeMouseListener(shopPanel);
                gameLoop.start();
            }

        }

        if (e.getKeyCode() == KeyEvent.VK_T){
            new CollectibleModel(new Point2D.Double(500, 400));
        }

        if (gameLoop.isRunning()) {
            if (e.getKeyCode() == KeyEvent.VK_E){
                Game.ShopAbility ability = Game.shopAbility;
                if (ability == HEAL) {
                    Game.getINSTANCE().heal();
                } else if (ability == EMPOWER){
                    // TODO empower
                    Game.getINSTANCE().empower();
                } else if (ability == BANISH){
                    Game.getINSTANCE().banish();
                }

            }
        }
        if (e.getKeyCode() == KeyEvent.VK_F){
            Game.SkillTreeAbility ability = Game.skillTreeAbility;
            if (ability == ares) {
                System.out.println("aceso");
            } else if (ability == aceso){
                System.out.println("aceso");
            } else if (ability == proteus){
                System.out.println("proteus");
            }

        }



        keysPressed.add(e.getKeyCode());
        if (!movementInProgress) {
            startMovementTimer();
            movementInProgress = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {



        keysPressed.remove(e.getKeyCode());
        if (keysPressed.isEmpty()) {
            stopMovementTimer();
            movementInProgress = false;
//            deltaX = 0; deltaY=0;
        }
    }

    public Timer getGameLoop() {
        return gameLoop;
    }

    public void setGameLoop(Timer gameLoop) {
        this.gameLoop = gameLoop;
    }
}