package com.github.artemkoloshva.model.entity;

public abstract class Herbivore extends Creature implements Attackable {
    public Herbivore(int healthPoints, int speed) {
        super(healthPoints, speed);
    }

    public abstract void eat();
}
