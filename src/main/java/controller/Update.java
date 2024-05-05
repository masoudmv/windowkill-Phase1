package controller;

import model.BulletModel;
import model.CollectibleModel;
import model.charactersModel.EpsilonModel;
import model.charactersModel.SquarantineModel;
import model.charactersModel.TrigorathModel;
import model.collision.Impactable;
import model.movement.Direction;
import model.movement.Movable;
import view.*;
import view.charactersView.SquarantineView;
import view.charactersView.TrigorathView;
import view.charactersView.EpsilonView;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static controller.Constants.RADIUS;
import static controller.Controller.*;
//import static controller.KeyController.deltaX;
//import static controller.KeyController.deltaY;
import static controller.Game.*;
//import static controller.Game.ShopAbility.*;
import static controller.Game.SkillTreeAbility.*;
import static controller.MouseController.*;
import static controller.Sound.playVictorySound;
import static controller.Utils.*;
import static model.CollectibleModel.collectibleModels;
import static model.charactersModel.SquarantineModel.squarantineModels;
import static model.charactersModel.TrigorathModel.trigorathModels;
import static model.collision.Collidable.collidables;
import static model.collision.Impactable.impactables;
import static model.movement.Movable.movables;
import static view.BulletView.bulletViews;
import static view.CollectibleView.collectibleViews;
//import static view.ShopPanel.heal;
import static view.charactersView.SquarantineView.squarantineViews;
import static view.charactersView.TrigorathView.trigorathViews;

public class Update implements KeyListener {
    private long lastTickTime = System.currentTimeMillis();

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
    private boolean acesoInProgress=false;
    public static int epsilonMeleeDamage=10;
    public static int epsilonRangedDamage=5;
    double lastHpRegainTime=-1;
    private double skillAbilityActivateTime=-1;
    private double hpRegainRate = Double.MAX_VALUE;
    public static ShopAbility shopAbility=null;





    public Update() {

//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//        Runnable task = () -> {
//            updateModel();
//        };
//        long initialDelay = 0;
//        long period = 16; // milliseconds
//        executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.MILLISECONDS);


        MainFrame.getINSTANCE().addKeyListener(this);


        int delay = 10; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...
                try {
                    updateView();
                    updateModel();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        };

        gameLoop = new Timer(delay, taskPerformer);
        gameLoop.start();

    }


    private void startMovementTimer() {}

    private void stopMovementTimer() {
        if (movementTimer != null) {
            movementTimer.stop();
            movementTimer = null;

        }
    }

    private void updateMovement() {

        double deltaX=0;
        double deltaY=0;

        Map<String, Integer> keyBindings = KeyBindingMenu.getINSTANCE().getKeyBindings();




        if (keysPressed.contains(keyBindings.get("Move Right"))) {
            deltaX += 0.7;
        }
        if (keysPressed.contains(keyBindings.get("Move Left"))) {
            deltaX -= 0.7;
        }
        if (keysPressed.contains(keyBindings.get("Move Up"))) {
            deltaY -= 0.7;
        }
        if (keysPressed.contains(keyBindings.get("Move Down"))) {
            deltaY += 0.7;
        }


        Point2D.Double vector = new Point2D.Double(deltaX, deltaY);
        Point2D point = multiplyVector(EpsilonModel.getINSTANCE().getDirection().getNormalizedDirectionVector(),
                EpsilonModel.getINSTANCE().getDirection().getMagnitude());
        Direction direction = new Direction(addVectors(point, vector));
        direction.adjustEpsilonDirectionMagnitude();
        EpsilonModel.getINSTANCE().setDirection(direction);
    }

    public void updateView() throws InterruptedException {

        MainFrame.label.setText("<html>Wave: "+ Game.wave + "<br>Elapsed Time: "+ (int) Game.elapsedTime
                + "<br> XP: "+Game.inGameXP +"<br>HP: "+ EpsilonModel.getINSTANCE().getHp());



        long currentTickTime = System.currentTimeMillis();
        long interval = currentTickTime - lastTickTime;
        double wait = 0;
        if (interval < 15.38461538) {
            wait = 15.38461538 - interval;
            interval = (long) 15.38461538;
        }
        try {
            Thread.sleep((long) wait);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        lastTickTime = currentTickTime;





        elapsedTime += (double) interval / 1000;

        if (!EpsilonModel.getINSTANCE().isImpactInProgress()) updateMovement();


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
            trigorathView.setVertices(calculateViewLocationTrigorath(MainPanel.getINSTANCE(), trigorathView.getId()));
        }
        for (BulletView bulletView : bulletViews){
            bulletView.setCurrentLocation(calculateViewLocationBullet(MainPanel.getINSTANCE(), bulletView.getId()));
        }
        for (CollectibleView collectibleView : collectibleViews){
            collectibleView.setCurrentLocation(calculateViewLocationCollectible(MainPanel.getINSTANCE(), collectibleView.getId()));
        }


        MainFrame.getINSTANCE().repaint();
    }

