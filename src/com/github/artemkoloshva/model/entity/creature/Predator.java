package com.github.artemkoloshva.model.entity.creature;

import com.github.artemkoloshva.model.entity.Attackable;
import com.github.artemkoloshva.model.entity.Attacker;
import com.github.artemkoloshva.model.entity.Entity;

import java.util.List;

public abstract class Predator extends Creature implements Attacker {
    protected final double damage;

    protected Predator(double healthPoints,
                       double damage,
                       int speed,
                       int detectionRange,
                       List<Class<? extends Entity>> targets) {
        super(healthPoints, speed, detectionRange, targets);
        this.damage = damage;
    }

    @Override
    public void attack(Attackable target) {
        target.takeDamage(damage);
    };
}
