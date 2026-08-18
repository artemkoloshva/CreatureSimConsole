package com.github.artemkoloshva.models;

public abstract class Herbivore extends Creature implements Attackable {
    public Herbivore(int healthPoints, int speed) {
        super(healthPoints, speed);
    }

    public abstract void eat();
}
