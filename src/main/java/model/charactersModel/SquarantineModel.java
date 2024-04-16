package model.charactersModel;

import model.collision.Collidable;
import model.collision.CollisionState;
import model.movement.Direction;
import model.movement.Movable;

import javax.swing.plaf.IconUIResource;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.UUID;

import static controller.Constants.*;
import static controller.Controller.createSquarantineView;
import static controller.Utils.*;

public class SquarantineModel implements Movable, Collidable {
    private Point2D anchor;
    Point2D currentLocation;
    public double impactMaxVelocity;
    double radius;
    String id;
    Direction direction;
    private boolean impactInProgress = false;
    private final Point2D[] vertices;
    public static ArrayList<SquarantineModel> squarantineModels =new ArrayList<>();

    private double angle;
    private double angularVelocity;
    private double angularAcceleration;


    public SquarantineModel(Point2D anchor, double radius) {
        this.anchor = anchor;
        this.radius = radius;
        Point2D point1 = new Point2D.Double((anchor.getX()- (double) SQUARANTINE_EDGE /2), (anchor.getY()- (double) SQUARANTINE_EDGE /2));
        Point2D point2 = new Point2D.Double((anchor.getX()+ (double) SQUARANTINE_EDGE /2), (anchor.getY()- (double) SQUARANTINE_EDGE /2));
        Point2D point3 = new Point2D.Double((anchor.getX()+ (double) SQUARANTINE_EDGE /2), (anchor.getY()+ (double) SQUARANTINE_EDGE /2));
        Point2D point4 = new Point2D.Double((anchor.getX()- (double) SQUARANTINE_EDGE /2), (anchor.getY()+ (double) SQUARANTINE_EDGE /2));
        vertices = new Point2D[]{point1, point2, point3, point4};
        this.id= UUID.randomUUID().toString();
        this.direction = new Direction(0);
        squarantineModels.add(this);
        collidables.add(this);
        movables.add(this);
        createSquarantineView(id);
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean isImpactInProgress() {
        return impactInProgress;
    }
    @Override
    public void setImpactInProgress(boolean impactInProgress) {
        this.impactInProgress = impactInProgress;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public Point2D getAnchor() {
        return anchor;
    }

    @Override
    public void impact(CollisionState collisionState) {
        Point2D collisionPoint = collisionState.collisionPoint;
        Point2D collisionRelativeVector = relativeLocation(this.getAnchor(), collisionPoint);
        double impactCoefficient = getImpactCoefficient(collisionRelativeVector);
        Point2D impactVector = normalizeVector(relativeLocation(this.getAnchor(), collisionState.collisionPoint));
        impactVector = multiplyVector(impactVector ,impactCoefficient);
        Point2D r2 = addVectors(this.getDirection().getNormalizedDirectionVector(), impactVector);
        if(!isCircular()){
            Direction direction = new Direction(normalizeVector(r2));
            this.setDirection(direction);
        } else {
            Direction direction = new Direction(normalizeVector(r2));
            this.setDirection(direction);
        }
    }

    public void impact(Point2D normalVector, CollisionState collisionState) {
        Point2D collisionPoint = collisionState.collisionPoint;
        Point2D collisionRelativeVector = relativeLocation(this.getAnchor(), collisionPoint);
        double impactCoefficient = getImpactCoefficient(collisionRelativeVector);
        Point2D impactVector = reflect(normalVector);
        impactVector = multiplyVector(impactVector ,impactCoefficient);
        this.setDirection(new Direction(normalizeVector(impactVector)));
        // Angular motion
        Point2D r = relativeLocation(collisionPoint, anchor);
        Point2D f = normalVector;
        double torque = -r.getX()*f.getY()+r.getY()*f.getX();
//        System.out.println(torque);
        double momentOfInertia = calculateSquarantineInertia();
        angularAcceleration = torque/momentOfInertia;
        angularVelocity = 0;
    }

    @Override
    public double getImpactCoefficient(Point2D collisionRelativeVector) {
        double distance = Math.hypot(collisionRelativeVector.getX(), collisionRelativeVector.getY());
        double impactCoefficient;
        if (distance < SMALL_IMPACT_RADIUS) {
            setImpactInProgress(true);
            impactMaxVelocity = 2 * IMPACT_COEFFICIENT / 5;
            impactCoefficient = IMPACT_COEFFICIENT;
        } else if (distance > (LARGE_IMPACT_RADIUS + SMALL_IMPACT_RADIUS) /2) {
            setImpactInProgress(false);
            impactCoefficient = 0;
        } else {
            setImpactInProgress(true);
            double coefficient = 1 - (distance- SMALL_IMPACT_RADIUS)/(LARGE_IMPACT_RADIUS - SMALL_IMPACT_RADIUS);
            impactCoefficient = coefficient * IMPACT_COEFFICIENT;
            impactMaxVelocity = 2 * coefficient * IMPACT_COEFFICIENT / 5;
        }
        return impactCoefficient;
    }

    @Override
    public Point2D[] getVertices() {
        return this.vertices;
    }

    @Override
    public void move(Direction direction) {
        Point2D movement = multiplyVector(direction.getDirectionVector(), direction.getMagnitude());
        this.anchor = addVectors(anchor, movement);
        for (int i = 0; i < 4; i++) {
            vertices[i] = addVectors(vertices[i], movement);
        }
    }

    @Override
    public void move() {
        move(direction);
    }

    @Override
    public boolean isCircular() {
        return false;
    }

    public Point2D getCurrentLocation() {
        return currentLocation;
    }
    @Override
    public void friction(){
        direction.setMagnitude(direction.getMagnitude() * 0.97);
        if (direction.getMagnitude() < 1){
            setDirection(
                    new Direction(relativeLocation(EpsilonModel.getINSTANCE().getAnchor(), getAnchor())));
            getDirection().adjustDirectionMagnitude();
        }
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
    public void rotate(){
//        System.out.println(angularVelocity);
//        angularVelocity *= 0.99;
//        if (Math.abs(angularVelocity) < 0.001) {
//            angularVelocity = 0;
//        }
        if (Math.abs(angularVelocity) < 0.0001 && angularAcceleration ==0){
            angularVelocity = 0;
        }

        // Angular Friction
        if (angularVelocity<0 && angularAcceleration==0){
            angularVelocity += 0.0005;
        } else if (angularVelocity>0 && angularAcceleration==0) {
            angularVelocity -= 0.0005;
        }



        if (Math.abs(angularVelocity) < Math.abs(angularAcceleration*10)) {
            angularVelocity += angularAcceleration;
        }
        else angularAcceleration = 0;
        angle += angularVelocity;
        vertices[0] = new Point2D.Double(
                (anchor.getX()-SQUARANTINE_RADIUS*Math.sin(PI/4+angle)),
                (anchor.getY()-SQUARANTINE_RADIUS*Math.cos(PI/4+angle))
        );
        vertices[1] = new Point2D.Double(
                (anchor.getX()+SQUARANTINE_RADIUS*Math.cos(PI/4+angle)),
                (anchor.getY()-SQUARANTINE_RADIUS*Math.sin(PI/4+angle))
        );
        vertices[2] = new Point2D.Double(
                (anchor.getX()+SQUARANTINE_RADIUS*Math.cos(PI/4-angle)),
                (anchor.getY()+SQUARANTINE_RADIUS*Math.sin(PI/4-angle))
        );
        vertices[3] = new Point2D.Double(
                (anchor.getX()-SQUARANTINE_RADIUS*Math.sin(PI/4-angle)),
                (anchor.getY()+SQUARANTINE_RADIUS*Math.cos(PI/4-angle))
        );

    }
    public Point2D reflect(Point2D normalVector){
        double dotProduct = dotVectors(getDirection().getDirectionVector(), normalVector);
        Point2D reflection = addVectors(
                getDirection().getDirectionVector(),
                multiplyVector(normalVector,-2*dotProduct
                ));
        return normalizeVector(reflection);
    }
    private double calculateSquarantineInertia() {
        double mass = 200;
        double height = SQUARANTINE_EDGE;
        double width = SQUARANTINE_EDGE;
//        System.out.println(mass*(height*height+width*width)/12);
        return 50000;

    }

}