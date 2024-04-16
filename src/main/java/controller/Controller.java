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
    public static Point2D[] calculateViewLocationSquarantine(Component component, String id){
        SquarantineModel squarantineModel = findSquarantineModel(id);
        Point corner=new Point(component.getX(),component.getY());
        assert squarantineModel != null;
        Point2D point1 = relativeLocation(squarantineModel.getVertices()[0], corner);
        Point2D point2 = relativeLocation(squarantineModel.getVertices()[1], corner);
        Point2D point3 = relativeLocation(squarantineModel.getVertices()[2], corner);
        Point2D point4 = relativeLocation(squarantineModel.getVertices()[3], corner);
        return new Point2D[]{point1, point2, point3, point4};
    }
    public static Point2D[] calculateViewLocationTrigorath(Component component, String id){
        TrigorathModel trigorathModel = findTrigorathModel(id);
        Point corner=new Point(component.getX(),component.getY());
        assert trigorathModel != null;
//        return relativeLocation(trigorathModel.getAnchor(),corner);
        Point2D point1 = relativeLocation(trigorathModel.getVertices()[0], corner);
        Point2D point2 = relativeLocation(trigorathModel.getVertices()[1], corner);
        Point2D point3 = relativeLocation(trigorathModel.getVertices()[2], corner);
        return new Point2D[]{point1, point2, point3};
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