package com.github.artemkoloshva.model.entity;

public abstract class Predator extends Creature {
    public Predator(int healthPoints, int speed) {
        super(healthPoints, speed);
    }

    public abstract void attack();
}
