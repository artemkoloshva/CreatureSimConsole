package com.github.artemkoloshva.model.entity;

public abstract class Creature extends Entity implements Attackable{
    private int healthPoints;
    private final int speed;

    public Creature(int healthPoints, int speed) {
        this.healthPoints = healthPoints;
        this.speed = speed;
    }

    public abstract void makeMove();

    @Override
    public void takeDamage(int amount) {
        healthPoints -= amount;

        if (healthPoints < 0) {
            healthPoints = 0;
        }
    }
}
