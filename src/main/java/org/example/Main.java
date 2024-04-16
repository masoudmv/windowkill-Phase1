package org.example;
import controller.KeyController;
import controller.Update;
import model.charactersModel.EpsilonModel;
import model.charactersModel.SquarantineModel;
import model.charactersModel.TrigorathModel;
import view.myFrame;
import view.mainPanel;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.Random;

import static controller.Constants.*;

public class Main {
    static Random rng = new Random();
    public static void main(String[] args) {
//        System.out.println(Math.sin(Math.PI));
        SwingUtilities.invokeLater(() -> {
            myFrame.getINSTANCE();
            mainPanel.getINSTANCE();
            new Update();
            new KeyController();
            new EpsilonModel(new Point2D.Double((double) myFrame.getINSTANCE().getWidth() /2,(double) myFrame.getINSTANCE().getHeight() /2));
            new SquarantineModel(new Point2D.Double(500,500), (double) SQUARANTINE_EDGE /2);
//            new SquarantineModel(new Point2D.Double(500,700), (double) SQUARANTINE_EDGE /2);
//            new TrigorathModel(new Point2D.Double(700,700));
//            new TrigorathModel(new Point2D.Double(700,800));
            new TrigorathModel(new Point2D.Double(300,900));
        });
    }
}