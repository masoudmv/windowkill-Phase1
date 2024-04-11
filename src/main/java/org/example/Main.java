package org.example;
import controller.KeyController;
import controller.Update;
import model.charactersModel.EpsilonModel;
import model.charactersModel.SquarantineModel;
import view.charactersView.EpsilonView;
import view.charactersView.SquarantineView;
import view.myFrame;
import view.myPanel;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.Random;

import static controller.Constants.*;
import static controller.Controller.calculateViewLocationEpsilon;
import static controller.Controller.calculateViewLocationSquarantine;

public class Main {
    static Random rng = new Random();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            myFrame.getINSTANCE();
            myPanel.getINSTANCE();
            new Update();
            new KeyController();
            new EpsilonModel(new Point2D.Double((double) myFrame.getINSTANCE().getWidth() /2,(double) myFrame.getINSTANCE().getHeight() /2));
            new SquarantineModel(new Point2D.Double(500,500), (double) SQUARANTINE_EDGE /2);
            new SquarantineModel(new Point2D.Double(500,700), (double) SQUARANTINE_EDGE /2);
        });
    }
}