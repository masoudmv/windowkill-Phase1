package controller;

import model.charactersModel.EpsilonModel;
import model.charactersModel.SquarantineModel;
import model.charactersModel.TrigorathModel;
import model.collision.CollisionState;
import model.movement.Movable;
import view.charactersView.SquarantineView;
import view.charactersView.TrigorathView;
import view.myFrame;
import view.mainPanel;
import view.charactersView.EpsilonView;

import javax.swing.*;

import static controller.Constants.*;
import static controller.Controller.*;
//import static jdk.jfr.internal.consumer.EventLog.start;
import static model.charactersModel.SquarantineModel.squarantineModels;
import static model.charactersModel.TrigorathModel.trigorathModels;
import static model.collision.Collidable.collidables;
import static model.movement.Movable.movables;
import static view.charactersView.SquarantineView.squarantineViews;
import static view.charactersView.TrigorathView.trigorathViews;

public class Update {
    // Add FPS tracking variables
    private long lastUpdateTime = System.currentTimeMillis();
    private int frameCount = 0;
    private long lastUpdateTimeUPS = System.currentTimeMillis();
    private int updateCount = 0;



    public Update() {



        Thread thread = new Thread(

        );
        new Timer((int) 5, e -> updateView()){{setCoalesce(true);}}.start();
//        new Thread(5 ) 5, e -> updateView().start()

//        new Timer((int) MODEL_UPDATE_TIME, e -> updateModel()){{setCoalesce(true);}}.start();


    }

    public void updateView(){
        updateModel();
        // Increment frame count every time updateView is called
        frameCount++;

        // Current time in milliseconds
        long currentTime = System.currentTimeMillis();

        // Check if one second has passed
        if (currentTime - lastUpdateTime >= 1000) {
            // Print the FPS (which is frameCount since it's been a second)
            System.out.println("FPS: " + frameCount);

            // Reset frame counter and last update time for the next second
            frameCount = 0;
            lastUpdateTime = currentTime;
        }

        for (EpsilonView epsilonView: EpsilonView.epsilonViews){
            epsilonView.setCurrentLocation(calculateViewLocationEpsilon(mainPanel.getINSTANCE(),epsilonView.getId()));
        }
        for (SquarantineView squarantineView: squarantineViews){
            squarantineView.setVertices(calculateViewLocationSquarantine(mainPanel.getINSTANCE(),squarantineView.getId()));
        }
        for (TrigorathView trigorathView : trigorathViews){
//            trigorathView.setCurrentLocation(calculateViewLocationTrigorath(mainPanel.getINSTANCE(),trigorathView.getId()));
            trigorathView.setVertices(calculateViewLocationTrigorath(mainPanel.getINSTANCE(), trigorathView.getId()));
        }
        myFrame.getINSTANCE().repaint();
    }

    public void updateModel(){
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
                if (trigorathModel.getDirection().getMagnitude() > trigorathModel.impactMaxVelocity){
                    trigorathModel.setImpactInProgress(false);
                }
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
//            System.out.println("UPS: " + updateCount);

            // Reset frame counter and last update time for the next second
            updateCount = 0;
            lastUpdateTimeUPS = currentTime;
        }
        for (Movable movable: movables){
            movable.move();
            movable.friction();
        }
//        for (TrigorathModel t:trigorathModels){
//            t.rotate();
//        } for (SquarantineModel s:squarantineModels){
//            s.rotate();
//        }
        for (int i=0;i<collidables.size();i++){
            for (int j=i+1;j<collidables.size();j++){
                collidables.get(i).collides(collidables.get(j));
            }
        }
    }
}