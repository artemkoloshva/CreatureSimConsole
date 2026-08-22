package com.github.artemkoloshva.model.entity;

public abstract class Creature extends Entity implements Attackable{
    protected final int speed;
    protected final double maxHealthPoints;
    protected double currentHealthPoints;

    protected Creature(double healthPoints, int speed) {
        maxHealthPoints = healthPoints;
        this.speed = speed;
    }

    public abstract void makeMove();

    @Override
    public void takeDamage(double damageAmount) {
        currentHealthPoints -= damageAmount;

        if (currentHealthPoints < 0) {
            currentHealthPoints = 0;
        }
    }
}
