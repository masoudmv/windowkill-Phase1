package model.movement;

public interface Movable {
    void setDirection(Direction direction);
    Direction getDirection();
    void move(Direction direction);
    void move();
}