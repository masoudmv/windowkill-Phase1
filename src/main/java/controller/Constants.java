package controller;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Constants {
    public static final Dimension FRAME_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    public static final Dimension PANEL_SIZE = new Dimension((int) (FRAME_DIMENSION.getWidth()/2), (int) (FRAME_DIMENSION.getWidth()/2));
//    public static final Dimension PANEL_SIZE = new Dimension((int) 600, (int) 600);

    public static final int FPS = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getRefreshRate();
    public static final double FRAME_UPDATE_TIME=(double) TimeUnit.SECONDS.toMillis(1)/FPS;
    public static final int UPS = 100;
    public static final double MODEL_UPDATE_TIME=(double) TimeUnit.SECONDS.toMillis(1)/UPS;
    public static final double EPSILON_MAX_SPEED = 3.5;
    public static final double SPEED = 1;
    public static final double RADIUS = 15;
    public static final double SQUARANTINE_EDGE = 40;
    public static final double SQUARANTINE_RADIUS = SQUARANTINE_EDGE/Math.sqrt(2);
    public static final double TRIGORATH_RADIUS = 25;
    public static final double TRIGORATH_EDGE = 50;
    public static final double FRICTION = 0.97;
    public static final double IMPACT_COEFFICIENT = 6;
    public static final double BULLET_IMPACT_COEFFICIENT = 5;
    public static final double SMALL_IMPACT_RADIUS = 100;
    public static final double LARGE_IMPACT_RADIUS = 400;
    public static final double PI = Math.PI;
    public static final double BULLET_RADIUS = 5;
    public static final double BULLET_VELOCITY = 10;
    public static final double TRIGORATH_MAX_VEL_RADIUS = 200;
}