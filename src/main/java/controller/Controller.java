package controller;

import model.charactersModel.EpsilonModel;
import model.charactersModel.SquarantineModel;
import model.charactersModel.TrigorathModel;
import view.charactersView.EpsilonView;
import view.charactersView.SquarantineView;
import view.charactersView.TrigorathView;

import java.awt.*;
import java.awt.geom.Point2D;

import static controller.Utils.relativeLocation;
import static model.charactersModel.SquarantineModel.squarantineModels;
import static model.charactersModel.TrigorathModel.trigorathModels;

public abstract class Controller {
    public static void createEpsilonView(String id){
        new EpsilonView(id);
    }
    public static void createSquarantineView(String id){
        new SquarantineView(id);
    }
    public static void createTrigorathView(String id){ new TrigorathView(id); }
    public static Point2D calculateViewLocationEpsilon(Component component, String id){
        EpsilonModel epsilonModel = findModel(id);
        Point corner=new Point(component.getX(),component.getY());
        assert epsilonModel != null;
        return relativeLocation(epsilonModel.getAnchor(),corner);
    }
    public static Point2D calculateViewLocationSquarantine(Component component, String id){
        SquarantineModel squarantineModel = findSquarantineModel(id);
        Point corner=new Point(component.getX(),component.getY());
        assert squarantineModel != null;
        return relativeLocation(squarantineModel.getAnchor(),corner);
    }
    public static Point2D calculateViewLocationTrigorath(Component component, String id){
        TrigorathModel trigorathModel = findTrigorathModel(id);
        Point corner=new Point(component.getX(),component.getY());
        assert trigorathModel != null;
        return relativeLocation(trigorathModel.getAnchor(),corner);
    }

    public static EpsilonModel findModel(String id){
        for (EpsilonModel epsilonModel: EpsilonModel.epsilonModels){
            if (epsilonModel.getId().equals(id)) return epsilonModel;
        }
        return null;
    }
    public static SquarantineModel findSquarantineModel(String id){
        for (SquarantineModel squarantineModel: squarantineModels){
            if (squarantineModel.getId().equals(id)) return squarantineModel;
        }
        return null;
    }
    public static TrigorathModel findTrigorathModel(String id){
        for (TrigorathModel trigorathModel: trigorathModels){
            if (trigorathModel.getId().equals(id)) return trigorathModel;
        }
        return null;
    }

}