    public void updateModel(){


        if (EpsilonModel.getINSTANCE().getHp() <= 0){
            MainFrame.getINSTANCE().remove(MainPanel.getINSTANCE());
            MainFrame.getINSTANCE().repaint();
            MainFrame.getINSTANCE().remove(MainFrame.label);
            gameLoop.stop();
            new GameOverPanel();

        }

        if (wave>3){

            playVictorySound();
            RADIUS += 1;
        }



        // Increment frame count every time updateView is called
        updateCount++;

        for (SquarantineModel squarantineModel: squarantineModels){
            if (squarantineModel.isImpactInProgress()){
                squarantineModel.getDirection().accelerateDirection(squarantineModel.impactMaxVelocity);
                if (squarantineModel.getDirection().getMagnitude() > squarantineModel.impactMaxVelocity){
                    squarantineModel.setImpactInProgress(false);
                }
            }
        }
        for (TrigorathModel trigorathModel: trigorathModels){

            if (trigorathModel.isImpactInProgress()){
                trigorathModel.getDirection().accelerateDirection(trigorathModel.impactMaxVelocity);
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

        for (CollectibleModel collectibleModel: collectibleModels){

            if (collectibleModel.impactInProgress){
                collectibleModel.getDirection().accelerateDirection(collectibleModel.impactMaxVel);
                if (collectibleModel.getDirection().getMagnitude() > collectibleModel.impactMaxVel){
                    collectibleModel.impactInProgress = false;
                }
            }

            collectibleModel.friction();

            collectibleModel.move();
        }


        // Current time in milliseconds
        long currentTime = System.currentTimeMillis();

        // Check if one second has passed
        if (currentTime - lastUpdateTimeUPS >= 1000) {
            // Print the FPS (which is frameCount since it's been a second)
//            System.out.println("FPS: " + updateCount);

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
        if (empowerIsOn && tripleShot && mousePosition!=null && extraBullet<2 && lastShot > empowerStartTime){
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

        if (acesoInProgress){
            EpsilonModel epsilon = EpsilonModel.getINSTANCE();
            if (lastHpRegainTime==-1) {
                epsilon.sumHpWith(1);
                lastHpRegainTime = elapsedTime;
            } else if (elapsedTime - lastHpRegainTime > hpRegainRate){
                epsilon.sumHpWith(1);
                lastHpRegainTime = elapsedTime;
            }
        }

    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        Map<String, Integer> keyBindings = KeyBindingMenu.getINSTANCE().getKeyBindings();

        if (e.getKeyCode() == keyBindings.get("Open Shop")){

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
        // todo add key change to abilities

//        Map<String, Integer> keyBindings = KeyBindingMenu.getINSTANCE().getKeyBindings();

        if (gameLoop.isRunning()) {
            if (e.getKeyCode() == keyBindings.get("Activate Skill Tree Ability")){
                if (activeAbility == ares) ares();
                if (activeAbility == aceso) aceso();
                if (activeAbility == proteus)  proteus();
            }
        }

        if (gameLoop.isRunning()) {
            if (e.getKeyCode() == keyBindings.get("Activate Shop Ability")){
//                ShopAbility ability = Game.shopAbility;
                if (shopAbility == ShopAbility.heal) {
                    heal();
                } else if (shopAbility == ShopAbility.empower){
                    empower();
                } else if (shopAbility == ShopAbility.banish){
                    banish();
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
        }
    }

    public Timer getGameLoop() {
        return gameLoop;
    }

    public void setGameLoop(Timer gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void ares(){
        if (inGameXP >= 100) {
            if (skillAbilityActivateTime == -1) {
                skillAbilityActivateTime = elapsedTime;
                epsilonMeleeDamage += 2;
                epsilonRangedDamage += 2;
                inGameXP -= 100;
            } else if (elapsedTime - skillAbilityActivateTime > 300) {
                skillAbilityActivateTime = elapsedTime;
                epsilonMeleeDamage += 2;
                epsilonRangedDamage += 2;
                inGameXP -= 100;
            }
        }

    }

    public void aceso(){
        if (inGameXP >= 100){
            if (skillAbilityActivateTime==-1) {
                skillAbilityActivateTime = elapsedTime;
                acesoInProgress = true;
                if (hpRegainRate>1) hpRegainRate=1;
                else hpRegainRate /= 2;
                inGameXP -= 100;
            }
            else if (elapsedTime-skillAbilityActivateTime>300){
                skillAbilityActivateTime = elapsedTime;
                acesoInProgress = true;
                if (hpRegainRate>1) hpRegainRate=1;
                else hpRegainRate /= 2;
                inGameXP -= 100;
            }
        }
    }

    public void proteus(){
        if (inGameXP >= 100){
            if (skillAbilityActivateTime == -1){
                skillAbilityActivateTime = elapsedTime;
                EpsilonModel.getINSTANCE().addVertex();
                inGameXP -=100;
            }
            else if (elapsedTime - skillAbilityActivateTime > 300){
                skillAbilityActivateTime = elapsedTime;
                EpsilonModel.getINSTANCE().addVertex();
                inGameXP -=100;
            }
        }
    }

    public void heal(){
        EpsilonModel.getINSTANCE().sumHpWith(10);
        shopAbility = null;
    }

    public void empower(){
            System.out.println("empower");
            empowerStartTime = elapsedTime;
            empowerEndTime = elapsedTime + 10;
            empowerIsOn = true;
            shopAbility = null;
    }
    public void banish(){
        // TODO add XP handler -100
        if (Game.getINSTANCE().getInGameXp() >= 0){
            for (Impactable impactable : impactables) {
                impactable.banish();
            }
//            Game.getINSTANCE().sumXpWith(-100);
        }
        shopAbility = null;
    }






    public enum ShopAbility{
        heal,
        empower,
        banish
    }
}