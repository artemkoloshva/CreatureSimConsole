package com.github.artemkoloshva.model.entity;

public abstract class Herbivore extends Creature implements Eater{
    public Herbivore(int healthPoints, int speed) {
        super(healthPoints, speed);
    }

    @Override
    public void eatIt(Edible target) {
        target.beEaten();
    };
}
