package controller;

import model.charactersModel.EpsilonModel;
import model.charactersModel.SquarantineModel;
import view.MainPanel;
import view.MainFrame;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import static view.MainFrame.label;


public class Game {



    private static Game INSTANCE;
    public static int inGameXP =100;
    public static double elapsedTime=0;
    public static int wave= 1;
    private boolean isPaused = false;
    private EpsilonModel epsilon;
    public static SkillTreeAbility skillTreeAbility=null;
    public static boolean empowerIsOn = false;
    public static double empowerEndTime = Double.MAX_VALUE;
    public static double empowerStartTime = Double.MAX_VALUE;
    public static Clip clip;



    public static BufferedImage bufferedImage;
    public static Image bufferedImageResult;
    public static SkillTreeAbility activeAbility;




    public Game (){
        INSTANCE = this;

        SwingUtilities.invokeLater(() -> {
            MainFrame.getINSTANCE().add(label);
            epsilon = new EpsilonModel(new Point2D.Double((double) MainFrame.getINSTANCE().getWidth() /2,(double) MainFrame.getINSTANCE().getHeight() /2));
//            new SquarantineModel(new Point2D.Double(500,500));
//            new SquarantineModel(new Point2D.Double(200,700));
//            new SquarantineModel(new Point2D.Double(500,300));
//            new SquarantineModel(new Point2D.Double(900,900));
//            new SquarantineModel(new Point2D.Double(800,900));
//
//            new TrigorathModel(new Point2D.Double(600,700));
//            new TrigorathModel(new Point2D.Double(700,1000));
//            new TrigorathModel(new Point2D.Double(300,500));
//            new TrigorathModel(new Point2D.Double(700,200));
//            new TrigorathModel(new Point2D.Double(900,900));
//            new TrigorathModel(new Point2D.Double(1000,1000));
            MainPanel.getINSTANCE();
            new Update();

        });
    }

    public static Game getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new Game();
        return INSTANCE;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }
    public void openShop(){}
    public void closeShop(){}
    public int getInGameXp() {
        return inGameXP;
    }

    public void sumInGameXpWith(int xp){
        this.inGameXP += xp;
    }

    public enum SkillTreeAbility{
        ares,
        aceso,
        proteus
    }

    public static void nullifyGameInstance() {
        INSTANCE = null;
    }
}
