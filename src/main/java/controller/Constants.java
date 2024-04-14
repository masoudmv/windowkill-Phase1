package controller;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Constants {
    public static final Dimension FRAME_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    public static final Dimension PANEL_SIZE = new Dimension((int) (FRAME_DIMENSION.getWidth()/2), (int) (FRAME_DIMENSION.getHeight()/2));
    public static final int FPS = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getRefreshRate();
    public static final double FRAME_UPDATE_TIME=(double) TimeUnit.SECONDS.toMillis(1)/FPS;
    public static final int UPS = 100;
    public static final double MODEL_UPDATE_TIME=(double) TimeUnit.SECONDS.toMillis(1)/UPS;
    public static final double EPSILON_MAX_SPEED = 4;
    public static final double SPEED = 1;
    public static final double RADIUS = 15;
    public static final int SQUARANTINE_EDGE = 50;
    public static final double TRIGORATH_RADIUS = 30;
    public static final double TRIGORATH_EDGE = 50;
    public static final double FRICTION = 0.97;
    public static final double IMPACT_COEFFICIENT = 6;
    public static final double SMALL_IMPACT_RADIUS = 50;
    public static final double LARGE_IMPACT_RADIUS = 300;
}