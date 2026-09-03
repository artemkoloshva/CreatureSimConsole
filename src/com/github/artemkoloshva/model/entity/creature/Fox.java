package com.github.artemkoloshva.model.entity.creature;

import com.github.artemkoloshva.model.entity.Entity;

import java.util.List;

public class Fox extends Predator {
    public Fox(double healthPoints,
               double damage,
               int speed,
               int detectionRange,
               Class<? extends Entity>... targets) {
        super(healthPoints, damage, speed, detectionRange, targets);
    }
}
