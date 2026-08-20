package com.github.artemkoloshva.model.entity;

public abstract class Creature extends Entity implements Attackable{
    private final int speed;
    private final double maxHealthPoints;
    private double currentHealthPoints;

    public Creature(double maxHealthPoints, int speed) {
        this.maxHealthPoints = maxHealthPoints;
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
