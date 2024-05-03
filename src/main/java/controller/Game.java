package controller;

import model.charactersModel.EpsilonModel;
import model.charactersModel.SquarantineModel;
import model.charactersModel.TrigorathModel;
import model.collision.Collidable;
import model.collision.CollisionState;
import model.collision.Impactable;
import model.movement.Direction;
import model.movement.Movable;
import view.MainPanel;
import view.MainFrame;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static controller.Constants.*;
import static controller.Utils.*;
import static controller.Utils.normalizeVector;
import static model.collision.Impactable.impactables;
import static model.movement.Movable.movables;


public class Game {



    private static Game INSTANCE;
    int wave;
    public static int xp=0;
    public static double elapsedTime=0;
    private boolean isPaused = false;
    private EpsilonModel epsilon;
    public static ShopAbility shopAbility=null;
    public static SkillTreeAbility skillTreeAbility=null;
    public static boolean empowerIsOn = false;
    public static double empowerEndTime = Double.MAX_VALUE;
    public static double empowerStartTime = Double.MAX_VALUE;
    public static Clip clip;
    private int epsilonMeleeDamage=10;
    private int epsilonRangedDamage=5;


    public static BufferedImage bufferedImage;
    public static Image bufferedImageResult;



    public Game (){

        // TODO add image to game
        try {
//            squarantine = ImageIO.read(new File("C:\\Users\\masoo\\Desktop\\Projects\\windowkill_AP\\src\\main\\resources\\squarantine.png"));
            bufferedImage = ImageIO.read(new File("C:\\Users\\masoo\\Desktop\\Projects\\windowkill_AP\\src\\main\\resources\\squarantine.png"));
            bufferedImageResult = bufferedImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT);


        } catch (IOException ex) {
            // handle exception...

    }





        INSTANCE = this;
        SwingUtilities.invokeLater(() -> {

//            SoundHandler.loadSound();
//

            epsilon = new EpsilonModel(new Point2D.Double((double) MainFrame.getINSTANCE().getWidth() /2,(double) MainFrame.getINSTANCE().getHeight() /2));
//            new SquarantineModel(new Point2D.Double(500,500));
//            new SquarantineModel(new Point2D.Double(200,700));
//            new SquarantineModel(new Point2D.Double(500,300));
//            new SquarantineModel(new Point2D.Double(900,900));
//            new SquarantineModel(new Point2D.Double(800,900));

            new TrigorathModel(new Point2D.Double(600,700));
            new TrigorathModel(new Point2D.Double(700,1000));
            new TrigorathModel(new Point2D.Double(300,500));
            new TrigorathModel(new Point2D.Double(700,200));
            new TrigorathModel(new Point2D.Double(900,900));
            new TrigorathModel(new Point2D.Double(1000,1000));
//            Sound.sound = new Sound("C:\\Users\\masoo\\Desktop\\Projects\\windowkill_AP\\src\\main\\resources\\burst2.wav");
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
    public int getXp() {
        return xp;
    }

    public void sumXpWith(int xp){
        this.xp += xp;
    }

    public void heal(){
        if (Game.getINSTANCE().getXp() >= 50){
            EpsilonModel.getINSTANCE().sumHpWith(10);
            Game.getINSTANCE().sumXpWith(-50);
        }
    }
    public void empower(){
        if (Game.getINSTANCE().getXp() >= 0){
            // TODO add XP handler -75
            System.out.println("empower");
            empowerStartTime = elapsedTime;
            empowerEndTime = elapsedTime + 10;
            empowerIsOn = true;
//            Game.getINSTANCE().sumXpWith(-75);
        }
    }
    public void banish(){
        // TODO add XP handler -100
        if (Game.getINSTANCE().getXp() >= 0){
            for (Impactable impactable : impactables) {
                impactable.banish();
            }
//            Game.getINSTANCE().sumXpWith(-100);
        }
    }

    public void ares(){
        if (xp >= 100){
            epsilonMeleeDamage+=2;
            epsilonRangedDamage+=2;
            xp-=100;
        }
    }

    public void proteus(){
        if (xp >= 100){
            EpsilonModel.getINSTANCE().addVertex();
            xp-=100;
        }
    }

    public enum ShopAbility{
        HEAL,
        EMPOWER,
        BANISH
    }

    public enum SkillTreeAbility{
        ares,
        aceso,
        proteus
    }




}
