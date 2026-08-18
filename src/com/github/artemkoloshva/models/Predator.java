package com.github.artemkoloshva.models;

public abstract class Predator extends Creature {
    public Predator(int healthPoints, int speed) {
        super(healthPoints, speed);
    }

    public abstract void attack();
}
