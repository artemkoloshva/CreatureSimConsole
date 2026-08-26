package com.github.artemkoloshva.model.entity.creature;

import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.World;
import com.github.artemkoloshva.model.entity.Attackable;
import com.github.artemkoloshva.model.entity.Entity;
import com.github.artemkoloshva.model.entity.Movable;

import java.util.List;

public abstract class Creature extends Entity implements Attackable, Movable {
    protected final int speed;
    protected final int detectionRange;
    protected final double maxHealthPoints;
    protected final List<Class<? extends Entity>> targets;
    protected double currentHealthPoints;

    protected Creature(double healthPoints,
                       int speed,
                       int detectionRange,
                       List<Class<? extends Entity>> targets) {
        maxHealthPoints = healthPoints;
        currentHealthPoints = maxHealthPoints;
        this.speed = speed;
        this.detectionRange = detectionRange;
        this.targets = targets;
    }

    @Override
    public void makeMove(World world, Position currentPosition) {

    }

    @Override
    public void takeDamage(double damageAmount) {
        currentHealthPoints -= damageAmount;

        if (currentHealthPoints < 0) {
            currentHealthPoints = 0;
        }
    }
}
