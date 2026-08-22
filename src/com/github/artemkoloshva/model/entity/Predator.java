package com.github.artemkoloshva.model.entity;

public abstract class Predator extends Creature implements Attacker {
    protected final double damage;

    protected Predator(double healthPoints, int speed, double damage) {
        super(healthPoints, speed);
        this.damage = damage;
    }

    @Override
    public void attack(Attackable target) {
        target.takeDamage(damage);
    };
}
