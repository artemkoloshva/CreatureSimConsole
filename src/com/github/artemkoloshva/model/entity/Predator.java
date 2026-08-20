package com.github.artemkoloshva.model.entity;

public abstract class Predator extends Creature implements Attacker {
    private final double damage;

    public Predator(int healthPoints, int speed, double damage) {
        super(healthPoints, speed);
        this.damage = damage;
    }

    @Override
    public void attack(Attackable target) {
        target.takeDamage(damage);
    };
}
